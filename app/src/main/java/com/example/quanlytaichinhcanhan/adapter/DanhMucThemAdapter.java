package com.example.quanlytaichinhcanhan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.model.danhmuc;

import java.util.ArrayList;

public class DanhMucThemAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<danhmuc> mlist;

    public DanhMucThemAdapter(Context context, int layout, ArrayList<danhmuc> mlist) {
        this.context = context;
        this.layout = layout;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        ImageView img_dm = convertView.findViewById(R.id.img_dm);
        TextView tv_tendm = convertView.findViewById(R.id.tv_tendm);

        danhmuc danhmuc = mlist.get(position);

        tv_tendm.setText(danhmuc.getTendm());
        int drawableResourceId = convertView.getContext().getResources().getIdentifier(danhmuc.getImage(), "drawable", convertView.getContext().getPackageName());

        Glide.with(convertView.getContext())
                .load(drawableResourceId)
                .into(img_dm);

        return convertView;
    }
}
