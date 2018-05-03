package com.example.group6.dulichdoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;


import com.example.group6.dulichdoday.Adapter.AdapterTours;
import com.example.group6.dulichdoday.Models.Tours;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManagerTourActivity extends AppCompatActivity {

    private ArrayList<Tours> arrayList;

    private AdapterTours adapterTour;
    private RecyclerView recyclerViewTour;
    private ImageView addTour;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_tour_layout);

        mData = FirebaseDatabase.getInstance().getReference();
        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewManager);
        addTour = (ImageView) findViewById(R.id.img_Add);

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(ManagerTourActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

        // Mảng thêm
        arrayList = new ArrayList<Tours>();

        adapterTour = new AdapterTours(arrayList,this);
        recyclerViewTour.setAdapter(adapterTour);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

//load tour
        mData.child("Tours").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Tours tours = dataSnapshot.getValue(Tours.class);
                arrayList.add(new Tours(tours.getTour_ID(),"1",tours.getTourName(),tours.getTourTime(),tours.getTourPrice(),tours.getTourDescription(),tours.getImgTour(),tours.getTenMien(),tours.isIschecked()));
                adapterTour.notifyDataSetChanged();
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

        addTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inAddTour = new Intent(getBaseContext(), AddPlaceActivity.class);
                startActivity(inAddTour);
            }
        });
        // registerForContextMenu(recyclerViewTour);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterTour.notifyDataSetChanged();
    }

    /*    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.tour_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }*/

/*    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        arrayList.get(menuInfo.position).setIschecked(true);
        switch (item.getItemId()){
            case R.id.menu_edit:
                return true;
            case R.id.menu_delete:
                return true;
                default: return super.onContextItemSelected(item);
        }

    }*/
/*    private void removeItem() {
        for (int i =0; i < arrayList.size();i++){
            if (arrayList.get(i).isIschecked()){
                arrayList.remove(i);
                i--;
            }
        }
        adapterTour.notifyDataSetChanged();
    }*/
}
