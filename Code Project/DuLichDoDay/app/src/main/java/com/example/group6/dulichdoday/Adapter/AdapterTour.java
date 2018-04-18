package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.DetailTourActivity;
import com.example.group6.dulichdoday.Models.Tour;
import com.example.group6.dulichdoday.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class AdapterTour extends RecyclerView.Adapter<AdapterTour.ViewHolder> {

    ArrayList<Tour> arrNew;
    Context context;

    public AdapterTour(ArrayList<Tour> arrNew, Context context) {
        this.arrNew = arrNew;
        this.context = context;
    }

    @Override
    public AdapterTour.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_tour,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterTour.ViewHolder holder, final int position) {

        Picasso.with(context).load(arrNew.get(position).getImgProduct()).into(holder.img);
        holder.tvCode.setText(arrNew.get(position).getCodeTour());
        holder.tvAdd.setText(arrNew.get(position).getAddTour());
        holder.tvDesciption.setText(arrNew.get(position).getDiscripTour());
        holder.tvPrice.setText(arrNew.get(position).getPriceTour());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, DetailTourActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tour",arrNew.get(position).getCodeTour());
                myIntent.putExtra("bundle", bundle);
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrNew.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvCode;
        TextView tvAdd;
        TextView tvDesciption;
        TextView tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_tour);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_tour);
            tvAdd = (TextView) itemView.findViewById(R.id.tv_add_tour);
            tvDesciption = (TextView) itemView.findViewById(R.id.tv_discip_tour);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price_tour);
        }
    }
}
