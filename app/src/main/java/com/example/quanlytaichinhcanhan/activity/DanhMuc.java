package com.example.quanlytaichinhcanhan.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.adapter.DanhmucAdapter;
import com.example.quanlytaichinhcanhan.animation.TranslateAnimationUtil;
import com.example.quanlytaichinhcanhan.api.ApiService;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhMuc extends AppCompatActivity {

    RecyclerView rcv_dm;
    FloatingActionButton btn_add_danhmuc;
    DanhmucAdapter danhmucAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        rcv_dm = findViewById(R.id.rcv_danhmuc);

        GetDataRecyclerView();

        rcv_dm.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0) {
                    btn_add_danhmuc.setVisibility(View.GONE);
                } else {
                    btn_add_danhmuc.setVisibility(View.VISIBLE);
                }
            }
        });
//        rcv_dm.setOnTouchListener(new TranslateAnimationUtil(this, btn_add_danhmuc));

    }

    public void GetDataRecyclerView() {
        ApiService.apiservice.getDanhMucs().enqueue(new Callback<ArrayList<danhmuc>>() {
            @Override
            public void onResponse(Call<ArrayList<danhmuc>> call, Response<ArrayList<danhmuc>> response) {
                ArrayList<danhmuc> danhmuclist = response.body();
                LoadDataRecyclerView(danhmuclist);
            }

            @Override
            public void onFailure(Call<ArrayList<danhmuc>> call, Throwable t) {

            }
        });
    }

    public void LoadDataRecyclerView(ArrayList<danhmuc> list) {

        danhmucAdapter = new DanhmucAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        danhmucAdapter.setData(list);
        rcv_dm.setLayoutManager(linearLayoutManager);
        rcv_dm.setAdapter(danhmucAdapter);
        btn_add_danhmuc = findViewById(R.id.btn_add_danhmuc);
    }
}