package com.example.myapplication;

public class LocationListItem {

    private String location;

    public LocationListItem(String gpsLocation) {
        this.location = gpsLocation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String gpsLocation) {
        this.location = gpsLocation;
    }
}
