package org.secondopinion.doctor.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.secondopinion.doctor.service.IDoctorRegistrationService;
import org.secondopinion.request.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class DoctorRedirectContoller {
	private static Logger LOG = LoggerFactory.getLogger(DoctorRedirectContoller.class);
	@Autowired
	private IDoctorRegistrationService doctorRegistrationService;
	
	@Value("${otpLinkForUI}")
	private String otpLinkForUI;
	
	@Value("${errorPageLink}")
	private String errorPageLink;
	
	@RequestMapping(value="/verification/email", method=RequestMethod.GET)
	public RedirectView   emailverification( HttpServletRequest request) {
		String verificationId = request.getParameter("verificationId");
		String uihostURL = request.getParameter("uihostURL");
		RedirectView redirectView = new RedirectView();
		
		Response<String> response = new Response<>();
		try {
		//	doctorRegistrationService.emailVerification(verificationId);
			response.setMessage("Email verified successfully.");
			LOG.debug("Response object created successfully..");
			redirectView.setUrl(String.format(uihostURL+"/"+otpLinkForUI, verificationId) );
		}catch(Exception ex) {
			LOG.error(ex.getMessage());
			
			redirectView.setUrl(uihostURL+"/"+errorPageLink);
		}
		
		return redirectView;
	}
	
}
