package com.example.group6.dulichdoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Spinner;

import com.example.group6.dulichdoday.Adapter.AdapterBookTour;
import com.example.group6.dulichdoday.Adapter.AdapterManagerBookTour;
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

public class ManagerBookingTourActivity extends AppCompatActivity {

    private static final String TAG = null;
    //private AdapterTours adapterTour;
    private ArrayList<Tours> arrTour;
    private ArrayList<UserInfor> arrAllUser;
    private RecyclerView recyclerViewTour;
    private DatabaseReference mData;
    FirebaseAuth mAuth;
    String tour_ID;
    String account_ID;
    private AdapterManagerBookTour adapterTourList;


    //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    //Query applesQuery = ref.child("TourDat").orderByChild("title").equalTo("codeTour");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_booking_tour_layout);

        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewManagerBookTour);

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);
        arrTour = new ArrayList<Tours>();
        arrAllUser = new ArrayList<UserInfor>();
        // Set adapter
        adapterTourList = new AdapterManagerBookTour(arrTour,arrAllUser, this);
        recyclerViewTour.setAdapter(adapterTourList);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        //Lay user người dùng hiện tại
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

        //Lay book tour
        mData.child(TourBooking.CHILD_BOOK_TOUR).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final TourBooking tourBooking = dataSnapshot.getValue(TourBooking.class);
                //if (tourBooking.getAccount_ID().equalsIgnoreCase(account_ID)) {
                //arrTourBook.add(new TourBooking(tourBooking.getAccount_ID(), tourBooking.getTour_ID()));
                //tour_ID = tourBooking.getTour_ID();
                //loadData();
                //Lay thong tin tour
                mData.child(Tours.CHILD_TOURS).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        final Tours tour = dataSnapshot.getValue(Tours.class);
                        //arrTour.add(new Tours(tour.getTour_ID(),tour.getAccount_ID(),tour.getTourName(),tour.getTourTime(),tour.getTourPrice(),tour.getTourDescription(),tour.getImgTour(),tour.getTenMien()));


                        if (tour.getAccount_ID().equalsIgnoreCase(account_ID) && tour.getTour_ID().equalsIgnoreCase(tourBooking.getTour_ID())) {

                            // Lay all user
                            mData.child(UserInfor.CHILD_USER).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    UserInfor user = dataSnapshot.getValue(UserInfor.class);
                                    if (user.getUserID().equalsIgnoreCase(tourBooking.getAccount_ID())){
                                        arrAllUser.add(new UserInfor(user.getAddress(), user.getEmail(), user.getPass(), user.getUserID(), user.getName(), user.getPhoneNumber(), user.getImageUrl(), user.getUserType(), user.getDate(), user.getSex()));
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
                //}
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

        //loadData();
    }

    private void loadData() {

        mData.child(Tours.CHILD_TOURS).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Tours tour = dataSnapshot.getValue(Tours.class);
                if (tour.getAccount_ID().equalsIgnoreCase(account_ID)) {
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


/*        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
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
