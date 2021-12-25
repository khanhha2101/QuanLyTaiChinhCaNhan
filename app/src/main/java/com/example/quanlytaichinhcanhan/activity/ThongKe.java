package com.example.quanlytaichinhcanhan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.adapter.BieuDoAdapter;
import com.example.quanlytaichinhcanhan.adapter.ChiTietAdapter;
import com.example.quanlytaichinhcanhan.adapter.DanhmucAdapter;
import com.example.quanlytaichinhcanhan.adapter.HoatDongAdapter;
import com.example.quanlytaichinhcanhan.api.ApiService;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKe extends AppCompatActivity {

    TextView tv_thongke_nam, tv_thongke_thang;
    RecyclerView rcv_thongke_bieudo, rcv_thongke_chitiet;
    LinearLayout lo_thongke_chonthang;
    Spinner spn_phanloai;
    BieuDoAdapter bieuDoAdapter;
    ChiTietAdapter chiTietAdapter;
    ArrayList<danhmuc> danhmucs = new ArrayList<>();
    ArrayList<hoatdong> hoatdongs = new ArrayList<>();
    int phanloai = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        AnhXa();
        bottomNavigation();


        //set tháng năm
        Calendar calendar2 = Calendar.getInstance();
        int thang = calendar2.get(Calendar.MONTH);
        int nam = calendar2.get(Calendar.YEAR);
        tv_thongke_thang.setText((thang + 1) + "");
        tv_thongke_nam.setText(nam + "");

        getDataCSDL(thang+1, nam, phanloai);

        lo_thongke_chonthang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonThang();
            }
        });

        setSpinner();

        spn_phanloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                    phanloai = 1;
                else
                    phanloai = 0;

                getDataCSDL(thang+1, nam, phanloai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void AnhXa() {
        rcv_thongke_chitiet = findViewById(R.id.rcv_thongke_chitiet);
        rcv_thongke_bieudo = findViewById(R.id.rcv_thongke_bieudo);
        tv_thongke_nam = findViewById(R.id.tv_thongke_nam);
        tv_thongke_thang = findViewById(R.id.tv_thongke_thang);
        lo_thongke_chonthang = findViewById(R.id.lo_thongke_chonthang);
        spn_phanloai = findViewById(R.id.spn_phanloai);
    }

    public void getDataCSDL(int thang, int nam, int phanloai) {
        ApiService.apiservice.getHoatDongs(TrangChu.nguoidung.getIdnd()).enqueue(new Callback<ArrayList<hoatdong>>() {
            @Override
            public void onResponse(Call<ArrayList<hoatdong>> call, Response<ArrayList<hoatdong>> response) {
                ArrayList<hoatdong> list = response.body();

                ArrayList<danhmuc> danhmucs = new ArrayList<>();
                ArrayList<hoatdong> hoatdongs = new ArrayList<>();

                for (hoatdong a: list
                ) {
                    if (a.getPhanloai()==phanloai && a.getNam()==nam && a.getThang()==thang){

                        hoatdongs.add(a);
                        if (danhmucs.size()==0){

                            danhmucs.add(new danhmuc(a.getIddm(), a.getTendm(), a.getImage(), a.getPhanloai()));
                        } else {
                            int z = 0;
                            for (danhmuc b: danhmucs
                            ) {
                                if (b.getIddm() == a.getIddm()) {

                                    z=1;
                                }
                            }
                            if (z == 0) {

                                danhmucs.add(new danhmuc(a.getIddm(), a.getTendm(), a.getImage(), a.getPhanloai()));
                            }
                        }
                    }
                }

                BieuDoRecyclerView(hoatdongs, danhmucs);
                ChiTietRecyclerView(hoatdongs, danhmucs);
            }

            @Override
            public void onFailure(Call<ArrayList<hoatdong>> call, Throwable t) {

            }
        });
    }

    public void BieuDoRecyclerView(ArrayList<hoatdong> hoatdongs, ArrayList<danhmuc> danhmucs) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        bieuDoAdapter = new BieuDoAdapter(this);
        bieuDoAdapter.setData(danhmucs, hoatdongs);
        rcv_thongke_bieudo.setLayoutManager(linearLayoutManager);
        rcv_thongke_bieudo.setAdapter(bieuDoAdapter);
    }
    public void ChiTietRecyclerView(ArrayList<hoatdong> hoatdongs, ArrayList<danhmuc> danhmucs) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        chiTietAdapter = new ChiTietAdapter(this);
        chiTietAdapter.setData(danhmucs, hoatdongs);
        rcv_thongke_chitiet.setLayoutManager(linearLayoutManager);
        rcv_thongke_chitiet.setAdapter(chiTietAdapter);
    }

    public void chonThang() {
        Calendar calendar = Calendar.getInstance();
        int ngay = 1;
        int thang = Integer.parseInt(tv_thongke_thang.getText().toString());
        int nam = Integer.parseInt(tv_thongke_nam.getText().toString());

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                int a = calendar.get(Calendar.MONTH);
                int b = calendar.get(Calendar.YEAR);
                tv_thongke_thang.setText((a+1) + "");
                tv_thongke_nam.setText((b) + "");

                getDataCSDL(a+1, b, phanloai);

            }
        }, nam, thang-1, ngay);
        datePickerDialog.show();

    }

    public void setSpinner() {
        ArrayList<String> listsp = new ArrayList<>();
        listsp.add("Chi tiêu");
        listsp.add("Thu nhập");

        ArrayAdapter adaptersp = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listsp);
        adaptersp.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spn_phanloai.setAdapter(adaptersp);
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
}