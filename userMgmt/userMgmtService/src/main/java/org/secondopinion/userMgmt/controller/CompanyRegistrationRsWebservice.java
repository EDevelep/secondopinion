package org.secondopinion.userMgmt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.secondopinion.userMgmt.dto.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.userMgmt.service.CompanyRegistrationService;
import org.secondopinion.userMgmt.service.UserService;
import org.secondopinion.userMgmt.util.EmailUtil;
import org.secondopinion.utils.MailProperties;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@RequestMapping("/companyRegistrationRsWebservice")
public class CompanyRegistrationRsWebservice {
	private static Logger LOG = LoggerFactory.getLogger(CompanyRegistrationRsWebservice.class);

	@Autowired
	private CompanyRegistrationService companyRegistrationService;
	@Autowired
	private UserService userService;


	public CompanyRegistrationService getService() {
		return companyRegistrationService;
	}

	public void setService(CompanyRegistrationService service) {
		this.companyRegistrationService = service;
	}

	@Autowired(required = true)
	private MailProperties mailProperties;

	//@Value("${endPointUrl}")
	private String endPointUrl;

	@Value("${companyRegistrationPage}")
	private String companyRegistrationPage;
	
	public MailProperties getMailProperties() {
		return mailProperties;
	}

	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties;
	}

	public String getEndPointUrl() {
		return endPointUrl;
	}

	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}

	public String getCompanyRegistrationPage() {
		return companyRegistrationPage;
	}

	public void setCompanyRegistrationPage(String companyRegistrationPage) {
		this.companyRegistrationPage = companyRegistrationPage;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private MailProperties getMailProperties(Company company) {
		MailProperties properties = mailProperties.clone();
		
		properties.addToRecipient(company.getEmailId());
		//		properties.setSmtpHost("smtp.gmail.com");
		return properties;
	}

	private String buildVerificationEmailContent(String verificationId, Company company){

		StringBuilder sb = new StringBuilder();
		sb.append("Hi ").append(company.getName()).append("\n")
		.append("Welcome onboard. Please, click the link below to verify your email\n")
		.append(endPointUrl).append(companyRegistrationPage) //(localhost:8080/HireOffers/Admin/CompanyVerification.html\n")
		.append("\nYour Verification Id:").append(verificationId);

		return sb.toString();
	}

	//==============================APIs
	/**
	 * it is for authentication through swagger
	 * 
	 * @param email
	 * @param password
	 * @throws Exception
	 */
	@ApiOperation(value = "/webSiteExists/{website}", notes = "This method is used to verify the website exists")
	@RequestMapping(path = "/webSiteExists/{website}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<Boolean>> webSiteExists(
			@PathVariable("website") String website) {
		try{
			boolean webSiteExists = companyRegistrationService.webSiteExists( website);
			return new ResponseEntity<>(new Response<>(webSiteExists, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	} 

	@ApiOperation(value = "/emailExists", notes = "This method is used to verify the email exists")
	@GetMapping("/emailExists")
	public ResponseEntity<Response<Boolean>> emailExists(@RequestParam("emailId") String emailId) {

		try{
			boolean emailExists = companyRegistrationService.emailExists( emailId);
			return new ResponseEntity<>(new Response<>(emailExists, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	} 

	@ApiOperation(value = "/companyEmailVerification/{verificationId}", notes = "This method is used to email verification witht he verificationID")
	@GetMapping("/companyEmailVerification/{verificationId}")
	public ResponseEntity<Response<String>> emailVerification(@PathVariable("verificationId") String verificationId) {

		try{
			companyRegistrationService.companyEmailVerificationComplete( verificationId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Verification Successfull. Our team will assess, "
					+ "and activate your registration shortly. You will be receiving an email shortly."), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "/companyRegisterService", notes = "This method is used to create a new company")
	@PostMapping("/companyRegisterService")
	public ResponseEntity<Response<String>> companyRegister(@RequestBody Company company) {
		LOG.debug("Company registration information: " + company.toString());

		try{
			String verificationId = companyRegistrationService.registerCompany(company);
			//String emailContent = buildVerificationEmailContent(verificationId, company);
			//MailProperties properties = getMailProperties(company);

			//EmailUtil.sendEmail(properties, "Registration Verification", emailContent);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, "Email Sent successfully.."), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@ApiOperation(value = "/userCheck/{userId}", notes = "This method is used to check the user exists")
	@RequestMapping("/userCheck/{userId}")
	public ResponseEntity<Response<Boolean>> userCheck(@PathVariable("userId") String userId) {

		try{
			boolean exists = getUserService().userNameExists(userId);
			return new ResponseEntity<>(new Response<>(exists, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "/userEmailCheck", notes = "This method is used to verify the user's email")
	@GetMapping("/userEmailCheck")
	public ResponseEntity<Response<Boolean>> userEmailCheck(
			@RequestParam("emailId") String emailId) {

		try{
			boolean exists = getUserService().userEmailExists(emailId);
			return new ResponseEntity<>(new Response<>(exists, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
