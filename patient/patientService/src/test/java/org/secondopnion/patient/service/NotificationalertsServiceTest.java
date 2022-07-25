package org.secondopnion.patient.service;

import java.util.Date;
import org.junit.Test;
import org.secondopinion.patient.dto.Notificationalerts;
import org.secondopinion.patient.service.INotificationalertsService;
import org.secondopinion.utils.NotificationAlert;
import org.secondopnion.patient.PatientApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationalertsServiceTest extends PatientApplicationTest {

  @Autowired
  INotificationalertsService iNotificationalertsService;

  @Test
  public void saveNotificationalerts() {
    Notificationalerts notificationalerts = new Notificationalerts();
    iNotificationalertsService.saveNotificationalerts(notificationalerts);
  }

  @Test
  public void getAllNotificationalerts() {
    Long patientId = 141L;
    Date date = null;
    iNotificationalertsService.getAllNotificationalerts(patientId);
  }

  @Test
  public void getNotificationsNotificationsStatusIsview() {
    Long patientId = 141L;
    iNotificationalertsService.getNotificationsNotificationsStatusIsview(patientId);
  }

  @Test
  public void testUpdateNotificationalerts() {
    Notificationalerts notificationalerts = new Notificationalerts();
    notificationalerts.setNotificationalertsId(618L);
    notificationalerts.setReason("temproaryJatins");
    notificationalerts.setViewed('Y');
    iNotificationalertsService.saveNotificationalerts(notificationalerts);
  }

  @Test
  public void sendNotification() {
    NotificationAlert alert = new NotificationAlert();
    alert.setMessage("welcomd");

  }

  @Test
  public void sendNotificationForAppointment(NotificationAlert alert) {
    Notificationalerts notificationalerts = new Notificationalerts();
    notificationalerts.setNotificationalertsId(618L);
    notificationalerts.setReason("temproaryJatins");
    notificationalerts.setViewed('Y');
    notificationalerts.setObjectkey(5l);
    iNotificationalertsService.sendNotificationForAppointment(alert);
  }

}
