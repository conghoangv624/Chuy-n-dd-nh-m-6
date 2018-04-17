package com.example.group6.dulichdoday.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group6.dulichdoday.R;
import com.example.group6.dulichdoday.Models.Comment;
import com.example.group6.dulichdoday.Models.New;

import java.util.ArrayList;

/**
 * Created by Nhan IT on 4/9/2018.
 */

public class AdapterNew extends RecyclerView.Adapter<AdapterNew.ViewHolder> {

    ArrayList<New> arrNew;
    Context context;

    private RecyclerView recyclerComment;
    private ArrayList<Comment> arrayListComment;
    private AdapterComment adapterComment;
    private TextView tv;


    public AdapterNew(ArrayList<New> arrNew, Context context) {
        this.arrNew = arrNew;
        this.context = context;
    }

    @Override
    public AdapterNew.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_menu_new,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterNew.ViewHolder holder, final int position) {

        holder.imgTile.setImageResource(arrNew.get(position).getImgProduct());
        holder.tvTitle.setText(arrNew.get(position).getDescription());

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

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.dialog_comment);


        recyclerComment = (RecyclerView) dialog.findViewById(R.id.recyclerComment);
        tv = (TextView) dialog.findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý hiển thị recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
        recyclerComment.setHasFixedSize(true);
        recyclerComment.setLayoutManager(layoutManager);

        // Thêm bình luận luận vào recylerView
        arrayListComment = new ArrayList<Comment>();
        arrayListComment.add(new Comment(R.drawable.img_user,"Đây là nới chúng tôi muốn tới nhất"));
        arrayListComment.add(new Comment(R.drawable.img_user,"Tôi cũng muốn tới lắm nhưng chưa có tiền"));
        arrayListComment.add(new Comment(R.drawable.img_user,"Wow , quá tuyệt vời luôn"));
        arrayListComment.add(new Comment(R.drawable.img_user,"Đây là vẻ đẹp của việt nam chăng"));
        arrayListComment.add(new Comment(R.drawable.img_user,"=)))))))))) , Bình thường thôi"));
        arrayListComment.add(new Comment(R.drawable.img_user,"Đây là nới chúng tôi muốn tới nhất"));

        // Xử lý hiển  thị adapter
        adapterComment = new AdapterComment(arrayListComment,context);
        recyclerComment.setAdapter(adapterComment);
        recyclerComment.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());



        ImageView imgExit = (ImageView) dialog.findViewById(R.id.exit_dialog);

        holder.imgCommt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
            }
        });

        imgExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrNew.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvNumberLike;
        TextView tvNumberUnlike;
        TextView tvNumberCommment;
        ImageView imgTile;
        CheckBox cbxHomeLike;
        CheckBox cbxHomeUnlike;
        ImageView imgCommt;

        public ViewHolder(View itemView) {
            super(itemView);
            imgTile = (ImageView) itemView.findViewById(R.id.img_title);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

            tvNumberLike = (TextView) itemView.findViewById(R.id.tv_number_like);
            tvNumberUnlike = (TextView) itemView.findViewById(R.id.tv_number_unlike);
            tvNumberCommment = (TextView) itemView.findViewById(R.id.tv_number_comment);
            cbxHomeLike = (CheckBox) itemView.findViewById(R.id.cbx_like);
            cbxHomeUnlike = (CheckBox) itemView.findViewById(R.id.cbx_unlike);

            imgCommt = (ImageView) itemView.findViewById(R.id.cbx_comment);

        }
    }

}
