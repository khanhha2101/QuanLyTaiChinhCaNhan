package com.example.quanlytaichinhcanhan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlytaichinhcanhan.R;
import com.example.quanlytaichinhcanhan.api.ApiService;
import com.example.quanlytaichinhcanhan.model.hoatdong;
import com.example.quanlytaichinhcanhan.model.nguoidung;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {

    EditText edt_taikhoan, edt_matkhau, edt_hoten;
    TextView tv_dangnhap;
    Button btn_dangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        AnhXa();

        tv_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
                finish();
            }
        });

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edt_hoten.getText().toString();
                String taikhoan = edt_taikhoan.getText().toString();
                String matkhau = edt_matkhau.getText().toString();

                if(hoten.equals("") || taikhoan.equals("") || matkhau.equals("")){
                    Toast.makeText(DangKy.this, "Mời nhập đầy đue thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    ThemNguoiDung(taikhoan, matkhau, hoten);
                }
            }
        });
    }

    public void AnhXa() {
        edt_taikhoan = findViewById(R.id.edt_dangky_matkhau);
        edt_matkhau = findViewById(R.id.edt_dangky_taikhoan);
        edt_hoten = findViewById(R.id.edt_dangky_hoten);
        btn_dangky = findViewById(R.id.btn_dangky);
        tv_dangnhap = findViewById(R.id.tv_dangnhap);
    }

    public void ThemNguoiDung(String taikhoan, String matkhau, String hoten) {
        ApiService.apiservice.getNguoiDungs().enqueue(new Callback<ArrayList<nguoidung>>() {
            @Override
            public void onResponse(Call<ArrayList<nguoidung>> call, Response<ArrayList<nguoidung>> response) {
                ArrayList<nguoidung> list = response.body();
                int kt = 0;

                for (nguoidung a: list
                     ) {
                    if (a.getTaikhoan().equals(taikhoan)) {
                        Toast.makeText(DangKy.this, "Tài khoản đã tồn tại.", Toast.LENGTH_SHORT).show();
                        kt =1;
                    }
                }

                if(kt==0) {
                    nguoidung nguoidung = new nguoidung(0, taikhoan, matkhau, hoten);
                    ApiService.apiservice.addNguoidung(nguoidung).enqueue(new Callback<hoatdong>() {
                        @Override
                        public void onResponse(Call<hoatdong> call, Response<hoatdong> response) {
                            Toast.makeText(DangKy.this, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<hoatdong> call, Throwable t) {
                            Toast.makeText(DangKy.this, "Thao tác thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(DangKy.this, DangNhap.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<nguoidung>> call, Throwable t) {

            }
        });
    }
}