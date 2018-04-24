package com.example.group6.dulichdoday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.group6.dulichdoday.Adapter.AdapterTourList;
import com.example.group6.dulichdoday.Models.Tour;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TourListActivity extends AppCompatActivity {
    private AdapterTourList adapterTourList;
    private ArrayList<Tour> arrTour;
    private RecyclerView recyclerViewTour;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list_layout);
        mData = FirebaseDatabase.getInstance().getReference();
        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewTourDat);

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

        //arrTour = new ArrayList<Tour>();
        //mData.child("TourDat").setValue(arrTour);

        loadData();
    }
    private void loadData() {
        arrTour = new ArrayList<Tour>();
        // Set adapter
        adapterTourList = new AdapterTourList(arrTour,this);
        recyclerViewTour.setAdapter(adapterTourList);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        mData.child("TourDat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Tour tour = dataSnapshot.getValue(Tour.class);
                arrTour.add(new Tour(tour.getImgProduct(),tour.getCodeTour(),tour.getAddTour(),tour.getDiscripTour(),tour.getPriceTour(),tour.getTenmien(),tour.getNoidung()));
                adapterTourList.notifyDataSetChanged();
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

