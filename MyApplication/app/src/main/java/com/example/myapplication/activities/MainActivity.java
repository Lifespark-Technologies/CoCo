package com.example.myapplication.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.judemanutd.autostarter.AutoStartPermissionHelper;

public class MainActivity extends AppCompatActivity {

    private Button reportBtn;
    private Button registerBtn;
    private Button autoStart;
    final int LAUNCH_NEXT_ACTIVITY = 1;
    static final int ASK_PERMISSIONS = 10;
    private final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onStart() {
        super.onStart();
        if (!checkPermissions()) {
            requestPermissions();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reportBtn = findViewById(R.id.report_button);
        registerBtn = findViewById(R.id.register_button);
        autoStart = findViewById(R.id.auto_start);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //make register buttono invisible if user has registered his name
        if(prefs.contains("name")) {
            registerBtn.setVisibility(View.INVISIBLE);
        }

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        autoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoStartPermissionHelper.getInstance().getAutoStartPermission(MainActivity.this);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkPermissions()) {
                    requestPermissions();
                }
                else {
                    Intent intent2 = new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivityForResult(intent2, LAUNCH_NEXT_ACTIVITY);
                }
            }
        });
    }

    //checks whether permissions have been granted or not
    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    //requests permissions from the user
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                ASK_PERMISSIONS
        );
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_NEXT_ACTIVITY){
            if (resultCode == RESULT_OK){
                String user = data.getExtras().getString("name");
                registerBtn.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show();
                Log.d(TAG, "Registration complete!!");
                //user has been registered
            }
        }
    }
}

