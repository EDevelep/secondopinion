package org.secondopinion.utilities.feedbackreport.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.utilities.feedbackreport.dto.FeedbackLoop;
import org.secondopinion.utilities.jobs.dto.FeedbackRequestDTO;

public interface FeedbackLoopDAO extends IDAO<FeedbackLoop,Long >{
	
	List<FeedbackLoop> findByStatus(String status);
	List<FeedbackLoop> findByStatusAndUser(FeedbackRequestDTO feedbackRequestDTO);
	List<FeedbackLoop> findByUserAndCompany(Long companyId, String userId);
}