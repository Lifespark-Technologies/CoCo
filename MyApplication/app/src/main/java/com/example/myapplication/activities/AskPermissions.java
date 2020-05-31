package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.FrameLayout;

import com.example.myapplication.R;
import com.example.myapplication.fragments.onboarding.OnboardingBluetoothFragment;
import com.example.myapplication.fragments.onboarding.OnboardingExposureAndPushNotifications;
import com.example.myapplication.utilities.switchGPS;

import java.security.Permission;

public class AskPermissions extends AppCompatActivity  {

    static final int ASK_PERMISSIONS = 10;
    private int permissionCheck;
    switchGPS GPSswitch = new switchGPS();
    FrameLayout mainFrameLayout;
    OnboardingBluetoothFragment onboardingBluetoothFragment;
    OnboardingExposureAndPushNotifications onboardingExposureAndPushNotifications;
    FragmentTransaction ft;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_permissons);

        //proceedButton=findViewById(R.id.proceed_button);
        permissionCheck = ContextCompat.checkSelfPermission(AskPermissions.this, Manifest.permission.ACCESS_FINE_LOCATION);
        mainFrameLayout = findViewById(R.id.main_frame_layout);
        onboardingBluetoothFragment = new OnboardingBluetoothFragment();
        onboardingExposureAndPushNotifications = new OnboardingExposureAndPushNotifications();
        GPSswitch.setContext(AskPermissions.this);
        /*prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean firstRun = prefs.getBoolean("firstRun",false);

        if(!firstRun) {
            startActivity(new Intent(AskPermissions.this, MainActivity.class));
            finish();
        }*/

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout, onboardingBluetoothFragment, "ONBOARDING_BLUETOOTH");
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            onboardingExposureAndPushNotifications.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 10) {

            for (int i = 0; i<permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        GPSswitch.enableGPS();
                        startActivity(new Intent(AskPermissions.this, MainActivity.class));
                    }
                }
            }
        }
    }
}
