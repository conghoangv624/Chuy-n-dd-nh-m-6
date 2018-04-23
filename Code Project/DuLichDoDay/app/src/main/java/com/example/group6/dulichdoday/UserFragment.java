package com.example.group6.dulichdoday;


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
public class UserFragment extends Fragment {

    private LinearLayout personal;
    private LinearLayout manager;
    private LinearLayout password;
    private LinearLayout logout;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        personal = (LinearLayout) view.findViewById(R.id.linear_personal);
        manager = (LinearLayout) view.findViewById(R.id.linear_product_manager);
        password = (LinearLayout) view.findViewById(R.id.linear_pass);
        logout = (LinearLayout) view.findViewById(R.id.linear_logout);


        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inManager = new Intent(getContext(),TourListActivity.class);
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
                Intent inpersonal1 = new Intent(getContext(),DetailPersonalActivity.class);
                startActivity(inpersonal1);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inpersonal1 = new Intent(getContext(),LoginActivity.class);
                startActivity(inpersonal1);

            }
        });
        return view;
    }

}
