package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class LoginOrSignUpActivity extends AppCompatActivity {
    private Button login,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_sign_up);

        login = findViewById(R.id.login_button);
        signUp = findViewById(R.id.signup_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toAskPermission = new Intent(LoginOrSignUpActivity.this, AskPermissons.class);
                startActivity(toAskPermission);
            }
        });
    }
}
