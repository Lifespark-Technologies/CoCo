package com.example.myapplication.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.example.myapplication.services.ForegroundNotificationService;

public class BootReceiver extends BroadcastReceiver {

    private final String TAG = "BOOT RECEIVER";
    //if device reboots, it's intent is sent here
    public void onReceive(Context context, Intent intent) {

        if((Intent.ACTION_REBOOT.equals(intent.getAction()) || Intent.
                ACTION_BOOT_COMPLETED.equals(intent.getAction()) || Intent.
                ACTION_LOCKED_BOOT_COMPLETED.equals(intent.getAction()))) {

            Intent serviceIntent = new Intent(context, ForegroundNotificationService.class);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
                return;
            }
            context.startService(serviceIntent);
            Log.d(TAG, "Foreground service started");
        }
    }
}