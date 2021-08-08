package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    Button b1;
    EditText email,pass,name,con,phone;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b1 = findViewById(R.id.up1);
        email = findViewById(R.id.email1);
        name = findViewById(R.id.name);
        con = findViewById(R.id.conpass1);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass1);
        t1 = findViewById(R.id.textView3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               register();
            }
        });
    }

    private  void register()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.REGISTER, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getJSONObject("user") != null){
                    t1.setText("nnnnnn");
                    Intent switchActivityIntent = new Intent(this, MainActivity.class);
                    startActivity(switchActivityIntent);
                }
            }catch (JSONException e){
                e.printStackTrace();

            }

        },error -> {
            error.printStackTrace();
           t1.setText("ddddddd");
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("name",name.getText().toString().trim());
                map.put("email",email.getText().toString().trim());
                map.put("password",pass.getText().toString());
                map.put("password_confirmation",con.getText().toString());
                map.put("numéro",phone.getText().toString());
                map.put("role","client");
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        queue.add(request);
    }
}