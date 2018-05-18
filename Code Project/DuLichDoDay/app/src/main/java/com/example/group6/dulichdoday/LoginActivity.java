package com.example.group6.dulichdoday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class LoginActivity extends AppCompatActivity {

    private TextView tvRegister;
    private Intent myIntent;
    private Button btn_Login;
    private EditText edtMail, edtPass;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MAIN__ACTIVITY";
    private static final int RC_SIGN_IN = 1;
    private ProgressDialog mProgress;
    FrameLayout frameLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        tvRegister = (TextView) findViewById(R.id.tv_Register);
        btn_Login = (Button) findViewById(R.id.btn_Login);
        edtMail = (EditText) findViewById(R.id.edtMailLogin);
        edtPass = (EditText) findViewById(R.id.edtPassLogin);

        frameLayout = (FrameLayout) findViewById(R.id.frameLogin);
        animationDrawable = (AnimationDrawable)frameLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
        //Get Firebase
        mAuth = FirebaseAuth.getInstance();


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent myIntentLogin = new Intent(getApplication(), MainLayoutActivity.class);
                startActivity(myIntentLogin);*/
                mProgress = new ProgressDialog(LoginActivity.this);
                mProgress.setTitle("Đang kết nối");
                mProgress.setMessage("Vui lòng chờ");
                final String email = edtMail.getText().toString();
                final String password = edtPass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    edtMail.setError("Không bỏ trống");
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    edtPass.setError("Không bỏ trống");
                    return;
                } else {
                    mProgress.show();
                }
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            if (password.length() < 10 || password.length() > 30) {
                                edtPass.setError("Tối thiểu 10 ký tự");
                                mProgress.dismiss();
                            } else {
                                mProgress.dismiss();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent myIntentLogin = new Intent(getApplication(), MainLayoutActivity.class);
                            startActivity(myIntentLogin);
                        }
                    }
                });

            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myIntent = new Intent(getApplication(), RegisterActivity.class);
                startActivity(myIntent);

            }
        });
    }
}