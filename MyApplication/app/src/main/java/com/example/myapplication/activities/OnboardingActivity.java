package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.adapters.OnboardingViewPagerAdapter;
import com.example.myapplication.databinding.ActivityOnboardingBinding;
import com.example.myapplication.fragments.onboarding.OnboardingBluetoothFragment;
import com.example.myapplication.fragments.onboarding.OnboardingExposureAndPushNotifications;
import com.example.myapplication.utilities.OnboardingScreenItem;
import com.example.myapplication.utilities.switchGPS;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    ActivityOnboardingBinding onboardingBinding;
    private OnboardingViewPagerAdapter onboardingViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting up view binder
        onboardingBinding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        View view = onboardingBinding.getRoot();
        setContentView(view);

        //setting up view pager
        setupOnboardingItems();
        onboardingBinding.onboardingViewPager.setAdapter(onboardingViewPagerAdapter);
        setupOnboardingIndicators();
        setCurrentOnboardingIndicators(0);

        onboardingBinding.onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        onboardingBinding.skipButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        onboardingBinding.nextButton.setOnClickListener(v -> {
            if (onboardingBinding.onboardingViewPager.getCurrentItem() + 1 < onboardingViewPagerAdapter.getItemCount()) {
                onboardingBinding.onboardingViewPager
                        .setCurrentItem(onboardingBinding.onboardingViewPager.getCurrentItem() + 1);
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
    }

    private void setupOnboardingItems() {

        List<OnboardingScreenItem> onboardingScreenItemList = new ArrayList<>();
        int img, desc;

        img = R.drawable.contact_tracing;
        desc = R.string.contact_tracing_onboarding;
        OnboardingScreenItem itemContactTracing = new OnboardingScreenItem(img, desc);
        onboardingScreenItemList.add(itemContactTracing);

        img = R.drawable.data_safe;
        desc = R.string.data_safe;
        OnboardingScreenItem itemDataSafe = new OnboardingScreenItem(img, desc);
        onboardingScreenItemList.add(itemDataSafe);

        img = R.drawable.self_report;
        desc = R.string.self_report;
        OnboardingScreenItem itemSelfReport = new OnboardingScreenItem(img, desc);
        onboardingScreenItemList.add(itemSelfReport);

        onboardingViewPagerAdapter = new OnboardingViewPagerAdapter(onboardingScreenItemList);
    }

    private void setupOnboardingIndicators() {
        ImageView[] indicators = new ImageView[onboardingViewPagerAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_inactive_indicator
            ));
            indicators[i].setLayoutParams(layoutParams);
            onboardingBinding.onboardingIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicators(int index) {
        int childCount = onboardingBinding.onboardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) onboardingBinding.onboardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_active_indicator)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_inactive_indicator)
                );
            }
        }
    }
}
