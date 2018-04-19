package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DialogComment extends AppCompatActivity {

    private static final int CAM_REQUEST = 1313;
    private Dialog dialog;
    private View view;
    ImageButton img_ava_patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment);


        dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialog_getavatar);
        dialog.setTitle("Choose Avatar Image");

        Button btn_chooseImg = (Button) dialog.findViewById(R.id.btn_choosenGallery);
        Button btn_takeaphoto = (Button) dialog.findViewById(R.id.btn_choosenTakephoto);

        btn_chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFromGallery();
            }
        });

        btn_takeaphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profilepictureOnClick();
            }
        });


    ;}

        public void chooseFromGallery() {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 0);
        }


        private void takeNewProfilePicture(){
            DialogComment profileFrag = this;
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            profileFrag.startActivityForResult(cameraintent, CAM_REQUEST);
        }

        public void profilepictureOnClick(){
            takeNewProfilePicture();
        }
    }
