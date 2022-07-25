package org.secondopinion.patient.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.patient.dto.Questionairre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class QuestionairreDAOHibernateImpl extends BaseQuestionairreDAOHibernate{


	@Override
	@Transactional
	public void save(  Questionairre questionairre) throws DataAccessException {
		if(Objects.isNull(questionairre.getQuestionairreid())) {
			questionairre.setActive('Y');
		}
		super.save(questionairre);
	}
	
	
	@Override
	@Transactional
	public List<Questionairre> findQuestionairreByUserId(Long userid) {
		List<Criterion> criterions = new ArrayList<>();
		criterions.add(Restrictions.eq(Questionairre.FIELD_userId, userid));
		criterions.add(Restrictions.eq(Questionairre.FIELD_active, 'Y'));
		return findByCrieria(criterions);
	}
}