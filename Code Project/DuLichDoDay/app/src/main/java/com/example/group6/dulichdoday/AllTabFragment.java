package com.example.group6.dulichdoday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group6.dulichdoday.Models.Tour;
import com.example.group6.dulichdoday.Adapter.AdapterTour;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTabFragment extends Fragment {


    private ArrayList<Tour> arrayList;
    private AdapterTour adapterTour;
    private RecyclerView recyclerViewTour;

    public AllTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_tab, container, false);
        recyclerViewTour = (RecyclerView) view.findViewById(R.id.recyclerViewTour);

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
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


        adapterTour = new AdapterTour(arrayList,getContext());
        recyclerViewTour.setAdapter(adapterTour);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        return view;
    }


}
