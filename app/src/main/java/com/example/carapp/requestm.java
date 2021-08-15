package com.example.carapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLng;

public class requestm extends FragmentActivity {
    MapView mapView;
    public static String _name;
    public static double _lat;
    public static double _lng;
    public int j;

    FusedLocationProviderClient fusedLocationProviderClient;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    double currentLat = 0, currentLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         j=0;
        setContentView(R.layout.activity_requestm);
        //getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.requestm);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.requestm:
                        startActivity(new Intent(getApplicationContext(), requestm.class));
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
        statusCheck();


    }
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }else{
            getCurrentLocation();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    public void getCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(),"Bluetooth Turned OFF",Toast.LENGTH_SHORT).show();
        }else{
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            Task<Location> task = fusedLocationProviderClient.getLastLocation();

            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if(location != null) {

                        currentLat = location.getLatitude();
                        currentLong = location.getLongitude();
                        GetM(currentLat,currentLong);
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                map = googleMap;
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLong), 10));
                                MarkerOptions marker = new MarkerOptions();
                                marker.position(new LatLng(currentLat, currentLong));
                                marker.title("my position");
                                map.addMarker(marker);
                            }
                        });

                    }

                }
            });
        }

   }

    private  void GetM(double lat, double lng)
    {

        StringRequest request = new StringRequest(Request.Method.GET, Constant.Mecha, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getJSONArray("mechanic") != null){
                    JSONArray client = object.getJSONArray("mechanic");
                    int i;



                    for (i=0; i<client.length(); i++) {
                        JSONObject client1 = client.getJSONObject(i);
                        _name = client1.getString("name");
                        _lat = client1.getDouble("lat");
                        _lng = client1.getDouble("lng");

                        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {

                                map = googleMap;
                                MarkerOptions marker = new MarkerOptions();

                                try {

                                    marker.position(new LatLng(client1.getDouble("lat"), client1.getDouble("lng")));
                                    marker.title(String.valueOf(gg(lng,lat,client1.getDouble("lng"),client1.getDouble("lat"))));

                                    if(gg(lng,lat,client1.getDouble("lng"),client1.getDouble("lat"))<50 && j <5){
                                        map.addMarker(marker).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                        j++;

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    }












                }
            }catch (JSONException e){
                e.printStackTrace();

            }

        },error -> {
            error.printStackTrace();


        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                String token = MainActivity._token;
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                return map;
            }


        };

        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        queue.add(request);
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

    public double gg(double lngC,double latC,double lngM,double latM)
    {
    /*    var lon1 = Math.PI * this.lngC/180,
                lat1 = Math.PI * this.latC/180,
                lon2 = Math.PI * response.data[i].lng/180,
                lat2 = Math.PI * response.data[i].lat/180;

        var deltaLat = (lat2 - lat1);
        var deltaLon =(lon2 - lon1);

        var a = Math.pow(Math.sin(deltaLat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(deltaLon/2), 2);
        var c = 2 * Math.asin(Math.sqrt(a));
        var EARTH_RADIUS = 6371;
        var res =c * EARTH_RADIUS;

     */
        double lngC1 = Math.PI *lngC/180;
        double latC1 = Math.PI *latC/180;
        double lngM1 = Math.PI *lngM/180;
        double latM1 = Math.PI *latM/180;
        double deltaLat = (latM1 - latC1);
        double deltaLon =(lngM1 - lngC1);
        double a = Math.pow(Math.sin(deltaLat/2), 2) + Math.cos(latC1) * Math.cos(latM1) * Math.pow(Math.sin(deltaLon/2), 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double EARTH_RADIUS = 6371;
        return c * EARTH_RADIUS;

    }

}