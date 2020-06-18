package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.utilities.OnboardingScreenItem;

import java.util.List;

public class OnboardingViewPagerAdapter extends RecyclerView.Adapter<OnboardingViewPagerAdapter.OnboardingViewHolder> {

    private List<OnboardingScreenItem> onboardingScreenItems;

    public OnboardingViewPagerAdapter(List<OnboardingScreenItem> onboardingScreenItems) {
        this.onboardingScreenItems = onboardingScreenItems;
    }

    @NonNull
    @Override
    public OnboardingViewPagerAdapter.OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.onboarding_item, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewPagerAdapter.OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingScreenItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingScreenItems.size();
    }

    public class OnboardingViewHolder extends RecyclerView.ViewHolder{

        private ImageView onboardingImg;
        private TextView onboardingText;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            onboardingImg = itemView.findViewById(R.id.onboarding_image);
            onboardingText = itemView.findViewById(R.id.onboarding_text);
        }

        void setOnboardingData (OnboardingScreenItem onboardingItem) {
            onboardingImg.setImageResource(onboardingItem.getImg());
            onboardingText.setText(onboardingItem.getDescription());
        }
    }
}
