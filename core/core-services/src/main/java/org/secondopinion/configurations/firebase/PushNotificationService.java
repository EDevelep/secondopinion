package org.secondopinion.configurations.firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {


    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

//    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
//    public void sendSamplePushNotification() {
//        try {
//        	PushNotificationRequest request = new PushNotificationRequest();
//        	request.setMessage("hi");
//        	request.setTitle("hi");
//        	request.setTopic("hi");
//            fcmService.sendMessageWithoutData(request);
//        } catch (InterruptedException | ExecutionException e) {
//            logger.error(e.getMessage());
//        }
//    }

    public void sendPushNotification(PushNotificationRequest request) {
        try {
    		Map<String, String> appData = new HashMap<>();
            fcmService.sendMessage(appData, request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }

    public void sendPushNotificationWithoutData(PushNotificationRequest request) {
        try {
            fcmService.sendMessageWithoutData(request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }


    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }




}
