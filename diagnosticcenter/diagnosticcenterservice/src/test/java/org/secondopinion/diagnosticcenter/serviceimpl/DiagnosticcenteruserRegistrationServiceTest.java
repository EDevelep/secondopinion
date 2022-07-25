package org.secondopinion.diagnosticcenter.serviceimpl;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenteruserUpdateDTO;
import org.secondopinion.diagnosticcenter.dto.Personaldetail;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteruser.PasswordTypeEnum;
import org.secondopinion.diagnosticcenter.dto.Role;
import org.secondopinion.diagnosticcenter.dto.Role.RoleEnum;
import org.secondopinion.diagnosticcenter.dto.UpdatePasswordRequest;
import org.secondopinion.diagnosticcenter.service.IDiagnosticCenterUserService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterService;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenteruserRegistrationService;
import org.secondopinion.diagnosticcenter.test.DiagnosticcenterServiceApplicationTest;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;

public class DiagnosticcenteruserRegistrationServiceTest extends DiagnosticcenterServiceApplicationTest {

	@Autowired
	private IDiagnosticcenteruserRegistrationService iDiagnosticcenteruserRegistrationService;

	@Autowired
	private IDiagnosticCenterUserService iDiagnosticCenterUserService;
	

	@Autowired
	private IDiagnosticcenterService iDiagnosticcenterService;

	@Test
	public void getAssociatedUsers() {
		Long diagnosticcenterId = 1L;
		Integer pageNum = 1;
		Integer maxResults = 2;
		Response<List<Diagnosticcenteruser>> list = iDiagnosticcenteruserRegistrationService
				.getAssociatedUsers(diagnosticcenterId, pageNum, maxResults);
		assertNotNull(list);
	}

	@Test
	public void registerDiagnosticcenteruser() {
		Diagnosticcenteruser diagnosticcenteruser = new Diagnosticcenteruser();
		Role role = new Role();
		diagnosticcenteruser.setFirstName("java");
		diagnosticcenteruser.setEmailId("java1@qontrack.com");
		diagnosticcenteruser.setLastName("kumari");
		diagnosticcenteruser.setCellNumber("9985747723");
		diagnosticcenteruser.setActive('Y');
		diagnosticcenteruser.setHomeNumber("9985747723");
		diagnosticcenteruser.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
		diagnosticcenteruser.setGstInr("1234567");
		diagnosticcenteruser.setOfficeNumber("040123456789");
		diagnosticcenteruser.setPassword("Jatin@123");
		diagnosticcenteruser.setLicencenumber("123456789");
		diagnosticcenteruser.setDiagnosticCenterAddressId(8L);
		diagnosticcenteruser.setUiHostURL("localhost://4200");
		diagnosticcenteruser.setRoleId(3);
		role.setRoleName(RoleEnum.TECHNICIAN.name());
		diagnosticcenteruser.setRoles(Arrays.asList(role));
		diagnosticcenteruser.setDiagnosticName("MCH");
		iDiagnosticcenteruserRegistrationService.addDiagnosticcenteruser(diagnosticcenteruser, Boolean.FALSE,
				Arrays.asList(Role.RoleEnum.ADMIN.name()));

	}

