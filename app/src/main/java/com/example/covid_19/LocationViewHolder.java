package com.example.covid_19;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocationViewHolder extends RecyclerView.ViewHolder {

    public TextView location;

    public LocationViewHolder(@NonNull View itemView) {
        super(itemView);

        location = (TextView) itemView.findViewById(R.id.location_item);
    }
}