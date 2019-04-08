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

public class NewAccountActivity extends AppCompatActivity {


    EditText etInputCode;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        System.setProperty("com.warrenstrange.googleauth.rng.algorithmProvider", "AndroidOpenSSL");
        final GoogleAuthenticator gAuth = new GoogleAuthenticator();

        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        final String sharedKey = key.getKey();
        TextView tvKey = findViewById(R.id.key);
        tvKey.setText(key.getKey());

        etInputCode = findViewById(R.id.input_code);
        btnSubmit = findViewById(R.id.submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Integer code = Integer.parseInt(etInputCode.getText().toString());
                    boolean isCodeValid = gAuth.authorize(sharedKey, code);
                    if(isCodeValid){
                        SharedPreferences sharedPreferences = getSharedPreferences("details", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString("sharedKey",sharedKey).apply();
                        startActivity(new Intent(NewAccountActivity.this,MainActivity.class));
                        Toast.makeText(NewAccountActivity.this,"Registered Successfully!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(NewAccountActivity.this,"Incorrect Code!",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(NewAccountActivity.this,"Enter VALID Code!",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
