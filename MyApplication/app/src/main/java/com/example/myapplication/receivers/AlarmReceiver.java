package com.example.myapplication.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.myapplication.services.BackgroundLocationService;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //runs the background service to get location
        Intent backgroundService = new Intent(context, BackgroundLocationService.class);
        context.startService(backgroundService);
    }
}
