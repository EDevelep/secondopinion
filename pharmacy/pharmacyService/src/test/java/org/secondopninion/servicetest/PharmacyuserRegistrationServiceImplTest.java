package org.secondopninion.servicetest;

import org.junit.Test;
import org.secondopinion.enums.InvoiceStatusEnum;
import org.secondopinion.enums.PrimaryContactEnum;
import org.secondopinion.pharmacy.dto.Invoice;
import org.secondopinion.pharmacy.dto.Pharmacyuser;
import org.secondopinion.pharmacy.dto.Pharmacyuser.PasswordTypeEnum;
import org.secondopinion.pharmacy.dto.Roles;
import org.secondopinion.pharmacy.dto.Roles.PharmacyRolesEnum;
import org.secondopinion.pharmacy.dto.UpdatePasswordRequest;
import org.secondopinion.pharmacy.service.IPharmacyInvoiceService;
import org.secondopinion.pharmacy.service.IPharmacyuserRegistrationService;
import org.secondopinion.request.Response;
import org.secondopninion.PharmacyApplactionTest;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PharmacyuserRegistrationServiceImplTest extends PharmacyApplactionTest {

	@Autowired
	private IPharmacyuserRegistrationService pharmacyuserRegistrationService;
	@Autowired
	private IPharmacyInvoiceService iPharmacyInvoiceService;

	@Test
	public void testRegisterPharmacyuser() {
		Pharmacyuser pharmacyuser = new Pharmacyuser();
		Roles role = new Roles();
		role.setRoleName(PharmacyRolesEnum.MANAGER.name());
		pharmacyuser.setEmailId("jatin@cure-metric.com");
		pharmacyuser.setFirstName("jatin");
		pharmacyuser.setLastName("jatin");
		pharmacyuser.setPharmacyName("Harsha pharmacy");
		pharmacyuser.setRoleId(3);
		pharmacyuser.setActive('Y');
		pharmacyuser.setCellNumber("9498761234");
		pharmacyuser.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
		pharmacyuser.setPharmacyaddressId(1L);
		pharmacyuser.setPassword("Baloney12@");
		pharmacyuser.setUiHostURL("http://localhost:4200");
		pharmacyuserRegistrationService.addPharmacyuser(pharmacyuser, Boolean.FALSE,
				Arrays.asList(Roles.PharmacyRolesEnum.ADMIN.name()));
		assertNotNull(pharmacyuser);
	}

	@Test
	public void saveInvoiceShipeeingDetail() {
		Invoice invoice = new Invoice();
		invoice.setPaid('Y');
		invoice.setDoctorid(4L);
		invoice.setTrackingId("test");
		invoice.setInvoicestatus(InvoiceStatusEnum.SHIPPED.name());
		invoice.setDoctorname("jatin");
		invoice.setExpectedDate(new Date());
		invoice.setPatientid(50L);
		invoice.setTotal(203.00);
		invoice.setPaidamount(203.6);
		invoice.setPharmacyaddressId(1L);
		invoice.setMobileNumber("9759655764");
		invoice.setShippedBy("Harsha Pharmacy");
		iPharmacyInvoiceService.saveInvoiceShipeeingDetail(invoice);
	}

	@Test
	public void getShipeeingDetail() {

		Long invoiceId = 2L;
		Long patientId = 4L;
		List<Invoice> invoices = iPharmacyInvoiceService.getShipeeingDetail(invoiceId, patientId);
		assertNotNull(invoices);
	}

	@Test
	public void updatePharmacyuser() {
		Pharmacyuser pharmacy = new Pharmacyuser();
		pharmacy.setPharmacyUserId(3L);
		pharmacy.setPharmacyName("Hasha");
		pharmacy.setFirstName("jk");
		pharmacy.setLastName("kuma");
		pharmacy.setCellNumber("8755661558");
		pharmacyuserRegistrationService.updatePrimaryDetails(pharmacy);
		;

	}
	
	@Test
	public void updateInvoiceAfterShipped() {

		iPharmacyInvoiceService.updateInvoiceAfterShipped(2L, InvoiceStatusEnum.SHIPPED);
	}

	@Test
	public void testGetPharmacyuserByVerificationId() {

		Pharmacyuser pharmacyuser = pharmacyuserRegistrationService.getPharmacyuserByVerificationId(
				"$2a$10$QjUqbNyeNNj0577EcFyc3uQqICLQIKngOhH/IQTNV3ubiqlaagyL6", PasswordTypeEnum.CREATE.name());
		assertNotNull(pharmacyuser);
	}

	@Test
	public void testGetResetPasswordByVerificationId() {
		UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
		updatePasswordRequest.setNewPassword("Vardhan@123");
		updatePasswordRequest.setOtp(439150);
		updatePasswordRequest.setVerificationid("$2a$10$QjUqbNyeNNj0577EcFyc3uQqICLQIKngOhH/IQTNV3ubiqlaagyL6");
		pharmacyuserRegistrationService.resetPasswordByVerificationId(updatePasswordRequest);
	}

	@Test
	public void testgetByPharmacyIdAndEmailId() {
		Long pharmacyAddressId = 8L;
		String emailId = "vishnu@qontrack.com";
		Pharmacyuser pharmacyuser = pharmacyuserRegistrationService.getByPharmacyAddressIdAndEmailId(pharmacyAddressId,
				emailId);
		assertNotNull(pharmacyuser);
	}

	@Test
	public void testresetPasswordByPharmacyUserId() {
		UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
		updatePasswordRequest.setNewPassword("Gani@1234");
		updatePasswordRequest.setOtp(439150);
		updatePasswordRequest.setVerificationid("$2a$10$QjUqbNyeNNj0577EcFyc3uQqICLQIKngOhH/IQTNV3ubiqlaagyL6");
		pharmacyuserRegistrationService.resetPasswordByPharmacyUserId(updatePasswordRequest);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testverifyemail() {
		Long pharmacyId = 8L;
		String emailId = "vishnu@qontrack.com";
		pharmacyuserRegistrationService.verifyemail(pharmacyId, emailId);
	}

	@Test
	public void testactivateOrDeactivateUser() {
		Long pharmacyUserId = 8l;
		boolean deactivateUser = true;
		pharmacyuserRegistrationService.activateOrDeactivateUser(pharmacyUserId, deactivateUser);
	}

	@Test
	public void testforgotPassword() {
		String emailId = "ganesh@qontrack.com";
		pharmacyuserRegistrationService.forgotPassword(emailId, "http://localhost:4200");
	}

	@Test
	public void testgetAssociatedUsers() {
		Long pharmacyId = 8l;
		Integer pageNum = 1;
		Integer maxResults = 10;
		Response<List<Pharmacyuser>> pharmacyusers = pharmacyuserRegistrationService.getAssociatedUsers(pharmacyId,
				pageNum, maxResults);
		assertNotNull(pharmacyusers);
	}

	@Test
	public void testgetPrimaryDetails() {
		Long pharmacyuserid = 8l;
		Pharmacyuser pharmacyuser = pharmacyuserRegistrationService.getPrimaryDetails(pharmacyuserid);
		assertNotNull(pharmacyuser);
	}

	@Test
	public void resetPasswordForPharmacyuser() {
		UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
		updatePasswordRequest.setNewPassword("Baloney12@");
		updatePasswordRequest.setVerificationid("$2a$10$Qb7X82nFPCXGGvaERCk1/uEKdeM9IPanXAR.CXsvcZaFqzOwpk3oy");
		updatePasswordRequest.setOtp(325530);
		pharmacyuserRegistrationService.resetPasswordForPharmacyuser(updatePasswordRequest);

	}

	@Test
	public void getPharmacyuserByVerificationId() {
		String verificationId = "$2a$10$Qb7X82nFPCXGGvaERCk1/uEKdeM9IPanXAR.CXsvcZaFqzOwpk3oy";
		String passwordTypeEnum = PasswordTypeEnum.CREATE.name();
		Pharmacyuser pharmacyuser = pharmacyuserRegistrationService.getPharmacyuserByVerificationId(verificationId,
				passwordTypeEnum);
		assertNotNull(pharmacyuser);
	}

	@Test
	public void testupdatePrimaryDetails() {
		Pharmacyuser pharmacyuser = new Pharmacyuser();
		pharmacyuser.setEmailId("vishnu@qontrack.com");
		pharmacyuser.setFirstName("vishnu");
		pharmacyuser.setLastName("vardhan");
		pharmacyuser.setRoleId(2);
		pharmacyuser.setCellNumber("9498761234");
		pharmacyuser.setPrimaryContact(PrimaryContactEnum.CELL_NUMBER.name());
		pharmacyuser.setPharmacyaddressId(8L);
		pharmacyuser.setPassword("Vadhan@123");
		pharmacyuserRegistrationService.updatePrimaryDetails(pharmacyuser);
		assertNotNull(pharmacyuser);
	}
}
