package com.example.group6.dulichdoday;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.group6.dulichdoday.Adapter.AdapterPlaceTap;


/**
 * A simple {@link Fragment} subclass.
 */
public class TourFragment extends Fragment {

    private ViewPager mViewPager;

    public TourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tour, container, false);
        // InitView
        mViewPager = (ViewPager) view.findViewById(R.id.viewPagerPlace);
        mViewPager.setAdapter(new AdapterPlaceTap(getFragmentManager()));
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutPlace);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }


}
