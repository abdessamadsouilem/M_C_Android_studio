package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;

public class bluetooth extends AppCompatActivity {
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        t1 =findViewById(R.id.bl);
        BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {

          //  Toast.makeText(this, "At least one paired bluetooth device found", Toast.LENGTH_SHORT).show();

            // TODO at this point you'd have to iterate these devices and check if any of them is a wearable (HOW?)
            for (BluetoothDevice device : pairedDevices) {
                Toast.makeText(this, "At least one paired bluetooth device found" +device.getName() , Toast.LENGTH_SHORT).show();
                //Log.d("YOUR_TAG", "Paired device: "+ device.getName() + ", with address: " + device.getAddress());
            }
        } else {
            Toast.makeText(this, "No paired bluetooth devices found", Toast.LENGTH_SHORT).show();
        }
    }


}
