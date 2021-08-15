package com.example.carapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.Circle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static java.security.AccessController.getContext;

public class Profile extends AppCompatActivity {
   TextView t2,t3,t4,t5,t6,t7,t8;
   ImageView I1;
   Button log;
    public static String _name;
    public static String _email;
    public static String _number;
    public static String _model;
    public static String _make;
    public static String _series;
    public static String _picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        GetC();
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();


                BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
                bottomNavigationView.setSelectedItemId(R.id.profileIm);
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.requestm:
                                startActivity(new Intent(getApplicationContext(),requestm.class));
                                overridePendingTransition(0,0);
                                return true;
                            case R.id.profileIm:
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
                t3 = findViewById(R.id.nameC);
                t4 = findViewById(R.id.emailC);
                t5 = findViewById(R.id.numberC);
                t6 = findViewById(R.id.modelC);
                t7 = findViewById(R.id.makeC);
                t8 = findViewById(R.id.seriesC);
                I1 = findViewById(R.id.imageView5);
                log = findViewById(R.id.buttonL);
                log.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToM();

                    }
                });

            }
    public void ToM()
    {
        MainActivity.IsLogin = false;
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
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
                    _name = client1.getString("name");
                    _email = client1.getString("email");
                    _number =client1.getString("Numéro");
                    _picture = client1.getString("picture");
                    _model = client1.getString("model");
                    _make = client1.getString("make");
                    _series = client1.getString("séries");

                    Picasso.get().load(Constant.URL+"images/"+_picture).resize(250, 250).centerCrop().transform(new RoundedCornersTransformation(1500,10)).into(I1);


                    t2.setText(_name);
                    t3.setText("Full Name : "+_name);
                    t4.setText("Email : "+_email);
                    t5.setText("Number phone : "+_number );
                    t6.setText("Car model : "+_model);
                    t7.setText("Car make : "+_make);
                    t8.setText("Car séries : "+_series);



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

