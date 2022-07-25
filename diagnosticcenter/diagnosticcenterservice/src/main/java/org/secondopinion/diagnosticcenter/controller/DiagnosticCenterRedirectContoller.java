/*
 * package org.secondopinion.diagnosticcenter.controller;
 * 
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.secondopinion.diagnosticcenter.service.IDiagnosticcenterService;
 * import org.secondopinion.request.Response; import org.slf4j.Logger; import
 * org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.servlet.view.RedirectView;
 * 
 * @Controller public class DiagnosticCenterRedirectContoller {
 * 
 * private static Logger LOG =
 * LoggerFactory.getLogger(DiagnosticCenterRedirectContoller.class);
 * 
 * @Autowired private IDiagnosticcenterService iDiagnosticcenterService;
 * 
 * @Value("${otpLinkForUI}") private String otpLinkForUI;
 * 
 * @Value("${errorPageLink}") private String errorPageLink;
 * 
 * @GetMapping(value = "/verification/email") public RedirectView
 * emailverification(HttpServletRequest request) { String verificationId =
 * request.getParameter("verificationId"); String uihostURL =
 * request.getParameter("uihostURL"); RedirectView redirectView = new
 * RedirectView();
 * 
 * Response<String> response = new Response<>(); try {
 * iDiagnosticcenterService.emailVerification(verificationId);
 * response.setMessage("Email verified successfully.");
 * LOG.debug("Response object created successfully..");
 * redirectView.setUrl(String.format(uihostURL + "/" + otpLinkForUI,
 * verificationId)); } catch (Exception e) { LOG.error(e.getMessage());
 * 
 * redirectView.setUrl(uihostURL + "/" + errorPageLink); } return redirectView;
 * } }
 */