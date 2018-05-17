package com.example.group6.dulichdoday.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.Models.Tours;
import com.example.group6.dulichdoday.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class AdapterTourList extends RecyclerView.Adapter<AdapterTourList.ViewHolder> {

    ArrayList<Tours> arrNew;
    Context context;
    private Context applicationContext;
    DataSnapshot dataSnapshot;
    DatabaseError databaseError;
    //Query applesQuery;

    private DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    Query applesQuery = ref.child("TourDat").orderByChild("title");


    public AdapterTourList(ArrayList<Tours> arrNew, Context context) {
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

        /*Picasso.with(context).load(arrNew.get(position).getImgProduct()).into(holder.img);*/
        holder.tvCode.setText(arrNew.get(position).getTour_ID());
        holder.tvAdd.setText(arrNew.get(position).getTourName());
        holder.tvDesciption.setText(arrNew.get(position).getTourDescription());
        holder.tvPrice.setText(arrNew.get(position).getTourPrice());
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_listtour_reque);
        dialog.setCanceledOnTouchOutside(false);


        holder.img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog.show();
                TextView tvDelete = (TextView) dialog.findViewById(R.id.deleteTourList);
                TextView tvCancel = (TextView) dialog.findViewById(R.id.cancelTourList);
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        arrNew.remove(arrNew.get(position));
                        mData.child("TourDat").setValue(arrNew);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                tvCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrNew.size();
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tvCode;
        TextView tvAdd;
        TextView tvDesciption;
        TextView tvPrice;
        ImageView img;
        @RequiresApi(api = Build.VERSION_CODES.M)
        public ViewHolder(View itemView) {
            super(itemView);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_tour);
            tvAdd = (TextView) itemView.findViewById(R.id.tv_add_tour);
            tvDesciption = (TextView) itemView.findViewById(R.id.tv_discip_tour);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price_tour);
            img = (ImageView) itemView.findViewById(R.id.img_tour);
            //itemView.setOnCreateContextMenuListener(this);
        }

//        @Override
//        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//            MenuItem delete = contextMenu.add(Menu.NONE,1,1,"Delete");
//            MenuItem cancel = contextMenu.add(Menu.NONE,2,2,"Cancel");
//            delete.setOnMenuItemClickListener(onEditMenu);
//            cancel.setOnMenuItemClickListener(onEditMenu);
//        }

//        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case 1:
////                        arrNew.remove(getAdapterPosition());
////                        mData.child("TourDat").setValue(arrNew);
////                        notifyDataSetChanged();
//                        break;
//                    case 2:
//                        break;
//                }
//                return true;
//            }
//        };

    }
}
