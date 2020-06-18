package com.example.myapplication.fragments.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.utilities.AutostarterPermissions;
import com.example.myapplication.R;

public class OnboardingAutostartFragment extends Fragment {

    private Button proceedButton, remindMeLater;
    AutostarterPermissions autostarterPermissions;

    public OnboardingAutostartFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View onboardingAutostartFragment = inflater.inflate(R.layout.fragment_onboarding_autostart, container, false);
        proceedButton = onboardingAutostartFragment.findViewById(R.id.proceed_button);
        remindMeLater = onboardingAutostartFragment.findViewById(R.id.remind_me_later_button);
        autostarterPermissions = new AutostarterPermissions();

        proceedButton.setOnClickListener(v -> {

            if(autostarterPermissions.isAutoStartPermissionAvailable(getActivity().getApplicationContext())) {

                autostarterPermissions.getAutoStartPermission(getActivity().getApplicationContext());

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_frame_layout, new OnboardingExposureAndPushNotifications())
                        .addToBackStack(null)
                        .commit();
            }
        });

        remindMeLater.setOnClickListener(v -> {

        });

        return onboardingAutostartFragment;
    }
}