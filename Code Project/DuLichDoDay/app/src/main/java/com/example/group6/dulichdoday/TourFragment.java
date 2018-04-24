package com.example.group6.dulichdoday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.group6.dulichdoday.Adapter.AdapterSpinner;
import com.example.group6.dulichdoday.Adapter.AdapterTour;
import com.example.group6.dulichdoday.Models.Tour;
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
        arraylistMien.add(new mdSpinner(R.drawable.northpole,"Tất Cả"));
        arraylistMien.add(new mdSpinner(R.drawable.itinerary,"Miền Nam"));
        arraylistMien.add(new mdSpinner(R.drawable.itinerary,"Miền Trung"));
        arraylistMien.add(new mdSpinner(R.drawable.itinerary,"Miền Bắc"));

        AdapterSpinner arrayAdapterMien = new AdapterSpinner(getContext(), R.layout.custom_spinner, arraylistMien);
        spinnerMien.setAdapter(arrayAdapterMien);


        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerViewTour.setHasFixedSize(true);
        recyclerViewTour.setLayoutManager(layoutManager);

     /*   arrTour = new ArrayList<Tour>();
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/cao%20nguy%C3%AAn.jpg?alt=media&token=87f511c2-8c39-45b4-8758-d236d3493fd3","01","Hà Nội- Cao Nguyên","3 Ngày 2 Đêm","1.900.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/haiem.jpg?alt=media&token=410d56a8-068d-4d61-8ba4-5f8f4562f430    ","02","Hà Nội - Mộc Châu","3 Ngày 2 Đêm","200.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/Hoa%20anh%20%C4%91%C3%A0o.jpg?alt=media&token=5173fc2f-c6e3-48de-9e75-be5b559ca4ab","03","Hà Nội - Sơn La","3 Ngày 2 Đêm","1.000.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img1.jpg?alt=media&token=83cc135a-da92-4cf6-a462-8c47cd52c1d7","04","TP.HCM - Đà Lạt","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/4.jpg?alt=media&token=17cee307-6d7f-47e0-a143-48a6781768c2","05","Nha Trang - Hà Nội","7 Ngày 6 Đêm","1.700.000 đ","Miền Trung", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/3.jpg?alt=media&token=1089b680-4fc5-4462-896a-1e6e57928fc7","06","TP.HCM - Phan Thiết","3 Ngày 2 Đêm","1.200.000 đ","Miền Nam", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_3.jpg?alt=media&token=9b77ab77-f5f7-4948-b715-b43c753c3446","07","Hà Nội - TPHCM","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_4.jpg?alt=media&token=4be56fb3-aada-4d2e-9c88-385dc0ad3241","08","Hà Nội - Hải Phòng","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_dalat.jpg?alt=media&token=6402eb5a-5c0f-459c-b333-0c0cf31a76e3","09","Nha Trang - Cổ Thạch","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_nhatrang.jpg?alt=media&token=8c30e370-6d8a-4ae3-8a48-2f62551153ab","10","Nha Trang - Lagi","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/%C4%90%C3%A0%20N%E1%BA%B5ng.jpg?alt=media&token=74ce9a46-310a-435f-b462-b0e480297c6c","11","TP.HCM - Đà Nẵng","3 Ngày 2 Đêm","1.200.000 đ","Miền Nam", ""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/m%E1%BB%99c%20ch%C3%A2u.jpg?alt=media&token=dbe8ede5-3d41-4266-b505-b73bb8c761a7","12","TP.HCM - Vũng Tàu","3 Ngày 2 Đêm","250.000 đ","Miền Nam",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/m%E1%BB%99c%20ch%C3%A2u2.jpg?alt=media&token=2ad612e7-0e2d-4aad-810d-3643c9ee40f6","13","Nha Trang - Lagi","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/nhatrang_ok.jpg?alt=media&token=44434127-5f5c-40c7-b7d9-77f7306b2fd5","14","TP.HCM - Hà Tiên","3 Ngày 2 Đêm","1.200.000 đ","Miền Nam",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/ph%C3%BA%20y%C3%AAn%202.jpg?alt=media&token=8d83d29b-a381-4798-b879-a36b150f91a4","15","TP.HCM - Bàu Trắng","3 Ngày 2 Đêm","250.000 đ","Miền Nam",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/sapa.jpg?alt=media&token=886adb44-0202-4bde-8875-99e05d9882b6","16","Hà Nội- Cao Nguyên","3 Ngày 2 Đêm","1.900.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/img_vhl.jpg?alt=media&token=ab5423ea-ea89-4ef4-9587-8f15f7f65f45","17","Hà Nội - Vịnh Hạ Long","3 Ngày 2 Đêm","200.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/m%E1%BB%99c%20ch%C3%A2u3.jpg?alt=media&token=b84dff45-4ee9-43e8-8716-3c29d4626046","18","Nha Trang - Sapa","3 Ngày 2 Đêm","1.000.000 đ","Miền Trung",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/ph%C3%BA%20y%C3%AAn.jpg?alt=media&token=043137de-8210-4851-830a-c82aa6ae257f","19","Nha Trang - Bình Ba","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/2.jpg?alt=media&token=d90eebdf-cbc3-4e10-9fa2-38186168091c","20","TP.HCM - Tam Đảo","3 Ngày 2 Đêm","1.200.000 đ","Miền Nam",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/%C4%91%E1%BB%93ng%20v%C3%A2n.jpg?alt=media&token=bfb020a1-5512-4d98-bb3b-71f8d6d923aa","21","Hà Nội - Lạng Sơn","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/Th%C3%A1p%20Ch%C4%83m.jpg?alt=media&token=140ca4e5-80bf-4461-8410-2a89b45c2d3a","22","Hà Nội - Quảng Bình","3 Ngày 2 Đêm","1.200.000 đ","Miền Bắc",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/1.JPG?alt=media&token=c5655b3d-e4e3-4499-af2c-e3e3daa913e0","23","Nha Trang - Mộc Châu","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung",""));
        arrTour.add(new Tour("https://firebasestorage.googleapis.com/v0/b/dulichdoday-7d0dd.appspot.com/o/qu%C3%AA%20tui.jpg?alt=media&token=0f94d179-e2f4-4ee3-8cd7-d793af701222","24","Nha Trang - Lagi","3 Ngày 2 Đêm","1.200.000 đ","Miền Trung",""));

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
