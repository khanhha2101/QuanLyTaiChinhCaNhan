package com.example.quanlytaichinhcanhan.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HoatDongAdapter extends RecyclerView.Adapter<HoatDongAdapter.ViewHolder>{
    private Context context;
    private ArrayList<hoatdong> mlist;
    private HoatDongClickItem clickItem;

    public HoatDongAdapter(Context context, HoatDongClickItem clickItem) {
        this.clickItem = clickItem;
        this.context = context;
    }

    public void setData (ArrayList<hoatdong> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoatdong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        hoatdong hoatdong = mlist.get(position);

        holder.tv_tendm.setText(hoatdong.getTendm());
        holder.tv_ghichu.setText(hoatdong.getGhichu());

        if (hoatdong.getPhanloai() == 1){
            holder.tv_sotien.setText("-"+currencyFormat(hoatdong.getSotien()));
            holder.tv_sotien.setTextColor(Color.RED);
        } else {
            holder.tv_sotien.setText("+"+currencyFormat(hoatdong.getSotien()));
            holder.tv_sotien.setTextColor(Color.GREEN);
        }


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(hoatdong.getImage(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.img_ldm);


        holder.item_hoatdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.onItemClick(hoatdong);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_ldm;
        private TextView tv_tendm, tv_ghichu, tv_sotien;
        LinearLayout item_hoatdong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_ldm = itemView.findViewById(R.id.img_ldm_hd);
            tv_tendm = itemView.findViewById(R.id.iv_tendm_hd);
            tv_ghichu = itemView.findViewById(R.id.tv_ghichu_hd);
            tv_sotien = itemView.findViewById(R.id.tv_sotien_hd);
            item_hoatdong = itemView.findViewById(R.id.lo_item_hoatdong);
        }
    }

    public static String currencyFormat(float amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((amount));
    }

    public interface HoatDongClickItem {
        public void onItemClick (hoatdong hoatdong);
    }
}
