package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ASUS on 4/9/2018.
 */

public class UpdateInforBusinessActivity extends AppCompatActivity {

    private TextView tvCancle_update;
    private ImageView img_Choosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_infor_business_layout);

        tvCancle_update = (TextView) findViewById(R.id.cancel_update);
        init();

        final Dialog dialog = new Dialog(UpdateInforBusinessActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_choosen);

        img_Choosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        tvCancle_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCancel = new Intent(UpdateInforBusinessActivity.this,DetailBusinessActivity.class);
                startActivity(intentCancel);
            }
        });
    }

    private void init() {
        img_Choosen = (ImageView) findViewById(R.id.img_choosen);
    }
}
