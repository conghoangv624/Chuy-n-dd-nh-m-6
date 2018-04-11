package com.example.group6.dulichdoday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import com.example.group6.dulichdoday.Adapter.AdapterNew;
import com.example.group6.dulichdoday.Models.New;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private ArrayList<New> arrayListSP;
    private RecyclerView recyclerView;
    private AdapterNew adapterNew;
    private Spinner spinnerHome;
    private EditText editTextSearch;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //spinnerHome = (Spinner) view.findViewById(R.id.spinnerHome);
//        editTextSearch = (EditText) view.findViewById(R.id.edtSearchHome);
//        String location = editTextSearch.getText().toString();



        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(HomeFragment.this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

//        final ArrayList<String> arrHome = new ArrayList<String>();
//        arrHome.add("TP.HCM");
//        arrHome.add("Cần Thơ");
//        arrHome.add("Tiền Giang");
//        arrHome.add("Đà Lạt");
//        arrHome.add("Vũng Tàu");
//
//        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrHome);
//        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
//        spinnerHome.setAdapter(adapter);
//
//        spinnerHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (arrHome.get(i) == "0")
//                {
//                    LatLng sydney = new LatLng(10.8230989, 106.6296638);
//                }
//                else if (arrHome.get(i) == "1")
//                {
//                    LatLng sydney = new LatLng(10.8230989, 106.6296638);
//                }
//                else if (arrHome.get(i) == "2")
//                {
//
//                }
//                else if (arrHome.get(i) == "3")
//                {
//
//                }
//                else if (arrHome.get(i) == "4")
//                {
//
//                }
//            }
//        });


        //double a = 10.850789;
        //double b = 106.758846;
        LatLng sydney = new LatLng(10.850789, 106.758846);

        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("TravelLocation")
                .snippet("Trường Cao Đẳng Công Nghệ Thủ Đức")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_cook)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
    }

//    public void getLocation (View view) throws IOException {
//        editTextSearch = (EditText) view.findViewById(R.id.edtSearchHome);
//        String location = editTextSearch.getText().toString();
//
//        //
//        Geocoder gc = new Geocoder(getContext());
//        List<Address> list = gc.getFromLocationName(location,1);
//        Address address = list.get(0);
//        String locationty = address.getLocality();
//
//
//
//    }


}
