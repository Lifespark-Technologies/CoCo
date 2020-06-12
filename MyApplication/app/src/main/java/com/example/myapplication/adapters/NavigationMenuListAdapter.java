package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.activities.AboutUsActivity;
import com.example.myapplication.activities.SettingsActivity;
import com.example.myapplication.fragments.HomeFragment;

public class NavigationMenuListAdapter extends ArrayAdapter {

    Context context;

    public NavigationMenuListAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Object[] objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder")

        View view = inflater.inflate(R.layout.side_navigation_item, parent, false);
        LinearLayout menuItem = view.findViewById(R.id.menu_item);

        switch (position) {
            case 0:
                menuItem.setOnClickListener(v -> {
                    Intent settingsIntent = new Intent(context, AboutUsActivity.class);
                    ((AppCompatActivity)context).startActivityForResult(settingsIntent, Activity.RESULT_OK);
                });
                break;
            case 1:
                menuItem.setOnClickListener(v -> {
                    Intent settingsIntent = new Intent(context, SettingsActivity.class);
                    ((AppCompatActivity)context).startActivityForResult(settingsIntent, Activity.RESULT_OK);
                });
                break;
        }
        return super.getView(position, view, parent);
    }
}
