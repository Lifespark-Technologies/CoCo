package com.example.myapplication.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.myapplication.receivers.AlarmReceiver;
import com.example.myapplication.activities.MainActivity;

public class ForegroundNotificationService extends Service {

    private static final String CHANNEL_ID = "ForegroundServiceChannel";
    private static final String TAG = "FOREGROUND SERVICE";
    private AlarmManager alarmManager;
    private PendingIntent pendingIntentForAlarm;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntentForAlarm = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        createNotificationChannel();
        Intent notifyIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, notifyIntent, 0);
        Notification notification = new
                NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Service is Running")
                .setContentIntent(pendingIntent)
                .build();
        startAlarm();
        startForeground(1, notification);
        Log.d(TAG, "Alarms is set and foreground service started");

        return START_STICKY;
    }

    //for creation of persisting notification
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    /* Use if needed for debugging
    @Override
    public void onDestroy() {
        super.onDestroy();

    }*/

    //the alarm for triggering the background service at every 5 minutes
    private void startAlarm() {
        Log.d(TAG, "Alarm Set!!!");
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 300000, pendingIntentForAlarm);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }
}
