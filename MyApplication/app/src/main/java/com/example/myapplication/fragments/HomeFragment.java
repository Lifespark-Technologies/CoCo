package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.activities.SymptomChecker;
import com.example.myapplication.activities.SymptomCheckerNo;

public class HomeFragment extends Fragment {

    public Button symptomCheckerButton, highDensityButton, highRiskButton;
    ImageButton searchLocationButton;
    EditText searchLocation;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View homeFragmentLayout = inflater.inflate(R.layout.fragment_home, container, false);
        symptomCheckerButton = homeFragmentLayout.findViewById(R.id.symptom_checker_button);
        searchLocationButton = homeFragmentLayout.findViewById(R.id.home_area_search_button);
        highDensityButton = homeFragmentLayout.findViewById(R.id.high_density_button);
        highRiskButton = homeFragmentLayout.findViewById(R.id.high_risk_button);
        searchLocation = homeFragmentLayout.findViewById(R.id.home_area_search);

        symptomCheckerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent symptomchecker = new Intent(getActivity().getApplicationContext(), SymptomChecker.class);
                startActivity(symptomchecker);
            }
        });
        return homeFragmentLayout;
    }
}
