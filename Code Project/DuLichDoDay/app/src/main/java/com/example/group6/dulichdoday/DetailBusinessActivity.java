package com.example.group6.dulichdoday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailBusinessActivity extends AppCompatActivity {

    TextView btnUpdateInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_business_layout);
        btnUpdateInfor = (TextView) findViewById(R.id.btn_update);
        btnUpdateInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inUpdate = new Intent(getBaseContext(),UpdateInforBusinessActivity.class);
                startActivity(inUpdate);
            }
        });
    }
}
