package com.lavawerk.test.helloalertfgs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;

public class FGService extends Service {

    private static final String PTT_DOWN = "android.intent.action.PTT.down";
    private static final String PTT_DOWN_ISM = "ism.intent.action.PTT.down";
    private static final String SOS_DOWN = "android.intent.action.sos.down";

    private static final int NOTIFICATION_ID = 1;
    private static final String TAG = "HELLO";

    private static final IntentFilter INTENT_FILTER = new IntentFilter();
    static {
        INTENT_FILTER.setPriority(1000);
        INTENT_FILTER.addAction(PTT_DOWN);
        INTENT_FILTER.addAction(PTT_DOWN_ISM);
        INTENT_FILTER.addAction(SOS_DOWN);
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long receiverTime = SystemClock.uptimeMillis();
            final String action = intent.getAction();
            Log.d(TAG, "FGService.receiver.onReceive");
            Log.d(TAG, "Intent: " + action);
            if (intent.hasExtra(Intent.EXTRA_KEY_EVENT)) { //FIXME SOS_DOWN does not have extras!
                KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
                if (keyEvent != null) {
                    long keyTime = keyEvent.getEventTime();
                    long delay = receiverTime - keyTime;
                    Log.d(TAG, "Delay: " + delay);
                    final String label;
                    if (PTT_DOWN.equalsIgnoreCase(action) || PTT_DOWN_ISM.equalsIgnoreCase(action)) {
                        label = "PTT";
                    } else if (SOS_DOWN.equalsIgnoreCase(action)) {
                        label = "SOS";
                    } else {
                        label = action;
                    }
                    FGService.this.updateNotification(label + " (" + delay + "ms)");
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "FGService.onCreate");
        registerReceiver(receiver, INTENT_FILTER);
        Log.d(TAG, "FGService.receiver registered");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "FGService.onDestroy");
        unregisterReceiver(receiver);
        Log.d(TAG, "FGService.receiver unregistered");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "FGService.onStartCommand");
        startForeground(NOTIFICATION_ID, updateNotification("Running..."));
        return START_STICKY;
    }

    private Notification updateNotification(String text) {
        final Notification notification = new Notification.Builder(this, HelloApp.CHANNEL_ID)
                .setContentTitle("HelloAlertFGS")
                .setContentText(text)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build();
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.notify(NOTIFICATION_ID, notification);
        return notification;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
