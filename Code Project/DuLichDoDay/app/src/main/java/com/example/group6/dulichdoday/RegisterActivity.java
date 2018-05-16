package com.example.group6.dulichdoday;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Models.User;
import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvCancelRegister;
    private Intent myIntent;
    private EditText edtMail,edtPass,edtPASS;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2;
    private Button btnRegister;

    FrameLayout frameLayout;
    AnimationDrawable animationDrawable;

     private ProgressDialog mProgress;
    /// FIREBASE
    private FirebaseAuth mAuth;
    private DatabaseReference mData;
    private DatabaseReference mData1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        tvCancelRegister = (TextView) findViewById(R.id.cancel_Register);

        edtMail = (EditText) findViewById(R.id.edtMail);
        edtPass = (EditText) findViewById(R.id.edtPass);

        edtPASS = (EditText) findViewById(R.id.edtPassAg);
        radioGroup = (RadioGroup) findViewById(R.id.rdg);
        rb1 = (RadioButton) findViewById(R.id.rdb1);
        rb2 = (RadioButton) findViewById(R.id.rdb2);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        frameLayout = (FrameLayout) findViewById(R.id.frameRegister);
        animationDrawable = (AnimationDrawable)frameLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5200);
        animationDrawable.setExitFadeDuration(4200);
        animationDrawable.start();



        //Get FireBasae
        mAuth = FirebaseAuth.getInstance();
        mData1 = FirebaseDatabase.getInstance().getReference();
        mData = FirebaseDatabase.getInstance().getReference().child("Users");
        //Progressbar
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Đang tạo tài khoản");
        mProgress.setMessage("Xin vui lòng chờ");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = edtMail.getText().toString().trim();
                final String password = edtPass.getText().toString().trim();
                final String type1 = rb1.getText().toString().trim();
                final String type2 = rb2.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Nhập email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Nhập password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 10 || password.length() > 30) {
                    Toast.makeText(getApplicationContext(), "Nhập lại password", Toast.LENGTH_SHORT).show();
                    return;
                } else  {
                    mProgress.show();
                }

                //Create user
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            mProgress.dismiss();
                            Toast.makeText(RegisterActivity.this, "Tạo tài khoản thất bại",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();


                            String userID = mAuth.getCurrentUser().getUid();
                            String key = mData1.child("Users").push().getKey();
                            if (rb1.isChecked()) {

                                mData1.child("Users").child(key).setValue(new UserInfor("",edtMail.getText().toString(),edtPass.getText().toString(),"","","Thành Viên",key,""));
                               // mData.child(userID).setValue(new User(email+"",password +"","Thành viên"));
                            }else if (rb2.isChecked()) {

                                mData1.child("Users").child(key).setValue(new UserInfor("",edtMail.getText().toString(),edtPass.getText().toString(),"","","Quản lý",key,""));
                                //mData.child(userID).setValue(new User(email+"",password +"","Quản lý"));
                            }
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                    }
                });
            }
        });




        tvCancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myIntent = new Intent(getApplication(), LoginActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
