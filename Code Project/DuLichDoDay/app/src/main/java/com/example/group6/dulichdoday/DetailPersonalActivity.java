package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class DetailPersonalActivity extends AppCompatActivity {

    private TextView tvUpdate;
    private TextView tvback;
    private ImageView imgPresonalUrl;
    private TextView txtPresonalName;
    private TextView txtPresonalDate;
    private TextView txtPresonalSex;
    private TextView txtPresonalPhone;
    private TextView txtPresonalAddress;
    private ImageView imgHinh;
    Dialog dialog;

    DatabaseReference mData;
    FirebaseAuth mAuth;

    //firebaseImage
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    int REQUEST_CODE_IMAGE = 1;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_personal_layout);

        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //Load data
        imgPresonalUrl = (ImageView) findViewById(R.id.imagePresonal);
        txtPresonalName = (TextView) findViewById(R.id.edtPresonalName);
        txtPresonalDate = (TextView) findViewById(R.id.edtPresonalDate);
        txtPresonalSex = (TextView) findViewById(R.id.edtPresonalSex);
        txtPresonalPhone = (TextView) findViewById(R.id.edtPresonalPhone);
        txtPresonalAddress = (TextView) findViewById(R.id.edtPresonalAddress);


        tvUpdate = (TextView) findViewById(R.id.btnUpdateInfo);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUpdate = new Intent(DetailPersonalActivity.this,UpdateInforActivity.class);
                startActivity(intentUpdate);
            }
        });

        mData.child(UserInfor.CHILD_USER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInfor userInfor = dataSnapshot.getValue(UserInfor.class);
                String useID = mAuth.getCurrentUser().getEmail();
                if (useID.equalsIgnoreCase(userInfor.getEmail())) {
                    txtPresonalName.setText(userInfor.getName());
                    txtPresonalDate.setText(userInfor.getDate());
                    txtPresonalSex.setText(userInfor.getSex());
                    txtPresonalPhone.setText(userInfor.getPhoneNumber());
                    txtPresonalAddress.setText(userInfor.getAddress());
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
        tvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
