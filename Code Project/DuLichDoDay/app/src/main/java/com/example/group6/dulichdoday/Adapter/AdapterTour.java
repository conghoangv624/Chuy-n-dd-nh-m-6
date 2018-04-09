package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.group6.dulichdoday.Models.Tour;
import com.example.group6.dulichdoday.R;

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

        return new AdapterTour.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterTour.ViewHolder holder, final int position) {
        holder.img.setImageResource(arrNew.get(position).getImgProduct());
        holder.tvCode.setText(arrNew.get(position).getCodeTour());
        holder.add.setText(arrNew.get(position).getAddTour());
        holder.descip.setText(arrNew.get(position).getDiscripTour());
        holder.price.setText(arrNew.get(position).getPriceTour());
        holder.tvNumberLike.setText(arrNew.get(position).getnNumberLike());
        holder.tvNumberUnlike.setText(arrNew.get(position).getnNumberUnlike());
        holder.tvNumberCommment.setText(arrNew.get(position).getnNumberComment());

        holder.cbxHomeLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( holder.cbxHomeLike.isChecked())
                {
                    arrNew.get(position).setCheckLike(true);
                }
                else
                {
                    arrNew.get(position).setCheckLike(false);
                }
            }
        });

        holder.cbxHomeUnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( holder.cbxHomeUnlike.isChecked())
                {
                    arrNew.get(position).setCheckUnLike(true);
                }
                else
                {
                    arrNew.get(position).setCheckUnLike(false);
                }
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
        TextView add;
        TextView descip;
        TextView price;
        TextView tvNumberLike;
        TextView tvNumberUnlike;
        TextView tvNumberCommment;
        CheckBox cbxHomeLike;
        CheckBox cbxHomeUnlike;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img_tour);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code_tour);
            add = (TextView) itemView.findViewById(R.id.tv_add_tour);
            descip = (TextView) itemView.findViewById(R.id.tv_discip_tour);
            price = (TextView) itemView.findViewById(R.id.tv_price_tour);
            tvNumberLike = (TextView) itemView.findViewById(R.id.tv_number_like);
            tvNumberUnlike = (TextView) itemView.findViewById(R.id.tv_number_unlike);
            tvNumberCommment = (TextView) itemView.findViewById(R.id.tv_number_comment);
            cbxHomeLike = (CheckBox) itemView.findViewById(R.id.cbx_like);
            cbxHomeUnlike = (CheckBox) itemView.findViewById(R.id.cbx_unlike);
        }
    }
}
