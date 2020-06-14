package com.example.myapplication.fragments.symptomChecker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSymptomCheckerQuestionBinding;

public class SymptomCheckerQuestionFragment extends Fragment {

    FragmentSymptomCheckerQuestionBinding questionBinding;
    SymptomCheckerInfoFragment symptomCheckerInfoFragment;
    SymptomCheckerLowRiskFragment symptomCheckerLowRiskFragment;

    public SymptomCheckerQuestionFragment () {
        //Required public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        questionBinding = FragmentSymptomCheckerQuestionBinding.inflate(inflater, container,false);
        View view = questionBinding.getRoot();
        symptomCheckerInfoFragment = new SymptomCheckerInfoFragment();
        symptomCheckerLowRiskFragment = new SymptomCheckerLowRiskFragment();

        questionBinding.options.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.option_1:
                    questionBinding.option1
                            .setBackground(getResources().getDrawable(R.drawable.secondary_button));
                    questionBinding.option2
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option3
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option4
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    break;

                case R.id.option_2:
                    questionBinding.option1
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option2
                            .setBackground(getResources().getDrawable(R.drawable.secondary_button));
                    questionBinding.option3
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option4
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    break;

                case R.id.option_3:
                    questionBinding.option1
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option2
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option3
                            .setBackground(getResources().getDrawable(R.drawable.secondary_button));
                    questionBinding.option4
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    break;

                case R.id.option_4:
                    questionBinding.option1
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option2
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option3
                            .setBackground(getResources().getDrawable(R.drawable.settings_options));
                    questionBinding.option4
                            .setBackground(getResources().getDrawable(R.drawable.secondary_button));
                    break;
            }
        });

        getActivity().findViewById(R.id.symptom_checker_back_button).setOnClickListener( v ->
                getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.symptom_frame_layout, symptomCheckerInfoFragment)
                .addToBackStack(null)
                .commit());

        questionBinding.questionContinueButton.setOnClickListener( v ->
                getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.symptom_frame_layout, symptomCheckerLowRiskFragment)
                .addToBackStack(null)
                .commit());
        return view;
    }
}