package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;

public class LoginOrSignUpActivity extends AppCompatActivity {
    Button Login,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_sign_up);

        initViews();
        login();
    }

    private void initViews(){
        Login = findViewById(R.id.login_button);
        signUp = findViewById(R.id.signup_button);
    }

    private void login(){
        Intent toAskPermissions = new Intent(LoginOrSignUpActivity.this,AskPermissons.class);
        startActivity(toAskPermissions);
    }
}
