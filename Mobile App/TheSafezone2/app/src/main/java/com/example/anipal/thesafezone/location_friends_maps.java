package com.example.anipal.thesafezone;

import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class location_friends_maps extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lat,lon;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLastLocation;
    String name ;
    String phone ;//= getIntent().getStringExtra("phone");
    String safezone;// = getIntent().getStringExtra("safezone");
    String address ;//= getIntent().getStringExtra("address");
    String la ;//= getIntent().getStringExtra("lat");
    String lo ;//= getIntent().getStringExtra("lon");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_friends_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        safezone = getIntent().getStringExtra("safezone");
        address = getIntent().getStringExtra("address");
        la = getIntent().getStringExtra("lat");
        lo = getIntent().getStringExtra("lon");
        lat = Double.parseDouble(la);
        lon = Double.parseDouble(lo);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sz = new LatLng(lat, lon);

        mMap.addMarker(new MarkerOptions().position(sz).title("Safezone "+safezone)
                .snippet("Name: "+name+"\nPhone: "+phone+"\nAddress: "+address)).showInfoWindow();

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(getBaseContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getBaseContext());
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getBaseContext());
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sz,14));
    }
}
