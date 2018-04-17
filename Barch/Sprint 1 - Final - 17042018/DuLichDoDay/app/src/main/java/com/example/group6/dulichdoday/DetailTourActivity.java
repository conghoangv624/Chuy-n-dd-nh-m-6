package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DetailTourActivity extends AppCompatActivity {

    private TextView tvDatTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tour_layout);

        tvDatTour = (TextView) findViewById(R.id.tvDatTour);

        final Dialog dialog = new Dialog(DetailTourActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_accept);
        tvDatTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }
}
