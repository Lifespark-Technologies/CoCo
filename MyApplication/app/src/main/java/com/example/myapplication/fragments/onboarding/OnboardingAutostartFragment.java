package com.example.myapplication.fragments.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.autostartPermissions.Autostarter;
import com.example.myapplication.R;

public class OnboardingAutostartFragment extends Fragment {

    private Button proceedButton, remindMeLater;
    Autostarter autostarter;

    public OnboardingAutostartFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View onboardingAutostartFragment = inflater.inflate(R.layout.fragment_onboarding_autostart, container, false);
        proceedButton = onboardingAutostartFragment.findViewById(R.id.proceed_button);
        remindMeLater = onboardingAutostartFragment.findViewById(R.id.remind_me_later_button);
        autostarter = new Autostarter();

        proceedButton.setOnClickListener(v -> {

            if(autostarter.isAutoStartPermissionAvailable(getActivity().getApplicationContext())) {

                autostarter.getAutoStartPermission(getActivity().getApplicationContext());

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