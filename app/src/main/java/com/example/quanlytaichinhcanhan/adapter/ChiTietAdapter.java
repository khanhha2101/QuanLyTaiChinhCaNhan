package com.example.quanlytaichinhcanhan.adapter;


import android.content.Context;
import android.graphics.Color;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietAdapter extends RecyclerView.Adapter<ChiTietAdapter.ViewHolder>{

    private Context context;
    private ArrayList<danhmuc> danhmucs;
    private ArrayList<hoatdong> hoatdongs;

    public ChiTietAdapter(Context context) {
        this.context = context;
    }

    public void setData (ArrayList<danhmuc> danhmucs, ArrayList<hoatdong> hoatdongs) {
        this.danhmucs = danhmucs;
        this.hoatdongs = hoatdongs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoatdong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        danhmuc danhmuc = danhmucs.get(position);

        float sotien=0;

        for (hoatdong a: hoatdongs
        ) {
            if (a.getIddm() == danhmuc.getIddm()) {
                sotien += a.getSotien();
            }
        }

        holder.tv_tendm_hd.setText(danhmuc.getTendm());
        holder.tv_ghichu_hd.setText("");

        if (danhmuc.getPhanloai() == 1){
            holder.tv_sotien_hd.setText(currencyFormat(sotien));
            holder.tv_sotien_hd.setTextColor(Color.RED);
        } else {
            holder.tv_sotien_hd.setText(currencyFormat(sotien));
            holder.tv_sotien_hd.setTextColor(Color.GREEN);
        }

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(danhmuc.getImage(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.img_ldm_hd);
    }

    @Override
    public int getItemCount() {
        return danhmucs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_tendm_hd, tv_ghichu_hd, tv_sotien_hd;
        private ImageView img_ldm_hd;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);

             tv_tendm_hd = itemView.findViewById(R.id.iv_tendm_hd);
             tv_ghichu_hd = itemView.findViewById(R.id.tv_ghichu_hd);
             tv_sotien_hd = itemView.findViewById(R.id.tv_sotien_hd);
             img_ldm_hd = itemView.findViewById(R.id.img_ldm_hd);
        }
    }

    public static String currencyFormat(float amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((amount));
    }
}
