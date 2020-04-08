package com.example.covid_19;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BackgroundService extends Service {

    private FusedLocationProviderClient mFusedLocationClient;
    private String lat, lon;
    Handler handler = new Handler();
    FileOutputStream fOut;
    ArrayList<String> arr = new ArrayList<>(12);
    ArrayList<String> arr2 = new ArrayList<>(12);


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        handler.post(runnableCode);
        try {
            fOut = openFileOutput("location_data", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return START_REDELIVER_INTENT;
    }
    /* Use if needed for debugging
        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    */
    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        mFusedLocationClient.getLastLocation().addOnCompleteListener(
                new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            lat = location.getLatitude() + "" + "\n";
                            lon = location.getLongitude() + "" + "\n";
                            Toast.makeText(getApplicationContext(), lat + "///" + lon, Toast.LENGTH_LONG).show();
                            try {
                                fOut.write(lat.getBytes());
                                fOut.write(lon.getBytes());
                                fOut.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat = mLastLocation.getLatitude()+"";
            lon = mLastLocation.getLongitude()+"";
        }
    };

    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            getLastLocation();
            Log.d("Handlers", "Called on main thread");
            handler.postDelayed(this, 300000);
        }
    };
}