package com.example.group6.dulichdoday;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserBusinessFragment extends Fragment {

    private LinearLayout business;
    private LinearLayout manager;
    private LinearLayout listTour;


    public UserBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_business, container, false);

        business = (LinearLayout) view.findViewById(R.id.linear_business);
        manager = (LinearLayout) view.findViewById(R.id.linear_product_manager);
        listTour = (LinearLayout) view.findViewById(R.id.linear_list_tour);

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inBusiness = new Intent(getContext(),DetailBusinessActivity.class);
                startActivity(inBusiness);
            }
        });

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inManager = new Intent(getContext(),ManagerTourActivity.class);
                startActivity(inManager);
            }
        });
        listTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inTour = new Intent(getContext(),TourListActivity.class);
                startActivity(inTour);
            }
        });
        return view;
    }

}
