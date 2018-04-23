package com.example.group6.dulichdoday;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.group6.dulichdoday.Models.Tours;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddPlaceActivity extends AppCompatActivity {

    ImageView img_choosen;
    RadioButton rabMienNam;
    RadioButton rabMienTrung;
    RadioButton rabMienBac;
    EditText edtTourID;
    EditText edtToutName;
    EditText edtTourTime;
    EditText edtDescript;
    EditText edtTourPrice;
    TextView btnAdd;
    TextView btnCancel;
    TextView btnGallery;
    TextView btnCamera;
    Uri mLastPhotoURl = null;

    DatabaseReference mData;
    FirebaseStorage storage;

    int PICK_IMAGE_CAMERA = 1;
    int PICK_IMAGE_GALLERY = 2;
    String imgPath = null;
    Bitmap bitmap;
    File destination = null;
    InputStream inputStreamImg;

    // AdapterTours adapterTours;
    ArrayList<Tours> arrTours;
    String tour_ID;
    String account_ID;
    String tourName;
    String tourTime;
    String tourPrice;
    String tourDescription;
    String imgTour;
    String tenMien = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_place_layout);


        //Cài đặt các nút
        Initiation();
        //Lay du lieu ve
        mData.child("Tours").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Tours tours = dataSnapshot.getValue(Tours.class);
                arrTours.add(new Tours(tours.getTour_ID(), tours.getAccount_ID(), tours.getTourName(), tours.getTourTime(), tours.getTourPrice(), tours.getTourDescription(), tours.getImgTour(), tours.getTenMien()));
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

        final Dialog dialog = new Dialog(AddPlaceActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_choosen);

        btnCancel = (TextView) dialog.findViewById(R.id.btnCancel);
        btnCamera = (TextView) dialog.findViewById(R.id.btnCamera);
        btnGallery = (TextView) dialog.findViewById(R.id.btnGallery);

        //update hình ảnh
        img_choosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                //Chọn ảnh từ camera
                btnCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(takePicture,PICK_IMAGE_CAMERA);


                    }
                });
                //Chọn ảnh từ thư viện
                btnGallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_IMAGE_GALLERY);

                    }
                });
                //Kết thúc dialog
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tour_ID = edtTourID.getText().toString().trim();
                //account_ID;
                tourName = edtToutName.getText().toString().trim();
                tourTime = edtTourTime.getText().toString().trim();
                tourPrice = edtTourPrice.getText().toString().trim();
                tourDescription = edtDescript.getText().toString().trim();
                //imgTour

                if (TextUtils.isEmpty(tour_ID)) {
                    edtTourID.setError("Không được để trống");
                } else {
                    if (TextUtils.isEmpty(tourName)) {
                        edtToutName.setError("Không được để trống");
                    } else {
                        if (TextUtils.isEmpty(tourTime)) {
                            edtTourTime.setError("Không được để trống");
                        } else {
                            if (TextUtils.isEmpty(tourPrice)) {
                                edtTourPrice.setError("Không được để trống");
                            } else {
                                //tour_ID = edtTourID.getText().toString();
                                if (rabMienNam.isChecked()) {
                                    tenMien = "Miền Nam";
                                }
                                if (rabMienTrung.isChecked()) {
                                    tenMien = "Miền Trung";
                                }
                                if (rabMienBac.isChecked()) {
                                    tenMien = "Miền Bắc";
                                }
                                //Thêm tour mới
                                Tours tourNew = new Tours(edtTourID.getText().toString(), "1", edtToutName.getText().toString(), edtTourTime.getText().toString(), edtTourPrice.getText().toString(), edtDescript.getText().toString(), "Link Hinh", tenMien);
                                arrTours.add(tourNew);
                                mData.child("Tours").setValue(arrTours, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                        if (databaseError == null) {
                                            Toast.makeText(AddPlaceActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(AddPlaceActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                            edtTourID.setText("");
                                            edtToutName.setText("");
                                            edtTourTime.setText("");
                                            edtDescript.setText("");
                                            edtTourPrice.setText("");
                                        }
                                    }
                                });

                            }
                        }
                    }
                }


            }
        });

    }

    private void Initiation() {
        img_choosen = (ImageView) findViewById(R.id.img_choosen);
        rabMienNam = (RadioButton) findViewById(R.id.rabMienNam);
        rabMienTrung = (RadioButton) findViewById(R.id.rabMienTrung);
        rabMienBac = (RadioButton) findViewById(R.id.rabMienBac);
        edtTourID = (EditText) findViewById(R.id.tourID);
        edtToutName = (EditText) findViewById(R.id.tourTitle);
        edtTourTime = (EditText) findViewById(R.id.tourTime);
        edtDescript = (EditText) findViewById(R.id.tourDescript);
        edtTourPrice = (EditText) findViewById(R.id.tourPrice);
        btnAdd = (TextView) findViewById(R.id.btnAdd);


        mData = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        arrTours = new ArrayList<Tours>();
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
                img_choosen.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_GALLERY) {
            try {
                Uri selectedImage = data.getData();
                //InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                //Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                img_choosen.setImageBitmap(bitmap);
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

}
