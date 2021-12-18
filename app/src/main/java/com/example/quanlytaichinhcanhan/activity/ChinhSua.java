package com.example.quanlytaichinhcanhan.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.api.ApiService;
import com.example.quanlytaichinhcanhan.model.danhmuc;
import com.example.quanlytaichinhcanhan.model.hoatdong;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSua extends AppCompatActivity {

    ImageView img_phanloai_sua;
    EditText nhaptien_sua, edt_phanloai_sua, edt_thoigian_sua, edt_ghichu_sua;
    Button btn_xoa, btn_luu;
    LinearLayout lo_chonngay_sua,lo_chonphanloai_sua, lo_back_sua;

    int dd=0, mm=0, yy=0;
    hoatdong hoatdong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua);

        AnhXa();
        nhanData();
        lo_chonngay_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonThoiGian();
            }
        });

        lo_back_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChinhSua.this, TrangChu.class);
                startActivity(intent);
                finish();
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = hoatdong.getIdhd();
                ApiService.apiservice.xoaHoatDong(id).enqueue(new Callback<com.example.quanlytaichinhcanhan.model.hoatdong>() {
                    @Override
                    public void onResponse(Call<com.example.quanlytaichinhcanhan.model.hoatdong> call, Response<com.example.quanlytaichinhcanhan.model.hoatdong> response) {
                        Toast.makeText(ChinhSua.this, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChinhSua.this, TrangChu.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<com.example.quanlytaichinhcanhan.model.hoatdong> call, Throwable t) {
                        Toast.makeText(ChinhSua.this, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChinhSua.this, TrangChu.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float sotien = Float.parseFloat(nhaptien_sua.getText().toString());
                String ghichu = edt_ghichu_sua.getText().toString();
                danhmuc danhmuc = new danhmuc(4, "Mua sắm", "ldm_2", 1);

                if( sotien == 0 && dd == 0){
                    Toast.makeText(ChinhSua.this, "Mời nhập dữ liệu", Toast.LENGTH_SHORT);
                } else {
                    hoatdong hoatdong2 = new hoatdong(hoatdong.getIdhd(),1, danhmuc.getIddm(), danhmuc.getTendm(), danhmuc.getImage(), danhmuc.getPhanloai(), sotien, ghichu, dd, mm, yy);
                    ApiService.apiservice.editHoatDong(hoatdong2, hoatdong2.getIdhd()).enqueue(new Callback<com.example.quanlytaichinhcanhan.model.hoatdong>() {
                        @Override
                        public void onResponse(Call<com.example.quanlytaichinhcanhan.model.hoatdong> call, Response<com.example.quanlytaichinhcanhan.model.hoatdong> response) {
                            Toast.makeText(ChinhSua.this, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                            if(response.isSuccessful()){
                                Intent intent = new Intent(ChinhSua.this, TrangChu.class);
                                startActivity(intent);
                                finish();
                            }

                        }

                        @Override
                        public void onFailure(Call<com.example.quanlytaichinhcanhan.model.hoatdong> call, Throwable t) {
                            Toast.makeText(ChinhSua.this, "Thao tác thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void AnhXa() {
        img_phanloai_sua = findViewById(R.id.img_phanloai_sua);
        nhaptien_sua = findViewById(R.id.sotien_sua);
        edt_phanloai_sua = findViewById(R.id.edt_phanloai_sua);
        edt_thoigian_sua = findViewById(R.id.edt_thoigian_sua);
        edt_ghichu_sua = findViewById(R.id.edt_ghichu_sua);
        btn_luu = findViewById(R.id.btn_luu);
        btn_xoa = findViewById(R.id.btn_xoa);
        lo_chonphanloai_sua = findViewById(R.id.lo_chonphanloai_sua);
        lo_chonngay_sua = findViewById(R.id.lo_chonngay_sua);
        lo_back_sua = findViewById(R.id.lo_back_sua);
    }

    public void nhanData() {
        Intent intent = getIntent();
        hoatdong = (com.example.quanlytaichinhcanhan.model.hoatdong) intent.getSerializableExtra("hoatdong");

        nhaptien_sua.setText(hoatdong.getSotien()+"");
        edt_phanloai_sua.setText(hoatdong.getTendm());
        edt_thoigian_sua.setText(hoatdong.getNgay()+"/"+hoatdong.getThang()+"/"+hoatdong.getNam());
        edt_ghichu_sua.setText(hoatdong.getGhichu());
    }

    public void chonThoiGian() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ChinhSua.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                dd = dayOfMonth;
                mm = month + 1;
                yy = year;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_thoigian_sua.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

}