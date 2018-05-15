package com.example.group6.dulichdoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailPersonalActivity extends AppCompatActivity {

    private TextView tvUpdate;
    private ImageView PresonalImgUrl;
    private TextView txtPresonalName;
    private TextView txtPresonalDate;
    private TextView txtPresonalSex;
    private TextView txtPresonalPhone;
    private TextView txtPresonalAddress;

    DatabaseReference mData;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_personal_layout);

        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //Load data
        PresonalImgUrl = (ImageView) findViewById(R.id.imagePresonal);
        txtPresonalName = (TextView) findViewById(R.id.edtPresonalName);
        txtPresonalDate = (TextView) findViewById(R.id.edtPresonalDate);
        txtPresonalSex = (TextView) findViewById(R.id.edtPresonalSex);
        txtPresonalPhone = (TextView) findViewById(R.id.edtPresonalPhone);
        txtPresonalAddress = (TextView) findViewById(R.id.edtPresonalAddress);

        tvUpdate = (TextView) findViewById(R.id.btn_update);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUpdate = new Intent(DetailPersonalActivity.this,UpdateInforActivity.class);
                startActivity(intentUpdate);

                mData.child("Users").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        final UserInfor userInfor = dataSnapshot.getValue(UserInfor.class);
                        if (mAuth.getCurrentUser().getEmail().equalsIgnoreCase(userInfor.getEmail())) {
                            tvUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mData.child("Users").child(userInfor.getUserID()).child("Name").setValue(txtPresonalName.getText().toString());
                                    mData.child("Users").child(userInfor.getUserID()).child("Date").setValue(txtPresonalDate.getText().toString());
                                    mData.child("Users").child(userInfor.getUserID()).child("Sex").setValue(txtPresonalSex.getText().toString());
                                    mData.child("Users").child(userInfor.getUserID()).child("Phone").setValue(txtPresonalPhone.getText().toString());
                                    mData.child("Users").child(userInfor.getUserID()).child("address").setValue(txtPresonalAddress.getText().toString());
                                }
                            });
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
        });


    }
}
