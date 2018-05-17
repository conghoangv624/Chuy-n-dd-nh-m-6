package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PasswordActivity extends AppCompatActivity {
    //----
    private TextView btnSave;
    private TextView btnCancel;
    private EditText txtNewPassword;
    private EditText txtCurrentPassword;
    private EditText txtConfirmNewPassword;
    private DatabaseReference mData;

    TextView txtdialog;
    TextView txtDongY;
    TextView txtThoat;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_layout);
        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        btnSave = (TextView) findViewById(R.id.btnSave);
        txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
        txtConfirmNewPassword = (EditText) findViewById(R.id.txtConfirmNewPassword);
        btnCancel = (TextView) findViewById(R.id.btnCancel);

        final Intent intent = new Intent(PasswordActivity.this,HomeFragment.class);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(intent);
                /*TourFragment fragmentTour = new TourFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.content,fragmentTour,"Fragment");
                fragmentTransaction3.commit();*/
                //startActivity(new Intent(getActivity(), LoginActivity.class));
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(PasswordActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.setContentView(R.layout.dialog_change_passwork);
                dialog.setTitle("Do you want to remain logged on?");

                Button btnHuy  = (Button) dialog.findViewById(R.id.dialog_huy);
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //kiem tra hai tai khoan co giong nhau khong
                        if ( !txtNewPassword.getText().toString().trim().equalsIgnoreCase(txtConfirmNewPassword.getText().toString().trim())) {
                            Toast.makeText(PasswordActivity.this, "Hai tài khoản khác nhau", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updatePassword(txtNewPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //startActivity(intent);
                                        mData.child("Users").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                final UserInfor users = dataSnapshot.getValue(UserInfor.class);
                                                if (users.getEmail().equalsIgnoreCase(mAuth.getCurrentUser().getEmail())) {
                                                    final String userID = mAuth.getCurrentUser().getUid();
                                                    //txtConfirmNewPassword.setText(users.getPass()+"");
                                                    mData.child("Users").child(userID).child("pass").setValue(txtConfirmNewPassword.getText().toString());
                                                }
                                            }

                                            @Override
                                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                            }

                                            @Override
                                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                                            }

                                            @Override
                                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                        Toast.makeText(PasswordActivity.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(PasswordActivity.this,LoginActivity.class);
                                        startActivity(intent1);
                                        dialog.dismiss();
                                        finish();

                                    } else {
                                        Toast.makeText(PasswordActivity.this, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });

                Button btnYes = (Button) dialog.findViewById(R.id.dialog_yes);
                dialog.show();
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //kiem tra hai tai khoan co giong nhau khong
                        if ( !txtNewPassword.getText().toString().trim().equalsIgnoreCase(txtConfirmNewPassword.getText().toString().trim())) {
                            Toast.makeText(PasswordActivity.this, "Hai tài khoản khác nhau", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updatePassword(txtNewPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //startActivity(intent);
                                        mData.child("Users").addChildEventListener(new ChildEventListener() {
                                            @Override
                                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                                final UserInfor users = dataSnapshot.getValue(UserInfor.class);
                                                if (users.getEmail().equalsIgnoreCase(mAuth.getCurrentUser().getEmail())) {
                                                    final String userID = mAuth.getCurrentUser().getUid();
                                                    //txtConfirmNewPassword.setText(users.getPass()+"");
                                                    mData.child("Users").child(userID).child("pass").setValue(txtConfirmNewPassword.getText().toString());
                                                }
                                            }

                                            @Override
                                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                            }

                                            @Override
                                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                                            }

                                            @Override
                                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                        Toast.makeText(PasswordActivity.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        finish();

                                    } else {
                                        Toast.makeText(PasswordActivity.this, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}
