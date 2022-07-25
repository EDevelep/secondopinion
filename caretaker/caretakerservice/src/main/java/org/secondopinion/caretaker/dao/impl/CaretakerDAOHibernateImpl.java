package org.secondopinion.caretaker.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.secondopinion.caretaker.dao.PersonaldetailDAO;
import org.secondopinion.caretaker.dto.CareTakerSearchRequest;
import org.secondopinion.caretaker.dto.Caretaker;
import org.secondopinion.caretaker.dto.Personaldetail;

import org.secondopinion.utils.StringUtil;
import org.secondopinioncaretaker.domain.BaseAddress;
import org.secondopinioncaretaker.domain.BaseCaretaker;
import org.secondopinioncaretaker.domain.BaseRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CaretakerDAOHibernateImpl extends BaseCaretakerDAOHibernate {

	private static final Logger LOG = LoggerFactory.getLogger(BaseCaretakerDAOHibernate.class);
	private static final String FETCH_EMAIL_ID_QUERY = "select " + BaseCaretaker.FIELD_emailId
			+ " from caretaker where " + BaseCaretaker.FIELD_caretakerId + " =  :" + BaseCaretaker.FIELD_caretakerId;

	/*
	 * private static final String updateLastLoginUserSql = "update User set " +
	 * User.FIELD_lastLogin + "=:" + User.FIELD_lastLogin + ",  " + User.FIELD_retry
	 * + "=:" + User.FIELD_retry + ",  " + User.FIELD_locked + "=:" +
	 * User.FIELD_locked + " where " + User.FIELD_userId + "= :" +
	 * User.FIELD_userId;
	 */	
	private static final String UPDATE_LAST_LOGIN_USER_SQL = "update Caretaker set " + BaseCaretaker.FIELD_lastLogin
			+ "=:" + BaseCaretaker.FIELD_lastLogin + ",  " + BaseCaretaker.FIELD_retry + "=:"
			+ BaseCaretaker.FIELD_retry + ",  " + BaseCaretaker.FIELD_locked + "=:" + BaseCaretaker.FIELD_locked
			+ " where " + BaseCaretaker.FIELD_caretakerId + "= :" + BaseCaretaker.FIELD_caretakerId;
	@Autowired
	private PersonaldetailDAO personaldetailDAO;

	@Override
	public String getCaretakerEmailId(Long userPrimaryKey) {
		Map<String, Object> params = new HashMap<>();
		params.put(BaseCaretaker.FIELD_caretakerId, userPrimaryKey);

		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(BaseCaretaker.FIELD_emailId, StandardBasicTypes.STRING);

		String emailId = null;

		List<Map<String, Object>> objects = findBySqlQueryMapTransformer(FETCH_EMAIL_ID_QUERY, params, scalarMapping);
		for (Iterator<Map<String, Object>> iterator = objects.iterator(); iterator.hasNext();) {
			Map<String, Object> userEmailidMap = iterator.next();
			emailId = (String) userEmailidMap.get(BaseCaretaker.FIELD_emailId);
		}

		return emailId;
	}

	@Override
	@Transactional
	public void updateLastLoginTime(Long caretakerId) {
		Map<String, Object> params = new HashMap<>();
		params.put(BaseCaretaker.FIELD_lastLogin, new Date());
		params.put(BaseCaretaker.FIELD_caretakerId, caretakerId);
		params.put(BaseCaretaker.FIELD_locked, 'N');
		params.put(BaseCaretaker.FIELD_retry, 0);
		executeQuery(UPDATE_LAST_LOGIN_USER_SQL, params);

	}

	@Override
	@Transactional
	public Caretaker findCaretakerBynameAndEmail(String userName) {
		List<Criterion> criterions = new ArrayList<>();

		criterions.add(Restrictions.or(Restrictions.eq(Caretaker.FIELD_emailId, userName),
				Restrictions.eqOrIsNull(Caretaker.FIELD_userName, userName)));

		List<Caretaker> caretakers = findByCrieria(criterions);
		if(caretakers!=null & caretakers .size()>0) {
			return caretakers.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public List<Caretaker> findAllcareTakerBySearchRequest(CareTakerSearchRequest careTakerSearchRequest) {
		StringBuilder selectClause = new StringBuilder("SELECT distinct d." + BaseCaretaker.FIELD_caretakerId);
		StringBuilder fromClause = new StringBuilder(" FROM caretaker d ");
		StringBuilder joinClause = new StringBuilder(
				" INNER JOIN registration r on r." + BaseRegistration.FIELD_caretakerId + " = d."
						+ BaseCaretaker.FIELD_caretakerId + " and (LOWER( d.isProfileCompleted)) ='Y' ");
		StringBuilder whereClause = new StringBuilder(" WHERE (r." + BaseCaretaker.FIELD_active + "='Y')");

		if (!StringUtil.isNullOrEmpty(careTakerSearchRequest.getCareTakerName())) {
			String careTakerName = "%" + careTakerSearchRequest.getCareTakerName().toLowerCase() + "%";
			whereClause.append(" AND (LOWER(d." + BaseCaretaker.FIELD_firstName + ") LIKE '" + careTakerName
					+ "' OR LOWER(d." + BaseCaretaker.FIELD_lastName + ") LIKE '" + careTakerName + "' OR LOWER(d."
					+ BaseCaretaker.FIELD_middleName + ") LIKE '" + careTakerName + "')");
		}

		if (!StringUtil.isNullOrEmpty(careTakerSearchRequest.getEmailId())) {
			String emailId = "%" + careTakerSearchRequest.getEmailId().toLowerCase() + "%";
			whereClause.append(" OR ").append("(LOWER(d." + BaseCaretaker.FIELD_emailId + ") LIKE '" + emailId + "')");

		}

		if (!StringUtil.isNullOrEmpty(careTakerSearchRequest.getGender())) {
			String gender = careTakerSearchRequest.getGender().toLowerCase();
			whereClause.append(" AND ").append("(LOWER(d." + BaseCaretaker.FIELD_gender + ") = '" + gender + "')");
		}
		// Address
		String addressJoin = " INNER JOIN address ad on ad." + BaseAddress.FIELD_caretakerId + " = d."
				+ BaseAddress.FIELD_caretakerId;

		Double latitude = careTakerSearchRequest.getLatitude();
		Double longitude = careTakerSearchRequest.getLongitude();
		if (!Objects.isNull(latitude) && !Objects.isNull(longitude)) {
			joinClause.append(addressJoin);
			whereClause.append(" AND ");
			whereClause.append("(ad." + BaseAddress.FIELD_latitude + " >= '" + latitude + "' AND ad."
					+ BaseAddress.FIELD_latitude + " <= '" + longitude + "')");
		}
		String finalQuery = selectClause.append(fromClause).append(joinClause).append(whereClause).toString();
		LOG.info("care taker search criteria : {} ", finalQuery);
		Map<String, Type> scalarMapping = new HashMap<>();
		scalarMapping.put(BaseAddress.FIELD_caretakerId, StandardBasicTypes.LONG);

		List careTakerIds = querySqlMapTransformer(finalQuery, null, null, null, scalarMapping);
		if (careTakerIds.isEmpty()) {
			return new ArrayList<>();
		}

		List<Caretaker> caretakers = findByCrieria(Restrictions.in(BaseAddress.FIELD_caretakerId, careTakerIds));

		caretakers = caretakers.stream().map(caretaker -> {
		//	caretaker.setPassword("");
			Personaldetail pd = personaldetailDAO.findOneByProperty(BaseAddress.FIELD_caretakerId,
					caretaker.getCaretakerId());

			caretaker.setPersonaldetail(pd);

			return caretaker;

		}).collect(Collectors.toList());

		return caretakers;
	}
}