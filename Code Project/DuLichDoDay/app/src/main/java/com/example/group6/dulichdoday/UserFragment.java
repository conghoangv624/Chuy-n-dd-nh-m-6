package com.example.group6.dulichdoday;


import android.app.Dialog;
import android.content.Context;
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
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
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
    private Context applicationContext;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        mAuth = FirebaseAuth.getInstance();
        personal = (LinearLayout) view.findViewById(R.id.linear_personal);
        manager = (LinearLayout) view.findViewById(R.id.linear_product_manager);
        password = (LinearLayout) view.findViewById(R.id.linear_pass);
        logout = (LinearLayout) view.findViewById(R.id.linear_logout);

        //Danh sách tour đặt
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inManager = new Intent(getContext(), BookTour.class);
                startActivity(inManager);
            }
        });

        //Đổi mật khẩu
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inpersonal = new Intent(getContext(), PasswordActivity.class);
                startActivity(inpersonal);
            }
        });

        //Trang cá nhân
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inpersonal1 = new Intent(getContext(), DetailPersonalActivity.class);
                startActivity(inpersonal1);
            }
        });

        //Đăng xuất
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(getContext(), "Đăng xuất tài khoản thành công", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        return view;
    }
}
