
package org.secondopnion.patient.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.patient.dto.ForUser;
import org.secondopinion.patient.dto.PatientRequest;
import org.secondopinion.patient.dto.RELATIONSHIP_TYPE;
import org.secondopinion.patient.dto.Relationship;
import org.secondopinion.patient.dto.User;
import org.secondopinion.patient.service.IPatientAssociation;

import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientAssocationServiceImplTest extends PatientApplicationTest {

	@Autowired
	private IPatientAssociation associationPatientService;

	@Test
	public void testSavePatientRequest() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setAlignment("order");
		patientRequest.setDescription("immediate visit");
		patientRequest.setDoctorId(11L);
		patientRequest.setPatientId(7L);
		RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf("DOCTO");
		associationPatientService.savePatientRequest(patientRequest, relationship_TYPE);

		assertNotNull(patientRequest);
	}

	@Test
	public void testUpdatePatientRequestUponDoctorRejections() {
		RELATIONSHIP_TYPE relationship_TYPE = RELATIONSHIP_TYPE.valueOf("DOCTO");
		associationPatientService.updatePatientRequestUponDoctorRejections(11L, 7L, relationship_TYPE);

	}

	@Test
	public void testUpdateRelationship() {

		ForUser foruser = new ForUser();
		foruser.setRelationType(RELATIONSHIP_TYPE.PARENT);
		
		foruser.setForUserId(3L);
		foruser.setUserId(2L);

		associationPatientService.updateAssocateUser(foruser, RELATIONSHIP_TYPE.PARENT);
	}
	
	


	@Test
	public void addManagedUser() {

		User user = new User();
		RELATIONSHIP_TYPE relationshipType = RELATIONSHIP_TYPE.FRIEND;
		ForUser foruser = getForUser(user);
		user.setUiHostURL("zoom.com");
		user.setLastName("test");
		user.setEmailId("test@gmail.com");
		user.setFirstName("test");

		user.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
		user.setCellNumber("9759655764");
		user.setUserName("test");
		foruser.setUserId(2L);
		foruser.setRelationType(relationshipType);
		foruser.setNewregister("N");
		foruser.setForUserId(4L);
		associationPatientService.addManagedUser(user, foruser);
	}

	private ForUser getForUser(User user) {
		ForUser foruser = new ForUser();
		foruser.setUserId(50L);
		foruser.setNewregister("Y");
		foruser.setForUserId(50L);
		return foruser;
	}

}
