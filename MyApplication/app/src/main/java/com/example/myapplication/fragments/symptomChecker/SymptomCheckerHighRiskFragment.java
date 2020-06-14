package com.example.myapplication.fragments.symptomChecker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSymptomCheckerHighRiskBinding;

public class SymptomCheckerHighRiskFragment extends Fragment {

    FragmentSymptomCheckerHighRiskBinding highRiskBinding;

    public SymptomCheckerHighRiskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        highRiskBinding = FragmentSymptomCheckerHighRiskBinding.inflate(inflater, container, false);
        View view = highRiskBinding.getRoot();

        return view;
    }
}