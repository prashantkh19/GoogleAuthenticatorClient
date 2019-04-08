package com.example.googleauthenticatorclient;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    EditText etInputCode;
    Button btnSubmit;
    TextView tvNewAccount ,tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputCode = findViewById(R.id.input_code);
        btnSubmit = findViewById(R.id.submit);
        tvNewAccount = findViewById(R.id.new_account);
        tvStatus = findViewById(R.id.status);

        SharedPreferences sharedPreferences = getSharedPreferences("details", Context.MODE_PRIVATE);
        if(sharedPreferences.getString("sharedKey","").isEmpty()){
            //intent to new account activity
            startActivity(new Intent(this,NewAccountActivity.class));
        }
        final String sharedKey = sharedPreferences.getString("sharedKey","");

        System.setProperty("com.warrenstrange.googleauth.rng.algorithmProvider", "AndroidOpenSSL");
        final GoogleAuthenticator gAuth = new GoogleAuthenticator();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Integer code = Integer.parseInt(etInputCode.getText().toString());
                    boolean isCodeValid = gAuth.authorize(sharedKey, code);
                    if(isCodeValid){
                        tvStatus.setText("Verified");
                    }else {
                        tvStatus.setText("Failed");
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"Enter VALID Code!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewAccountActivity.class));
            }
        });

        /*utility*/
//        try {
//            Provider p[] = Security.getProviders();
//            for (int i = 0; i < p.length; i++) {
//                System.out.println("Providers  - "+p[i].getName());
//                for (Enumeration e = p[i].keys(); e.hasMoreElements();)
//                    System.out.println("\t" + e.nextElement());
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }

    }
}
