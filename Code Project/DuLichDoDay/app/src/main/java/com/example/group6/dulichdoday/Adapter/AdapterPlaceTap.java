package com.example.group6.dulichdoday.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.group6.dulichdoday.AllTabFragment;
import com.example.group6.dulichdoday.SouthTabFragment;
import com.example.group6.dulichdoday.DomainTabFragment;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class AdapterPlaceTap extends FragmentPagerAdapter {


    private String listTab[] = {"Tất cả","Miền Nam","Miền Bắc"};
    private AllTabFragment allTabFragment;
    private DomainTabFragment domainTabFragment;
    private SouthTabFragment southTabFragment;


    public AdapterPlaceTap(FragmentManager fm) {
        super(fm);
        allTabFragment = new AllTabFragment();
        domainTabFragment = new DomainTabFragment();
        southTabFragment = new SouthTabFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            // Locate and Return to the Top Screen Review
            return allTabFragment;
        }else if (position == 1) {
            // Locate and Return to the Food Screen
            return domainTabFragment;
        }else if (position == 2) {
            // Locate and Return to the Drink Screen
            return southTabFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
