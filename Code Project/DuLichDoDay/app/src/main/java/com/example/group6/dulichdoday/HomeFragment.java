package com.example.group6.dulichdoday;


import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.Models.mdLocation;
import com.example.group6.dulichdoday.Models.mdTravel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {

    private ImageView imgSetGPSLocation;
    // EDT search map
    private EditText edtSearchMap;
    // MAP : Add Manifest
    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;

    private static final String COUASE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    // Camera zoom 15f
    private static final float DEFAULT_ZOOM = 15f;
    private Boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    // API : user compile "com.google.android.gms:play-services-location:12.0.1"
    private FusedLocationProviderClient mFusedLocationProviderClient;
    // ADAPTER autocomplete search location
    //
    // DISPLAY LOCATION
    private ChildEventListener mChildEventListener;
    private DatabaseReference mLocations;
    Marker marker;
    List<mdLocation> venueList;
    private DatabaseReference mTravel;
    private ArrayList<mdTravel> arrayListTravel;

    private GoogleApiClient mGoogleApiClient;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40,-168),new LatLng(71,136));



    public HomeFragment() {
        // Required empty public constructor
    }


   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        edtSearchMap = (EditText) view.findViewById(R.id.edt_search_map);
        imgSetGPSLocation = (ImageView) view.findViewById(R.id.img_set_gps);
        getLocationPermisssion();
        initMap();
        mLocations = FirebaseDatabase.getInstance().getReference();
        mLocations.push().setValue(marker);
        ArrayList<mdLocation> arrayList = new ArrayList<>();


       arrayList.add(new mdLocation("KDL Suối Tiên","", 10.861760, 106.802324));
       arrayList.add(new mdLocation("Đầm sen tam đa ","", 10.794751, 106.842922));
       arrayList.add(new mdLocation("Đền thờ tổ nghiệp nghệ sỹ hoài linh ","", 10.824624, 106.864849));
       arrayList.add(new mdLocation("Khu du lịch BCR","", 10.807047, 106.842622));
       arrayList.add(new mdLocation("Nhà thờ Đức Bà","", 48.853018, 2.349891));
       arrayList.add(new mdLocation("Nhà thờ tân định ","", 10.789194, 106.690383));
       arrayList.add(new mdLocation("Bảo tàng lịch sử  ","", 10.788027, 106.704750));
       arrayList.add(new mdLocation("Bảo tàng thành phố","", 10.776052, 106.699646));
       arrayList.add(new mdLocation("Bảo tàng chiến tranh ","", 10.779502, 106.692138));
       arrayList.add(new mdLocation("Chợ bến thành","", 10.772689, 106.698041));
       arrayList.add(new mdLocation("Thảo cầm viên ","", 10.787305, 106.705039));
       arrayList.add(new mdLocation("Đầm sen ","", 10.766115, 106.638833));
       arrayList.add(new mdLocation("Công viên nước Đại Thế giới ","", 10.751450, 106.668768));
       arrayList.add(new mdLocation("Công viên thỏ trắng ","", 10.785525, 106.665884));
       arrayList.add(new mdLocation("Dinh Độc lập ","", 10.777265, 106.695482));
       arrayList.add(new mdLocation("Địa dạo củ chi ","", 11.143755, 106.464463));
       arrayList.add(new mdLocation("Phố đi bộ Nguyễn Huệ","", 10.774419, 106.703439));
       arrayList.add(new mdLocation("Bến bạch đằng","", 10.773465, 106.706341));

       arrayList.add(new mdLocation("Chùa Thích Ca Phật Đài","", 10.374101, 107.071320));
       arrayList.add(new mdLocation("Đồi Con Heo","", 10.327739, 107.086692));
       arrayList.add(new mdLocation("Tượng Chúa GiêSu Kitô Vua","", 10.326542, 107.084538));
       arrayList.add(new mdLocation("Công viên Thỏ Trắng","", 10.345777, 107.096436));
       arrayList.add(new mdLocation("Ngọn Hải Đăng ","", 10.334521, 107.077638));
       arrayList.add(new mdLocation("Khu du lịch Hồ Mây ","", 10.359687, 107.068083));
       arrayList.add(new mdLocation("Bạch Dinh","", 10.350697, 107.067833));
       arrayList.add(new mdLocation("Du lịch Cáp treo Vũng Tàu","", 10.351110, 107.066579));
       arrayList.add(new mdLocation("Bảo tàng tỉnh Bà Rịa - Vũng Tàu","", 10.350428, 107.069581));
       arrayList.add(new mdLocation("Công Viên Bãi Trước","", 10.342035, 107.074336));
       arrayList.add(new mdLocation("Mũi Nghinh Phong","", 10.320748, 107.083605));

       arrayList.add(new mdLocation("Trường Cao đẳng sư phạm Đà Lạt","", 11.945346, 108.451742));
       arrayList.add(new mdLocation("Ga Đà Lạt","", 11.941746, 108.454432));
       arrayList.add(new mdLocation("TKhu Du Lịch Thung Lũng Vàng","", 12.007104, 108.382981));
       arrayList.add(new mdLocation("Du Lịch Đất Sét","", 11.882604, 108.411265));
       arrayList.add(new mdLocation("Dinh I ","", 11.944470, 108.469786));
       arrayList.add(new mdLocation("Ma Rừng Lữ Quán","", 12.011113, 108.347432));
       arrayList.add(new mdLocation("Thác Voi","", 11.823320, 108.334795));
       arrayList.add(new mdLocation("Chợ đêm Đà Lạt","", 11.943073, 108.436806));
       arrayList.add(new mdLocation("Làng hoa Đà Lạt","", 11.945864, 108.412400));
       arrayList.add(new mdLocation("Hồ Xuân Hương","", 11.942639,  108.447039));
       arrayList.add(new mdLocation("Bảo tàng Lâm Đồng","", 11.940488, 108.459889));

       arrayList.add(new mdLocation("Chợ Đồng Xuân","", 21.038361, 105.849449));
       arrayList.add(new mdLocation("Hồ Tây","", 21.055148, 105.825882));
       arrayList.add(new mdLocation("Dairy Queen Royal City ","", 21.003324, 105.815167));
       arrayList.add(new mdLocation("Sân vận động Mỹ Đình","", 21.043761, 105.919538));
       arrayList.add(new mdLocation("Chợ Đêm Phố Cổ Hà Nội","", 21.033420, 105.850940));
       arrayList.add(new mdLocation("Thủy cung Modernlife","", 20.994874, 105.867513));
       arrayList.add(new mdLocation("Hồ Hoàn Kiếm","", 21.028905, 105.852150));
       arrayList.add(new mdLocation("Tượng đài Vladimir Lenin","", 21.031774, 105.839494));
       arrayList.add(new mdLocation("Nhà Hát Lớn Hà Nội","", 21.024315, 105.857438));
       arrayList.add(new mdLocation("Lăng Chủ Tịch Hồ Chí Minh","", 21.036891, 105.834642));
       arrayList.add(new mdLocation("Văn Miếu Môn","", 21.027674, 105.835510));
       arrayList.add(new mdLocation("Hoàng Thành Thăng Long","", 21.037136, 105.839871));
       arrayList.add(new mdLocation("Quảng trường Ba Đình","", 21.037672, 105.836031));
       arrayList.add(new mdLocation("Chùa Một Cột","", 21.035990, 105.833570));
       arrayList.add(new mdLocation("Bảo tàng Hồ Chí Minh","", 21.035370, 105.832837));
       arrayList.add(new mdLocation("Tượng Đài Lê Nin","", 21.031771, 105.839494));
       arrayList.add(new mdLocation("Bảo Tàng Mỹ Thuật Việt Nam","", 21.030933, 105.837050));
       arrayList.add(new mdLocation("Cung thể thao Quần Ngựa","", 21.040289, 105.814361));
       arrayList.add(new mdLocation("Bảo tàng Pháo binh","", 21.038528, 105.808519));
       arrayList.add(new mdLocation("Đền quán Thanh","", 21.043059, 105.836378));

       arrayList.add(new mdLocation("Vườn Quốc Gia U Minh Thượng","", 9.599171, 105.090772));
       arrayList.add(new mdLocation("Núi đá dựng","", 10.427932, 104.476295));
       arrayList.add(new mdLocation("Quần Đảo Nam Du","", 9.684241, 104.352650));
       arrayList.add(new mdLocation("Hòm Trẹm","", 10.143618, 104.628087));
       arrayList.add(new mdLocation("Vịnh Hòn Chông","", 10.147085, 104.599714));
       arrayList.add(new mdLocation("Đảo Phú Quốc","", 10.292328, 103.983762));
       arrayList.add(new mdLocation("Vườn Quốc Gia Phú  Quốc","", 10.292328, 103.983762));
       arrayList.add(new mdLocation("Chùa Ratanaransĩ (Chùa Láng Cát)","", 9.999504, 105.094487));
       arrayList.add(new mdLocation("Công viên Bãi Dương","", 9.992073, 105.083809));
       arrayList.add(new mdLocation("Khu du lịch Mũi Nai","", 10.383137, 104.448182));
       arrayList.add(new mdLocation("Khu du lịch Đồi Nai Vàng","", 10.383599, 104.444384));

       mLocations.child("Locations").setValue(arrayList);
       mTravel = FirebaseDatabase.getInstance().getReference();

        return view;
    }
     // Get location device , Show where we are
    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        try {
            if (mLocationPermissionGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();
                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM, "MyLocation");
                        } else {
                            Toast.makeText(getContext(), "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
        }
    }

    // Display marker , camera , zom , title location
    private void moveCamera(LatLng latLng, float zoom, String title) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        // Other location
        if (!title.equals("MyLocation")) {
            // Display marker in map
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title);
            mMap.addMarker(markerOptions);
        }
        // HIDE KEY
        hideKeySoftWindow();

    }

    private void init() {
        edtSearchMap.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == keyEvent.ACTION_DOWN
                        || keyEvent.getAction() == keyEvent.KEYCODE_ENTER) {
                    geoLocation();
                }
                return false;
            }
        });

        // Set location with image gps
        imgSetGPSLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDeviceLocation();
            }
        });
        // HIDE KEY
        hideKeySoftWindow();
    }

    // Search location
    private void geoLocation() {
        // Get location
        String searchString = edtSearchMap.getText().toString();
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (Exception e) {
        }
        // List >0 character
        if (list.size() > 0) {
            Address address = list.get(0);
            // After search succesful , return location
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
    }

    // Get map , Map successfull , Map Ready display
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(getContext(), "Map is ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(getContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            // Function edtSearch return location search
            init();
            googleMap.setOnMarkerClickListener(this);
            mLocations.child("Locations").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot s : dataSnapshot.getChildren()){
                        final mdLocation venue = s.getValue(mdLocation.class);
                        venueList  = new ArrayList<>();
                        venueList.add(venue);
                        for (int i = 0; i < venueList.size(); i++)
                        {
                            LatLng latLng = new LatLng(venue.getLatitude(),venue.getLongitude());
                            if (mMap != null) {
                                marker = mMap.addMarker(new MarkerOptions().position(latLng).title(venue.getStrNameAđ()).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
//            mLocations.child("Travel").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for (DataSnapshot s : dataSnapshot.getChildren()) {
//                        final mdTravel venue = s.getValue(mdTravel.class);
//                        arrayListTravel = new ArrayList<mdTravel>();
//                        arrayListTravel.add(venue);
//
//                        for (int i = 0; i < arrayListTravel.size(); i++) {
//                            LatLng latLng = new LatLng(venue.getLan(), venue.getLog());
//                            if (mMap != null) {
//                                marker = mMap.addMarker(new MarkerOptions().position(latLng).title(venue.getNameTravel() + "\n" + venue.getAddressTravel()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_gps)));
//                            }
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
        }
    }

    private void getLocationPermisssion(){
        String[] permission = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getContext(),FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getContext(),COUASE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            }else {
                ActivityCompat.requestPermissions(getActivity(),permission,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else {
            ActivityCompat.requestPermissions(getActivity(),permission,LOCATION_PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0;i < grantResults.length; i++) {
                        if (grantResults[0] != PackageManager.PERMISSION_GRANTED )
                            mLocationPermissionGranted = false;
                        return;
                    }
                    mLocationPermissionGranted = true;
                    //Initalize our map
                    initMap();
                }
            }
        }
    }

    private void hideKeySoftWindow() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(getContext(), "Return", Toast.LENGTH_SHORT).show();
        return false;
    }
}
