package com.example.carapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {
    Button b1;
    EditText email,pass;
    TextView t1;


    public static String _name;
    public static String _email;
    public static String _role;
    public static String _id;
    public static String _Numéro;
    public static String _token;
    public static boolean  IsLogin = false;

    public void ff()
    {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
    public void onBackPressed() {
        super.onBackPressed();
        if(MainActivity.IsLogin==false)
        {
            ff();
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // hide the title bar
        email = findViewById(R.id.login);
        pass = findViewById(R.id.pass);
        b1 = findViewById(R.id.button);
         t1 = findViewById(R.id.Sign);


                 t1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
       signup();
             }
         });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
login();
            }
        });


    }
    private  void  signup(){
        Intent switchActivityIntent = new Intent(this, Signup.class);
        startActivity(switchActivityIntent);
    }
    private  void login()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.LOGIN,response -> {
            try {
                JSONObject object = new JSONObject(response);
                if (object.getJSONObject("user") != null){
                    String token = object.getString("token");
                    JSONObject user = object.getJSONObject("user");

                    IsLogin = true;
                    _name=user.getString("name");
                    _email=user.getString("email");
                    _role=user.getString("role");
                    _id=user.getString("id");
                    _Numéro=user.getString("Numéro");
                    _token = token;
                    Intent switchActivityIntent = new Intent(this, Aceuill.class);
                    startActivity(switchActivityIntent);
                }
            }catch (JSONException e){
                e.printStackTrace();

            }

        },error -> {
        error.printStackTrace();

        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("email",email.getText().toString().trim());
                map.put("password",pass.getText().toString());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        queue.add(request);
    }
}