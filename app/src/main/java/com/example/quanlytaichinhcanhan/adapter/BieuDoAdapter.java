package com.example.quanlytaichinhcanhan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;
import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;

public class BieuDoAdapter extends RecyclerView.Adapter<BieuDoAdapter.ViewHolder>{

    private Context context;
    private ArrayList<danhmuc> danhmucs;
    private ArrayList<hoatdong> hoatdongs;

    public BieuDoAdapter(Context context) {
        this.context = context;
    }

    public void setData (ArrayList<danhmuc> danhmucs, ArrayList<hoatdong> hoatdongs) {
        this.danhmucs = danhmucs;
        this.hoatdongs = hoatdongs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongke, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        danhmuc danhmuc = danhmucs.get(position);

        float sotien=0;
        float tongtien=0;
        float pt =0;

        for (hoatdong a: hoatdongs
             ) {
            tongtien += a.getSotien();

            if (a.getIddm() == danhmuc.getIddm()) {
                sotien += a.getSotien();
            }
        }

        pt = sotien/tongtien*100;

        float a = Math.round(pt*100)/100;

        holder.cot.setProgress(a);
        holder.cot.setLabelText(danhmuc.getTendm() + " " + a + "%" );


    }

    @Override
    public int getItemCount() {
        return danhmucs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ProgressView cot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cot= itemView.findViewById(R.id.cot);
        }
    }

    public void setGiatri(ArrayList<hoatdong> list, danhmuc danhmuc, float sotien, float tongtien) {
        for (hoatdong a : list
             ) {
            tongtien += a.getSotien();
            if (a.getIddm() == danhmuc.getIddm()) {
                sotien += a.getSotien();
            }
        }

    }
}
