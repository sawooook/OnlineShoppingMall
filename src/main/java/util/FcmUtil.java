package util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.aspectj.weaver.ast.And;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FcmUtil {

    public void sendPushMessage(String token, String title, String content) {

        try {
            FileInputStream refreshToken = new FileInputStream("/secret/saouk-rdvuoe-firebase-adminsdk-888v7-2c7ed8dc19.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://fcm.googleapis.com/fcm/send")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            String resgiterationToken = token;

            AndroidNotification notification = AndroidNotification.builder().setTitle(title)
                    .setBody(content).build();

            AndroidConfig config = AndroidConfig.builder()
                    .setTtl(3600 * 1000).setPriority(AndroidConfig.Priority.NORMAL)
                    .setNotification(notification).build();

            Message msg = Message.builder().setAndroidConfig(config)
                    .setToken(resgiterationToken).build();

            FirebaseMessaging.getInstance().send(msg);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}
