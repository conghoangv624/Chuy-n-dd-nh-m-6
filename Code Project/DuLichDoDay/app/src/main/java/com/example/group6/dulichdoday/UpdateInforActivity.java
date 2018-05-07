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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.Tours;
import com.example.group6.dulichdoday.Models.UserInfor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UpdateInforActivity extends AppCompatActivity {

    private static final int CAM_REQUEST = 1747;
    private TextView tvCancle_update;
    private ImageView img_Choosen;
    TextView btnCancel;
    TextView btnCamera;
    TextView btnGallery;
    TextView btnUpdate;
    EditText edtName;
    EditText edtDate;
    EditText edtSet;
    EditText edtPhone;
    EditText edtAddress;
    TextView  tvUpdate;

    ArrayList<UserInfor> arrNew;
    Context context;
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();


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
        final Dialog dialog = new Dialog(UpdateInforActivity.this);
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
                btnCamera = (TextView) dialog.findViewById(R.id.btnCamera) ;
                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent taPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(taPicture,1);
                    }
                });

                //chon hinh anh tu thu vien
                btnGallery = (TextView) dialog.findViewById(R.id.btnGallery);
                ;
                btnGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent picPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(picPhoto,1);
                    }
                });
            }
        });

        //huy bo cap nhap thong tin
        tvCancle_update = (TextView) findViewById(R.id.cancel_update);
        final Intent intentCancel = new Intent(UpdateInforActivity.this,DetailPersonalActivity.class);
        tvCancle_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCancel = new Intent(UpdateInforActivity.this,DetailPersonalActivity.class);
                startActivity(intentCancel);
            }
        });

        /*tvUpdate = (TextView) findViewById(R.id.btn_update);
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            private Object adapterPosition;

            public Object getAdapterPosition() {
                return adapterPosition;
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailPersonalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userInfo", arrNew.get((Integer) getAdapterPosition()).getName());
                intent.putExtra("bundle", bundle);
                //intent.putExtra("position",getAdapterPosition());
                context.startActivity(intent);

            }
        });
*/

        //cap nhap thong tin
       /* edtName = (EditText) findViewById(R.id.edtName);
        edtDate = (EditText) findViewById(R.id.edtDate);
        edtSet = (EditText) findViewById(R.id.edtSet);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        btnUpdate = (TextView) findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtName.getText().toString() == "" || edtDate.getText().toString() == "" || edtSet.getText().toString() == "" || edtPhone.getText().toString() == "" || edtAddress.getText().toString() == "")
                {
                    Toast.makeText(UpdateInforActivity.this, "Điền đầy đủ", Toast.LENGTH_SHORT).show();
                }
                else {
                   *//* FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    //user.updateProfile(edtPhone.getText().toString())).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(intentCancel);
                                Toast.makeText(UpdateInforActivity.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(UpdateInforActivity.this, "Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*//*
                }
            }
        });*/
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

            /*try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Log.e("Activity", "Pick from Gallery::>>> ");

                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath.toString());
                img_choosen.setImageBitmap(loadSampleResource(selectedImage,150,150));
               // img_choosen.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }*/
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
    }
}
