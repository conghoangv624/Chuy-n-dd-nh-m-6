package com.example.group6.dulichdoday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DetailBusinessActivity extends AppCompatActivity {

    private TextView tvUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_personal_layout);

        tvUpdate = (TextView) findViewById(R.id.btn_update);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUpdate = new Intent(DetailBusinessActivity.this,UpdateInforActivity.class);
                startActivity(intentUpdate);
            }
        });
    }
}
