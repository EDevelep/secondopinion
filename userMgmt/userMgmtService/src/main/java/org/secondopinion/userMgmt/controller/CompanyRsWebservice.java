package org.secondopinion.userMgmt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.secondopinion.userMgmt.dto.Company;
import org.secondopinion.userMgmt.dto.UserRequest;
import org.secondopinion.userMgmt.dto.UserRequest.CompanyFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.userMgmt.service.CompanyRegistrationService;
import org.secondopinion.userMgmt.util.EmailUtil;

import org.secondopinion.utils.MailProperties;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/companyRsWebservice")
public class CompanyRsWebservice {
	private static Logger LOG = LoggerFactory.getLogger(CompanyRsWebservice.class);

	@Autowired
	private CompanyRegistrationService service;

	@Autowired 
	private MailProperties mailProperties;
	
	public CompanyRegistrationService getService() {
		return service;
	}

	public void setService(CompanyRegistrationService service) {
		this.service = service;
	}


	private MailProperties getMailProperties(Company company) { 
		MailProperties properties = mailProperties.clone();
		properties.addToRecipient(company.getEmailId()); 
		return properties; 
	}
	
	private String buildActivationEmailContent(Company company){
		StringBuilder sb = new StringBuilder();
		sb.append("Hi ").append(company.getName()).append(",\n")
		.append("Welcome onboard! We are pleased to inform you that your companies account is fully active and ready to use.\n");
		return sb.toString();
	}

	public MailProperties getMailProperties() { 
		return mailProperties; 
	}

	public void setMailProperties(MailProperties mailProperties) {
		this.mailProperties = mailProperties; 
	}

	//===================APIs

