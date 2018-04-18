package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.mdSpinner;
import com.example.group6.dulichdoday.R;

import java.util.List;

/**
 * Created by DELL on 4/18/2018.
 */

public class AdapterSpinner extends BaseAdapter {

    Context context;
    int myLayout;
    List<mdSpinner> arraySpinner;

    public AdapterSpinner(Context context, int myLayout, List<mdSpinner> arraySpinner) {
        this.context = context;
        this.myLayout = myLayout;
        this.arraySpinner = arraySpinner;
    }

    @Override
    public int getCount() {
        return arraySpinner.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(myLayout,null);

        ImageView img = (ImageView) view.findViewById(R.id.imgSpinner);
        TextView tv = (TextView) view.findViewById(R.id.tvSpinner);

        tv.setText(arraySpinner.get(i).getTxt());
        img.setImageResource(arraySpinner.get(i).getIMG());
        return view;
    }
}
