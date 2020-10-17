package com.lavawerk.test.helloalertfgs;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class HelloApp extends Application {
    public static final String CHANNEL_ID = "HelloAlertFGS";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
    private void createNotificationChannel() {
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "HelloAlertFGS",
                NotificationManager.IMPORTANCE_DEFAULT
        );
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
    }
}
