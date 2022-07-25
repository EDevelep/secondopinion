package org.secondopinion.doctor.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.doctor.dto.Appointmentnotes;

public interface AppointmentnotesDAO extends IDAO<Appointmentnotes,Long >{
	
	List<Appointmentnotes> previousAppointmentsnotes(List<Long> appointmentIds);

	Appointmentnotes findappointmentnotesDetailsById(Long appointmentNotesId);
	
}