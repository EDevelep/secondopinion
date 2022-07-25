
package org.secondopinion.patient.controller;

import javax.servlet.http.HttpServletRequest;

import org.secondopinion.patient.service.impl.UserRegistrationService;
import org.secondopinion.request.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PatientRedirectContoller {
	private static Logger LOG = LoggerFactory.getLogger(PatientRedirectContoller.class);

	@Autowired
	private UserRegistrationService userRegistrationServiceImpl;

	@Value("${otpLinkForUI}")
	private String otpLinkForUI;

	@Value("${errorPageLink}")
	private String errorPageLink;

	@GetMapping(value = "/verification/username")
	public RedirectView associateUserverification(HttpServletRequest request) {
		String username = request.getParameter("username");
		String uihostURL = null;
		RedirectView redirectView = new RedirectView();

		Response<String> response = new Response<>();
		try {
			userRegistrationServiceImpl.activeAssociateUser(username);
			response.setMessage("User Active successfully.");
			LOG.debug("Response object created successfully..");

		} catch (Exception e) {
			LOG.error(e.getMessage());

			redirectView.setUrl(uihostURL + "/" + errorPageLink);
		}
		return redirectView;
	}

}
