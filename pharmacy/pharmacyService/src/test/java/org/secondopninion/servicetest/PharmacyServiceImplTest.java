package org.secondopninion.servicetest;


import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.secondopinion.enums.PrimaryContactEnum;

import org.secondopinion.pharmacy.dto.Pharmacy;
import org.secondopinion.pharmacy.dto.PharmacySearchRequest;
import org.secondopinion.pharmacy.dto.Pharmacyaddress;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.pharmacy.dto.ProfileCompltedDTO;
import org.secondopinion.pharmacy.service.IPharmacyService;
import org.secondopinion.request.Response;
import org.secondopninion.PharmacyApplactionTest;
import org.springframework.beans.factory.annotation.Autowired;

public class PharmacyServiceImplTest extends PharmacyApplactionTest {
	
	@Autowired
	private IPharmacyService pharmacyService;
	
	@Test
	public void testRegisterPharmacy() {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setEmailId("jatin@cure-metric.com");
		pharmacy.setLicenseNumber("123456789");
		pharmacy.setName("2mg");
		pharmacy.setPhoneNumber("9381930137");
		pharmacy.setSecondaryPhoneNumber("9985747723");
		pharmacy.setActive('Y');
		pharmacy.setBranchCode("328499");
		pharmacy.setBranchName("HYD1");
		pharmacy.setPharmacyAdminId(1L);
		Pharmacyuser pharmacyuser = new Pharmacyuser();
		pharmacyuser.setEmailId("jatin@cure-metric.com");
		pharmacyuser.setFirstName("harahs");
		pharmacyuser.setLastName("kumar");
		pharmacyuser.setMiddleName("sharma");
		pharmacyuser.setPassword("Jatin@123");
		pharmacyuser.setCellNumber("9381931037");
		pharmacyuser.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
		pharmacy.setPrimaryPharmacyUser(pharmacyuser);
		Pharmacyaddress primaryPharmacyAddress = new Pharmacyaddress();
		primaryPharmacyAddress.setAddress1("asdsdf");
		primaryPharmacyAddress.setAddress2("sdgfd");
		primaryPharmacyAddress.setCity("hyd");
		primaryPharmacyAddress.setCountry("india");
		primaryPharmacyAddress.setState("ts");
		primaryPharmacyAddress.setZip("506349");
		primaryPharmacyAddress.setIsPrimary('Y');
		
		pharmacy.setPrimaryPharmacyAddress(primaryPharmacyAddress);
		pharmacyService.registerPharmacy(pharmacy);
	}
	
	@Test
	public void testFetchAllPharmacies() {
		pharmacyService.fetchAllPharmacies();
	}
	
	@Test
	public void testFetchByPharmacyId() {
		pharmacyService.fetchByPharmacyId(12L);
	}
	
	@Test
	public void testFetchPharmacyByLicenseNumber() {
		pharmacyService.fetchPharmacyByLicenseNumber("Ae&65100");	
	}
	
	@Test
	public void testIsEmailExistOrNot() {
		pharmacyService.isEmailExistOrNot("mam@gmail.com");
	}
	
	@Test
	public void isProfileComplted() {
		ProfileCompltedDTO profileCompltedDTO= pharmacyService.isProfileComplted(2L);
		assertNotNull(profileCompltedDTO);
	}
	
	

	@Test
	public void getAssoctedPharmcy() {
		 List<Pharmacy> pharmacies= pharmacyService.getAssoctedPharmcy(1L);
		assertNotNull(pharmacies);
	}
	//emailVerification
	

	@Test
	public void emailVerification() {
		String emailid="jatin@cure-metric.com";
		Integer emailotp= 288594;
		pharmacyService.emailVerification(emailid, emailotp);
	}
	
	@Test
	public void phoneverification() {
		String phonenumber="8755661558";
		Integer otp= 288594;
		pharmacyService.phoneverification(phonenumber, otp);
	}
	@Test
	public void getAllPharmacyBySearchCritieria() {
		PharmacySearchRequest pharmacySearchRequest=new PharmacySearchRequest();
		pharmacySearchRequest.setAddressId(6L);
		pharmacySearchRequest.setLicenseNumber("1234566888");
		pharmacySearchRequest.setPharmacyName("Harsha Pharmacy");
		Response<List<Pharmacy>> list=pharmacyService.getAllPharmacyBySearchCritieria(pharmacySearchRequest);
		assertNotNull(list);
	}

}
