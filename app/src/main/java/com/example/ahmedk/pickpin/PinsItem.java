package com.example.ahmedk.pickpin;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class PinsItem implements ClusterItem {
    private final LatLng mPosition;
    private final String name;

    public PinsItem(double lat, double lng, String name) {
        mPosition = new LatLng(lat, lng);
        this.name = name;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    public String getName() {
        return name;
    }

}