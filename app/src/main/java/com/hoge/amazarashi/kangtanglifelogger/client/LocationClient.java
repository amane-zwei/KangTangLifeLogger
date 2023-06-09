package com.hoge.amazarashi.kangtanglifelogger.client;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.hoge.amazarashi.kangtanglifelogger.domain.KTLLLocation;

public class LocationClient {
    private final FusedLocationProviderClient client;

    public LocationClient(Context context) {
        client = LocationServices.getFusedLocationProviderClient(context);
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    @androidx.annotation.RequiresPermission(anyOf = {
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION"})
    public void getLastLocation(Context context, Consumer consumer) {
        client.getLastLocation()
                .addOnSuccessListener(location -> consumer.apply(new KTLLLocation(location)));
    }

    public interface Consumer {
        void apply(KTLLLocation location);
    }
}