	@Test
	public void updateUser() {
		Diagnosticcenteruser diagnosticcenteruser = new Diagnosticcenteruser();
		Role role = new Role();
		diagnosticcenteruser.setFirstName("vijaya");
		diagnosticcenteruser.setEmailId("jitendra@qontrack.com");
		diagnosticcenteruser.setLastName("kumari");
		diagnosticcenteruser.setCellNumber("9985747723");
		diagnosticcenteruser.setActive('Y');
		diagnosticcenteruser.setHomeNumber("9985747723");
		diagnosticcenteruser.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
		diagnosticcenteruser.setGstInr("1234567");
		diagnosticcenteruser.setOfficeNumber("040123456789");
		diagnosticcenteruser.setPassword("Jatin@123");
		diagnosticcenteruser.setLicencenumber("123456789");
		diagnosticcenteruser.setDiagnosticCenterAddressId(1L);
		diagnosticcenteruser.setUiHostURL("localhost://4200");
		diagnosticcenteruser.setRoleId(3);
		role.setRoleName(RoleEnum.TECHNICIAN.name());
		diagnosticcenteruser.setRoles(Arrays.asList(role));
		diagnosticcenteruser.setDiagnosticName("jatinkumar");
		iDiagnosticCenterUserService.updateUser(diagnosticcenteruser);

	}
	@Test
	public void updateUserRole() {
		DiagnosticcenteruserUpdateDTO diagnosticCenteruser=new DiagnosticcenteruserUpdateDTO();
		diagnosticCenteruser.setDiagnosticCenterUserId(15L);
		diagnosticCenteruser.setRoleId(2);
		iDiagnosticCenterUserService.updateUserRole(diagnosticCenteruser);
		
	}
	
	@Test
	public void getAllRoles() {
		Collection<Role> role = iDiagnosticcenteruserRegistrationService.getAllRoles();
		assertNotNull(role);
	}

	@Test
	public void getByDiagnosticcenterIdAndEmailId() {
		Long diagnosticcenterId = 20L;
		String emailId = "jatin@gmail.com";
		Diagnosticcenteruser diagnosticcenteruser = iDiagnosticcenteruserRegistrationService
				.getByDiagnosticcenterIdAndEmailId(diagnosticcenterId, emailId);
		assertNotNull(diagnosticcenteruser);
	}
	
	@Test
	public void forgotPassword() {
		String emailId="jitendra@qontrack.com";
         String uiHostURL="http://localhost:4200";
		 iDiagnosticcenteruserRegistrationService.forgotPassword(emailId, uiHostURL);
		
	}
	@Test
	public void resetPasswordByDiagnosticcenter() {
		UpdatePasswordRequest updatePasswordRequest=new UpdatePasswordRequest();
		updatePasswordRequest.setEmailId("ganesh@qontrack.com");
		updatePasswordRequest.setNewPassword("Baloney12@");
		updatePasswordRequest.setOtp(168219);
		 iDiagnosticcenteruserRegistrationService.resetPasswordByDiagnosticcenter(updatePasswordRequest);
	}
	
	@Test
	public void getDiagnosticcenteruserByVerificationId() {
		String verificationId="$2a$10$KwBdzsArpG/5Y/L9nKOoVe/SHo5EOHdV62tNosd2qPlvgvcplEJQm";
		String passwordTypeEnum=PasswordTypeEnum.CREATE.name();
		 iDiagnosticcenteruserRegistrationService.getDiagnosticcenteruserByVerificationId(verificationId, passwordTypeEnum);
	}
	
	@Test
	public void resetPasswordForDiagnosticcenteruser() {
		UpdatePasswordRequest updatePasswordRequest=new UpdatePasswordRequest();
		updatePasswordRequest.setVerificationid("$2a$10$KwBdzsArpG/5Y/L9nKOoVe/SHo5EOHdV62tNosd2qPlvgvcplEJQm");
		updatePasswordRequest.setNewPassword("Baloney12@");
		updatePasswordRequest.setOtp(387486);
		 iDiagnosticcenteruserRegistrationService.resetPasswordForDiagnosticcenteruser(updatePasswordRequest);
	}
	//iDiagnosticCenterUserService
	@Test
	public void getPrimaryDetails() {
		Long diagnosticcenteruserid = 20L;

		Diagnosticcenteruser diagnosticcenteruser = iDiagnosticcenteruserRegistrationService
				.getPrimaryDetails(diagnosticcenteruserid);
		assertNotNull(diagnosticcenteruser);
	}
	
	@Test
	public void findPersonalDetailBydiagnosticcenterId() {
		Long diagnosticcenteruserid = 20L;

		Personaldetail diagnosticcenteruser = iDiagnosticcenterService.findPersonalDetailBydiagnosticcenterId(diagnosticcenteruserid);
		assertNotNull(diagnosticcenteruser);
	}
}
