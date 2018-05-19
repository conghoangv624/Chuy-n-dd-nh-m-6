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
import android.widget.LinearLayout;
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
import com.squareup.picasso.Picasso;

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

/**
 * Created by ASUS on 5/14/2018.
 */

public class UpdateInforBusinessActivity extends AppCompatActivity {
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
    private EditText edtUpdateEmail;
    private EditText edtUpdatePhone;
    private EditText edtUpdateAddress;
    private TextView btnUpdateUser;
    private ImageView imgPost;

    DatabaseReference mData;
    FirebaseAuth mAuth;

    //firebaseImage
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    int REQUEST_CODE_IMAGE = 1;
    Uri uri;

    ArrayList<UserInfor> arrNew;
    Context context;

    // Check ch?p camera or l?y ?nh t? thư vi?n
    boolean takePhoToFromCamera = false;
    boolean takePhoToFromLibrary = false;

    // Bi?n check xem khi update có ph?i là h?nh  m?i hay h?nh c?
    boolean CheckNewImageCamera = false;
    boolean CheckNewImageLibrary = false;

    int PICK_IMAGE_CAMERA = 1;
    int PICK_IMAGE_GALLERY = 2;
    String imgPath = null;
    Bitmap bitmap;
    File destination = null;
    InputStream inputStreamImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_infor_business_layout);

        //hien thi dialog chuc nang hinh anh
        init();
        // bi?n c?n thi?t firebase
        mData = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        // bi?n c?n thi?t dùng firebase
        mData = FirebaseDatabase.getInstance().getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();



        final Dialog dialog = new Dialog(UpdateInforBusinessActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_choosen);
        dialog.setTitle("Choose Avatar Image");
        img_Choosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                //dong dialog hinh anh
                btnCancel = (TextView)dialog.findViewById(R.id.btnCancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                //chon hinh anh tu camera
                btnCamera = (TextView) dialog.findViewById(R.id.btnCamera);
                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent taPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(taPicture,PICK_IMAGE_CAMERA);
                        dialog.dismiss();
                    }
                });

                //chon hinh anh tu thu vien
                btnGallery = (TextView) dialog.findViewById(R.id.btnGallery);
                btnGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent picPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(picPhoto,PICK_IMAGE_GALLERY);
                        dialog.dismiss();
                    }
                });
            }
        });

        //huy bo cap nhap thong tin
        tvCancle_update = (TextView) findViewById(R.id.cancel_update);
       // final Intent intentCancel = new Intent(UpdateInforBusinessActivity.this,DetailBusinessActivity.class);
        tvCancle_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentCancel = new Intent(UpdateInforBusinessActivity.this,DetailBusinessActivity.class);
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
                                    //UserInfor users = dataSnapshot.getValue(UserInfor.class);
                                    if (users.getEmail().equalsIgnoreCase(mAuth.getCurrentUser().getEmail())) {
                                        final String userID = mAuth.getCurrentUser().getUid();
                                       // Picasso.with(UpdateInforBusinessActivity.this).load(users.getImageUrl()).into(img_Choosen);
                                        mData.child(UserInfor.CHILD_USER).child(userID).child("name").setValue(edtUpdateName.getText().toString());
                                        //mData.child("Users").child(userID).child("email").setValue(edtUpdateEmail.getText().toString());
                                        mData.child(UserInfor.CHILD_USER).child(userID).child("phoneNumber").setValue(edtUpdatePhone.getText().toString());
                                        mData.child(UserInfor.CHILD_USER).child(userID).child("address").setValue(edtUpdateAddress.getText().toString());

                                        Toast.makeText(UpdateInforBusinessActivity.this, "cập nhật thông tin tài khoản thành công !", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(UpdateInforBusinessActivity.this,DetailBusinessActivity.class);
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
        img_Choosen = (ImageView) findViewById(R.id.avatarBusiness);
        edtUpdateName = (EditText) findViewById(R.id.edtNameBusiness);
        //edtUpdateEmail = (EditText) findViewById(R.id.edtEmailBusiness);
        edtUpdatePhone = (EditText) findViewById(R.id.edtPhoneBusiness);
        edtUpdateAddress = (EditText) findViewById(R.id.edtAddressBusiness);
        btnUpdateUser = (TextView) findViewById(R.id.txtUpdateBusiness);
    }
}
