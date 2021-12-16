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
import com.example.quanlytaichinhcanhan.interfaces.ClickItem;
import com.example.quanlytaichinhcanhan.model.danhmuc;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class DanhmucAdapter extends RecyclerView.Adapter<DanhmucAdapter.ViewHolder>{

    private Context context;
    private ClickItem clickItem;
    private ArrayList<danhmuc> mlist;

    public DanhmucAdapter(Context context, ClickItem clickItem) {
        this.context = context;
        this.clickItem = clickItem;
    }

    public void setData (ArrayList<danhmuc> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmuc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        danhmuc danhmuc = mlist.get(position);

        holder.tv_tendm.setText(danhmuc.getTendm());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(danhmuc.getImage(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.img_dm);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_dm;
        private TextView tv_tendm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_dm = itemView.findViewById(R.id.img_dm);
            tv_tendm = itemView.findViewById(R.id.tv_tendm);
        }
    }
}
