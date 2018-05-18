package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.Tours;
import com.example.group6.dulichdoday.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ASUS on 4/11/2018.
 */

public class AdapterBookTour extends RecyclerView.Adapter<AdapterBookTour.ViewHolder> {
    ArrayList<Tours> arrNew;
    Context context;

    public AdapterBookTour(ArrayList<Tours> arrNew,Context context){
        this.arrNew = arrNew;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_tour,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(arrNew.get(position).getImgTour()).into(holder.img);
        holder.tvCode.setText(arrNew.get(position).getTour_ID());
        holder.tvName.setText(arrNew.get(position).getTourName());
        holder.tvTime.setText(arrNew.get(position).getTourTime());
        holder.tvPrice.setText(arrNew.get(position).getTourPrice());
    }

    @Override
    public int getItemCount() {
        return arrNew.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;




        TextView tvCode;
        TextView tvName;
        TextView tvTime;
        TextView tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_tour);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_tour);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_tour);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_tour);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price_tour);
        }
    }
}
