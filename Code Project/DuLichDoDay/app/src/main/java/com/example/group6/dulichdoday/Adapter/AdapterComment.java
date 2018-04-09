package com.example.group6.dulichdoday.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group6.dulichdoday.R;

import java.util.ArrayList;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder> {

    ArrayList<com.example.group6.dulichdoday.Models.Comment> arrComment;
    Context context;

    public AdapterComment(ArrayList<com.example.group6.dulichdoday.Models.Comment> arrComment, Context context) {
        this.arrComment = arrComment;
        this.context = context;
    }

    @Override
    public AdapterComment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_comment_new,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterComment.ViewHolder holder, int position) {
        holder.imgUserComment.setImageResource(arrComment.get(position).getImgUserComment());
        holder.tvUserComment.setText(arrComment.get(position).getTvUserComment());
    }

    @Override
    public int getItemCount() {
        return arrComment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUserComment;
        TextView tvUserComment;

        public ViewHolder(View itemView) {
            super(itemView);

            imgUserComment = (ImageView) itemView.findViewById(R.id.img_user_comment);
            tvUserComment = (TextView) itemView.findViewById(R.id.tv_user_comment);
        }
    }

}
