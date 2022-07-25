package org.secondopinion.userMgmt.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.secondopinion.userMgmt.dto.KeyValueDTO;
import org.secondopinion.userMgmt.dto.Roles;
import org.secondopinion.userMgmt.dto.User;
import org.secondopinion.userMgmt.dto.UserRequest;
import org.secondopinion.userMgmt.dto.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.secondopinion.request.Request;
import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.secondopinion.userMgmt.service.UserService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/userRsWebservice")
public class UserRsWebservice {
	private static Logger LOG = LoggerFactory.getLogger(UserRsWebservice.class);
	
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value = "/usersInCompany/{companyId}", notes = "This method is used to get users In Company")
	@RequestMapping(value="/usersInCompany/{companyId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<List<User>>> usersInCompany(
			@PathVariable("companyId") String companyId) {
		
		try{
			List<User> users = getUserService().getAllUsersInCompany(new Integer(companyId));
			for(User user : users){
				user.setPassword("");
			}
			return new ResponseEntity<>(new Response<>(users, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "/clientnameusername", notes = "This method is used to get username by key")
	@PostMapping("/clientnameusername")
    public ResponseEntity<Response<Map<Long, Map<String, String>>>> getUserNameByKey(
			@RequestBody Request request) {
		
		try{
			Map<Long, Map<String, String>> userName= getUserService().getUserNameAndClientNameByUserKeys(request.getUserIds());
			
			return new ResponseEntity<>(new Response<>(userName, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "/userEmailCheck/{companyId}", notes = "This method is used to verify userEmailCheck")
	@GetMapping("/userEmailCheck/{companyId}")
    public ResponseEntity<Response<Boolean>> userEmailCheck(
			@PathVariable("companyId") Long companyId, @RequestParam("emailId") String emailId) {
		
		try{
			boolean exists = getUserService().userEmailExists(companyId, emailId);
			return new ResponseEntity<>(new Response<>(exists, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "/saveUser", notes = "This method is used to get newlyRegisteredCompanies")
	@PostMapping("/saveUser")
	public ResponseEntity<Response<String>> saveUser(@RequestBody User user){
		
		
		if(user.getRoles() != null && user.getRoles().size()>0){
			UserRole userRole = new UserRole();
			Roles role = user.getRoles().get(0);
			userRole.setRoleId(role.getRoleId());
			userRole.setUserId(user.getUserName());
			user.addRole(userRole);
		}
		
		try{
			userService.saveUser(user, null);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/updatePassword", notes = "This method is used to get newlyRegisteredCompanies")
	@PutMapping( "/updatePassword")
	public ResponseEntity<Response<String>> updatePassword(@RequestBody UserRequest user){
		HttpStatus httpStatus = HttpStatus.OK;
		Response<String> response = new Response<>();
		try{
			userService.updatePassword(user.getUserId(), user.getPassword());
			response.setMessage("updated the user password");
		}catch(Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus("FAILURE");
			response.setMessage("Error saving user.." + ex.getMessage());
			LOG.error(ex.getMessage(), ex);
		}
		
		LOG.debug("Response object created successfully.." + response);
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
	@ApiOperation(value = "/resetUserPassword", notes = "This method is used to get newlyRegisteredCompanies")
	@PutMapping("/resetUserPassword/{userName}")
	public ResponseEntity<Response<String>> resetUserPassword(@PathVariable("userName") String userName){
		
		try{
			String newPassword = userService.resetUserPassword(userName);
			return new ResponseEntity<>(new Response<>(newPassword, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/saveUserRole", notes = "This method is used to get saveUserRole")
	@PostMapping(value="/saveUserRole")
	public ResponseEntity<Response<String>> saveUserRole(@RequestBody @Valid UserRole userRole){
		HttpStatus httpStatus = HttpStatus.OK;
		
		Response<String> response = new Response<>();
		try{
			userService.saveUserRole(userRole);
			response.setMessage("user role saved successfully");
		}catch(Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus("FAILURE");
			response.setMessage("Error saving user.." + ex.getMessage());
			LOG.error(ex.getMessage(), ex);
		}
		
		LOG.debug("Response object created successfully.." + response);
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
	@ApiOperation(value = "/deleteUserRole", notes = "This method is used to get delete user role")
	@DeleteMapping("/deleteUserRole")
	public ResponseEntity<Response<String>> deleteUserRole(@RequestBody @Valid UserRole userRole){
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response<String> response = new Response<>();
		try{
			userService.deleteUserRole(userRole);
			response.setMessage("user Role Deleted successfully");
		}catch(Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus("FAILURE");
			response.setMessage("Error delete user role.." + ex.getMessage());
			LOG.error(ex.getMessage(), ex);
		}
		
		LOG.debug("Response object created successfully.." + response);
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
	@ApiOperation(value = "/allUserRoleNames", notes = "This method is used to get newlyRegisteredCompanies")
	@GetMapping("/allUserRoleNames")
    public ResponseEntity<Response<List<String>>> allRoleNames(){
		try{
			List<String> allRoles = userService.getAllUserRoleNames();
			return new ResponseEntity<>(new Response<>(allRoles, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} 
		catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@ApiOperation(value = "/allUserRoles", notes = "This method is used to get all user roles")
	@GetMapping("/allUserRoles")
    public ResponseEntity<Response<Collection<Roles>>> allRoles(){
		try{
			Collection<Roles> allRoles = userService.getAllUserRoles();
			return new ResponseEntity<>(new Response<>(allRoles, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/allUserRolesForSelection", notes = "This method is used to get allUserRolesForSelection")
	@GetMapping("/allUserRolesForSelection")
    public ResponseEntity<Response<Collection<KeyValueDTO>>> allRolesForSelection(){
		try{
			Collection<KeyValueDTO> dtos = userService.getAllUserRolesForSelection();
			return new ResponseEntity<>(new Response<>(dtos, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/usersByRole/{companyId}/{role}", notes = "This method is used to get users by role")
	@GetMapping("/usersByRole/{companyId}/{role}")
    public ResponseEntity<Response<List<KeyValueDTO>>> usersByRole(@PathVariable("companyId") String companyId, @PathVariable("role") String role){
		try{
			List<KeyValueDTO> allRoles = userService.getUsersByRole(new Integer(companyId), role);
			return new ResponseEntity<>(new Response<>(allRoles, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/saveProfilePic/{userId}", notes = "This method is used to save profile pic")
	@PostMapping(value="/saveProfilePic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Response<String>> saveProfilePic(@RequestParam("userId") String userId,
			@RequestParam("profilePic") MultipartFile profilePic){
		HttpStatus httpStatus = HttpStatus.OK;
		
		Response<String> response = new Response<>();
		try{
			userService.saveProfilePic(userId, profilePic);
			response.setMessage("user profile pic saved successfully");
		}catch(Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus("FAILURE");
			response.setMessage("Error saving profile pic.." + ex.getMessage());
			LOG.error(ex.getMessage(), ex);
		}
		
		LOG.debug("Response object created successfully.." + response);
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
	@ApiOperation(value = "/deleteProfilePic", notes = "This method is used to get delete the profile pic")
	@DeleteMapping("/deleteProfilePic/{userId}")
	public ResponseEntity<Response<String>> deleteProfilePic(@PathVariable("userId") String userId){
		
		HttpStatus httpStatus = HttpStatus.OK;
		Response<String> response = new Response<>();
		try{
			userService.deleteUserProfilePic(userId);
			response.setMessage("user profile pic deleted successfully");
		}catch(Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus("FAILURE");
			response.setMessage("Error delete profile pic.." + ex.getMessage());
			LOG.error(ex.getMessage(), ex);
		}
		
		LOG.debug("Response object created successfully.." + response);
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
	@ApiOperation(value = "/usersById/{companyId}/{userId}", notes = "This method is used to get newlyRegisteredCompanies")
	@GetMapping(path = "/usersById/{companyId}/{userId}")
    public ResponseEntity<Response<User>> usersById(@PathVariable("companyId") String companyId, @PathVariable("userId") String userId){
		try{
			User user = userService.getUserById(userId);
			if(user != null) {
				user.setPassword("");
			}
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@ApiOperation(value = "/userById/{userId}", notes = "This method is used to get user by id")
	@GetMapping("/userById/{userId}")
    public ResponseEntity<Response<User>> userById( @PathVariable("userId") String userId){
		try{
			User user = userService.getUserById(userId);
			if(user != null) {
				user.setPassword("");
			}
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@JsonIgnore
	@GetMapping("/username")
    public ResponseEntity<Response<User>> userByusername( @RequestParam("username") String username){
		
		try{
			User user = userService.getUserById(username);
			return new ResponseEntity<>(new Response<>(user, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "/exists/username", notes = "This method is used to verify user exists by user name")
	@GetMapping("/exists/username/{username}")
    public ResponseEntity<Response<Boolean>> usernamecheck(@PathVariable("username") String username) {
		
		try{
			User user = getUserService().getUserById(username);
			boolean isExist = false;
			if(user != null) {
				isExist = true;
			}
			return new ResponseEntity<>(new Response<>(isExist, StatusEnum.SUCCESS, null), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
