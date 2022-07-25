package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.domain.BaseDiagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class DiagnosticcenteruserDAOHibernateImpl extends BaseDiagnosticcenteruserDAOHibernate {

	private static final String updateLastLoginUserSql = "update Diagnosticcenteruser set "
			+ Diagnosticcenteruser.FIELD_lastLogin + "=:" + Diagnosticcenteruser.FIELD_lastLogin + ",  "
			+ Diagnosticcenteruser.FIELD_retry + "=:" + Diagnosticcenteruser.FIELD_retry + ",  "
			+ Diagnosticcenteruser.FIELD_locked + "=:" + Diagnosticcenteruser.FIELD_locked + " where "
			+ Diagnosticcenteruser.FIELD_diagnosticCenterUserId + "= :"
			+ Diagnosticcenteruser.FIELD_diagnosticCenterUserId;
	private static final String updateRetryUserSql = "update Diagnosticcenteruser set "
			+ Diagnosticcenteruser.FIELD_retry + "=:" + Diagnosticcenteruser.FIELD_retry + ",  "
			+ Diagnosticcenteruser.FIELD_locked + "=:" + Diagnosticcenteruser.FIELD_locked + " where "
			+ Diagnosticcenteruser.FIELD_diagnosticCenterUserId + "= :"
			+ Diagnosticcenteruser.FIELD_diagnosticCenterUserId;

	@Override
	@Transactional
	public List<Diagnosticcenteruser> readById(Long diagnosticcenteruserId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Diagnosticcenteruser.FIELD_diagnosticCenterUserId, diagnosticcenteruserId));
		criterions.add(Restrictions.eq(Diagnosticcenteruser.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public List<Diagnosticcenteruser> readByDiagnosticCenterAddressId(Long diagnosticCenterAddressId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions
				.add(Restrictions.eq(Diagnosticcenteruser.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		criterions.add(Restrictions.eq(Diagnosticcenteruser.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public void updateLastLoginTime(Long diagnosticCenterUserId) {
		Map<String, Object> params = new HashMap<>();
		params.put(Diagnosticcenteruser.FIELD_lastLogin, new Date());
		params.put(Diagnosticcenteruser.FIELD_diagnosticCenterUserId, diagnosticCenterUserId);
		params.put(Diagnosticcenteruser.FIELD_locked, 'N');
		params.put(Diagnosticcenteruser.FIELD_retry, 0);
		executeQuery(updateLastLoginUserSql, params);

	}

	@Override
	@Transactional
	public void updateRetryCountIfLoginFailed(Long diagnosticCenterUserId, Integer retry) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Diagnosticcenteruser.FIELD_diagnosticCenterUserId, diagnosticCenterUserId);
		params.put(Diagnosticcenteruser.FIELD_locked, 'N');

		retry = retry == null ? 1 : retry + 1;
		if (retry >= 4) {
			params.put(Diagnosticcenteruser.FIELD_locked, 'Y');
		}
		params.put(Diagnosticcenteruser.FIELD_retry, retry);
		executeQuery(updateRetryUserSql, params);
	}

	@Override
	@Transactional
	public void save(Diagnosticcenteruser diagnosticcenteruser) {
		if (Objects.isNull(diagnosticcenteruser.getDiagnosticCenterUserId())) {
			diagnosticcenteruser.setLocked('N');
			diagnosticcenteruser.setRetry(0);
		}
		super.save(diagnosticcenteruser);
	}

	@Override
	public Diagnosticcenteruser getByDiagnosticcenteraddressIdAndEmailId(Long diagnosticCenterAddressId, String emailId,
			boolean withinthesamediagsnosticcenter) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseDiagnosticcenteruser.FIELD_emailId, emailId));
		if (withinthesamediagsnosticcenter) {
			criterions.add(Restrictions.eq(BaseDiagnosticcenteruser.FIELD_diagnosticCenterAddressId,
					diagnosticCenterAddressId));
		} else {
			criterions.add(Restrictions.eq(BaseDiagnosticcenteruser.FIELD_active, 'Y'));
		}
		List<Diagnosticcenteruser> pharmacyusers = findByCrieria(criterions);
		if (CollectionUtils.isEmpty(pharmacyusers)) {
			return null;
		}
		return pharmacyusers.get(0);
	}

	@Override
	public Response<List<Diagnosticcenteruser>> getAssociatedUsersOfAddress(Long diagnosticcenteraddressId,
			Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions
				.add(Restrictions.eq(Diagnosticcenteruser.FIELD_diagnosticCenterAddressId, diagnosticcenteraddressId));
		return findByCrieria(criterions, null, pageNum, maxResults);
	}

	@Override
	@Transactional
	public Diagnosticcenteruser findDiagnosticcenterUserByUserId(Long diagnosticCenterUserId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Diagnosticcenteruser.FIELD_diagnosticCenterUserId, diagnosticCenterUserId));
		criterions.add(Restrictions.eq(Diagnosticcenteruser.FIELD_active, 'Y'));
		List<Diagnosticcenteruser> digDiagnosticcenterusers = findByCrieria(criterions);
		return digDiagnosticcenterusers.get(0);
	}

}