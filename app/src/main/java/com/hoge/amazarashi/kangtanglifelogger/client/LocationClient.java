package com.hoge.amazarashi.kangtanglifelogger.client;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationClient {
    private final FusedLocationProviderClient client;

    public LocationClient(Context context) {
        client = LocationServices.getFusedLocationProviderClient(context);
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    public void getLastLocation(Context context, Consumer consumer) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            }, 1);

            return;
        }
        client.getLastLocation()
                .addOnSuccessListener(consumer::apply);
    }

    public interface Consumer {
        void apply(Location location);
    }
}
