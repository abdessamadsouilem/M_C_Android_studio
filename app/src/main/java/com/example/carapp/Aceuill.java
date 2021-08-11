package com.example.carapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;

import static androidx.navigation.Navigation.findNavController;

public class Aceuill extends AppCompatActivity {

    TextView t1;
    Button b1;
    String name;



    public void dd()
    {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
    public void ff()
    {
        Intent switchActivityIntent = new Intent(this, Aceuill.class);
        startActivity(switchActivityIntent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(MainActivity.IsLogin==true)
        {
            ff();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceuill);
        getSupportActionBar().hide();
        // hide the title bar



        t1 =findViewById(R.id.textView22);
        t1.setText("Welcome "+MainActivity._name);
       b1=findViewById(R.id.button3);

    BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
    bottomNavigationView.setSelectedItemId(R.id.aceuill);
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.requestm:
                    startActivity(new Intent(getApplicationContext(),requestm.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(),Profile.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.aceuill:

                    return true;
            }
            return false;
        }
    });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter == null) {
                    // Device does not support Bluetooth
                    b1.setEnabled(false);
                } else if (!mBluetoothAdapter.isEnabled()) {
                    // Bluetooth is not enabled :)
                    if(!mBluetoothAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned OFF",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Bluetooth is enabled
                    Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();
                    ToActivityBluetooth();
                }

            }
        });

        }










    public void ToActivityBluetooth()
    {
        Intent switchActivityIntent = new Intent(this, bluetooth.class);
        startActivity(switchActivityIntent);
    }

    public void ToReq()
    {
        Intent switchActivityIntent = new Intent(this, requestm.class);
        startActivity(switchActivityIntent);
    }
    public void ToReq1()
    {
        Intent switchActivityIntent = new Intent(this, Profile.class);
        startActivity(switchActivityIntent);
    }
    public void ToReq2()
    {
        Intent switchActivityIntent = new Intent(this, Aceuill.class);
        startActivity(switchActivityIntent);
    }

}