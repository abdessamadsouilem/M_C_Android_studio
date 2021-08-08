package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                ToActivityBluetooth();
            }
        });


    }
    public void ToActivityBluetooth()
    {
        Intent switchActivityIntent = new Intent(this, bluetooth.class);
        startActivity(switchActivityIntent);
    }

}