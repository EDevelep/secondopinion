package org.secondopinion.configurations.firebase;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;

@Service
public class FCMService {

  private Logger logger = LoggerFactory.getLogger(FCMService.class);

  public void sendMessage(Map<String, String> data, PushNotificationRequest request)
      throws InterruptedException, ExecutionException {
    Message message = getPreconfiguredMessageWithData(data, request);
    String response = sendAndGetResponse(message);
    logger.debug("Sent message with data. Topic: " + request.getTopic() + ", " + response);

  }

  public void sendMessageWithoutData(PushNotificationRequest request)
      throws InterruptedException, ExecutionException {
    Message message = getPreconfiguredMessageWithoutData(request);
    String response = sendAndGetResponse(message);
    logger.debug("Sent message with data. Topic: " + request.getTopic() + ", " + response);

  }

  public void sendMessageToToken(PushNotificationRequest request)
      throws InterruptedException, ExecutionException {
    Message message = getPreconfiguredMessageToToken(request);
    String response = sendAndGetResponse(message);
    logger.debug("Sent message to token. Device token: " + request.getToken() + ", " + response);

  }

  private String sendAndGetResponse(Message message)
      throws InterruptedException, ExecutionException {
    return FirebaseMessaging.getInstance().sendAsync(message).get();
  }

  private AndroidConfig getAndroidConfig(String topic) {
    return AndroidConfig.builder().setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
        .setPriority(AndroidConfig.Priority.HIGH)
        .setNotification(
            AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build())
        .build();
  }

  private ApnsConfig getApnsConfig(String topic) {
    return ApnsConfig.builder().setAps(Aps.builder().setCategory(topic).setThreadId(topic).build())
        .build();
  }

  private WebpushConfig getWebpushConfig(String topic) {
    return WebpushConfig.builder().setNotification(WebpushNotification.builder()
        .setTimestampMillis(Duration.ofMinutes(2).toMillis()).setTag(topic).build()).build();
  }

  private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
    return getPreconfiguredMessageBuilder(request).setToken(request.getToken()).build();
  }

  private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
    return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic()).build();
  }

  private Message getPreconfiguredMessageWithData(Map<String, String> data,
      PushNotificationRequest request) {
    return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic())
        .build();
  }

  private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {


    AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
    ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
    WebpushConfig webpushConfig = getWebpushConfig(request.getTopic());
    return Message.builder().setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
        .setWebpushConfig(webpushConfig).setNotification(Notification.builder()
            .setTitle(request.getTitle()).setBody(request.getMessage()).build());
  }


}
