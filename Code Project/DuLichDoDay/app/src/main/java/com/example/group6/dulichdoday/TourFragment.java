package com.example.group6.dulichdoday;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.group6.dulichdoday.Adapter.AdapterSpinner;
import com.example.group6.dulichdoday.Models.Tour;
import com.example.group6.dulichdoday.Adapter.AdapterTour;
import com.example.group6.dulichdoday.Models.mdSpinner;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TourFragment extends Fragment {

    // Hãng
    private ArrayList<mdSpinner> arraylistMien;
    private ArrayList<String> arrayListPrice;
    private Spinner spinnerMien;
    private AdapterTour adapterTour;
    private ArrayList<Tour> arrTour;
    private RecyclerView recyclerViewTour;
    private DatabaseReference mData;
    int posi;
    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tour, container, false);
        //FIREBASE
        mData = FirebaseDatabase.getInstance().getReference();
        spinnerMien = (Spinner) view.findViewById(R.id.spinnerTour);
        recyclerViewTour = (RecyclerView) view.findViewById(R.id.recyclerViewTour);
        // Spinner
        arraylistMien = new ArrayList<mdSpinner>();
        arraylistMien.add(new mdSpinner(R.drawable.sun_umbrella,"Tất Cả"));
        arraylistMien.add(new mdSpinner(R.drawable.itinerary,"Miền Nam"));
        arraylistMien.add(new mdSpinner(R.drawable.itinerary,"Miền Trung"));
        arraylistMien.add(new mdSpinner(R.drawable.itinerary,"Miền Bắc"));

        AdapterSpinner arrayAdapterMien = new AdapterSpinner(getContext(), R.layout.custom_spinner, arraylistMien);
        spinnerMien.setAdapter(arrayAdapterMien);


        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

        /*arrTour = new ArrayList<Tour>();
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/cao%20nguy%C3%AAn.jpg?alt=media&token=4e5186b9-dfc5-4cae-ab60-796d608237f5","01","Hà Nội- Cao Nguyên","3 Ngày 2 Đêm","1.900.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/haiem.jpg?alt=media&token=e7ced928-3502-4299-90ef-880a5df2631d","02","Hà Nội - Mộc Châu","3 Ngày 2 Đêm","200.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/Hoa%20anh%20%C4%91%C3%A0o.jpg?alt=media&token=c49a5614-1f77-4cd9-80a5-55c31c549b94","03","Hà Nội - Sơn La","3 Ngày 2 Đêm","1.000.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/h%E1%BB%93%20g%C6%B0%C6%A1m.jpg?alt=media&token=c2f545f8-d199-4634-80e2-1e0d1570db2d","04","Hà nội - Hồ Gươm","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img1.jpg?alt=media&token=32a83121-6efd-4e9c-b99f-db1e893c8477","05","TP.HCM - Đà Lạt","7 Ngày 6 Đêm","1.700.000 đ","Miền Nam", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_3.jpg?alt=media&token=7779fc97-a984-4a62-ba43-84e2f52e2c5f","06","TP.HCM - Phan Thiết","3 Ngày 2 Đêm","1.200.000 đ","Miền Nam", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_4.jpg?alt=media&token=6388c5ef-89b0-48ec-97b0-9151fdfc30af","07","Hà Nội - TPHCM","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_5.jpg?alt=media&token=013645c4-2da1-4a16-8767-e44258370ff6","08","Hà Nội - Hải Phòng","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_dalat.jpg?alt=media&token=28f6bef2-c14c-4a4e-bc6d-d3ac3e0293c3","09","Nha Trang - Cổ Thạch","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_nhatrang.jpg?alt=media&token=0b8b12a4-4a94-4fac-83de-687cf8cbf160","10","Nha Trang - Lagi","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/%C4%90%C3%A0%20N%E1%BA%B5ng.jpg?alt=media&token=4547b000-d480-4d0b-81b6-80a66aaf99ba","11","TP.HCM - Đà Nẵng","3 Ngày 2 Đêm","1.200.000 đ","Miền Nam", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/ph%C3%BA%20y%C3%AAn.jpg?alt=media&token=88c00ab8-0e7e-4628-bdd1-50428e238ac1","12","TP.HCM - Vũng Tàu","3 Ngày 2 Đêm","250.000 đ","Miền Nam",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/qu%C3%AA%20tui.jpg?alt=media&token=ad8415b9-4a13-4bf2-bc70-848777631855","13","Nha Trang - Lagi","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/nhatrang_ok.jpg?alt=media&token=6548aaf8-4b9f-4ab0-9f1a-dd76762fdc22","14","TP.HCM - Hà Tiên","3 Ngày 2 Đêm","1.200.000 đ","Miền Nam",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_2.jpg?alt=media&token=c247244b-74b8-40d7-8f77-97d9257621eb","15","TP.HCM - Vũng Tàu","3 Ngày 2 Đêm","250.000 đ","Miền Nam",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/m%E1%BB%99c%20ch%C3%A2u3.jpg?alt=media&token=c51cf6b5-87f9-41a5-b36e-72db69dc9e6f","16","Hà Nội- Cao Nguyên","3 Ngày 2 Đêm","1.900.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_vhl.jpg?alt=media&token=a216537c-3a2e-4d9e-8c06-52ed65f6d833","17","Hà Nội - Vịnh Hạ Long","3 Ngày 2 Đêm","200.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/m%E1%BB%99c%20ch%C3%A2u3.jpg?alt=media&token=c51cf6b5-87f9-41a5-b36e-72db69dc9e6f","18","Nha Trang - Sapa","3 Ngày 2 Đêm","1.000.000 đ","Miền Trung",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/Th%C3%A1p%20Ch%C4%83m.jpg?alt=media&token=c6feb10a-044e-4cb9-b9b5-b8578495c5d3","19","Nha Trang - Bình Ba","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/%C4%91%E1%BB%93ng%20v%C3%A2n.jpg?alt=media&token=68895251-e4a3-414a-a702-e6b175661bf2","20","TP.HCM - Châu Đốc","3 Ngày 2 Đêm","1.200.000 đ","Miền Nam",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/m%E1%BB%99c%20ch%C3%A2u.jpg?alt=media&token=b7840de9-cbf8-4907-8efd-b4c168ee2948","21","Hà Nội - Lạng Sơn","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/m%E1%BB%99c%20ch%C3%A2u2.jpg?alt=media&token=d8403878-0b9b-4d64-97d4-08cf007a2e36","22","Hà Nội - Quảng Bình","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/ph%C3%BA%20y%C3%AAn1.jpg?alt=media&token=0ae8baf1-ab83-4171-ae56-5e929eae9cc4","23","Nha Trang - Phú Yên","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/ph%C3%BA%20y%C3%AAn%202.jpg?alt=media&token=53812f1e-e49c-429c-bb14-7f16efae1306","24","Nha Trang - Lagi","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung",""));

        mData.child("Tour").setValue(arrTour);*/

        loadData();
        return view;
    }

    private void loadData() {

        arrTour = new ArrayList<Tour>();
        // Set adapter
        adapterTour = new AdapterTour(arrTour,getContext());
        recyclerViewTour.setAdapter(adapterTour);
        recyclerViewTour.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
        spinnerMien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                arrTour.clear();
                posi = i;
                mData.child("Tour").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        final Tour tour = dataSnapshot.getValue(Tour.class);
                        if (posi == 0)
                        {
                            arrTour.add(new Tour(tour.getImgProduct(),tour.getCodeTour(),tour.getAddTour(),tour.getDiscripTour(),tour.getPriceTour(),tour.getTenmien(),tour.getNoidung()));
                            adapterTour.notifyDataSetChanged();
                        }
                        else if (posi == 1 && tour.getTenmien().equalsIgnoreCase("Miền Nam"))
                        {
                            arrTour.add(new Tour(tour.getImgProduct(),tour.getCodeTour(),tour.getAddTour(),tour.getDiscripTour(),tour.getPriceTour(),tour.getTenmien(),tour.getNoidung()));
                            adapterTour.notifyDataSetChanged();
                        }else if (posi == 2 && tour.getTenmien().equalsIgnoreCase("Miền Trung")) {
                            arrTour.add(new Tour(tour.getImgProduct(),tour.getCodeTour(),tour.getAddTour(),tour.getDiscripTour(),tour.getPriceTour(),tour.getTenmien(),tour.getNoidung()));
                            adapterTour.notifyDataSetChanged();
                        }else if (posi == 3 && tour.getTenmien().equalsIgnoreCase("Miền Bắc")) {
                            arrTour.add(new Tour(tour.getImgProduct(),tour.getCodeTour(),tour.getAddTour(),tour.getDiscripTour(),tour.getPriceTour(),tour.getTenmien(),tour.getNoidung()));
                            adapterTour.notifyDataSetChanged();
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

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
