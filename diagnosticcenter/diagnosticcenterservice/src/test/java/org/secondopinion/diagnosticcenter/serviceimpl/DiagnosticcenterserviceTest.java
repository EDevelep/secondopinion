package org.secondopinion.diagnosticcenter.serviceimpl;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.SearchEnum;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterService;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.secondopinion.enums.PrimaryContactEnum;
import org.springframework.beans.factory.annotation.Autowired;

public class DiagnosticcenterserviceTest extends DiagnosticcenterServiceApplicationTest {

	@Autowired
	private IDiagnosticcenterService diagnosticCenterService;

	@Test
	public void registerDiagnosticcenterTest() {

		Diagnosticcenteraddress primaryDataCenterAddress = new Diagnosticcenteraddress();
		primaryDataCenterAddress.setAddress1("hyd");
		primaryDataCenterAddress.setAddress2("hyd2");
		primaryDataCenterAddress.setCity("hyd");
		primaryDataCenterAddress.setState("ts");
		primaryDataCenterAddress.setCountry("india");
		primaryDataCenterAddress.setZip("506349");
		Diagnosticcenteruser primaryDiagnosticcenteruser = new Diagnosticcenteruser();
		primaryDiagnosticcenteruser.setFirstName("vijayaK");
		primaryDiagnosticcenteruser.setEmailId("jitendra@qontrack.com");
		primaryDiagnosticcenteruser.setLastName("kumari");
		primaryDiagnosticcenteruser.setCellNumber("9381930137");
		primaryDiagnosticcenteruser.setHomeNumber("9985747723");
		primaryDiagnosticcenteruser.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
		primaryDiagnosticcenteruser.setGstInr("nothing");
		primaryDiagnosticcenteruser.setOfficeNumber("040123456789");
		primaryDiagnosticcenteruser.setPassword("Jatin@123");
		primaryDiagnosticcenteruser.setLicencenumber("123456789MLB");

		Diagnosticcenter diagnosticcenter = new Diagnosticcenter();
		diagnosticcenter.setName("viajya diagnostic12");
		diagnosticcenter.setNewlyRegistered("Y");
		diagnosticcenter.setCellNumber("9381930137");
		diagnosticcenter.setUiHostURL("locolhost");
		diagnosticcenter.setWebsiteURL("http:///www.vijayadiagnostic.com");

		diagnosticcenter.setPrimaryDataCenterAddress(primaryDataCenterAddress);
		diagnosticcenter.setPrimaryDiagnosticcenteruser(primaryDiagnosticcenteruser);

		diagnosticCenterService.registerDiagnosticcenter(diagnosticcenter);
	}

	@Test
	public void fetchAllDiagnosticcenters() {
		diagnosticCenterService.fetchAllDiagnosticcenters();
	}

	@Test
	public void getAllDiagnosticcenterBySearchCritieriaForMenu() {
		DiagnosticcenterSearchRequest diagnosticcenterSearchRequest = new DiagnosticcenterSearchRequest();
		diagnosticcenterSearchRequest.setDiagnosticcenterId(24L);
		

		List<Diagnosticcenteraddress> diagnosticcenteraddresses = diagnosticCenterService
				.getAllDiagnosticcenterBySearchCritieria(diagnosticcenterSearchRequest);
		assertNotNull(diagnosticcenteraddresses);
	}

	@Test
	public void getAllDiagnosticcenterBySearchCritieriaForPackage() {
		DiagnosticcenterSearchRequest diagnosticcenterSearchRequest = new DiagnosticcenterSearchRequest();
		/// diagnosticcenterSearchRequest.setDiagnosticcenterId(2L);
		//diagnosticcenterSearchRequest.setSearchEnum(SearchEnum.MENU);
		diagnosticcenterSearchRequest.setLatitude(60.00);
		diagnosticcenterSearchRequest.setLongitude(70.00);
	//	diagnosticcenterSearchRequest.setSearchEnumKeyword("MRI Knee ");
		diagnosticCenterService.getAllDiagnosticcenterBySearchCritieria(diagnosticcenterSearchRequest);
	}

	@Test
	public void fetchByDiagnosticcenterId() {
		Long DiagnosticcenterId = 12L;
		diagnosticCenterService.fetchByDiagnosticcenterId(DiagnosticcenterId);
	}

	@Test
	public void fetchDiagnosticcenterByLicenseNumber() {
		String licenseNumber = "";
		diagnosticCenterService.fetchDiagnosticcenterByLicenseNumber(licenseNumber);
	}

	@Test
	public void isEmailExistOrNot() {
		String email = "jatinkumar@gmail.com";
		diagnosticCenterService.isEmailExistOrNot(email);
	}

	@Test
	public void getAddressesOfDiagnosticcenter() {
		Long DiagnosticcenterId = 12L;
		Integer pageNum = 1;
		Integer maxResults = 10;
		diagnosticCenterService.getAddressesOfDiagnosticcenter(DiagnosticcenterId, pageNum, maxResults);
	}

	@Test
	public void emailVerification() {
		String emailid = "jitendra@qontrack.com";
		Integer otp = 563214;
		diagnosticCenterService.emailVerification(emailid, otp);
	}

	@Test
	public void phoneverification() {
		String verificationId = "9381930137";
		Integer otp = 598171;
		diagnosticCenterService.phoneverification(verificationId, otp);
	}

	@Test
	public void requestOTPForEmail() {
		String emailid = "jitendra@qontrack.com";
		diagnosticCenterService.requestOTPForEmail(emailid);
	}

	@Test
	public void requestOTPForPhone() {
		String phonenumber = "9381930137";
		diagnosticCenterService.requestOTPForPhone(phonenumber);
	}

	@Test
	public void saveAddressesOfDiagnosticcenter() {
		Diagnosticcenteraddress address = new Diagnosticcenteraddress();
		address.setActive('Y');
		address.setAddress1("hyser");
		address.setAddress2("ghiju");
		address.setCity("bejin");
		address.setCountry("asiie");
		address.setDiagnosticcenterId(19L);
		address.setZip("345672");
		diagnosticCenterService.saveAddressesOfDiagnosticcenter(address);
	}

	@Test
	public void updateAddressesOfDiagnosticcenter() {
		Diagnosticcenteraddress address = new Diagnosticcenteraddress();
		diagnosticCenterService.updateAddressesOfDiagnosticcenter(address);
	}

	@Test
	public void getAllDiagnosticcenterBySearchCritieria() {
		List<String> serviceName = Arrays.asList("Blood Percentage","MRI Knee","Blood Group");
		List<Diagnosticcenter> diagnosticcenters = diagnosticCenterService
				.getAllDiagnosticcenterBySearchCritieria(serviceName);

		assertNotNull(diagnosticcenters);
	}
	
	@Test
	public void findDiagnosticcenterByLocation() {
		DiagnosticcenterSearchRequest diagnosticcenterSearchRequest=new DiagnosticcenterSearchRequest();
		diagnosticcenterSearchRequest.setLatitude(17.00);
		diagnosticcenterSearchRequest.setLongitude(78.00);
		List<Diagnosticcenter> diagnosticcenters = diagnosticCenterService.findDiagnosticcenterByLocation(diagnosticcenterSearchRequest);

		assertNotNull(diagnosticcenters);
	}
}
