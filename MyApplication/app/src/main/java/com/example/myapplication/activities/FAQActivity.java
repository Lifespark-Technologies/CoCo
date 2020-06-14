package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.myapplication.databinding.ActivityFAQBinding;

public class FAQActivity extends AppCompatActivity {

    ActivityFAQBinding faqBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        faqBinding = ActivityFAQBinding.inflate(getLayoutInflater());
        View view = faqBinding.getRoot();
        setContentView(view);

        faqBinding.faqBackButton.setOnClickListener( v -> finish());
    }
}