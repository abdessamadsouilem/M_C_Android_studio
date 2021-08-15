package com.example.carapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLng;

public class requestm extends FragmentActivity {
    MapView mapView;
    FusedLocationProviderClient fusedLocationProviderClient;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    double currentLat = 0, currentLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestm);
        //getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.requestm);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.requestm:

                        return true;
                    case R.id.profileIm:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.aceuill:
                        startActivity(new Intent(getApplicationContext(), Aceuill.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // check permission
        if (ActivityCompat.checkSelfPermission(requestm.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();

        } else {
            ActivityCompat.requestPermissions(requestm.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


    }


    public void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();

       task.addOnSuccessListener(new OnSuccessListener<Location>() {
           @Override
           public void onSuccess(Location location) {
               if(location != null){
                   currentLat = location.getLatitude();
                   currentLong = location.getLongitude();
                   supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                       @Override
                       public void onMapReady(GoogleMap googleMap) {
                           map = googleMap;
                           map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat,currentLong),20));
                           MarkerOptions marker = new MarkerOptions();
                           marker.position(new LatLng(currentLat,currentLong));
                           marker.title("my position");
                           map.addMarker(marker);
                       }
                   });

               }

           }
       });
   }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    public void ToReq()
    {
        Intent switchActivityIntent = new Intent(this, requestm.class);
        startActivity(switchActivityIntent);
    }
    public void ToReq1()
    {
        Intent switchActivityIntent = new Intent(this, com.example.carapp.Profile.class);
        startActivity(switchActivityIntent);
    }
    public void ToReq2()
    {
        Intent switchActivityIntent = new Intent(this, Aceuill.class);
        startActivity(switchActivityIntent);
    }

}