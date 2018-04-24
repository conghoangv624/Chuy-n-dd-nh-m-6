package com.example.group6.dulichdoday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<String> arrSpinner;
    private TextView tvCancelRegister;
    private Intent myIntent;
    private EditText edtMail,edtPass,edtPASS;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2;
    private Button btnRegister;
    private ProgressDialog mProgress;
    /// FIREBASE
    private FirebaseAuth mAuth;
    private DatabaseReference root;

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


        //Get FireBasae
        mAuth = FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance().getReference().child("Users");;


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Progressbar
                mProgress = new ProgressDialog(RegisterActivity.this);
                mProgress.setTitle("Đang tạo tài khoản");
                mProgress.setMessage("Xin vui lòng chờ");

                final String email = edtMail.getText().toString().trim();
                final String pass = edtPass.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    edtMail.setError("Không bỏ trống");
                    return;
                } else if (TextUtils.isEmpty(pass)) {
                    edtPass.setError("Không bỏ trống");
                    return;
                } else if (pass.length() < 10 || pass.length() > 30) {
                    edtPass.setError("Số kí tự không hợp lệ");
                    return;
                }
                else  {
                    mProgress.show();
                    //Create user
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                mProgress.dismiss();
                                Toast.makeText(RegisterActivity.this, "Tạo tài khoản thất bại",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                                mProgress.dismiss();
                                String userID = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_id = root.child(userID);
                                current_user_id.child("name").setValue(email);
                                current_user_id.child("password").setValue(pass);
                                if (rb1.isSelected())
                                {
                                    rb1.getText().toString().trim();
                                    current_user_id.child("type").setValue(rb1);
                                }else {
                                    rb2.getText().toString().trim();
                                    current_user_id.child("type").setValue(rb2);
                                }

                            }
                        }
                    });
                }
                edtMail.setText("");
                edtPass.setText("");
                edtPASS.setText("");
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
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
