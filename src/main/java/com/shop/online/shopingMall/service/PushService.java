package com.shop.online.shopingMall.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.shop.online.shopingMall.dto.fcm.FcmRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PushService {

    private final UserService userService;

    @PostConstruct
    public void init() throws IOException {
        FileInputStream refreshToken = new FileInputStream("/secert/saouk-rdvuoe-firebase-adminsdk-888v7-2c7ed8dc19.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    /**
    * 비동기 방식으로 메세지를 전송함
    * */
    public void sendPushMessage(List<Message> message) {
        FirebaseMessaging.getInstance().sendAllAsync(message);
    }

    public void checkSendUser(FcmRequestDto fcmRequestDto) {
        userService.findFcmOnUser();
    }
}
