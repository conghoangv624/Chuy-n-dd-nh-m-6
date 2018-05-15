package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Models.New;
import com.example.group6.dulichdoday.Models.Tour;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/*public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_layout);
    }

}*/

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_layout);
        btnSave = (TextView) findViewById(R.id.btnSave);
        txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
        txtCurrentPassword = (EditText) findViewById(R.id.txtCurrentPassword);
        txtConfirmNewPassword = (EditText) findViewById(R.id.txtConfirmNewPassword);
        btnCancel = (TextView) findViewById(R.id.btnCancel);
        final Intent intent = new Intent(PasswordActivity.this,UserFragment.class);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                TourFragment fragmentTour = new TourFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.content,fragmentTour,"Fragment");
                fragmentTransaction3.commit();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(PasswordActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.setContentView(R.layout.dialog__change);
                dialog.setTitle("Do you want to remain logged on?");
                Button btnOK = (Button) dialog.findViewById(R.id.dialog_ok);
                dialog.show();

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //kiem tra hai tai khoan co giong nhau khong
                        if ( !txtNewPassword.getText().toString().trim().equalsIgnoreCase(txtConfirmNewPassword.getText().toString().trim())) {
                            Toast.makeText(PasswordActivity.this, "Hai tài khoản khác nhau", Toast.LENGTH_SHORT).show();
                        }
                        //dien day du thong tin khong duoc bo trong
                        else if (txtNewPassword.getText().toString() == "" || txtConfirmNewPassword.getText().toString() == "" || txtCurrentPassword.getText().toString() == "")
                        {
                            Toast.makeText(PasswordActivity.this, "Điền đầy đủ", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updatePassword(txtNewPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        //startActivity(intent);
                                        Toast.makeText(PasswordActivity.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(PasswordActivity.this, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });

                Button txtThoat = (Button) dialog.findViewById(R.id.dialog_cancel);
                txtThoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
//        final Dialog dialog = new Dialog(PasswordActivity.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
//        dialog.setContentView(R.layout.dialog_user);
//        dialog.setTitle("Do you want to remain logged on?");
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.show();
//
//                //quay lai man dang nhap
//                txtThoat = (TextView)dialog.findViewById(R.id.dialog_cancel);
//                txtThoat.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        final Intent intent = new Intent(PasswordActivity.this,LoginActivity.class);
//                        startActivity(intent);
//                    }
//                });
//
//                TextView txtDongY = dialog.findViewById(R.id.dialog_ok);
//                txtDongY.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                        Intent intent = new Intent(PasswordActivity.this,MainLayoutActivity.class);
//                        startActivity(intent);
//                    }
//                });
//            }
//        });

    }
}
