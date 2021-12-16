package com.example.quanlytaichinhcanhan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.adapter.DanhmucAdapter;
import com.example.quanlytaichinhcanhan.api.ApiService;
import com.example.quanlytaichinhcanhan.interfaces.ClickItem;
import com.example.quanlytaichinhcanhan.model.danhmuc;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhMuc extends AppCompatActivity implements ClickItem{

    RecyclerView rcv_dm;

    DanhmucAdapter danhmucAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);

        rcv_dm = findViewById(R.id.rcv_danhmuc);

        GetDataRecyclerView();

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

        danhmucAdapter = new DanhmucAdapter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        danhmucAdapter.setData(list);
        rcv_dm.setLayoutManager(linearLayoutManager);
        rcv_dm.setAdapter(danhmucAdapter);
    }
}