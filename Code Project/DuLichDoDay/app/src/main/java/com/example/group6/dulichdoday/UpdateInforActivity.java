package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpdateInforActivity extends AppCompatActivity {

    private static final int CAM_REQUEST = 1747;
    private TextView tvCancle_update;
    private ImageView img_Choosen;
    private TextView btnCancel;
    private TextView btnCamera;
    private TextView btnGallery;
    private TextView btnUpdate;
    private EditText edtName;
    private EditText edtDate;
    private EditText edtSet;
    private EditText edtPhone;
    private EditText edtAddress;
    private TextView  tvUpdate;
    private EditText edtUpdateName;
    private EditText edtUpdateDate;
    private EditText edtUpdateSex;
    private EditText edtUpdatePhone;
    private EditText edtUpdateAddress;
    private TextView btnUpdateUser;
    private ImageView img;

    DatabaseReference mData;
    FirebaseAuth mAuth;

    //firebaseImage
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    int REQUEST_CODE_IMAGE = 1;
    Uri uri;

    ArrayList<UserInfor> arrNew;
    Context context;

    int PICK_IMAGE_CAMERA = 1;
    int PICK_IMAGE_GALLERY = 2;
    String imgPath = null;
    Bitmap bitmap;
    File destination = null;
    InputStream inputStreamImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_infor_layout);

        //hien thi dialog chuc nang hinh anh
        init();
        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        //huy bo cap nhap thong tin
        tvCancle_update = (TextView) findViewById(R.id.cancel_update_user);
        //final Intent intentCancel = new Intent(UpdateInforActivity.this,DetailPersonalActivity.class);
        tvCancle_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentCancel = new Intent(UpdateInforActivity.this,DetailPersonalActivity.class);
                //startActivity(intentCancel);
                finish();
            }
        });

        mData.child(UserInfor.CHILD_USER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final UserInfor userInfor = dataSnapshot.getValue(UserInfor.class);
                if (mAuth.getCurrentUser().getEmail().equalsIgnoreCase(userInfor.getEmail()))
                {
                    btnUpdateUser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mData.child(UserInfor.CHILD_USER).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    UserInfor users = dataSnapshot.getValue(UserInfor.class);
                                    if (users.getEmail().equalsIgnoreCase(mAuth.getCurrentUser().getEmail())) {
                                        final String userID = mAuth.getCurrentUser().getUid();
                                        mData.child(UserInfor.CHILD_USER).child(userID).child("name").setValue(edtUpdateName.getText().toString());
                                        mData.child(UserInfor.CHILD_USER).child(userID).child("date").setValue(edtUpdateDate.getText().toString());
                                        mData.child(UserInfor.CHILD_USER).child(userID).child("sex").setValue(edtUpdateSex.getText().toString());
                                        mData.child(UserInfor.CHILD_USER).child(userID).child("phoneNumber").setValue(edtUpdatePhone.getText().toString());
                                        mData.child(UserInfor.CHILD_USER).child(userID).child("address").setValue(edtUpdateAddress.getText().toString());
                                        Toast.makeText(UpdateInforActivity.this, "Cap nhap tài khoản thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UpdateInforActivity.this,DetailPersonalActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
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
                    });
                }
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

    // Select image from camera and gallery
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inputStreamImg = null;
        if (requestCode == PICK_IMAGE_CAMERA) {
            try {
                Uri selectedImage = data.getData();
                bitmap = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);

                Log.e("Activity", "Pick from Camera::>>> ");

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                destination = new File(Environment.getExternalStorageDirectory() + "/" +
                        getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgPath = destination.getAbsolutePath();
                img_Choosen.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            try {
                Uri selectedImage = data.getData();
                //InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                //Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                img_Choosen.setImageBitmap(bitmap);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
                //Toast.makeText(PostImage.this, "Something went wrong", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public Bitmap loadSampleResource(String filePath, int targetHeight, int targetWidth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //BitmapFactory.decodeResource(getResources(), imageID, options);
        BitmapFactory.decodeFile(filePath, options);
        int originalHeight = options.outHeight;
        int originalWidth = options.outWidth;
        int inSampleSize = 1;
        while ((originalHeight / (inSampleSize * 2)) > targetHeight && (originalWidth / (inSampleSize * 2)) > targetWidth) {
            inSampleSize *=2;
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        //return  BitmapFactory.decodeResource(getResources(),imageID,options);
        return  BitmapFactory.decodeFile(filePath,options);
    }

    private void init() {
        img_Choosen = (ImageView) findViewById(R.id.img_choosen);
        edtUpdateName = (EditText) findViewById(R.id.edtName);
        edtUpdateDate = (EditText) findViewById(R.id.edtDate);
        edtUpdateSex = (EditText) findViewById(R.id.edtSex);
        edtUpdatePhone = (EditText) findViewById(R.id.edtPhone);
        edtUpdateAddress = (EditText) findViewById(R.id.edtAddress);
        btnUpdateUser = (TextView) findViewById(R.id.btnUpdateUser);
    }
}
