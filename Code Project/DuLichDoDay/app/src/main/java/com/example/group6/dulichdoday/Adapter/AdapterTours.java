package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.BookTour;
import com.example.group6.dulichdoday.EditPlaceActivity;
import com.example.group6.dulichdoday.Models.Tours;
import com.example.group6.dulichdoday.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HoangNghiep IT on 03/05/2018.
 */

public class AdapterTours extends RecyclerView.Adapter<AdapterTours.ViewHolder> {
    ArrayList<Tours> arrNew;
    Context context;
    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public AdapterTours(ArrayList<Tours> arrNew, Context context) {
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
        holder.tvAdd.setText(arrNew.get(position).getTourName());
        holder.tvDesciption.setText(arrNew.get(position).getTourTime());
        holder.tvPrice.setText(arrNew.get(position).getTourPrice());
    }

    @Override
    public int getItemCount() {
        return arrNew.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
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
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem edit = contextMenu.add(Menu.NONE,1,1,"Edit");
            MenuItem delete = contextMenu.add(Menu.NONE,2,2,"Delete");
            edit.setOnMenuItemClickListener(onEditMenu);
            delete.setOnMenuItemClickListener(onEditMenu);
        }
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case 1:
                        Intent intent = new Intent(context, EditPlaceActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("tours",arrNew.get(getAdapterPosition()).getTour_ID());
                        intent.putExtra("bundle",bundle);
                        //intent.putExtra("position",getAdapterPosition());
                        context.startActivity(intent);
                        break;
                    case 2:
                        arrNew.remove(getAdapterPosition());
                        mData.child("Tours").setValue(arrNew);
                        notifyDataSetChanged();
                        break;
                }
                return true;
            }
        };
    }
}
