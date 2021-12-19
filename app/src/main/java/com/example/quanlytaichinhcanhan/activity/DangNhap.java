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
import com.example.quanlytaichinhcanhan.model.nguoidung;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhap extends AppCompatActivity {

    EditText edt_taikhoan, edt_matkhau;
    TextView tv_dangky;
    Button btn_dangnhap;
    ArrayList<nguoidung> nguoidungs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        AnhXa();

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nguoidungs == null) {
                    nguoidungs = new ArrayList<>();
                    ApiService.apiservice.getNguoiDungs().enqueue(new Callback<ArrayList<nguoidung>>() {
                        @Override
                        public void onResponse(Call<ArrayList<nguoidung>> call, Response<ArrayList<nguoidung>> response) {

                            int ktdn = 0;
                            String taikhoan = edt_taikhoan.getText().toString();
                            String matkhau = edt_matkhau.getText().toString();
                            nguoidungs = response.body();
                            for (nguoidung nguoidung : nguoidungs) {
                                if (taikhoan.equals(nguoidung.getTaikhoan()) && matkhau.equals(nguoidung.getMatkhau())){
                                    Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DangNhap.this, TrangChu.class);
                                    intent.putExtra("nguoidung", nguoidung);
                                    startActivity(intent);
                                    ktdn = 1;
                                }
                            }
                            if (ktdn == 0) {
                                Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
                                edt_taikhoan.setText("");
                                edt_matkhau.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<nguoidung>> call, Throwable t) {
                            Toast.makeText(DangNhap.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();
                        }

                    });

                }
            }
        });

        tv_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void AnhXa() {
        edt_taikhoan = findViewById(R.id.edt_dangnhap_taikhoan);
        edt_matkhau = findViewById(R.id.edt_dangnhap_matkhau);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        tv_dangky = findViewById(R.id.tv_dangky);
    }

    public void getDuLieuNguoiDung() {
        if (nguoidungs == null) {
            nguoidungs = new ArrayList<>();
            ApiService.apiservice.getNguoiDungs().enqueue(new Callback<ArrayList<nguoidung>>() {
                @Override
                public void onResponse(Call<ArrayList<nguoidung>> call, Response<ArrayList<nguoidung>> response) {

                    int ktdn = 0;
                    String taikhoan = edt_taikhoan.getText().toString();
                    String matkhau = edt_matkhau.getText().toString();
                    nguoidungs = response.body();
                    for (nguoidung nguoidung : nguoidungs) {
                        if (taikhoan.equals(nguoidung.getTaikhoan()) && matkhau.equals(nguoidung.getMatkhau())){
                            Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangNhap.this, TrangChu.class);
                            intent.putExtra("nguoidung", nguoidung);
                            startActivity(intent);
                            ktdn = 1;
                        }
                    }
                    if (ktdn == 0) {
                        Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
                        edt_taikhoan.setText("");
                        edt_matkhau.setText("");
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<nguoidung>> call, Throwable t) {
                    Toast.makeText(DangNhap.this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();
                }

            });

        }

    }
}