package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Questionairre;

public interface QuestionairreDAO extends IDAO<Questionairre ,Long >{

	List<Questionairre> findQuestionairreByUserId(Long userid);
}