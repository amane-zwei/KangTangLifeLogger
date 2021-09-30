package com.hoge.amazarashi.kangtanglifelogger.domain;

import android.location.Location;

import androidx.annotation.NonNull;

import java.util.Locale;

public class KTLLLocation {

    private final double latitude;
    private final double longitude;
    public KTLLLocation(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

     @NonNull
     @Override
     public String toString() {
        return String.format(Locale.US, "%f, %f", latitude, longitude);
     }
}
