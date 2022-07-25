package org.secondopinion.diagnosticcenter.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.secondopinion.configurations.UtilComponent;
import org.secondopinion.configurations.firebase.PushNotificationRequest;
import org.secondopinion.configurations.firebase.PushNotificationService;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterNotificationalertsDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenteraddressDAO;
import org.secondopinion.diagnosticcenter.dao.DiagnosticcenterfcmtokenDAO;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterfcmtoken;
import org.secondopinion.diagnosticcenter.dto.Notificationalerts;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterNotificationalertsService;
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
public class DiagnosticCenterNotificationalertsServiceImpl implements IDiagnosticCenterNotificationalertsService {

	
	private static final Logger logger = LoggerFactory.getLogger(DiagnosticCenterNotificationalertsServiceImpl.class);

	@Autowired
	private DiagnosticcenterNotificationalertsDAO notificationalertsDAO;
	
	@Autowired
	private PushNotificationService puchNotificationService;

	@Autowired
	private DiagnosticcenterfcmtokenDAO diagnosticcenterfcmtokenDAO;

	@Autowired
	private MailProperties mailProperties;

	@Autowired
	private DiagnosticcenterDAO diagnosticcenterDAO;
	
	@Autowired
	private DiagnosticcenteraddressDAO diagnosticcenteraddressDAO;
	
	@Autowired
	private UtilComponent utilComponent;


	@Override
	@Transactional
	public List<Notificationalerts> getAllNotificationalerts(Long diagnoticCenterAddressId) {
		return notificationalertsDAO.getNotificationalerts(diagnoticCenterAddressId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Notificationalerts> getNotificationalertsForDate(Long diagnoticCenterAddressId,Date date) {
		return notificationalertsDAO.getNotificationalerts(diagnoticCenterAddressId,date);
	}
	
	@Override
	@Transactional(readOnly = true)
	public void saveNotificationalerts(Notificationalerts diagnosticcenternotificationalerts) {
		diagnosticcenternotificationalerts.setExpirydate(DateUtil.addAndGetDate(utilComponent.getTimeZone(), 7));
		diagnosticcenternotificationalerts.setActive('Y');
		notificationalertsDAO.save(diagnosticcenternotificationalerts);
		
	}

	@Override
	@Transactional
	public void updateNotificationalerts(Long notificationId, Notificationalerts diagnosticcenternotificationalerts) {
		Notificationalerts dbnotificationalerts = notificationalertsDAO.getByAlertId(notificationId);
		if (dbnotificationalerts == null) {
			throw new IllegalArgumentException("Notificationalerts is not found for given Id");
		}
		
		
		dbnotificationalerts.setDiagnosticcenternotifiactionalertsid(notificationId);
		dbnotificationalerts.setReason(diagnosticcenternotificationalerts.getReason());
		dbnotificationalerts.setDiagnosticCenterAddressId(diagnosticcenternotificationalerts.getDiagnosticCenterAddressId());
		dbnotificationalerts.setObjecttype(diagnosticcenternotificationalerts.getObjecttype());
		dbnotificationalerts.setViewed(diagnosticcenternotificationalerts.getViewed());
		diagnosticcenternotificationalerts.setExpirydate(DateUtil.addAndGetDate(utilComponent.getTimeZone(), 7));
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
	public void utiliesMethodForNotification(Long diagnosticcenteraddressId, String objectName, Long objectKey, String meesage) {
		// here we are getting the fcm tokent for the apprapate user
		List<Diagnosticcenterfcmtoken> diagnosticcenterfcmtokens=diagnosticcenterfcmtokenDAO.getByDiagnosticcenteraddressId(diagnosticcenteraddressId);
		if( !CollectionUtils.isEmpty(diagnosticcenterfcmtokens)) {
			diagnosticcenterfcmtokens.forEach(diagnosticcenterfcmtoken -> {
				PushNotificationRequest request=new  PushNotificationRequest();
				request.setTitle(objectName);
				request.setMessage(meesage);
				// new we are sending the token to fcmserivice to notifactionalert
				String browserToken = diagnosticcenterfcmtoken.getBrowsertoken();
				String androidToken = diagnosticcenterfcmtoken.getAndroidtoken();
				String iphoneToken = diagnosticcenterfcmtoken.getIphonetoken();
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


		//now i send email we want userdeatil 
		Diagnosticcenteraddress diagnosticcenteraddress=diagnosticcenteraddressDAO.findById(diagnosticcenteraddressId);
		if(Objects.nonNull(diagnosticcenteraddress)) {
			Diagnosticcenter diagnosticcenter=diagnosticcenterDAO.findById(diagnosticcenteraddress.getDiagnosticcenterId());
			if(Objects.nonNull(diagnosticcenter)) {
				emailSend(diagnosticcenter, objectName, meesage);
			}
			
		}
		//message to phone
		//twilio

		// afetr that we want save the data into notifaction table
		Notificationalerts notificationalerts=new Notificationalerts();	
		notificationalerts.setDiagnosticCenterAddressId(diagnosticcenteraddressId);
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

	private MailProperties getMailProperties(Diagnosticcenter diagnosticcenter) {
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(diagnosticcenter.getPrimaryUserEmailId());
		return properties;
	}

	public void emailSend(Diagnosticcenter diagnosticcenter, String subject, String body) {

		EmailUtil.sendMail(getMailProperties(diagnosticcenter),body,subject);
	}

	@Override
	@Transactional(readOnly=true)
	public void sendNotificationalerts(NotificationAlert alertsUtilDTO) {
		utiliesMethodForNotification(alertsUtilDTO.getUserPrimaryKey(),  alertsUtilDTO.getObjectName(), alertsUtilDTO.getObjectKey(), alertsUtilDTO.getMessage());
	}

	
	
}
