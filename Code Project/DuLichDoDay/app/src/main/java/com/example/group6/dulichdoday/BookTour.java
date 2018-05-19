package com.example.group6.dulichdoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.group6.dulichdoday.Adapter.AdapterBookTour;
import com.example.group6.dulichdoday.Adapter.AdapterTourList;
import com.example.group6.dulichdoday.Adapter.AdapterTours;
import com.example.group6.dulichdoday.Models.TourBooking;
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

public class BookTour extends AppCompatActivity {
    private static final String TAG = null;
    //private AdapterTours adapterTour;
    private ArrayList<Tours> arrTour;
    private ArrayList<TourBooking> arrTourBook;
    private RecyclerView recyclerViewTour;
    private DatabaseReference mData;
    FirebaseAuth mAuth;
    String tour_ID;
    String account_ID;
    private AdapterBookTour adapterTourList;


/*    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Query applesQuery = ref.child("Tour_Book").orderByChild("title").equalTo("codeTour");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_tour);
        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewTourBook);

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

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
        arrTour = new ArrayList<Tours>();
        // Set adapter
        adapterTourList = new AdapterBookTour(arrTour, this);
        recyclerViewTour.setAdapter(adapterTourList);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        //Lay book tour
        mData.child(TourBooking.CHILD_BOOK_TOUR).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final TourBooking tourBooking = dataSnapshot.getValue(TourBooking.class);
                if (tourBooking.getAccount_ID().equalsIgnoreCase(account_ID)) {
                    //arrTourBook.add(new TourBooking(tourBooking.getAccount_ID(), tourBooking.getTour_ID()));
                    //tour_ID = tourBooking.getTour_ID();
                    //loadData();

                    mData.child(Tours.CHILD_TOURS).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            final Tours tour = dataSnapshot.getValue(Tours.class);
                            //arrTour.add(new Tours(tour.getTour_ID(),tour.getAccount_ID(),tour.getTourName(),tour.getTourTime(),tour.getTourPrice(),tour.getTourDescription(),tour.getImgTour(),tour.getTenMien()));


                            if (tour.getTour_ID().equalsIgnoreCase(tourBooking.getTour_ID())) {
                                arrTour.add(new Tours(tour.getTour_ID(), tour.getAccount_ID(), tour.getTourName(), tour.getTourTime(), tour.getTourPrice(), tour.getTourDescription(), tour.getImgTour(), tour.getTenMien()));
                                adapterTourList.notifyDataSetChanged();
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

        //loadtour
        //loadData();
    }

    private void loadData() {
        arrTour = new ArrayList<Tours>();
        // Set adapter
        adapterTourList = new AdapterBookTour(arrTour, this);
        recyclerViewTour.setAdapter(adapterTourList);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        mData.child(Tours.CHILD_TOURS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Tours tour = dataSnapshot.getValue(Tours.class);
                if (tour.getTour_ID().equalsIgnoreCase(tour_ID)) {
                    arrTour.add(new Tours(tour.getTour_ID(), tour.getAccount_ID(), tour.getTourName(), tour.getTourTime(), tour.getTourPrice(), tour.getTourDescription(), tour.getImgTour(), tour.getTenMien()));
                    adapterTourList.notifyDataSetChanged();
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


        /*applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
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

        });*/
    }
}
