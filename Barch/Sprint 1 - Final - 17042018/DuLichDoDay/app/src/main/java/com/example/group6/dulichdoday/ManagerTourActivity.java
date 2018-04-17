package com.example.group6.dulichdoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.group6.dulichdoday.Models.Tour;
import com.example.group6.dulichdoday.Adapter.AdapterTour;

import java.util.ArrayList;

public class ManagerTourActivity extends AppCompatActivity {

    private ArrayList<Tour> arrayList;
    private AdapterTour adapterTour;
    private RecyclerView recyclerViewTour;
    private ImageView addTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_tour_layout);


        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewManager);
        addTour = (ImageView)findViewById(R.id.img_Add) ;

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(ManagerTourActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

        // Mảng thêm
        arrayList = new ArrayList<Tour>();

        adapterTour = new AdapterTour(arrayList,getApplicationContext());
        recyclerViewTour.setAdapter(adapterTour);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        addTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inAddTour = new Intent(getBaseContext(),AddPlaceActivity.class);
                startActivity(inAddTour);
            }
        });
    }

}
