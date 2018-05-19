package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Adapter.AdapterNew;
import com.example.group6.dulichdoday.Models.New;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class NewFragment extends Fragment {


    private ArrayList<New> arrayListSP;
    private RecyclerView recyclerView;
    private AdapterNew adapterNew;
    private ImageView chon,gui;
    private Dialog dialog;
    private TextView btnCancelAvatar;
    private EditText newfeed;
    FirebaseDatabase database;
    DatabaseReference refer;
    FirebaseAuth firebaseAuth;


    final int CROP_PIC = 2;
    private Uri picUri;


    Bitmap thumbnail;
    String hinhanh;


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
        gui = (ImageView) view.findViewById(R.id.gui);
        newfeed = (EditText) view.findViewById(R.id.newfeed);

        firebaseAuth = FirebaseAuth.getInstance();
        final String a;

        //khoi tao
        database = FirebaseDatabase.getInstance();
        refer = database.getReference();

        dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialog_getavatar);
        dialog.setTitle("Choose Avatar Image");

        btnCancelAvatar = (TextView) dialog.findViewById(R.id.btnCancelAvatar);
        btnCancelAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView btn_chooseImg;
        TextView btn_takeaphoto;
        btn_chooseImg = (TextView) dialog.findViewById(R.id.btnCameraAvatar);
        btn_takeaphoto = (TextView) dialog.findViewById(R.id.btnGalleryAvatar);

        chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        btn_chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chooseFromGallery();
                profilepictureOnClick();
            }
        });

        btn_takeaphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // profilepictureOnClick();
                chooseFromGallery();
            }
        });

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        // Array
        arrayListSP = new ArrayList<New>();
//        arrayListSP.add(new New("Hãy đi đến Đà Lạt",R.mipmap.img1,"11","22","33"));
//        arrayListSP.add(new New("Hãy đi đến Nha Trang",R.mipmap.img_2,"11","22","33"));
//        arrayListSP.add(new New("Hãy đi đến Vũng Tàu",R.mipmap.img_3,"11","22","33"));
//        arrayListSP.add(new New("Hãy đi đến Sài Gòn",R.mipmap.img_4,"11","22","33"));
//        arrayListSP.add(new New("Hãy đi đến Phú Quốc   ",R.mipmap.img_5,"11","22","33"));


        adapterNew = new AdapterNew(arrayListSP,getContext());
        recyclerView.setAdapter(adapterNew);
        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        refer.child("baiviet").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                New aa = dataSnapshot.getValue(New.class);
                arrayListSP.add(aa);
                adapterNew.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                arrayListSP.clear();
                refer.child("baiviet").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        New aa = dataSnapshot.getValue(New.class);
                        arrayListSP.add(aa);
                        adapterNew.notifyDataSetChanged();
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

        //gui
        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name[] = firebaseAuth.getCurrentUser().getEmail().split("@");



                //               arrayListSP.add(new New(newfeed.getText().toString(),hinhanh,firebaseAuth.getCurrentUser().getUid(),"11","22","33"));
                String key =refer.child("baiviet").push().getKey();
                long a =0 ,b= 0,c= 0;
                New s = new New(newfeed.getText().toString(),name[0],hinhanh,key,a,b,c);
                refer.child("baiviet").child(key).setValue(s);
//                adapterNew.notifyDataSetChanged();
                //dang du lieu len firebaser
                Toast.makeText(getActivity(), "Đăng Bài Thành Công", Toast.LENGTH_SHORT).show();
                newfeed.setText("");
            }
        });

        return view;
    }


    public void chooseFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

    private  String convertToBase64(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] byteArrayImage = baos.toByteArray();

        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

        return encodedImage;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == CAM_REQUEST) {
            if(requestCode == CAM_REQUEST){
                thumbnail = (Bitmap) data.getExtras().get("data");



                //  picUri = data.getData();

                chon.setImageBitmap(thumbnail);
                hinhanh= convertToBase64(thumbnail);
                dialog.dismiss();
            }
        }
        else if (resultCode == RESULT_OK){
            picUri = data.getData();

            // Uri targetUri = data.getData();
            //  textTargetUri.setText(targetUri.toString());

            try {
                Context applicationContext = getActivity();
                thumbnail = BitmapFactory.decodeStream(applicationContext.getContentResolver().openInputStream(picUri));
                hinhanh= convertToBase64(thumbnail);
                chon.setImageBitmap(thumbnail);


                dialog.dismiss();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
