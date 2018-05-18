package com.example.group6.dulichdoday;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailBusinessActivity extends AppCompatActivity {

    private TextView tvUpdate;
    private ImageView BusinessImgUrl;
    private TextView txtBusinessName;
    private TextView txtBusinessEmail;
    private TextView txtBusinessPhone;
    private TextView txtBusinessAddress;


    DatabaseReference mData;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_business_layout);

        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //Load data
        BusinessImgUrl = (ImageView) findViewById(R.id.avatar);
        txtBusinessName = (TextView) findViewById(R.id.txtName);
        txtBusinessEmail = (TextView) findViewById(R.id.txtEmail);
        txtBusinessPhone = (TextView) findViewById(R.id.txtPhone);
        txtBusinessAddress = (TextView) findViewById(R.id.txtAddress);

        tvUpdate = (TextView) findViewById(R.id.btn_update);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUpdate = new Intent(DetailBusinessActivity.this,UpdateInforBusinessActivity.class);
                startActivity(intentUpdate);
            }
        });

        mData.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInfor userInfor = dataSnapshot.getValue(UserInfor.class);
                String useID = mAuth.getCurrentUser().getEmail();
                if (useID.equalsIgnoreCase(userInfor.getEmail())) {
                    txtBusinessName.setText(userInfor.getName());
                    txtBusinessEmail.setText(userInfor.getEmail());
                    txtBusinessPhone.setText(userInfor.getPhoneNumber());
                    txtBusinessAddress.setText(userInfor.getAddress());

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
        });
    }
}
