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
//        ArrayList<mdLocation> arrayList = new ArrayList<>();
//        arrayList.add(new mdLocation("PHUC", 10.8522445, 106.7585893));
//        arrayList.add(new mdLocation("NHAN", 10.85140154, 106.75779537));
//        arrayList.add(new mdLocation("HOANG", 10.85468907, 106.75760225));
//        arrayList.add(new mdLocation("TRI", 10.85355108, 106.76069215));
//        arrayList.add(new mdLocation("SON", 10.85186517, 106.76075652));
//        mLocations.child("Locations").setValue(arrayList);
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
                                marker = mMap.addMarker(new MarkerOptions().position(latLng).title(venue.getStrNameAđ()));
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
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
