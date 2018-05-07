package com.example.group6.dulichdoday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group6.dulichdoday.Models.New;
import com.example.group6.dulichdoday.Adapter.AdapterNew;
import com.example.group6.dulichdoday.Models.New;

import java.util.ArrayList;


/**
 */
public class NewFragment extends Fragment {


    private ArrayList<New> arrayListSP;
    private RecyclerView recyclerView;
    private AdapterNew adapterNew;


    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        // Init
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        // Array
        arrayListSP = new ArrayList<New>();
        /*arrayListSP.add(new New("Hãy đi đến Đà Lạt",R.mipmap.img1,"11","22","33"));
        arrayListSP.add(new New("Hãy đi đến Nha Trang",R.mipmap.img_2,"11","22","33"));
        arrayListSP.add(new New("Hãy đi đến Vũng Tàu",R.mipmap.img_3,"11","22","33"));
        arrayListSP.add(new New("Hãy đi đến Sài Gòn",R.mipmap.img_4,"11","22","33"));
        arrayListSP.add(new New("Hãy đi đến Phú Quốc   ",R.mipmap.img_5,"11","22","33"));*/


        // Set adapter
        adapterNew = new AdapterNew(arrayListSP,getContext());
        recyclerView.setAdapter(adapterNew);
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        return view;
    }
}
