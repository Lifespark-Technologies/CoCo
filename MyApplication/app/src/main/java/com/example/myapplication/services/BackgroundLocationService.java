package com.example.myapplication.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.os.Looper;
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

import java.util.ArrayList;
import java.util.Random;

public class BackgroundLocationService extends Service {

    private FusedLocationProviderClient mFusedLocationClient;
    private String lat, lon;
    ArrayList<String> arr = new ArrayList<>();
    Random random = new Random();
    private static final String TAG = "BACKGROUND SERVICE";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //getting location using fused location listener
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        Log.d(TAG, "Getting location");

        return START_STICKY;
    }

    private void getLastLocation(){
        mFusedLocationClient.getLastLocation().addOnCompleteListener(
                new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            lat = location.getLatitude() + "" + "\t";
                            lon = location.getLongitude() + "";
                            arr.add(lat + lon);
                            Toast.makeText(getApplicationContext(), lat + "///" + lon, Toast.LENGTH_LONG).show();
                            Log.d(TAG, "SERVICE IS RUNNING!!!");
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

    private void requestNewLocationData(){

        //first time the service is run
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
}
