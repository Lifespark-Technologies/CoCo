package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent backgroundService = new Intent(context, BackgroundLocationService.class);
        context.startService(backgroundService);
    }
}
