package com.example.myapplication.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.NavigationMenuListAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.services.ForegroundNotificationService;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    private final String TAG = "MAIN_ACTIVITY";
    String menuList[] = {"About us", "Settings"};
    Boolean mSlideState = false;
    Toolbar toolbar;
    HomeFragment homeFragment;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.apply();
            startActivity(new Intent(MainActivity.this, AskPermissions.class));
        } else {
            initView();
            toolbar.setVisibility(View.VISIBLE);
            switchToHome();
            Log.d(TAG, "onCreate: MAIN ACTIVITY");
        }
    }

    public void initView() {

        /*mainBinding.navView.setNavigationItemSelectedListener(item ->{
            switch (item.getItemId()) {
                case R.id.nav_about_us:
                    //my appointment intent
                    break;

                case R.id.nav_settings:
                    //settings intent;
                    break;
            }
            return false;
        });*/

        NavigationMenuListAdapter navigationMenuListAdapter = new NavigationMenuListAdapter(this, R.layout.side_navigation_item, R.id.menu_item_name, menuList);
        mainBinding.navMenuList.setAdapter(navigationMenuListAdapter);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mainBinding.drawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mSlideState = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mSlideState = false;
            }
        };
        mainBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_24dp, null));
            toolbar.setNavigationOnClickListener(v -> {
                if (mSlideState) {
                    mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
                }
                else {
                    mainBinding.drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void switchToHome() {

        toolbar.setVisibility(View.VISIBLE);
        if(homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame_layout, homeFragment, "HOME FRAGMENT");
        ft.commit();

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_24dp));
    }
}

