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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu extends AppCompatActivity implements HoatDongAdapter.HoatDongClickItem {

    TextView txt_name;
    RecyclerView recyclerview_danhsach;
    HoatDongAdapter hoatDongAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        AnhXa();
        bottomNavigation();

//        nhanDuLieu();

        GetDataRecyclerView();



    }

    public void AnhXa(){

        txt_name = findViewById(R.id.txt_name);
        recyclerview_danhsach = findViewById(R.id.recyclerview_danhsach);
    }

    public void nhanDuLieu() {
        Intent intent = getIntent();
        nguoidung nguoidung = (com.example.quanlytaichinhcanhan.model.nguoidung) intent.getSerializableExtra("nguoidung");
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

    public void GetDataRecyclerView() {
        ApiService.apiservice.getHoatDongs().enqueue(new Callback<ArrayList<hoatdong>>() {
            @Override
            public void onResponse(Call<ArrayList<hoatdong>> call, Response<ArrayList<hoatdong>> response) {
                ArrayList<hoatdong> list = response.body();
                LoadDataRecyclerView(list);
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

    @Override
    public void onItemClick(hoatdong hoatdong) {

        Intent intent = new Intent(TrangChu.this, ChinhSua.class);
        intent.putExtra("hoatdong", hoatdong);
        startActivity(intent);
    }
}