package org.secondopinion.pharmacy.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import org.secondopinion.pharmacy.domain.BasePharmacyuser;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class PharmacyuserDAOHibernateImpl extends BasePharmacyuserDAOHibernate{



	private static final String updateLastLoginUserSql = "update Pharmacyuser set "
			+ Pharmacyuser.FIELD_lastLogin +"=:" +Pharmacyuser.FIELD_lastLogin + ",  "
			+ Pharmacyuser.FIELD_retry + "=:" + Pharmacyuser.FIELD_retry + ",  "
			+ Pharmacyuser.FIELD_locked + "=:" +Pharmacyuser.FIELD_locked
			+ " where " + Pharmacyuser.FIELD_pharmacyUserId + "= :" + Pharmacyuser.FIELD_pharmacyUserId;

	private static final String updateRetryUserSql = "update Pharmacyuser set "
			+ Pharmacyuser.FIELD_retry + "=:" + Pharmacyuser.FIELD_retry + ",  "
			+ Pharmacyuser.FIELD_locked + "=:" +Pharmacyuser.FIELD_locked
			+ " where " + Pharmacyuser.FIELD_pharmacyUserId + "= :" + Pharmacyuser.FIELD_pharmacyUserId;

	@Override
	@Transactional(readOnly=true)
	public Pharmacyuser getByPharmacyAddressIdAndEmailId(Long pharmacyAddressId, String emailId, boolean withinthesamepharmacy) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BasePharmacyuser.FIELD_emailId, emailId));
		if(withinthesamepharmacy) {
			criterions.add(Restrictions.eq(BasePharmacyuser.FIELD_pharmacyaddressId, pharmacyAddressId));
		} else {
			criterions.add(Restrictions.eq(BasePharmacyuser.FIELD_active, 'Y'));
		}
		List<Pharmacyuser> pharmacyusers = findByCrieria(criterions);
		if(CollectionUtils.isEmpty(pharmacyusers)) {
			return null;
		}
		return pharmacyusers.get(0);
	}

	@Override
	@Transactional(readOnly=true)
	public Response<List<Pharmacyuser>> getAssociatedUsers(Long pharmacyaddressId, Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BasePharmacyuser.FIELD_pharmacyaddressId, pharmacyaddressId));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}
	@Override
	@Transactional
	public void save(Pharmacyuser pharmacyuser) {
		if(Objects.isNull(pharmacyuser.getPharmacyUserId())) {
			pharmacyuser.setLocked('N');
			pharmacyuser.setRetry(0);
		}
		super.save(pharmacyuser);
	}

	@Override
	@Transactional()
	public void updateRetryCountIfLoginFailed(Long pharmacyUserId, Integer retry) {
		Map<String, Object> params = new HashMap<>();

		params.put(Pharmacyuser.FIELD_pharmacyUserId, pharmacyUserId);
		params.put(Pharmacyuser.FIELD_locked, 'N');

		retry = retry == null ? 1 : retry + 1;
		if(retry >= 4) {
			params.put(Pharmacyuser.FIELD_locked, 'Y');
		}
		params.put(Pharmacyuser.FIELD_retry, retry);

		executeQuery(updateRetryUserSql, params);

	}

	@Override
	@Transactional
	public void updateLastLoginTime(Long pharmacyUserId) {
		Map<String, Object> params = new HashMap<>();

		params.put(Pharmacyuser.FIELD_lastLogin, new Date());
		params.put(Pharmacyuser.FIELD_pharmacyUserId, pharmacyUserId);
		params.put(Pharmacyuser.FIELD_locked, 'N');
		params.put(Pharmacyuser.FIELD_retry, 0);
		executeQuery(updateLastLoginUserSql, params);

	}

	@Override
	@Transactional
	public Pharmacyuser findPharmacyByUserNameAndEmail(String userName) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.or(Restrictions.eq(Pharmacyuser.FIELD_userName, userName),
				Restrictions.eqOrIsNull(Pharmacyuser.FIELD_emailId, userName)));
		
		List<Pharmacyuser> pharmacyusers = findByCrieria(criterions);
		if (pharmacyusers != null && pharmacyusers.size() > 0) {
			return pharmacyusers.get(0);
		}

		return null;
	}
	}

