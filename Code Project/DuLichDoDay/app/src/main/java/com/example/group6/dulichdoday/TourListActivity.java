package com.example.group6.dulichdoday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.Adapter.AdapterTour;
import com.example.group6.dulichdoday.Adapter.AdapterTourList;
import com.example.group6.dulichdoday.Adapter.AdapterTours;
import com.example.group6.dulichdoday.Models.Tours;
import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TourListActivity extends AppCompatActivity {
    private ArrayList<Tours> arrayList;

    private AdapterTour adapterTour;
    private RecyclerView recyclerViewTour;
    private ImageView addTour;
    private TextView btnBack;
    private DatabaseReference mData;
    private FirebaseAuth mAuth;
    private String account_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list_layout);

        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewTourList);



        //Lay user
        mData.child(UserInfor.CHILD_USER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInfor user = dataSnapshot.getValue(UserInfor.class);
                if (user.getEmail().equalsIgnoreCase(mAuth.getCurrentUser().getEmail())) {
                    String userID = mAuth.getCurrentUser().getUid();
                    account_ID = userID;
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

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);


        // Mảng thêm
        arrayList = new ArrayList<Tours>();

        adapterTour = new AdapterTour(arrayList, this);
        recyclerViewTour.setAdapter(adapterTour);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

//load tour
        mData.child(Tours.CHILD_TOURS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Tours tours = dataSnapshot.getValue(Tours.class);
                if (tours.getAccount_ID().equalsIgnoreCase(account_ID)) {
                    arrayList.add(new Tours(tours.getTour_ID(), tours.getAccount_ID(), tours.getTourName(), tours.getTourTime(), tours.getTourPrice(), tours.getTourDescription(), tours.getImgTour(), tours.getTenMien()));
                    adapterTour.notifyDataSetChanged();
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

