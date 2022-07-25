package org.secondopinion.patient.dto; 


import javax.persistence.Entity;
import javax.persistence.Table; 

import org.secondopinion.patient.domain.BasePatientfcmtoken; 



@Entity 
@Table (name="patientfcmtoken")
public class Patientfcmtoken extends BasePatientfcmtoken{
	
	
	public static Patientfcmtoken biuldForupadtepatientfcmtoken(Patientfcmtoken uipatientfcmtoken,Patientfcmtoken dbpatientfcmtoken) {
	
		dbpatientfcmtoken.setAndroidtoken(uipatientfcmtoken.getAndroidtoken());
		dbpatientfcmtoken.setBrowsertoken(uipatientfcmtoken.getBrowsertoken());
		dbpatientfcmtoken.setIphonetoken(uipatientfcmtoken.getIphonetoken());
		dbpatientfcmtoken.setPatientid(uipatientfcmtoken.getPatientid());
		return dbpatientfcmtoken;
		
	}

}