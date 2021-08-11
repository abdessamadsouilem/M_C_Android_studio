package com.example.carapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class requestm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestm);
        getSupportActionBar().hide();

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.requestm);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.requestm:

                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.aceuill:
                        startActivity(new Intent(getApplicationContext(),Aceuill.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
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