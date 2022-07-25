package org.secondopinion.patient.dao;

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.patient.dto.Surgerydetails;

public interface SurgerydetailsDAO extends IDAO<Surgerydetails,Long >{

	List<Surgerydetails> findSurgerydetailsByUserId(Long userid);
}