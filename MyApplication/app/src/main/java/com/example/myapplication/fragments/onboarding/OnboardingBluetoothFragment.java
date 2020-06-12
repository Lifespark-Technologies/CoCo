package com.example.myapplication.fragments.onboarding;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

public class OnboardingBluetoothFragment extends Fragment {

    private Button proceedButton, remindMeLater;
    SharedPreferences prefs;

    public OnboardingBluetoothFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View onboardingBluetoothFragment =  inflater.inflate(R.layout.fragment_onboarding_bluetooth, container, false);
        remindMeLater = onboardingBluetoothFragment.findViewById(R.id.remind_me_later_button);
        proceedButton = onboardingBluetoothFragment.findViewById(R.id.proceed_button);

        proceedButton.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame_layout, new OnboardingLocationFragment())
                    .addToBackStack(null)
                    .commit();
        });

        remindMeLater.setOnClickListener(view -> {

        });

        return onboardingBluetoothFragment;
    }
}
