package com.example.myapplication.fragments.symptomChecker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentSymptomCheckerLowRiskBinding;

public class SymptomCheckerLowRiskFragment extends Fragment {

    FragmentSymptomCheckerLowRiskBinding lowRiskBinding;
    public SymptomCheckerLowRiskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lowRiskBinding = FragmentSymptomCheckerLowRiskBinding.inflate(inflater, container, false);
        View view = lowRiskBinding.getRoot();

        return view;
    }
}