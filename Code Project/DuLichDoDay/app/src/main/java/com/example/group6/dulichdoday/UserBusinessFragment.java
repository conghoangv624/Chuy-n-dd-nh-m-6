package com.example.group6.dulichdoday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class UserBusinessFragment extends Fragment {

    private LinearLayout personal;
    private LinearLayout manager;
    private LinearLayout password;


    public UserBusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_business, container, false);

        personal = (LinearLayout) view.findViewById(R.id.linear_personal);
        manager = (LinearLayout) view.findViewById(R.id.linear_product_manager);
        password = (LinearLayout) view.findViewById(R.id.linear_pass);

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inManager = new Intent(getContext(),UserBusinessFragment.class);
                startActivity(inManager);
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inpersonal = new Intent(getContext(),PasswordActivity.class);
                startActivity(inpersonal);
            }
        });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inBusiness = new Intent(getContext(),DetailBusinessActivity.class);
                startActivity(inBusiness);
            }
        });

        return view;
    }
}
