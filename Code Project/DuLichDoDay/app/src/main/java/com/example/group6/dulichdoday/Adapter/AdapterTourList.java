package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.Tour;
import com.example.group6.dulichdoday.R;

import java.util.ArrayList;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class AdapterTourList extends RecyclerView.Adapter<AdapterTourList.ViewHolder> {

    ArrayList<Tour> arrNew;
    Context context;

    public AdapterTourList(ArrayList<Tour> arrNew, Context context) {
        this.arrNew = arrNew;
        this.context = context;
    }

    @Override
    public AdapterTourList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_tour_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterTourList.ViewHolder holder, final int position) {
        holder.tvCode.setText(arrNew.get(position).getCodeTour());
        holder.tvAdd.setText(arrNew.get(position).getAddTour());
        holder.tvDesciption.setText(arrNew.get(position).getDiscripTour());
        holder.tvPrice.setText(arrNew.get(position).getPriceTour());
    }

    @Override
    public int getItemCount() {
        return arrNew.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCode;
        TextView tvAdd;
        TextView tvDesciption;
        TextView tvPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_tour);
            tvAdd = (TextView) itemView.findViewById(R.id.tv_add_tour);
            tvDesciption = (TextView) itemView.findViewById(R.id.tv_discip_tour);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price_tour);
        }
    }
}
