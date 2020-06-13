package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySymptomCheckerBinding;
import com.example.myapplication.fragments.symptomChecker.SymptomCheckerInfoFragment;
import com.example.myapplication.fragments.symptomChecker.SymptomCheckerQuestionFragment;

public class SymptomCheckerActivity extends AppCompatActivity {

    ActivitySymptomCheckerBinding symptomCheckerBinding;
    SymptomCheckerInfoFragment symptomCheckerInfoFragment;
    SymptomCheckerQuestionFragment symptomCheckerQuestionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        symptomCheckerBinding = ActivitySymptomCheckerBinding.inflate(getLayoutInflater());
        View view = symptomCheckerBinding.getRoot();
        setContentView(view);
        symptomCheckerInfoFragment = new SymptomCheckerInfoFragment();
        symptomCheckerQuestionFragment = new SymptomCheckerQuestionFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.symptom_frame_layout, symptomCheckerInfoFragment)
                .commit();
    }
}