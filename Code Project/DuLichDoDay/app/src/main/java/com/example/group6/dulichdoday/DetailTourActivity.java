package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Models.Tours;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailTourActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    private TextView tvDatTour;
    private TextView tvBack;
    private TextView tvCode,tvTime,tvPrice,tvName,tvNoidung;
    private ImageView img;

    private DatabaseReference mData;
    //private ArrayList<Tours> arrTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tour_layout);
        mData = FirebaseDatabase.getInstance().getReference();

        tvDatTour = (TextView) findViewById(R.id.tvDatTour);
        tvBack = (TextView) findViewById(R.id.cancel_detail) ;

        tvCode = (TextView) findViewById(R.id.tvCodeDetail);
        tvName = (TextView) findViewById(R.id.tvNameDetail);
        tvTime = (TextView) findViewById(R.id.tvTimeDetail);
        tvNoidung = (TextView) findViewById(R.id.tvDescriptionTour);
        tvPrice = (TextView) findViewById(R.id.tvPriceDetail);
        img = (ImageView) findViewById(R.id.imgDetail);

        final Dialog dialog = new Dialog(DetailTourActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_accept);
        loadData();
        tvDatTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                TextView tvDat = dialog.findViewById(R.id.tvDat);
                tvDat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //new Tours("","","","","","","","");
                        mData.child("TourDat").push().setValue(new Tours(tvCode.getText().toString(),"",tvName.getText().toString(),tvTime.getText().toString(),tvPrice.getText().toString(),tvNoidung.getText().toString(),"",""));
                        dialog.dismiss();
                        Toast.makeText(DetailTourActivity.this, "Đặt thành công", Toast.LENGTH_SHORT).show();
                        finish();
                        // Intent intent = new Intent(DetailTourActivity.this,MainLayoutActivity.class);
                        //startActivity(intent);
                    }
                });
            }
        });
        //
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData() {
        // Goi qua tu adaptertour
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        bundle.getString("tour");
        mData.child(Tours.CHILD_TOURS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Tours tour = dataSnapshot.getValue(Tours.class);
                if (tour.getTour_ID().equalsIgnoreCase(bundle.getString("tour"))) {
                    Picasso.with(DetailTourActivity.this).load(tour.getImgTour()).into(img);
                    tvCode.setText(tour.getTour_ID());
                    tvName.setText(tour.getTourName());
                    tvTime.setText(tour.getTourTime());
                    tvNoidung.setText(tour.getTourDescription());
                    tvPrice.setText(tour.getTourPrice());
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
