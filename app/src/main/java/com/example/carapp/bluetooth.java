package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class bluetooth extends AppCompatActivity {
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        t1 =findViewById(R.id.bl);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


    }


}
