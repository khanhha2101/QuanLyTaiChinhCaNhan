package com.example.quanlytaichinhcanhan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.adapter.DanhmucAdapter;
import com.example.quanlytaichinhcanhan.adapter.HoatDongAdapter;
import com.example.quanlytaichinhcanhan.api.ApiService;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;
import com.example.quanlytaichinhcanhan.model.nguoidung;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu extends AppCompatActivity implements HoatDongAdapter.HoatDongClickItem {


    static TextView txt_chitieu, txt_thunhap, txt_sodu;
    TextView txt_name, txt_date;
    RecyclerView recyclerview_danhsach;
    HoatDongAdapter hoatDongAdapter;
    static nguoidung nguoidung;
    static float tongChiThang = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        AnhXa();
        bottomNavigation();

        //set tháng năm
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        setDate(ngay, thang, nam);

        if (nguoidung == null)
        nhanDuLieu();

        GetDataRecyclerView(ngay,thang+1, nam);



    }

    public void AnhXa(){

        txt_name = findViewById(R.id.txt_name);
        recyclerview_danhsach = findViewById(R.id.recyclerview_danhsach);
        txt_date = findViewById(R.id.txt_date);
        txt_chitieu = findViewById(R.id.txt_chitieu);
        txt_thunhap = findViewById(R.id.txt_thunhap);
        txt_sodu = findViewById(R.id.txt_sodu);
    }

    public void setDate(int ngay, int thang, int nam) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        txt_date.setText(dayOfTheWeek);
        txt_date.setText(dayOfTheWeek + " " + ngay+" Tháng "+thang+ " năm "+nam);
    }

    public void nhanDuLieu() {
        Intent intent = getIntent();
        nguoidung = (com.example.quanlytaichinhcanhan.model.nguoidung) intent.getSerializableExtra("nguoidung");
        txt_name.setText(nguoidung.getHoten());
    }

    private void bottomNavigation() {
        FloatingActionButton btn_add = findViewById(R.id.btn_add);
        LinearLayout btn_home = findViewById(R.id.btn_home);
        LinearLayout btn_thongke = findViewById(R.id.btn_thongke);
        LinearLayout btn_lichsu = findViewById(R.id.btn_lichsu);
        LinearLayout btn_caidat = findViewById(R.id.btn_caidat);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThemMoi.class);
                startActivity(intent);
            }
        });

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrangChu.class);
                startActivity(intent);
            }
        });

        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThongKe.class);
                startActivity(intent);
            }
        });

        btn_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LichSu.class);
                startActivity(intent);
            }
        });

        btn_caidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CaiDat.class);
                startActivity(intent);
            }
        });

    }

    public void GetDataRecyclerView(int ngay, int thang, int nam) {
        ApiService.apiservice.getHoatDongs().enqueue(new Callback<ArrayList<hoatdong>>() {
            @Override
            public void onResponse(Call<ArrayList<hoatdong>> call, Response<ArrayList<hoatdong>> response) {
                ArrayList<hoatdong> list = response.body();
                ArrayList<hoatdong> hoatdongs = new ArrayList<>();
                for (hoatdong a: list
                     ) {
                    if(a.getNgay() == ngay && a.getThang() == thang && a.getNam() == nam) {
                        hoatdongs.add(a);
                    }
                }
                LoadDataRecyclerView(hoatdongs);

                tongThuChitheoThang(list, thang, nam);
            }

            @Override
            public void onFailure(Call<ArrayList<hoatdong>> call, Throwable t) {
                Toast.makeText(TrangChu.this, "That bai", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void LoadDataRecyclerView(ArrayList<hoatdong> list) {

        hoatDongAdapter = new HoatDongAdapter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        hoatDongAdapter.setData(list);
        recyclerview_danhsach.setLayoutManager(linearLayoutManager);
        recyclerview_danhsach.setAdapter(hoatDongAdapter);
    }

    public void tongThuChitheoThang(ArrayList<hoatdong> list, int thang, int nam) {
        float chi=0, thu=0, tongchi=0, tongthu=0;
        for (hoatdong a:list ) {

            if(a.getPhanloai()==1){
                tongchi += a.getSotien();
            } else {
                tongthu += a.getSotien();
            }

            if (a.getThang() == thang && a.getNam() == nam){
                if(a.getPhanloai() == 1){
                    chi += a.getSotien();
                } else {
                    thu += a.getSotien();
                }
            }
        }
        txt_chitieu.setText(currencyFormat(chi));
        txt_thunhap.setText(currencyFormat(thu));
        txt_sodu.setText(currencyFormat(tongthu-tongchi));
        tongChiThang = chi;
    }


    @Override
    public void onItemClick(hoatdong hoatdong) {

        Intent intent = new Intent(TrangChu.this, ChinhSua.class);
        intent.putExtra("hoatdong", hoatdong);
        startActivity(intent);
    }

    public static String currencyFormat(float amount) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format((amount));
    }
}