package com.example.group6.dulichdoday;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserBusinessFragment extends Fragment {

    private LinearLayout business;
    private LinearLayout manager;
    private LinearLayout listTour;
    private  LinearLayout tourOrder;
    private LinearLayout logout;
    private FirebaseAuth mAuth;

    public UserBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_business, container, false);

        mAuth = FirebaseAuth.getInstance();
        business = (LinearLayout) view.findViewById(R.id.linear_business);
        manager = (LinearLayout) view.findViewById(R.id.linear_product_manager);
        listTour = (LinearLayout) view.findViewById(R.id.linear_list_tour);
        tourOrder = (LinearLayout) view.findViewById(R.id.linear_product_manager_personal);
        logout = (LinearLayout) view.findViewById(R.id.linear_logout);

        //Thông tin cá nhân
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inBusiness = new Intent(getContext(),DetailBusinessActivity.class);
                startActivity(inBusiness);
            }
        });

        //Quản lý tour
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inManager = new Intent(getContext(),ManagerTourActivity.class);
                startActivity(inManager);
            }
        });

        //Danh sách tour
        listTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inTour = new Intent(getContext(),TourListActivity.class);
                startActivity(inTour);
            }
        });

        //Tour khách đặt
        tourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inTourOrder = new Intent(getContext(),ManagerBookingTourActivity.class);
                startActivity(inTourOrder);
            }
        });
        //Đăng xuất
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(getContext(), "Đăng xuất tài khoản thành công", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        return view;
    }

}
