package org.secondopinion.pharmacy.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.configurations.firebase.PushNotificationRequest;
import org.secondopinion.configurations.firebase.PushNotificationService;

import org.secondopinion.pharmacy.dao.NotificationalertsDAO;
import org.secondopinion.pharmacy.dao.PharmacyDAO;
import org.secondopinion.pharmacy.dao.PharmacyaddressDAO;
import org.secondopinion.pharmacy.dao.PharmacyfcmtokenDAO;
import org.secondopinion.pharmacy.dto.Notificationalerts;
import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.pharmacy.dto.Pharmacyfcmtoken;
import org.secondopinion.pharmacy.service.INotificationalertsService;
import org.secondopinion.utils.NotificationAlert;
import org.secondopinion.utils.DateUtil;
import org.secondopinion.utils.EmailUtil;
import org.secondopinion.utils.MailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class NotificationalertsServiceImpl implements INotificationalertsService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationalertsServiceImpl.class);

	@Autowired
	private NotificationalertsDAO notificationalertsDAO;
	
	@Autowired
	private PushNotificationService puchNotificationService;

	@Autowired
	private PharmacyfcmtokenDAO pharmacyfcmtokenDAO;

	@Autowired
	private MailProperties mailProperties;

	@Autowired
	private PharmacyDAO pharmacyDAO;
	
	@Autowired
	private PharmacyaddressDAO  addressDAO;
	
	@Autowired
	private UtilComponent utilComponent;


	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getAllNotificationalerts(Long pharmacyAddressId) {
		return notificationalertsDAO.getNotificationalerts(pharmacyAddressId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationalertsForDate(Long pharmacyAddressId,Date date) {
		return notificationalertsDAO.getNotificationalerts(pharmacyAddressId,date);
	}
	
	@Override
	@Transactional(readOnly = true)
	public void saveNotificationalerts(Notificationalerts notificationalerts) {
		notificationalerts.setExpirydate(DateUtil.addAndGetDate(utilComponent.getTimeZone(), 7));
		notificationalerts.setActive('Y');
		notificationalertsDAO.save(notificationalerts);
		
	}

	@Override
	@Transactional
	public void updateNotificationalerts(Long notificationId, Notificationalerts notificationalerts) {
		Notificationalerts dbnotificationalerts = notificationalertsDAO.getByAlertId(notificationId);
		if (dbnotificationalerts == null) {
			throw new IllegalArgumentException("Notificationalerts is not found for given Id");
		}
		
		
		dbnotificationalerts.setNotificationalertsId(notificationId);
		dbnotificationalerts.setReason(notificationalerts.getReason());
		dbnotificationalerts.setPharmacyaddressId(notificationalerts.getPharmacyaddressId());
		dbnotificationalerts.setObjecttype(notificationalerts.getObjecttype());
		dbnotificationalerts.setViewed(notificationalerts.getViewed());
		dbnotificationalerts.setExpirydate(DateUtil.addAndGetDate(utilComponent.getTimeZone(), 7));
		
		
		notificationalertsDAO.save(dbnotificationalerts);
	}

	@Override
	public void deleteNotificationalerts(Long notificationId) {

		Notificationalerts dbnotificationalerts = notificationalertsDAO.getByAlertId(notificationId);
		if (dbnotificationalerts == null) {
			throw new IllegalArgumentException("Notificationalerts is not found for given Id");
		}
		notificationalertsDAO.deleteNotificationalerts(dbnotificationalerts);
	}

	@Override
	public void sendNotificationalertstofierbase(PushNotificationRequest request) {
		try {
			puchNotificationService.sendPushNotificationToToken(request);
		}
		catch (Exception e) {
			logger.debug("Error in sending Message");
		}
	}

	@Override
	public void utiliesMethodForNotification(Long pharmacyAddressId, String objectName, Long objectKey, String meesage) {
		// here we are getting the fcm tokent for the apprapate user
		List<Pharmacyfcmtoken> pharmacyfcmtokens=pharmacyfcmtokenDAO.getByPharmacyAddressId(pharmacyAddressId);
		if( !CollectionUtils.isEmpty(pharmacyfcmtokens)) { 
			pharmacyfcmtokens.stream().forEach(pharmacyfcmtoken -> {
				PushNotificationRequest request=new  PushNotificationRequest();
				request.setTitle(objectName);
				request.setMessage(meesage);
				// new we are sending the token to fcmserivice to notifactionalert
				String browserToken = pharmacyfcmtoken.getBrowsertoken();
				String androidToken = pharmacyfcmtoken.getAndroidtoken();
				String iphoneToken = pharmacyfcmtoken.getIphonetoken();
				if(!StringUtils.isEmpty(browserToken)) {
					request.setToken(browserToken);
					puchNotificationService.sendPushNotificationToToken(request);
				}
				if(!StringUtils.isEmpty(androidToken)) {
					request.setToken(androidToken);
					puchNotificationService.sendPushNotificationToToken(request);
				}
				if(!StringUtils.isEmpty(iphoneToken)) {
					request.setToken(iphoneToken);
					puchNotificationService.sendPushNotificationToToken(request);
				}
			});
			
			
		}


		Pharmacyaddress address = addressDAO.findById(pharmacyAddressId);
		if(Objects.nonNull(address)) {
			//now i send email we want userdeatil 
			Pharmacy pharmacy=pharmacyDAO.readByPharmacyId(address.getPharmacyId());
			if(Objects.nonNull(pharmacy)) {
				emailSend(pharmacy, objectName, meesage);
			}
		}
		
		Notificationalerts notificationalerts=new Notificationalerts();	
		notificationalerts.setPharmacyaddressId(pharmacyAddressId);
		notificationalerts.setObjecttype(objectName);
		notificationalerts.setObjectkey(objectKey);
		notificationalerts.setReason(meesage);
		notificationalerts.setViewed('N');
		notificationalerts.setActive('Y');
		notificationalerts.setExpirydate(DateUtil.addAndGetDate(utilComponent.getTimeZone(), 7));

		notificationalertsDAO.save(notificationalerts);

	}


	/**
	 * @return the mailProperties
	 */
	public MailProperties getMailProperties() {
		return mailProperties;
	}

	/**
	 * @param mailProperties the mailProperties to set
	 */
	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

	private MailProperties getMailProperties(Pharmacy pharmacy) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(pharmacy.getEmailId());
		return properties;
	}

	public void emailSend(Pharmacy pharmacy, String subject, String body) {

		EmailUtil.sendMail(getMailProperties(pharmacy),body,subject);
	}

	@Override
	public void sendNotificationalertstoThePharmacyFromPatient(NotificationAlert alertsUtilDTO) {
		utiliesMethodForNotification(alertsUtilDTO.getUserPrimaryKey(),  alertsUtilDTO.getObjectName(), alertsUtilDTO.getObjectKey(), alertsUtilDTO.getMessage());
	}

	

	
}
