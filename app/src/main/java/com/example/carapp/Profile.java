package com.example.carapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
   TextView t2;
    public static String _model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

                BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
                bottomNavigationView.setSelectedItemId(R.id.profile);
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.requestm:
                                startActivity(new Intent(getApplicationContext(),requestm.class));
                                overridePendingTransition(0,0);
                                return true;
                            case R.id.profile:
                                GetC();
                                return true;
                            case R.id.aceuill:
                                startActivity(new Intent(getApplicationContext(),Aceuill.class));
                                overridePendingTransition(0,0);
                                return true;
                        }
                        return false;
                    }
                });
                t2 = findViewById(R.id.textView8);
                GetC();
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
    private  void GetC()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.GetC, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getJSONArray("client") != null){
                    JSONArray client = object.getJSONArray("client");
                    JSONObject client1 = client.getJSONObject(0);
                    _model = client1.getString("name");

                    t2.setText(_model);


                }
            }catch (JSONException e){
                e.printStackTrace();
                t2.setText("no");
            }

        },error -> {
            error.printStackTrace();
            t2.setText("noo");

        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                String token = MainActivity._token;
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                return map;
            }

            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                String id = MainActivity._id;
                HashMap<String,String> map = new HashMap<>();
                map.put("user_id",id);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        queue.add(request);
    }


        }

