package com.example.quanlytaichinhcanhan.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.adapter.DanhMucThemAdapter;
import com.example.quanlytaichinhcanhan.adapter.DanhmucAdapter;
import com.example.quanlytaichinhcanhan.api.ApiService;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemMoi extends AppCompatActivity {

    EditText nhaptien, edt_thoigian, edt_ghichu;
    Button btn_them;
    LinearLayout lo_chonngay_them,lo_chonphanloai_them, lo_back_them;
    Spinner spn_phanloai;
    DanhMucThemAdapter danhmucAdapter;
    danhmuc danhmuc;

    int dd=0, mm=0, yy=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi);

        AnhXa();
        lo_chonngay_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonThoiGian();
            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemTraData ();
            }
        });

        lo_back_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemMoi.this, TrangChu.class);
                startActivity(intent);
                finish();
            }
        });

        getDanhMucFromCSDL();

        spn_phanloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ApiService.apiservice.getDanhMucs().enqueue(new Callback<ArrayList<danhmuc>>() {
                    @Override
                    public void onResponse(Call<ArrayList<danhmuc>> call, Response<ArrayList<danhmuc>> response) {
                        ArrayList<danhmuc> list = response.body();
                        danhmuc = list.get(position);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<danhmuc>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void AnhXa() {
        nhaptien = findViewById(R.id.nhaptien);
        edt_thoigian = findViewById(R.id.edt_thoigian);
        edt_ghichu = findViewById(R.id.edt_ghichu);
        btn_them = findViewById(R.id.btn_them);
        lo_chonphanloai_them = findViewById(R.id.lo_chonphanloai_them);
        lo_chonngay_them = findViewById(R.id.lo_chonngay_them);
        lo_back_them = findViewById(R.id.lo_back_them);
        spn_phanloai = findViewById(R.id.spn_phanloai);
    }

    public void chonThoiGian() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ThemMoi.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                dd = dayOfMonth;
                mm = month + 1;
                yy = year;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_thoigian.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    public void kiemTraData () {
        float sotien = Float.parseFloat(nhaptien.getText().toString());
        String ghichu = edt_ghichu.getText().toString();
        int idnd = TrangChu.nguoidung.getIdnd();

        if( sotien == 0 && dd == 0){
            Toast.makeText(ThemMoi.this, "Mời nhập dữ liệu!", Toast.LENGTH_SHORT).show();
        } else {
            hoatdong hoatdong = new hoatdong(0,idnd, danhmuc.getIddm(), danhmuc.getTendm(), danhmuc.getImage(), danhmuc.getPhanloai(), sotien, ghichu, dd, mm, yy);
//
//            float c = TrangChu.tongChiThang + sotien;
////                Toast.makeText(ThemMoi.this, c + "", Toast.LENGTH_SHORT).show();
//            if(c > CaiDat.hanmuctoida) {
//
////                    Toast.makeText(ThemMoi.this, "Vượt hạn mức!", Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder alert = new AlertDialog.Builder(ThemMoi.this);
//                    alert.setTitle("Thông báo!");
//                    alert.setMessage("Chi tiêu vượt hạn mức cho phép, bạn có muốn tiếp tục?");
//
//                    alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            themHoatDong(hoatdong);
//                        }
//                    });
//                    alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                }
//            else {
                themHoatDong(hoatdong);
//            }

        }
    }

    public void getDanhMucFromCSDL() {
        ApiService.apiservice.getDanhMucs().enqueue(new Callback<ArrayList<danhmuc>>() {
            @Override
            public void onResponse(Call<ArrayList<danhmuc>> call, Response<ArrayList<danhmuc>> response) {
                ArrayList<danhmuc> list = response.body();
                setSpinner(list);
                danhmuc = list.get(0);
            }

            @Override
            public void onFailure(Call<ArrayList<danhmuc>> call, Throwable t) {

            }
        });
    }

    public void setSpinner(ArrayList<danhmuc> list) {
        danhmucAdapter = new DanhMucThemAdapter(this, R.layout.item_danhmuc, list);
        spn_phanloai.setAdapter(danhmucAdapter);
    }

    public void themHoatDong(hoatdong hoatdong){

        ApiService.apiservice.setHoatDongs(hoatdong).enqueue(new Callback<com.example.quanlytaichinhcanhan.model.hoatdong>() {
            @Override
            public void onResponse(Call<com.example.quanlytaichinhcanhan.model.hoatdong> call, Response<com.example.quanlytaichinhcanhan.model.hoatdong> response) {

            }

            @Override
            public void onFailure(Call<com.example.quanlytaichinhcanhan.model.hoatdong> call, Throwable t) {
                Toast.makeText(ThemMoi.this, "Thêm thất bại", Toast.LENGTH_SHORT);
            }
        });

        Toast.makeText(ThemMoi.this, "Thêm thành công", Toast.LENGTH_SHORT);
        Intent intent = new Intent(ThemMoi.this, TrangChu.class);
        startActivity(intent);
    }
}