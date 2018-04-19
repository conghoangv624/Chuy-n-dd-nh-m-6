package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ImageWriter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.group6.dulichdoday.Models.New;
import com.example.group6.dulichdoday.Adapter.AdapterNew;
import com.example.group6.dulichdoday.Models.New;

import static android.app.Activity.RESULT_OK;

import java.io.FileNotFoundException;
import java.util.ArrayList;



public class NewFragment extends Fragment {


    private ArrayList<New> arrayListSP;
    private RecyclerView recyclerView;
    private AdapterNew adapterNew;
    private ImageView chon;
    private Dialog dialog;

    final int CROP_PIC = 2;
    private Uri picUri;


    Bitmap thumbnail;


    private static final int CAM_REQUEST = 1313;


    public NewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        // Init
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        chon = (ImageView) view.findViewById(R.id.chon);

        dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialog_getavatar);
        dialog.setTitle("Choose Avatar Image");

        Button btn_chooseImg = (Button) dialog.findViewById(R.id.btn_choosenGallery);
        Button btn_takeaphoto = (Button) dialog.findViewById(R.id.btn_choosenTakephoto);

        chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

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

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        // Array
        arrayListSP = new ArrayList<New>();
        arrayListSP.add(new New("Hãy đi đến Đà Lạt",R.mipmap.img1,"11","22","33"));
        arrayListSP.add(new New("Hãy đi đến Nha Trang",R.mipmap.img_2,"11","22","33"));
        arrayListSP.add(new New("Hãy đi đến Vũng Tàu",R.mipmap.img_3,"11","22","33"));
        arrayListSP.add(new New("Hãy đi đến Sài Gòn",R.mipmap.img_4,"11","22","33"));
        arrayListSP.add(new New("Hãy đi đến Phú Quốc   ",R.mipmap.img_5,"11","22","33"));


        // Set adapter
        adapterNew = new AdapterNew(arrayListSP,getContext());
        recyclerView.setAdapter(adapterNew);
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        return view;
    }


    public void chooseFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }


    private void takeNewProfilePicture(){
        Fragment profileFrag = this;
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        profileFrag.startActivityForResult(cameraintent, CAM_REQUEST);
    }


    public void profilepictureOnClick(){
        takeNewProfilePicture();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == CAM_REQUEST) {
            if(requestCode == CAM_REQUEST){
                thumbnail = (Bitmap) data.getExtras().get("data");

                //  picUri = data.getData();

                chon.setImageBitmap(thumbnail);
                dialog.dismiss();
            }
        }
        else if (resultCode == RESULT_OK){
            picUri = data.getData();

            // Uri targetUri = data.getData();
            //  textTargetUri.setText(targetUri.toString());

            try {
                Context applicationContext = MainLayoutActivity.getContextOfApplication();
                thumbnail = BitmapFactory.decodeStream(applicationContext.getContentResolver().openInputStream(picUri));
                chon.setImageBitmap(thumbnail);


                dialog.dismiss();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
