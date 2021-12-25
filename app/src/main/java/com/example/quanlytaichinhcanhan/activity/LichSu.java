package com.example.quanlytaichinhcanhan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.adapter.ChiTietLichSuAdapter;
import com.example.quanlytaichinhcanhan.adapter.HoatDongAdapter;
import com.example.quanlytaichinhcanhan.api.ApiService;
import com.example.quanlytaichinhcanhan.model.hoatdong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSu extends AppCompatActivity implements HoatDongAdapter.HoatDongClickItem {

    TextView tv_thang_lichsu, tv_nam_lichsu;
    RecyclerView rcv_lichsu;
    ChiTietLichSuAdapter lichSuAdapter;
    LinearLayout lo_chonthang_lichsu;
    ArrayList<hoatdong> list = new ArrayList<>();
    ArrayList<Integer> listNgay = new ArrayList<>();

    int thang=0, nam=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);

        AnhXa();


        //set tháng năm
        Calendar calendar2 = Calendar.getInstance();
        thang = calendar2.get(Calendar.MONTH);
        nam = calendar2.get(Calendar.YEAR);
        tv_thang_lichsu.setText((thang + 1) + "");
        tv_nam_lichsu.setText(nam + "");

        getDataFromCSDL(thang+1, nam);

        lo_chonthang_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonThang();

            }
        });


        bottomNavigation();
    }

    public void AnhXa() {
        rcv_lichsu = findViewById(R.id.rcv_lichsu);
        tv_thang_lichsu = findViewById(R.id.tv_thang_lichsu);
        tv_nam_lichsu = findViewById(R.id.tv_nam_lichsu);
        lo_chonthang_lichsu = findViewById(R.id.lo_chonthang_lichsu);
    }



    public void getDataFromCSDL(int thang, int nam) {

        ApiService.apiservice.getHoatDongs(TrangChu.nguoidung.getIdnd()).enqueue(new Callback<ArrayList<hoatdong>>() {
            @Override
            public void onResponse(Call<ArrayList<hoatdong>> call, Response<ArrayList<hoatdong>> response) {
                ArrayList<hoatdong> listData = response.body();
                list = new ArrayList<>();
                listNgay = new ArrayList<>();

                for (hoatdong a : listData
                     ) {
                    int dd = a.getNgay();
                    int mm = a.getThang();
                    int yy = a.getNam();
                    int kt = 0;

                    if(mm == thang && yy == nam) {
                        list.add(a);

                        if (listNgay.size() == 0){
                            listNgay.add(dd);
                        } else {
                            for (int n:listNgay
                                 ) {
                                if (dd == n){
                                    kt = 1;
                                }
                            }

                            if(kt == 0) {
                                listNgay.add(dd);
                            }
                        }
                    }
                }

                loadDataToRecyclerView(list, listNgay);
            }

            @Override
            public void onFailure(Call<ArrayList<hoatdong>> call, Throwable t) {

            }
        });
    }

    public void loadDataToRecyclerView(ArrayList<hoatdong> list, ArrayList<Integer> listngay) {
        lichSuAdapter = new ChiTietLichSuAdapter(this);
        lichSuAdapter.setData(list, listngay);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_lichsu.setLayoutManager(linearLayoutManager);
        rcv_lichsu.setAdapter(lichSuAdapter);

    }

    public void chonThang() {
        Calendar calendar = Calendar.getInstance();
        int ngay = 1;
        int thang = Integer.parseInt(tv_thang_lichsu.getText().toString());
        int nam = Integer.parseInt(tv_nam_lichsu.getText().toString());

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                int a = calendar.get(Calendar.MONTH);
                int b = calendar.get(Calendar.YEAR);
                tv_thang_lichsu.setText((a+1) + "");
                tv_nam_lichsu.setText((b) + "");


                getDataFromCSDL(a+1, b);

            }
        }, nam, thang-1, ngay);
        datePickerDialog.show();

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

    @Override
    public void onItemClick(hoatdong hoatdong) {
        Intent intent = new Intent(LichSu.this, ChinhSua.class);
        intent.putExtra("hoatdong", hoatdong);
        startActivity(intent);
    }
}