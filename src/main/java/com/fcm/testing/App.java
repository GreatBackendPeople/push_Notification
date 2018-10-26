package com.fcm.testing;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import com.google.firebase.messaging.*;

public class App
{
    public static void main( String[] args ) throws IOException, FirebaseMessagingException
    {
        FileInputStream serviceAccount = new FileInputStream("src/main/java/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://gbk-project.firebaseio.com/")
                .build();
        FirebaseApp.initializeApp(options);


        String topic = "global";

        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                        .putData("key","value")
                        .setNotification(WebpushNotification.builder()
                                .setTimestampMillis(60000)
                                .setTitle("Notofication title 1")
                                .setBody("Notification Body 1")
                                .setImage("https://i.ytimg.com/vi/wuLKvcn-c7A/maxresdefault.jpg")
                                .build())
                        .build())

                .setApnsConfig(ApnsConfig.builder()
                        .setAps(Aps.builder()
                                .setSound("")
                                .setAlert(ApsAlert.builder()
                                        .setTitle("Notification title")
                                        .setBody("Notification body")
                                        .setLaunchImage("https://i.ytimg.com/vi/wuLKvcn-c7A/maxresdefault.jpg")
                                        .build())
                                .setBadge(1)
                                .build())
                        .build())

                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(200000000)
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .putData("key1","value1")
                        .putData("key2","value2")
                        .setNotification(AndroidNotification.builder()
                                .setTitle("Notification title 1")
                                .setBody("Notification Body test")
                                .setColor("#008800")
                                .setClickAction("https://www.google.com")
                                .setSound("")
                                .setIcon("https://i.ytimg.com/vi/wuLKvcn-c7A/maxresdefault.jpg")
                                .build())
                        .build())
                .setTopic(topic)
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);

    }
}