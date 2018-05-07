package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.group6.dulichdoday.EditPlaceActivity;
import com.example.group6.dulichdoday.Models.Tour;
import com.example.group6.dulichdoday.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class AdapterTourList extends RecyclerView.Adapter<AdapterTourList.ViewHolder> {

    ArrayList<Tour> arrNew;
    Context context;
    private Context applicationContext;
    DataSnapshot dataSnapshot;
    DatabaseError databaseError;
    //Query applesQuery;

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Query applesQuery = ref.child("Tour").orderByChild("title").equalTo("Apple");


    public AdapterTourList(ArrayList<Tour> arrNew, Context context) {
        this.arrNew = arrNew;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public AdapterTourList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_tour_list,parent,false);
        return new ViewHolder(itemView);
    }

     /* applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                appleSnapshot.getRef().removeValue();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(TAG, "onCancelled", databaseError.toException());
        }
    });*/

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

    public Context getApplicationContext() {
        return applicationContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView tvCode;
        TextView tvAdd;
        TextView tvDesciption;
        TextView tvPrice;
        @RequiresApi(api = Build.VERSION_CODES.M)
        public ViewHolder(View itemView) {
            super(itemView);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_tour);
            tvAdd = (TextView) itemView.findViewById(R.id.tv_add_tour);
            tvDesciption = (TextView) itemView.findViewById(R.id.tv_discip_tour);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price_tour);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuItem delete = contextMenu.add(Menu.NONE,1,1,"Delete");
            MenuItem cancel = contextMenu.add(Menu.NONE,2,2,"Cancel");
            delete.setOnMenuItemClickListener(onEditMenu);
            cancel.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case 1:
                        arrNew.remove(getAdapterPosition());
                        mData.child("BookTour").setValue(arrNew);
                        notifyDataSetChanged();
                        break;
                    case 2:
                        break;
                }
                return true;
            }
        };
    }
}

    /*ArrayList<Tour> arrNew;
    Context context;

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

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
                    bundle.putString("tours",arrNew.get(getItemCount()).getAddTour());
                    intent.putExtra("bundle",bundle);
                    //intent.putExtra("position",getAdapterPosition());
                    context.startActivity(intent);
                    break;
                case 2:
                    arrNew.remove(getItemCount());
                    mData.child("Tours").setValue(arrNew);
                    notifyDataSetChanged();
                    break;
            }
            return true;
        }
    };*/
