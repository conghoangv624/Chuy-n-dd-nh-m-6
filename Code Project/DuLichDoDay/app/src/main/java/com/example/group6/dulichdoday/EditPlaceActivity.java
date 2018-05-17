package com.example.group6.dulichdoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Models.Tours;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EditPlaceActivity extends AppCompatActivity {
    Intent intent;
    Bundle bundle;
    DatabaseReference mData;

    ImageView img_choosen;
    RadioButton rabMienNam;
    RadioButton rabMienTrung;
    RadioButton rabMienBac;
    EditText edtTourID;
    EditText edtToutName;
    EditText edtTourTime;
    EditText edtDescript;
    EditText edtTourPrice;
    TextView btnUpdate;
    TextView btnCancel;
    TextView btnGallery;
    TextView btnCamera;

    int PICK_IMAGE_CAMERA = 0;
    int PICK_IMAGE_GALLERY = 1;
    int position;

    ArrayList<Tours> arrTours;
    String tour_ID;
    String account_ID;
    String tourName;
    String tourTime;
    String tourPrice;
    String tourDescription;
    String imgTour;
    String tenMien = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_place_layout);
        Initiation();


        LoadData();
        /*if (arrTours.get(position).getTenMien().equalsIgnoreCase("Miền Nam")) {
            //tenMien = "Miền Nam";
            rabMienNam.setChecked(true);
        }
        if (arrTours.get(position).getTenMien().equalsIgnoreCase("Miền Trung")) {
            //tenMien = "Miền Trung";
            rabMienTrung.setChecked(true);
        }
        if (arrTours.get(position).getTenMien().equalsIgnoreCase("Miền Bắc")) {
            //tenMien = "Miền Bắc";
            rabMienBac.setChecked(true);
        }
        edtTourID.setText(arrTours.get(position).getTour_ID());
        edtToutName.setText(arrTours.get(position).getTourName());
        edtTourTime.setText(arrTours.get(position).getTourTime());
        edtDescript.setText(arrTours.get(position).getTourDescription());
        edtTourPrice.setText(arrTours.get(position).getTourPrice());*/


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rabMienNam.isChecked()) {
                    //tenMien = "Miền Nam";
                    arrTours.get(position).setTenMien(Tours.MIEN_NAM);
                }
                if (rabMienTrung.isChecked()) {
                    //tenMien = "Miền Trung";
                    arrTours.get(position).setTenMien(Tours.MIEN_TRUNG);
                }
                if (rabMienBac.isChecked()) {
                    //tenMien = "Miền Bắc";
                    arrTours.get(position).setTenMien(Tours.MIEN_BAC);
                }
                arrTours.get(position).setTourName(edtToutName.getText().toString());
                arrTours.get(position).setTourTime(edtTourTime.getText().toString());
                arrTours.get(position).setTourDescription(edtDescript.getText().toString());
                arrTours.get(position).setTourPrice(edtTourPrice.getText().toString());

                mData.child("Tours").setValue(arrTours, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            Toast.makeText(EditPlaceActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(EditPlaceActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            edtTourID.setText("");
                            edtToutName.setText("");
                            edtTourTime.setText("");
                            edtDescript.setText("");
                            edtTourPrice.setText("");
                        }
                    }
                });
            }
        });

    }
    private void LoadData(){
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        bundle.getString("tours");

        mData.child(Tours.CHILD_TOURS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Tours tours = dataSnapshot.getValue(Tours.class);
                arrTours.add(new Tours(tours.getTour_ID(), tours.getAccount_ID(), tours.getTourName(), tours.getTourTime(), tours.getTourPrice(), tours.getTourDescription(), tours.getImgTour(), tours.getTenMien()));
                for (int i = 0;i< arrTours.size();i++) {
                    if (arrTours.get(i).getTour_ID().equalsIgnoreCase(bundle.getString("tours"))) {
                        position = i;
                        if (arrTours.get(i).getTenMien().equalsIgnoreCase(Tours.MIEN_NAM)) {
                            //tenMien = "Miền Nam";
                            rabMienNam.setChecked(true);
                        }
                        if (arrTours.get(i).getTenMien().equalsIgnoreCase(Tours.MIEN_TRUNG)) {
                            //tenMien = "Miền Trung";
                            rabMienTrung.setChecked(true);
                        }
                        if (arrTours.get(i).getTenMien().equalsIgnoreCase(Tours.MIEN_BAC)) {
                            //tenMien = "Miền Bắc";
                            rabMienBac.setChecked(true);
                        }
                        edtTourID.setText(arrTours.get(i).getTour_ID());
                        edtToutName.setText(arrTours.get(i).getTourName());
                        edtTourTime.setText(arrTours.get(i).getTourTime());
                        edtDescript.setText(arrTours.get(i).getTourDescription());
                        edtTourPrice.setText(arrTours.get(i).getTourPrice());
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
        });
    }
    private void Initiation() {
        img_choosen = (ImageView) findViewById(R.id.img_choosen);
        rabMienNam = (RadioButton) findViewById(R.id.rabMienNam);
        rabMienTrung = (RadioButton) findViewById(R.id.rabMienTrung);
        rabMienBac = (RadioButton) findViewById(R.id.rabMienBac);
        edtTourID = (EditText) findViewById(R.id.tourID);
        edtToutName = (EditText) findViewById(R.id.tourTitle);
        edtTourTime = (EditText) findViewById(R.id.tourTime);
        edtDescript = (EditText) findViewById(R.id.tourDescript);
        edtTourPrice = (EditText) findViewById(R.id.tourPrice);
        btnUpdate = (TextView) findViewById(R.id.btnUpdate);

        mData = FirebaseDatabase.getInstance().getReference();

        arrTours = new ArrayList<Tours>();
    }
}
