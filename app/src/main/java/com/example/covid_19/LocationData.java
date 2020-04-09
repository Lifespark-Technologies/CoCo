package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationData extends AppCompatActivity {

    private RecyclerView locationData;
    private RecyclerView.Adapter adapter;
    private List<LocationListItem> locations;
    FileInputStream fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_data);

        locationData = findViewById(R.id.location_data);
        locationData.setHasFixedSize(true);
        locationData.setLayoutManager(new LinearLayoutManager(this));
        try {
            fin = openFileInput("location_data");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        locations = new ArrayList<>();

        for (int i = 0;i < 10;i++) {
            LocationListItem locate = new LocationListItem(
                    "location " + (i + 1)
            );
            locations.add(locate);
        }


        char c = ' ';
        String temp = "";

        do {
            try {
                c = (char) fin.read();
                temp = temp + c;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (c != '\n');
        try {
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        locations.add(new LocationListItem(temp));


        adapter = new LocationDataAdapter(locations, this);
        locationData.setAdapter(adapter);
    }
}