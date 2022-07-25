package org.secondopinion.diagnosticcenter.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.request.Response;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DiagnosticcenteraddressDAOHibernateImpl extends BaseDiagnosticcenteraddressDAOHibernate {

	@Override
	@Transactional(readOnly = true)
	public Response<List<Diagnosticcenteraddress>> readAllAddressesOfDiagnosticcenter(Long diagnosticcenterId,
			Integer pageNum, Integer maxResults) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Diagnosticcenteraddress.FIELD_diagnosticcenterId, diagnosticcenterId));

		return findByCrieria(criterions, null, pageNum, maxResults);
	}

	@Override
	@Transactional
	public void save(Diagnosticcenteraddress diagnosticcenteraddress) {
		if (Objects.isNull(diagnosticcenteraddress.getDiagnosticCenterAddressId())) {
			diagnosticcenteraddress.setActive('Y');
		}
		super.save(diagnosticcenteraddress);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Diagnosticcenteraddress> getByIds(List<Long> diagnosticcenteraddressIds) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.in(Diagnosticcenteraddress.FIELD_diagnosticcenterId, diagnosticcenteraddressIds));
		criterions.add(Restrictions.eq(Diagnosticcenteraddress.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional
	public Diagnosticcenteraddress getDiagnosticCenterBYAddressId(Long diagnosticCenterAddressId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(
				Restrictions.eq(Diagnosticcenteraddress.FIELD_diagnosticCenterAddressId, diagnosticCenterAddressId));
		criterions.add(Restrictions.eq(Diagnosticcenteraddress.FIELD_active, 'Y'));
		List<Diagnosticcenteraddress> digDiagnosticcenteraddresses = findByCrieria(criterions);
		return digDiagnosticcenteraddresses.get(0);
	}

	@Override
	@Transactional
	public List<Diagnosticcenteraddress> findDiagnosticcenterByLocation(
			DiagnosticcenterSearchRequest diagnosticcenterSearchRequest) {
		List<Criterion> criterions = new ArrayList<>();
		if(diagnosticcenterSearchRequest.getDiagnosticcenterId()!=null) {
			criterions.add(Restrictions.eq(Diagnosticcenteraddress.FIELD_diagnosticcenterId, diagnosticcenterSearchRequest.getDiagnosticcenterId()));
		}
		criterions.add(Restrictions.eq(Diagnosticcenteraddress.FIELD_latitude, diagnosticcenterSearchRequest.getLatitude()));
		criterions.add(Restrictions.eq(Diagnosticcenteraddress.FIELD_longitude, diagnosticcenterSearchRequest.getLongitude()));
		criterions.add(Restrictions.eq(Diagnosticcenteraddress.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}