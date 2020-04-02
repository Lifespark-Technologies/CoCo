package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationDataAdapter extends RecyclerView.Adapter<LocationViewHolder> {

    private List<LocationListItem> locations;
    private Context context;

    public LocationDataAdapter(List<LocationListItem> locations, Context context)
    {
        this.locations = locations;
        this.context = context;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.location_data_list_item, parent, false);

        return new LocationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        LocationListItem location = locations.get(position);

        holder.location.setText(location.getLocation());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
