package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import java.io.IOException;

public class bluetooth extends AppCompatActivity {
     BluetoothDevice nn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        nn.getName();
        try {
            BluetoothManager.connect(nn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
