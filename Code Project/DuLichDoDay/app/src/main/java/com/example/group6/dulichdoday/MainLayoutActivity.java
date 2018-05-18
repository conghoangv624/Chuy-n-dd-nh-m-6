package com.example.group6.dulichdoday;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainLayoutActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView UserInfo;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDataUser = FirebaseDatabase.getInstance().getReference();
    //
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

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
                    mData.child("Users").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            UserInfor userInfor = dataSnapshot.getValue(UserInfor.class);
                            if (userInfor.getEmail().equalsIgnoreCase(mAuth.getCurrentUser().getEmail())) {
                                if (userInfor.getUserType().equalsIgnoreCase("Thành viên")) {
                                    UserFragment fragmentUser = new UserFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction4.replace(R.id.content,fragmentUser,"Fragment");
                                    fragmentTransaction4.commit();
                                } else if (userInfor.getUserType().equalsIgnoreCase("Quản lý")) {
                                    UserBusinessFragment fragmentUserBusiness = new UserBusinessFragment();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction5.replace(R.id.content,fragmentUserBusiness,"Fragment");
                                    fragmentTransaction5.commit();
                                }
                            }
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }) ;
                    return true;
            }
            return false;
        }
    };

    public static Context contextOfApplication;
    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

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
      /*  rb1 = (RadioButton) findViewById(R.id.rdb1);
        rb2 = (RadioButton) findViewById(R.id.rdb2);*/

    }


}
