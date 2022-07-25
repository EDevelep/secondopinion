package org.secondopinion.caretaker.dao.impl; 


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;



import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.caretaker.dto.Certification;
import org.secondopinioncaretaker.domain.BaseCertification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Repository
public class CertificationDAOHibernateImpl extends BaseCertificationDAOHibernate{
	
	@Override
	@Transactional
	public void save(Certification certification) {
		
		if(Objects.isNull(certification.getCertificationId())) {
			certification.setActive('Y');
		}
		
		super.save(certification);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Collection<Certification> findCaretakerCertificationsByCaretakerId(Long caretakerId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseCertification.FIELD_caretakerId,caretakerId));
		criterions.add(Restrictions.eq(BaseCertification.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}

	@Override
	@Transactional(readOnly = true)
	public Certification findCertificationById(Long certificationId) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(BaseCertification.FIELD_certificationId,certificationId));
		criterions.add(Restrictions.eq(BaseCertification.FIELD_active, 'Y'));
		List<Certification> certifications=findByCrieria(criterions);
		if(CollectionUtils.isEmpty(certifications)) return null;
		return certifications.get(0);
	}
}