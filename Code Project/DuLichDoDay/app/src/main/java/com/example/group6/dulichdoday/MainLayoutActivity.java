package com.example.group6.dulichdoday;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.security.Key;

public class MainLayoutActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RadioButton rb1;
    private RadioButton rb2;
    private TextView User;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment fragmentHome = new HomeFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.content,fragmentHome,"Fragment");
                    fragmentTransaction1.commit();
                    return true;
                case R.id.navigation_new:
                    NewFragment fragmentNew = new NewFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.content,fragmentNew,"Fragment");
                    fragmentTransaction2.commit();
                    return true;
                case R.id.navigation_tour:
                    TourFragment fragmentTour = new TourFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.content,fragmentTour,"Fragment");
                    fragmentTransaction3.commit();
                    return true;
                case R.id.navigation_users:

                        UserFragment userFragment = new UserFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction4.replace(R.id.content, userFragment, "Fragment");
                        fragmentTransaction4.commit();

                        /*UserBusinessFragment userBusinessFragment = new UserBusinessFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction5.replace(R.id.content, userBusinessFragment, "Fragment");
                        fragmentTransaction5.commit();*/

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment fragmentHome = new HomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.content,fragmentHome,"Fragment");
        fragmentTransaction1.commit();
        rb1 = (RadioButton) findViewById(R.id.rdb1);
        rb2 = (RadioButton) findViewById(R.id.rdb2);

    }

}
