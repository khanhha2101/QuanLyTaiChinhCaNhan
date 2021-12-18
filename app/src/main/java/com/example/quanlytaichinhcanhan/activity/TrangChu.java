package com.example.quanlytaichinhcanhan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class TrangChu extends AppCompatActivity {

    TextView txt_name;
    RecyclerView recyclerview_danhsach;
    HoatDongAdapter hoatDongAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        AnhXa();

//        nhanDuLieu();

        bottomNavigation();
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

            }
        });

    }

    public void LoadDataRecyclerView(ArrayList<hoatdong> list) {

        hoatDongAdapter = new HoatDongAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        hoatDongAdapter.setData(list);
        recyclerview_danhsach.setLayoutManager(linearLayoutManager);
        recyclerview_danhsach.setAdapter(hoatDongAdapter);
    }
}