package com.example.quanlytaichinhcanhan.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;

import java.util.ArrayList;

public class HoatDongAdapter extends RecyclerView.Adapter<HoatDongAdapter.ViewHolder>{
    private Context context;
    private ArrayList<hoatdong> mlist;

    public HoatDongAdapter(Context context) {
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
        holder.tv_sotien.setText(hoatdong.getSotien()+"");

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(hoatdong.getImage(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.img_ldm);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_ldm;
        private TextView tv_tendm, tv_ghichu, tv_sotien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_ldm = itemView.findViewById(R.id.img_ldm_hd);
            tv_tendm = itemView.findViewById(R.id.iv_tendm_hd);
            tv_ghichu = itemView.findViewById(R.id.tv_ghichu_hd);
            tv_sotien = itemView.findViewById(R.id.tv_sotien_hd);
        }
    }
}
