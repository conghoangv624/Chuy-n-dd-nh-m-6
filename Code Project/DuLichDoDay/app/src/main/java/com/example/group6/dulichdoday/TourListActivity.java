package com.example.group6.dulichdoday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.group6.dulichdoday.Adapter.AdapterTourList;
import com.example.group6.dulichdoday.Models.Tours;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TourListActivity extends AppCompatActivity {
    private static final String TAG = null;
    private AdapterTourList adapterTourList;
    private ArrayList<Tours> arrTour;
    private RecyclerView recyclerViewTour;
    private DatabaseReference mData;



    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Query applesQuery = ref.child("TourDat").orderByChild("title").equalTo("codeTour");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list_layout);
        mData = FirebaseDatabase.getInstance().getReference();
        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewTourDat);

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

        //arrTour = new ArrayList<Tour>();
        //mData.child("TourDat").setValue(arrTour);

        loadData();
    }

    private void loadData() {
        arrTour = new ArrayList<Tours>();
        // Set adapter
        adapterTourList = new AdapterTourList(arrTour, this);
        recyclerViewTour.setAdapter(adapterTourList);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        mData.child("TourDat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Tours tours = dataSnapshot.getValue(Tours.class);
                arrTour.add(new Tours(tours.getTour_ID(), tours.getAccount_ID(), tours.getTourName(), tours.getTourTime(), tours.getTourPrice(), tours.getTourDescription(), tours.getImgTour(), tours.getTenMien()));
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


        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }

        });
    }

}

