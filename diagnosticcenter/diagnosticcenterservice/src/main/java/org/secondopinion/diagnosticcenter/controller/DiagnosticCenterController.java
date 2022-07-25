package org.secondopinion.diagnosticcenter.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenter;
import org.secondopinion.diagnosticcenter.dto.DiagnosticcenterSearchRequest;
import org.secondopinion.diagnosticcenter.dto.Diagnosticcenteraddress;
import org.secondopinion.diagnosticcenter.dto.ProfileCompltedDTO;
import org.secondopinion.diagnosticcenter.exception.DiagnosticCenterServerException;
import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterService;

import org.secondopinion.request.Response;
import org.secondopinion.request.Response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiagnosticCenterController {

  @Autowired
  private IDiagnosticcenterService diagnosticCenterService;

  @PostMapping(value = "/register")
  public ResponseEntity<Response<String>> registerDiagnosticcenter(
      @RequestBody Diagnosticcenter diagnosticcenter) {
    try {
      diagnosticCenterService.registerDiagnosticcenter(diagnosticcenter);
      return new ResponseEntity<>(
          new Response<>(null, StatusEnum.SUCCESS, "Diagnosticcenteruser data saved successfully"),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  // retrieve all Diagnosticcenter
  @GetMapping(value = "/all")
  public ResponseEntity<Response<Collection<Diagnosticcenter>>> fetchAllDiagnosticcenters() {
    try {
      return new ResponseEntity<>(
          new Response<>(diagnosticCenterService.fetchAllDiagnosticcenters(), StatusEnum.SUCCESS,
              "List of Diagnosticcenters fetched successfully."),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  // retrieve Diagnosticcenter info by using diagnoticCenterId
  @GetMapping(value = "/id/{diagnoticCenterId}")
  public ResponseEntity<Response<Diagnosticcenter>> fetchByDiagnosticcenterId(
      @PathVariable("diagnoticCenterId") Long diagnoticCenterId) {
    try {
      return new ResponseEntity<>(
          new Response<>(diagnosticCenterService.fetchByDiagnosticcenterId(diagnoticCenterId),
              StatusEnum.SUCCESS, "Diagnosticcenter data fetched successfully."),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  // retrieve Diagnosticcenter info by using license number
  @GetMapping(value = "/license/{licenseId}")
  public ResponseEntity<Response<Diagnosticcenter>> fetchDiagnosticcenterByLicenseId(
      @PathVariable("licenseId") String licenseId) {
    try {
      return new ResponseEntity<>(
          new Response<>(diagnosticCenterService.fetchDiagnosticcenterByLicenseNumber(licenseId),
              StatusEnum.SUCCESS, "Diagnosticcenter data fetched successfully."),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  // email id exist or not
  @GetMapping(value = "/validate/email")
  public ResponseEntity<Response<Boolean>> isEmailExistOrNot(@RequestParam("email") String email) {
    try {
      Boolean isExist = diagnosticCenterService.isEmailExistOrNot(email);
      String message = (isExist) ? "Email already exist." : " Email not exist.";
      return new ResponseEntity<>(new Response<>(diagnosticCenterService.isEmailExistOrNot(email),
          StatusEnum.SUCCESS, message), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  @GetMapping(value = "/validate/phonenumber")
  public ResponseEntity<Response<Boolean>> isPhoneExistOrNot(
      @RequestParam("phonenumber") String phonenumber) {
    try {
      Boolean isExist = diagnosticCenterService.isPhoneExistOrNot(phonenumber);
      String message = (isExist) ? "Phone Number already exist." : " Phone Number not exist.";
      return new ResponseEntity<>(
          new Response<>(diagnosticCenterService.isPhoneExistOrNot(phonenumber), StatusEnum.SUCCESS,
              message),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  @PostMapping(value = "/address")
  public ResponseEntity<Response<String>> saveDiagnosticcenteraddressesOfDiagnosticcenter(
      @RequestBody Diagnosticcenteraddress address) {
    try {
      diagnosticCenterService.saveAddressesOfDiagnosticcenter(address);
      return new ResponseEntity<>(
          new Response<>(null, StatusEnum.SUCCESS, "address saved successfully"), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  @PutMapping(value = "/address/update")
  public ResponseEntity<Response<String>> updateDiagnosticcenteraddressesOfDiagnosticcenter(
      @RequestBody Diagnosticcenteraddress address) {
    try {
      diagnosticCenterService.updateAddressesOfDiagnosticcenter(address);
      return new ResponseEntity<>(
          new Response<>(null, StatusEnum.SUCCESS, "address saved successfully"), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  @GetMapping(value = "/address/id/{diagnoticCenterAddressId}")
  public ResponseEntity<Response<Diagnosticcenteraddress>> getDiagnosticcenteraddressById(
      @PathVariable Long diagnoticCenterAddressId) {
    try {
      Diagnosticcenteraddress address =
          diagnosticCenterService.getDiagnosticcenteraddressById(diagnoticCenterAddressId);
      return new ResponseEntity<>(
          new Response<>(address, StatusEnum.SUCCESS, "address fetched succefully"), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  @GetMapping(value = "/address/{diagnoticCenterId}/{pageNum}/{maxResults}")
  public ResponseEntity<Response<List<Diagnosticcenteraddress>>> getDiagnosticcenteraddressesOfDiagnosticcenter(
      @PathVariable Long diagnoticCenterId, @PathVariable Integer pageNum,
      @PathVariable Integer maxResults) {
    try {
      Response<List<Diagnosticcenteraddress>> addresses = diagnosticCenterService
          .getAddressesOfDiagnosticcenter(diagnoticCenterId, pageNum, maxResults);
      return new ResponseEntity<>(addresses, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

  @GetMapping("/myreport/{diagnosticCenterAddressid}")
  public ResponseEntity<Response<Map<String, Object>>> getMyReports(
      @PathVariable Long diagnosticCenterAddressid) {
    try {
      Map<String, Object> myReports =
          diagnosticCenterService.getMyReports(diagnosticCenterAddressid);
      return new ResponseEntity<>(
          new Response<>(myReports, StatusEnum.SUCCESS, "My Reports data fetched successfully."),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/verification/request/email/link")
  public ResponseEntity<Response<String>> requestemailverificationlink(
      @RequestParam("emailId") String emailId) {
    try {
      diagnosticCenterService.requestEmailVerificationLink(emailId);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/phoneverification")
  public ResponseEntity<Response<String>> phoneverification(
      @RequestParam("cellNumber") String cellNumber, @RequestParam("otp") Integer otp) {
    try {
      diagnosticCenterService.phoneverification(cellNumber, otp);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/phoneverificationForDiagnosticcenterUser")
  public ResponseEntity<Response<String>> phoneverificationForDiagnosticcenterUser(
      @RequestParam("cellNumber") String cellNumber, @RequestParam("otp") Integer otp) {
    try {
      diagnosticCenterService.phoneverificationForDiagnosticcenterUser(cellNumber, otp);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/emailVerification")
  public ResponseEntity<Response<String>> emailVerification(@RequestParam("emailid") String emailid,
      @RequestParam("emailotp") Integer emailotp) {
    try {
      diagnosticCenterService.emailVerification(emailid, emailotp);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/emailVerificationForDiagnosticcenterUser")
  public ResponseEntity<Response<String>> emailVerificationForDiagnosticcenterUser(
      @RequestParam("emailid") String emailid, @RequestParam("emailotp") Integer emailotp) {
    try {
      diagnosticCenterService.emailVerificationForDiagnosticcenterUser(emailid, emailotp);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/requestOTPForPhone")
  public ResponseEntity<Response<String>> requestOTPForPhone(
      @RequestParam("phonenumber") String phonenumber) {
    try {
      diagnosticCenterService.requestOTPForPhone(phonenumber);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  // resendOTPForEmail
  @GetMapping("/requestOTPForEmail")
  public ResponseEntity<Response<String>> requestOTPForEmail(@RequestParam("email") String email) {
    try {
      diagnosticCenterService.requestOTPForEmail(email);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/requestOTPForEmailDiagnosticcenterUser")
  public ResponseEntity<Response<String>> requestOTPForEmailDiagnosticcenterUser(
      @RequestParam("email") String email) {
    try {
      diagnosticCenterService.requestOTPForEmailDiagnosticcenterUser(email);
      return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @GetMapping("/resendOTPForEmail")
  public ResponseEntity<Response<String>> resendOTPForEmail(@RequestParam("email") String email) {
    try {
      diagnosticCenterService.resendOTPForEmail(email);
      return new ResponseEntity<>(
          new Response<>("OTP is Send To Mail", StatusEnum.SUCCESS, "OTP is Send To Mail"),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }



  @GetMapping("/otpverification")
  public ResponseEntity<Response<String>> otpverification(@RequestParam("otp") Integer otp,
      @RequestParam("emailId") String emailId) {
    try {
      String value = diagnosticCenterService.otpverification(otp, emailId);
      return new ResponseEntity<>(new Response<>(value, StatusEnum.SUCCESS, value), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @PostMapping("/all/search/critieria")
  public ResponseEntity<Response<Collection<Diagnosticcenteraddress>>> getAllDiagnosticcenteraddresssBySearchCritieria(
      @RequestBody DiagnosticcenterSearchRequest diagnosticcenterSearchRequest) {
    try {
      Collection<Diagnosticcenteraddress> diagnosticcenteraddresses = diagnosticCenterService
          .getAllDiagnosticcenterBySearchCritieria(diagnosticcenterSearchRequest);
      return new ResponseEntity<>(
          new Response<>(diagnosticcenteraddresses, StatusEnum.SUCCESS, null), HttpStatus.OK);

    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @PostMapping("/searchByserviceName")
  public ResponseEntity<Response<Collection<Diagnosticcenter>>> getAllDiagnosticcenterSearchByserviceName(
      @RequestParam List<String> serviceName) {
    try {
      List<Diagnosticcenter> diagnosticcenters =
          diagnosticCenterService.getAllDiagnosticcenterBySearchCritieria(serviceName);
      return new ResponseEntity<>(new Response<>(diagnosticcenters, StatusEnum.SUCCESS, null),
          HttpStatus.OK);

    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @PostMapping("/isProfileCompleted/{diagnosticcenterId}")
  public ResponseEntity<Response<ProfileCompltedDTO>> isProfileCompleted(
      @PathVariable Long diagnosticcenterId) {
    try {
      ProfileCompltedDTO bolen = diagnosticCenterService.isProfileCompleted(diagnosticcenterId);
      return new ResponseEntity<>(new Response<>(bolen, StatusEnum.SUCCESS, null), HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }

  }

  @PostMapping(value = "/getAssoctedDiagnosticCenter")
  public ResponseEntity<Response<List<Diagnosticcenter>>> getAssoctedDiagnosticCenter(
      @RequestParam Long diagnosticCenteradminId) {
    try {
    	List<Diagnosticcenter> diagnosticcenters = diagnosticCenterService.getAssoctedDiagnosticCenter(diagnosticCenteradminId);
      return new ResponseEntity<>(
          new Response<>(diagnosticcenters, StatusEnum.SUCCESS, "Assocted Diagnosticcenter Get successfully"),
          HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
          HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      throw new DiagnosticCenterServerException(e.getMessage(), e);
    }
  }

}
