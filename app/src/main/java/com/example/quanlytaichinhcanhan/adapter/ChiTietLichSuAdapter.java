package com.example.quanlytaichinhcanhan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;
import com.ramotion.foldingcell.FoldingCell;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class ChiTietLichSuAdapter extends RecyclerView.Adapter<ChiTietLichSuAdapter.ViewHolder>{
    private Context context;
    private ArrayList<hoatdong> mlist;
    private ArrayList<Integer> listCon;

    public ChiTietLichSuAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<hoatdong> list, ArrayList<Integer> list2) {
        this.mlist = list;
        this.listCon = list2;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichsu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int ngay = listCon.get(position);

        holder.tv_ngay_itemls.setText(ngay+"");
        holder.tv_tongchi_lichsu.setText(currencyFormat(TongChiMang(mlist, ngay))+"");
        holder.tv_tongthu_lichsu.setText(currencyFormat(TongthuMang(mlist, ngay))+"");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rcv_itemls.setLayoutManager(linearLayoutManager);
        holder.rcv_itemls.setFocusable(false);

        HoatDongAdapter hoatDongAdapter = new HoatDongAdapter(context, (HoatDongAdapter.HoatDongClickItem) context);
        hoatDongAdapter.setData(getListByNgay(mlist, ngay));

        holder.rcv_itemls.setAdapter(hoatDongAdapter);


        //animation
//        holder.folding_cell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.folding_cell.toggle(false);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listCon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_ngay_itemls, tv_tongthu_lichsu, tv_tongchi_lichsu;
        private RecyclerView rcv_itemls;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_ngay_itemls = itemView.findViewById(R.id.tv_ngay_itemls);
            tv_tongthu_lichsu = itemView.findViewById(R.id.tv_tongthu_lichsu);
            tv_tongchi_lichsu = itemView.findViewById(R.id.tv_tongchi_lichsu);

            rcv_itemls = itemView.findViewById(R.id.rcv_itemls);
        }
    }


    ///hàm xử lý mảng

    //trả về tổng chi tiêu trong ngày
    public float TongChiMang(ArrayList<hoatdong> list, int ngay){
        float tongchi = 0;
        for (hoatdong a : list
        ) {
            if (a.getPhanloai() == 1 && a.getNgay() == ngay){
                tongchi += a.getSotien();
            }
        }

        return tongchi;
    }
    //trả về tổng thu nhập trong ngày
    public float TongthuMang(ArrayList<hoatdong> list, int ngay){
        float tongthu = 0;
        for (hoatdong a : list
        ) {
            if (a.getPhanloai() == 0 & a.getNgay() == ngay){
                tongthu += a.getSotien();
            }
        }

        return tongthu;
    }
    //trả về hiệu chi tiêu và thu nhập trong ngày
    public float hieuMang(ArrayList<hoatdong> list, int ngay){
        float thu = TongthuMang(list, ngay);
        float chi = TongChiMang(list, ngay);

        return thu - chi;
    }
    //trả về list hoạt động theo ngày
    public ArrayList<hoatdong> getListByNgay (ArrayList<hoatdong> list, int ngay) {
        ArrayList<hoatdong> a = new ArrayList<>();
        for (hoatdong b:list
             ) {
            if (b.getNgay() == ngay){
                a.add(b);
            }
        }
        return a;
    }

    //format tiền
    public static String currencyFormat(float amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((amount));
    }
}
