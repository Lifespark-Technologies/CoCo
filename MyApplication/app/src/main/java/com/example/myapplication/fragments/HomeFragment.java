package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.activities.SymptomChecker;
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
            Intent symptomChecker = new Intent(getActivity().getApplicationContext(), SymptomChecker.class);
            startActivity(symptomChecker);
        });

        return view;
    }
}
