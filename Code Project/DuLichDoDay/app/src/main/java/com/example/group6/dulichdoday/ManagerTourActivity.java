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
        arrayList.add(new Tour(R.mipmap.img_nhatrang,"Mã tour : 01","TP.HCM - Nha Trang","3 ngày 2 đêm","560.000 đ","26","24","11"));
        arrayList.add(new Tour(R.mipmap.img1,"Mã tour : 02","TP.HCM - Đà Lạt","3 ngày 2 đêm","560.000 đ","26","24","11"));
        arrayList.add(new Tour(R.mipmap.img_2,"Mã tour : 03","TP.HCM - Vũng Tàu","3 ngày 2 đêm","560.000 đ","26","24","11"));
        arrayList.add(new Tour(R.mipmap.img_3,"Mã tour : 01","TP.HCM - Nha Trang","3 ngày 2 đêm","560.000 đ","26","24","11"));
        arrayList.add(new Tour(R.mipmap.img_4,"Mã tour : 02","TP.HCM - TP.HCM","3 ngày 2 đêm","560.000 đ","26","24","11"));
        arrayList.add(new Tour(R.mipmap.img_5,"Mã tour : 03","TP.HCM - Vịnh Hạ Long","3 ngày 2 đêm","560.000 đ","26","24","11"));


        adapterTour = new AdapterTour(arrayList,getApplicationContext());
        recyclerViewTour.setAdapter(adapterTour);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
    }

}
