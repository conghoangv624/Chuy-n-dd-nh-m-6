package com.example.group6.dulichdoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.group6.dulichdoday.Models.Tour;
import com.example.group6.dulichdoday.Adapter.AdapterTour;

import java.util.ArrayList;

public class ManagerTourActivity extends AppCompatActivity {

    private ArrayList<Tour> arrayList;
    private AdapterTour adapterTour;
    private RecyclerView recyclerViewTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_tour_layout);


        recyclerViewTour = (RecyclerView) findViewById(R.id.recyclerViewManager);

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(ManagerTourActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

        // Mảng thêm
        arrayList = new ArrayList<Tour>();

        adapterTour = new AdapterTour(arrayList,getApplicationContext());
        recyclerViewTour.setAdapter(adapterTour);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
    }

}
