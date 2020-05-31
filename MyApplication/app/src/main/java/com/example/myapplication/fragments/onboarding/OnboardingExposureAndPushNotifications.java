package com.example.myapplication.fragments.onboarding;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activities.AskPermissions;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.utilities.switchGPS;
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

public class OnboardingExposureAndPushNotifications extends Fragment {

    private Button proceedButton, remindMeLater;
    static final int ASK_PERMISSIONS = 10;
    SharedPreferences prefs;

    public OnboardingExposureAndPushNotifications() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View onboardingExposureAndPushNotifications = inflater.inflate(R.layout.fragment_onboarding_exposure_and_push_notifications, container, false);
        proceedButton = onboardingExposureAndPushNotifications.findViewById(R.id.proceed_button);
        remindMeLater = onboardingExposureAndPushNotifications.findViewById(R.id.remind_me_later_button);
        //prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        //SharedPreferences.Editor editor = prefs.edit();
        //editor.putBoolean("firstRun", false);

        proceedButton.setOnClickListener(v -> {

            if (!checkPermissions()){
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
                    Toast.makeText(getContext(), "The permission to get gps location data is required", Toast.LENGTH_SHORT).show();
                    requestPermissions();
                }else{
                    requestPermissions();
                }
            }else{
                Toast.makeText(getContext(), "Location permissions already granted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            }

        });

        remindMeLater.setOnClickListener(v -> {

        });

        return onboardingExposureAndPushNotifications;
    }

    //checks whether permissions have been granted or not
    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    //requests permissions from the user
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                ASK_PERMISSIONS
        );
    }
    
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        if (requestCode == 1000) {
            if (resultCode == AskPermissions.RESULT_OK) {
                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            }
            else {
                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            }
        }
    }
}
