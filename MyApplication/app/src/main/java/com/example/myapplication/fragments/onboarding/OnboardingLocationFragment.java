package com.example.myapplication.fragments.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

public class OnboardingLocationFragment extends Fragment {

    private Button proceedButton, remindMeLater;

    public OnboardingLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View onboardingLocationFragment = inflater.inflate(R.layout.fragment_onboarding_location, container, false);
        proceedButton = onboardingLocationFragment.findViewById(R.id.proceed_button);
        remindMeLater = onboardingLocationFragment.findViewById(R.id.remind_me_later_button);

        proceedButton.setOnClickListener(v -> {

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame_layout, new OnboardingAutostartFragment())
                    .addToBackStack(null)
                    .commit();
        });

        remindMeLater.setOnClickListener(v -> {

        });

        return onboardingLocationFragment;
    }
}
