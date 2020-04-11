package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BootReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        // For our recurring task, we'll just display a message
        if((Intent.ACTION_REBOOT.equals(intent.getAction()) || Intent.
                ACTION_BOOT_COMPLETED.equals(intent.getAction()) || Intent.
                ACTION_LOCKED_BOOT_COMPLETED.equals(intent.getAction()))) {
            Intent serviceIntent = new Intent(context, ForegroundNotificationService.class);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent);
                return;
            }
            context.startService(serviceIntent);
        }
    }
}