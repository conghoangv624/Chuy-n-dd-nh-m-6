package com.example.group6.dulichdoday.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.DetailTourActivity;
import com.example.group6.dulichdoday.Models.DownLoadImageTask;
import com.example.group6.dulichdoday.Models.Tours;
import com.example.group6.dulichdoday.R;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HoangNghiep IT on 03/05/2018.
 */

public class AdapterTours extends RecyclerView.Adapter<AdapterTours.ViewHolder> {
    ArrayList<Tours> arrNew;
    Context context;
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private FirebaseAuth mAuth;

    private String tour_ID;
    private String account_ID;
    private String tourName;
    private String tourTime;
    private String tourPrice;
    private String tourDescription;
    private String imgTour ="";
    private String tenMien = "";

    public AdapterTours(ArrayList<Tours> arrNew, Context context) {
        this.arrNew = arrNew;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_tour_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(arrNew.get(position).getImgTour()).into(holder.img);
        holder.tvCode.setText(arrNew.get(position).getTour_ID());
        holder.tvName.setText(arrNew.get(position).getTourName());
        holder.tvTime.setText(arrNew.get(position).getTourTime());
        holder.tvPrice.setText(arrNew.get(position).getTourPrice());
        //Nut option menu
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tao popup Menu
                PopupMenu popupMenu = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource
                popupMenu.inflate(R.menu.tour_menu);
                //Add click listener
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_edit:
                                //updateItem();

                                final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                                dialog.setContentView(R.layout.dialog_edit_layout);
                                //dialog.setTitle("Update infor");
                                //DatabaseReference mData;

                                final ImageView img_choosen;
                                final RadioButton rabMienNam;
                                final RadioButton rabMienTrung;
                                final RadioButton rabMienBac;
                                final EditText edtTourID;
                                final EditText edtToutName;
                                final EditText edtTourTime;
                                final EditText edtDescript;
                                final EditText edtTourPrice;
                                final TextView btnUpdate;
                                final TextView btnCancel;
                                final ArrayList<Tours> arrTours;

                                img_choosen = (ImageView) dialog.findViewById(R.id.img_choosen);
                                rabMienNam = (RadioButton) dialog.findViewById(R.id.rabMienNam);
                                rabMienTrung = (RadioButton) dialog.findViewById(R.id.rabMienTrung);
                                rabMienBac = (RadioButton) dialog.findViewById(R.id.rabMienBac);
                                edtTourID = (EditText) dialog.findViewById(R.id.tourID);
                                edtToutName = (EditText) dialog.findViewById(R.id.tourTitle);
                                edtTourTime = (EditText) dialog.findViewById(R.id.tourTime);
                                edtDescript = (EditText) dialog.findViewById(R.id.tourDescript);
                                edtTourPrice = (EditText) dialog.findViewById(R.id.tourPrice);
                                btnUpdate = (TextView) dialog.findViewById(R.id.btnUpdate);
                                btnCancel = (TextView) dialog.findViewById(R.id.cancel_edit);

                                storage = FirebaseStorage.getInstance();
                                storageRef = storage.getReferenceFromUrl("gs://dulichdoday-7d0dd.appspot.com");
                                mAuth = FirebaseAuth.getInstance();

                                arrTours = new ArrayList<Tours>();

                                //load dữ liệu đổ lên các trường
                                mData.child(Tours.CHILD_TOURS).addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        Tours tours = dataSnapshot.getValue(Tours.class);
                                        arrTours.add(new Tours(tours.getTour_ID(), tours.getAccount_ID(), tours.getTourName(), tours.getTourTime(), tours.getTourPrice(), tours.getTourDescription(), tours.getImgTour(), tours.getTenMien()));
                                        for (int i = 0; i < arrTours.size(); i++) {
                                            if (arrTours.get(i).getTour_ID().equalsIgnoreCase(arrNew.get(position).getTour_ID())) {
                                                //position = i;
                                                if (arrTours.get(i).getTenMien().equalsIgnoreCase(Tours.MIEN_NAM)) {
                                                    //tenMien = "Miền Nam";
                                                    rabMienNam.setChecked(true);
                                                }
                                                if (arrTours.get(i).getTenMien().equalsIgnoreCase(Tours.MIEN_TRUNG)) {
                                                    //tenMien = "Miền Trung";
                                                    rabMienTrung.setChecked(true);
                                                }
                                                if (arrTours.get(i).getTenMien().equalsIgnoreCase(Tours.MIEN_NAM)) {
                                                    //tenMien = "Miền Bắc";
                                                    rabMienBac.setChecked(true);
                                                }
                                                //img_choosen.setImageURI(Uri.parse(arrTours.get(i).getImgTour()));
                                                //Uri uriImage = Uri.parse(arrTours.get(i).getImgTour());

                                                new DownLoadImageTask(img_choosen).execute(arrTours.get(i).getImgTour());
                                                edtTourID.setText(arrTours.get(i).getTour_ID());
                                                edtToutName.setText(arrTours.get(i).getTourName());
                                                edtTourTime.setText(arrTours.get(i).getTourTime());
                                                edtDescript.setText(arrTours.get(i).getTourDescription());
                                                edtTourPrice.setText(arrTours.get(i).getTourPrice());
                                            }
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

                                //khi nhan nut cap nhat
                                btnUpdate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                                        StorageReference mountainsRef = storageRef.child("images/IMG_" + timeStamp + ".jpg");

                                        img_choosen.setDrawingCacheEnabled(true);
                                        img_choosen.buildDrawingCache();
                                        Bitmap bitmap = img_choosen.getDrawingCache();
                                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                        byte[] data = baos.toByteArray();

                                        UploadTask uploadTask = mountainsRef.putBytes(data);
                                        uploadTask.addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(context, "Thêm ảnh Lỗi!!", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                                imgTour = downloadUrl+"";
                                                Toast.makeText(context, " Thêm ảnh thành Công", Toast.LENGTH_SHORT).show();
                                                //Log.e("AAA", downloadUrl + "");
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
                                                                if (rabMienNam.isChecked()) {
                                                                    //tenMien = "Miền Nam";
                                                                    arrTours.get(position).setTenMien(Tours.MIEN_NAM);
                                                                }
                                                                if (rabMienTrung.isChecked()) {
                                                                    //tenMien = "Miền Trung";
                                                                    arrTours.get(position).setTenMien(Tours.MIEN_TRUNG);
                                                                }
                                                                if (rabMienBac.isChecked()) {
                                                                    //tenMien = "Miền Bắc";
                                                                    arrTours.get(position).setTenMien(Tours.MIEN_BAC);
                                                                }
                                                                //Sửa tour
                                                                arrTours.get(position).setTourName(tourName);
                                                                arrTours.get(position).setTourTime(tourTime);
                                                                arrTours.get(position).setTourPrice(tourPrice);
                                                                arrTours.get(position).setTourDescription(tourDescription);
                                                                arrTours.get(position).setImgTour(imgTour);

                                                                mData.child(Tours.CHILD_TOURS).setValue(arrTours, new DatabaseReference.CompletionListener() {
                                                                    @Override
                                                                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                                        if (databaseError == null) {
                                                                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                                                            notifyDataSetChanged();
                                                                            dialog.dismiss();
                                                                        } else {
                                                                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
                                });
                                btnCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                                //notifyItemChanged(position);

                                //notifyDataSetChanged();


                                break;
                            case R.id.menu_delete:
                                arrNew.remove(position);
                                mData.child(Tours.CHILD_TOURS).setValue(arrNew);
                                notifyDataSetChanged();
                                break;
                        }
                        return false;
                    }
                });
                //show popup menu display
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrNew.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnCreateContextMenuListener*/ {
        ImageView img;
        TextView tvCode;
        TextView tvName;
        TextView tvTime;
        TextView tvPrice;
        TextView buttonViewOption;//Tao nut option menu

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_tour_list);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_tour_list);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_tour_list);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_tour_list);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price_tour_list);
            buttonViewOption = (TextView) itemView.findViewById(R.id.textViewOptions);
            //itemView.setOnCreateContextMenuListener(this);
        }

        /*@Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem edit = contextMenu.add(Menu.NONE,1,1,"Edit");
            MenuItem delete = contextMenu.add(Menu.NONE,2,2,"Delete");
            edit.setOnMenuItemClickListener(onEditMenu);
            delete.setOnMenuItemClickListener(onEditMenu);
        }
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case 1:
                        Intent intent = new Intent(context, EditPlaceActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("tours",arrNew.get(getAdapterPosition()).getTour_ID());
                        intent.putExtra("bundle",bundle);
                        //intent.putExtra("position",getAdapterPosition());
                        context.startActivity(intent);
                        break;
                    case 2:
                        arrNew.remove(getAdapterPosition());
                        mData.child("Tours").setValue(arrNew);
                        notifyDataSetChanged();
                        break;
                }
                return true;
            }
        };*/
    }
}
