package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenterfcmtoken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class DiagnosticcenterfcmtokenDAOHibernateImpl extends BaseDiagnosticcenterfcmtokenDAOHibernate{

	
	@Override
	@Transactional(readOnly=true)
	public Diagnosticcenterfcmtoken getByDiagnosticcenterAddressAndUserId(Long diagnosticcenteraddressId, Long diagnosticcenteruserId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(Diagnosticcenterfcmtoken.FIELD_diagnosticCenterAddressId, diagnosticcenteraddressId));
		criteria.add(Restrictions.eq(Diagnosticcenterfcmtoken.FIELD_diagnosticCenterUserId, diagnosticcenteruserId));
		List<Diagnosticcenterfcmtoken> diagnosticcenterfcmtokens = findByCrieria(criteria);
		if(CollectionUtils.isEmpty(diagnosticcenterfcmtokens)) {
			return null;
		}
		return diagnosticcenterfcmtokens.get(0);
	}

	@Override
	@Transactional
	public void save(Diagnosticcenterfcmtoken dbdiagnosticcenterfcmtoken) {
		dbdiagnosticcenterfcmtoken.setActive('Y');
		super.save(dbdiagnosticcenterfcmtoken);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Diagnosticcenterfcmtoken> getByDiagnosticcenteraddressId(Long diagnosticcenteraddressId) {
		List<Criterion> criteria = new ArrayList<>();
		criteria.add(Restrictions.eq(Diagnosticcenterfcmtoken.FIELD_diagnosticCenterAddressId, diagnosticcenteraddressId));
		criteria.add(Restrictions.eq(Diagnosticcenterfcmtoken.FIELD_active, 'Y'));
		return findByCrieria(criteria);
	}
}