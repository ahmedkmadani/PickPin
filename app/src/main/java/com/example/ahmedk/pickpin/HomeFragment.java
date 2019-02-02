package com.example.ahmedk.pickpin;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import de.hdodenhof.circleimageview.CircleImageView;

public  class HomeFragment extends Fragment implements ClusterManager.OnClusterItemInfoWindowClickListener<PinsItem> {

    private ClusterManager<PinsItem> mClusterManager;
    private PinsItem clusterItem;
    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                setUpCluster();

            }
        });

        return view;
    }

    private void setUpCluster() {

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(22.7483221, 72.88330078), 5));
        mClusterManager = new ClusterManager<PinsItem>(getActivity(), mMap);

        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());

        mClusterManager
                .setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<PinsItem>() {
                    @Override
                    public boolean onClusterItemClick(PinsItem item) {
                        clusterItem = item;
                        return false;
                    }
                });


        addItems();

        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(
                new MarkerAdapter());
        addItems();
    }

    private void addItems() {

        for (int i = 0; i < Pins.latitude.length; i++) {

            PinsItem offsetItem = new PinsItem(Pins.latitude[i], Pins.longitude[i], Pins.name[i]);
            mClusterManager.addItem(offsetItem);
        }
    }

    //added with edit
    @Override
    public void onClusterItemInfoWindowClick(PinsItem pinsItem) {
        Toast.makeText(getActivity(), pinsItem.getName() + " Clicked", Toast.LENGTH_SHORT).show();
    }

    public class MarkerAdapter implements GoogleMap.InfoWindowAdapter {

        private final View PinsView;

        MarkerAdapter() {
              PinsView = getLayoutInflater().inflate(
                    R.layout.custom_marker_layout, null);


        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView name = ((TextView) PinsView
                    .findViewById(R.id.name));
            ImageView user_dp = ((ImageView) PinsView
                    .findViewById(R.id.user_dp));

            name.setText(clusterItem.getName());
            user_dp.setImageResource(R.drawable.ahmedk);

            return PinsView;
        }
    }

}




