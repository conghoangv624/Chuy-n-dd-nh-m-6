package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.Tours;
import com.example.group6.dulichdoday.Models.UserInfor;
import com.example.group6.dulichdoday.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ASUS on 4/11/2018.
 */

public class AdapterManagerBookTour extends RecyclerView.Adapter<AdapterManagerBookTour.ViewHolder> {
    ArrayList<Tours> arrNew;
    ArrayList<UserInfor> arrUser;
    Context context;

    public AdapterManagerBookTour(ArrayList<Tours> arrNew, ArrayList<UserInfor> arrUser, Context context) {
        this.arrNew = arrNew;
        this.arrUser = arrUser;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_booking_tour, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(arrNew.get(position).getImgTour()).into(holder.img);

       // holder.tvUserName.setText(position + ". " + arrUser.get(position).getName());
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
        TextView tvUserName;
        TextView tvCode;
        TextView tvName;
        TextView tvTime;
        TextView tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_tour);
            tvUserName = (TextView) itemView.findViewById(R.id.idUserbooktour);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_tour);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_tour);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_tour);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price_tour);
        }
    }
}