	@ApiOperation(value = "/companyDetailsService/{companyId}", notes = "This method is used to verify the website exists")
	@GetMapping("/companyDetailsService/{companyId}")
	public ResponseEntity<Response<Company>> companyDetails(
			@PathVariable("companyId") Long companyId) {

		try{
			Company company = service.companyDetails( companyId);
			return new ResponseEntity<>(new Response<>(company, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@ApiOperation(value = "/companyname/{companyPK}", notes = "This method is used to get companyname by key")
	@GetMapping("/companyname/{companyPK}")
    public ResponseEntity<Response<String>> getUserNameByKey(
			@PathVariable("companyPK") Long companyPK) {
		
		try{
			String userName = service.getCompanyNameByKey(companyPK);
			
			return new ResponseEntity<>(new Response<>(userName, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "/updateCompanyDetails", notes = "This method is used to verify the website exists")
	@PutMapping(path = "/updateCompanyDetails")
	public ResponseEntity<Response<String>> updateCompanyDetails(@RequestBody Company company ){
		HttpStatus httpStatus = HttpStatus.OK;

		Company origCompany = service.getCompanyDAO().findById(company.getCompanyId());
		// looks like the update screen will not have a few attributes, get them from the orig
		// there might be a better way to do this in hibernate
		company.setNewlyRegistered(origCompany.getNewlyRegistered());
		company.setVerifyBy(origCompany.getVerifyBy());
		company.setVerificationCompleted(origCompany.getVerificationCompleted());
		company.setVerificationId(origCompany.getVerificationId());
		company.setSetupComplete(origCompany.getSetupComplete());

		Response<String> response = new Response<>();
		try{
			service.updateCompanyDetails(new Long(company.getCompanyId()), company);
			response.setMessage("Company details updated successfully..");
		}catch(Exception ex){
			
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus("FAILURE");
			response.setMessage("Error updating the company details" + ex.getMessage());
			LOG.error(ex.getMessage(), ex);
		}

		LOG.debug("Company details updated successfully.." + response);

		return new ResponseEntity<>(response, httpStatus);
	}

	@ApiOperation(value = "/updateAddress", notes = "This method is used to update the company address")
	@PutMapping(path = "/updateAddress")
	public ResponseEntity<Response<String>> updateAddress(@RequestBody UserRequest userRequest){
		HttpStatus httpStatus = HttpStatus.OK;

		Response<String> response = new Response<>();
		try{
			service.updateAddress(userRequest.getCompanyId(), userRequest.getAddress());
			response.setMessage("Company adress updated successfully..");
		}catch(Exception ex){
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus("FAILURE");
			response.setMessage("Error updating the company's address.." + ex.getMessage());
			LOG.error(ex.getMessage(), ex);
		}

		LOG.debug("Company's adress updated successfully.." + response);

		return new ResponseEntity<>(response, httpStatus);
	}

	@ApiOperation(value = "/getLogo/{companyId}", notes = "This method is used to get the logo")
	@RequestMapping(path = "/getLogo/{companyId}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> getLogo(@PathVariable("companyId") Long companyId){
		final File file = service.getLogo(companyId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		InputStream targetStream;
		try {
			targetStream = new FileInputStream(file);

			return ResponseEntity
					.ok()
					.headers(headers)
					.contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(new InputStreamResource(targetStream));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Error retrieving logo", e);
		}

	}

	@ApiOperation(value = "/getTemplate/{companyId}/{companyTemplateId}", notes = "This method is used to get the company template")
	@RequestMapping(path = "/getTemplate/{companyId}/{companyTemplateId}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> getTemplate(@PathVariable("companyId") Long companyId, @PathVariable("companyTemplateId") Long companyTemplateId){
		final File file = service.getTemplate(companyId, companyTemplateId);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		InputStream targetStream;
		try {
			targetStream = new FileInputStream(file);

			return ResponseEntity
					.ok()
					.headers(headers)
					.contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(new InputStreamResource(targetStream));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Error retrieving template", e);
		}
	} 

	@ApiOperation(value = "/deleteTemplate/{companyId}/{companyTemplateId}", notes = "This method is used to delete the company template")
	@GetMapping(path = "/deleteTemplate/{companyId}/{companyTemplateId}")
	public ResponseEntity<Response<Company>> deleteTemplate(@PathVariable("companyId") Long companyId, @PathVariable("companyTemplateId") Long companyTemplateId){
		try{
			Company company = service.deleteTemplate(companyId, companyTemplateId);
			return new ResponseEntity<>(new Response<>(company, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "/activateCompany/{companyId}", notes = "This method is used to activate the company")
	@PutMapping(path = "/activateCompany/{companyId}")
	public ResponseEntity<Response<Company>> activateCompany(
			@PathVariable("companyId") Long companyId) {

		try{
			service.activateCompany( companyId);

			Company company = service.companyDetails(companyId);
			//MailProperties mailProperties = getMailProperties(company);

			//EmailUtil.sendEmail(mailProperties, "Activation Complete", buildActivationEmailContent(company) );
			return new ResponseEntity<>(new Response<>(company, StatusEnum.SUCCESS,null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	

	@ApiOperation(value = "/newlyRegisteredCompanies", notes = "This method is used to get newlyRegisteredCompanies")
	@GetMapping(path = "/newlyRegisteredCompanies")
	public ResponseEntity<Response<List<Company>>> newlyRegisteredCompanies() {

		try{
			List<Company> companies = service.newlyRegisteredcompanies();
			return new ResponseEntity<>(new Response<>(companies, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@ApiOperation(value = "/approve/{companyId}", notes = "This method is used to update the approved flag for the company")
	@PostMapping("/approve/{companyId}")
	public ResponseEntity<Response<String>> approveCompany(@PathVariable("companyId") Integer companyId) {

		try{
			service.approveTheCompany(companyId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "/approvedCompanies", notes = "This method is used to get approvedCompanies")
	@GetMapping("/approvedCompanies")
	public ResponseEntity<Response<List<Company>>> approvedCompanies() {

		try{
			List<Company> companies = service.approvedCompanies();
			return new ResponseEntity<>(new Response<>(companies, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "/all/flags", notes = "This method is used to get all companies")
	@PostMapping("/all/flags")
	public ResponseEntity<Response<Map<CompanyFlag, List<Company>>>> fetchCompaniesByFlagWithPagination(@RequestBody UserRequest userRequest) {
		LOG.info(" request data to fetch all companies by the flag : "+userRequest.toString());
		try{
			Map<CompanyFlag, List<Company>> companies = service.fetchCompaniesByFlagWithPagination(userRequest);
			return new ResponseEntity<>(new Response<>(companies, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
