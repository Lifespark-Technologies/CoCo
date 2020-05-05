package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.myapplication.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;


public class AskPermissons extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    static final int ASK_PERMISSIONS = 10;
    private Button proceedButton;
    private int permissionCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_permissons);

        proceedButton=findViewById(R.id.proceed_button);

        permissionCheck = ContextCompat.checkSelfPermission(AskPermissons.this, Manifest.permission.ACCESS_FINE_LOCATION);

        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPermissions()){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(AskPermissons.this, Manifest.permission.ACCESS_FINE_LOCATION)){
                        Toast.makeText(AskPermissons.this, "The permission to get gps location data is required", Toast.LENGTH_SHORT).show();
                    }else{
                        requestPermissions();
                    }
                }else{
                    Toast.makeText(AskPermissons.this, "Location permissions already granted", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AskPermissons.this, MainActivity.class));
                }
            }
        });
    }

    //checks whether permissions have been granted or not
    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    //requests permissions from the user
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                ASK_PERMISSIONS
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == ASK_PERMISSIONS) {
            for (int i = 0; i<permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if(permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        enableGPS();
                    }
                    else {
                        requestPermissions();
                    }
                }
            }
        }
    }

    public void enableGPS() {
        GoogleApiClient googleApiClient = null;

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(AskPermissons.this)
                    .build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            // **************************
            builder.setAlwaysShow(true); // this is the key ingredient
            // **************************

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                    .checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result
                            .getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can
                            // initialize location
                            // requests here.
                            startActivity(new Intent(AskPermissons.this, MainActivity.class));
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be
                            // fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling
                                // startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(AskPermissons.this, 1000);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have
                            // no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        if (requestCode == 1000) {
            if (resultCode == AskPermissons.RESULT_OK) {
                startActivity(new Intent(AskPermissons.this, MainActivity.class));
            }
            else {
                startActivity(new Intent(AskPermissons.this, MainActivity.class));
            }
        }
    }

    public void onConnected(@Nullable Bundle bundle) {

    }

    public void onConnectionSuspended(int i) {

    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
