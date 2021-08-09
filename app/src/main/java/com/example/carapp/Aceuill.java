package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        t1 =findViewById(R.id.textView22);
        t1.setText("Welcome "+MainActivity._name);
       b1=findViewById(R.id.button3);

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

}