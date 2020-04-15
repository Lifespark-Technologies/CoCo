package com.example.myapplication.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.receivers.BootReceiver;
import com.example.myapplication.services.ForegroundNotificationService;
import com.example.myapplication.R;

import java.io.File;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName;
    private String name;
    private Button submit;
    File file;

    @Override
    protected void onStart() {
        super.onStart();

        // takes user to enable gps in settings on start
        if (!isLocationEnabled()) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = prefs.edit();
        userName = findViewById(R.id.user_name);
        submit = findViewById(R.id.submit_button);
        file = new File(getFilesDir(), "location.txt");
        final Intent intent = new Intent(this, ForegroundNotificationService.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = userName.getText().toString();
                editor.putString("name", name).apply();
                Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
                if (name != null) {

                    //foreground service starts
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        startForegroundService(intent);
                    } else {
                        startService(intent);
                    }
                    Bundle b = new Bundle();
                    b.putString("name", name);
                    returnIntent.putExtras(b);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                    //registration complete
                }
            }
        });
    }

    //check if location is enabled in settings
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
