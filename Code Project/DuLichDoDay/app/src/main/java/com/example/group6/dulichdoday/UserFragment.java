package com.example.group6.dulichdoday;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private LinearLayout personal;
    private LinearLayout manager;
    private LinearLayout password;
    private LinearLayout logout;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

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
                if (mGoogleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                } else {
                    mAuth.signOut();
                    Toast.makeText(getActivity(), "Đăng xuất tài khoản thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

            }
        });
        return view;
    }

}
