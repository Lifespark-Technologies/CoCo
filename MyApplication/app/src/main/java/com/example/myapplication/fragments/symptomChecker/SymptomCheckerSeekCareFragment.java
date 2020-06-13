package com.example.myapplication.fragments.symptomChecker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSymptomCheckerSeekCareBinding;

public class SymptomCheckerSeekCareFragment extends Fragment {

    FragmentSymptomCheckerSeekCareBinding careBinding;
    public SymptomCheckerSeekCareFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        careBinding = FragmentSymptomCheckerSeekCareBinding.inflate(inflater, container, false);
        View view = careBinding.getRoot();

        return view;
    }
}