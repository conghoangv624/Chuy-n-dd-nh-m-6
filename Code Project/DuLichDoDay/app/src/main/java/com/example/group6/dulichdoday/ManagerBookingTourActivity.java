package com.example.group6.dulichdoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.group6.dulichdoday.Adapter.AdapterBookTour;
import com.example.group6.dulichdoday.Adapter.AdapterTour;
import com.example.group6.dulichdoday.Models.Tour;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManagerBookingTourActivity extends AppCompatActivity {
    private ArrayList<String> arraylistMien;
    private ArrayList<String> arrayListPrice;
    private Spinner spinnerMien;
    private AdapterBookTour adapterBookTour;
    private ArrayList<Tour> arrTour;
    private RecyclerView recyclerViewTour;
    private DatabaseReference mData;
    int posi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_booking_tour_layout);

        mData = FirebaseDatabase.getInstance().getReference();
        spinnerMien = (Spinner) findViewById(R.id.spinnerTour);
        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewTour);
        // Spinner
        arraylistMien = new ArrayList<String>();
        arraylistMien.add("Tất Cả");
        arraylistMien.add("Miền Nam");
        arraylistMien.add("Miền Bắc");
        arraylistMien.add("Miền Trung");

        ArrayAdapter<String> arrayAdapterMien = new ArrayAdapter<String>(this.getBaseContext(), R.layout.support_simple_spinner_dropdown_item, arraylistMien);
        arrayAdapterMien.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMien.setAdapter(arrayAdapterMien);


        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

        arrTour = new ArrayList<Tour>();

        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/mydemo-c9766.appspot.com/o/img_dalat.jpg?alt=media&token=f2348f25-972e-48f1-b4b8-4bf90407f1da","01","Hà Nội - Đà Lạt","7 Ngày 6 Đêm","1.700.000 đ","Miền Nam"));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/mydemo-c9766.appspot.com/o/img_nhatrang.jpg?alt=media&token=a3943d59-2ff7-4ea4-8e85-6385b289708b","02","TP.HCM - Nha Trang","3 Ngày 2 Đêm","1.900.000 đ","Miền Bắc"));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/mydemo-c9766.appspot.com/o/img_vhl.jpg?alt=media&token=eff78159-b024-4a8e-b7c9-d8001c044702","03","Hải Phòng - Vịnh Hạ Long","3 Ngày 2 Đêm","200.000 đ","Miền Bắc"));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/mydemo-c9766.appspot.com/o/nha.jpg?alt=media&token=6b05d90d-df3e-4a44-96c2-e384bf05b7cb","04","TP.HCM - Vũng Tàu","3 Ngày 2 Đêm","250.000 đ","Miền Nam"));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/mydemo-c9766.appspot.com/o/img_4.jpg?alt=media&token=821952da-ba43-40f9-9e0c-0b221c4d289f","05","Đà Lạt - Phan Thiết","3 Ngày 2 Đêm","1.000.000 đ","Miền Trung"));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/mydemo-c9766.appspot.com/o/img_5.jpg?alt=media&token=c773de58-1020-4f73-aafe-514d5cfb5249","06","Hạ Long - Lagi","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung"));


        mData.child("Tour").setValue(arrTour);
        mData.child("Hello");

        loadData();
    }

    private void loadData() {

        arrTour = new ArrayList<Tour>();
        // Set adapter
        adapterBookTour = new AdapterBookTour(arrTour, this.getBaseContext());
        recyclerViewTour.setAdapter(adapterBookTour);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        spinnerMien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arrTour.clear();
                posi = i;
                mData.child("Tour").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        final Tour tour = dataSnapshot.getValue(Tour.class);
                        if (posi == 0) {
                            arrTour.add(new Tour(tour.getImgProduct(), tour.getCodeTour(), tour.getAddTour(), tour.getDiscripTour(), tour.getPriceTour(), tour.getTenmien()));
                            adapterBookTour.notifyDataSetChanged();
                        } else if (posi == 1 && tour.getTenmien().equalsIgnoreCase("Miền Nam")) {
                            arrTour.add(new Tour(tour.getImgProduct(), tour.getCodeTour(), tour.getAddTour(), tour.getDiscripTour(), tour.getPriceTour(), tour.getTenmien()));
                            adapterBookTour.notifyDataSetChanged();
                        } else if (posi == 2 && tour.getTenmien().equalsIgnoreCase("Miền Trung")) {
                            arrTour.add(new Tour(tour.getImgProduct(), tour.getCodeTour(), tour.getAddTour(), tour.getDiscripTour(), tour.getPriceTour(), tour.getTenmien()));
                            adapterBookTour.notifyDataSetChanged();
                        } else if (posi == 3 && tour.getTenmien().equalsIgnoreCase("Miền Bắc")) {
                            arrTour.add(new Tour(tour.getImgProduct(), tour.getCodeTour(), tour.getAddTour(), tour.getDiscripTour(), tour.getPriceTour(), tour.getTenmien()));
                            adapterBookTour.notifyDataSetChanged();
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

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
