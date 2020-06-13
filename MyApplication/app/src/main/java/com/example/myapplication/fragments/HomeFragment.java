package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.activities.SymptomCheckerActivity;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.services.ForegroundNotificationService;

public class HomeFragment extends Fragment {

    FragmentHomeBinding homeBinding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = homeBinding.getRoot();

        homeBinding.areaSearchButton.setOnClickListener(v -> {

        });

        homeBinding.highDensityButton.setOnClickListener(v -> {

        });

        homeBinding.highRiskButton.setOnClickListener(v -> {

        });

        homeBinding.serviceTesting.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(getActivity().getApplicationContext(), ForegroundNotificationService.class);
            if (Build.VERSION.SDK_INT >- Build.VERSION_CODES.O) {
                getActivity().startForegroundService(serviceIntent);
            } else {
                getActivity().startService(serviceIntent);
            }
        });

        homeBinding.symptomCheckerButton.setOnClickListener(v -> {
            Intent symptomChecker = new Intent(getActivity().getApplicationContext(), SymptomCheckerActivity.class);
            startActivity(symptomChecker);
        });

        return view;
    }
}
