package com.lavawerk.test.helloalertfgs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CmdReceiver extends BroadcastReceiver {

    public static final String ACTION_START = "com.lavawerk.test.helloalertfgs.START";
    public static final String ACTION_STOP = "com.lavawerk.test.helloalertfgs.STOP";
    private static final String TAG = "HELLO";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        Log.d(TAG, "CmdReceiver.onReceive intent=" + action);

        if (ACTION_START.equalsIgnoreCase(action)) {
            context.startForegroundService(new Intent(context, FGService.class));
        } else if (ACTION_STOP.equalsIgnoreCase(action)) {
            context.stopService(new Intent(context, FGService.class));
        } else {
            Log.e(TAG, "Unknown command");
        }
    }
}