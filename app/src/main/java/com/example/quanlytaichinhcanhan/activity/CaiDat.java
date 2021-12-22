package com.example.quanlytaichinhcanhan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlytaichinhcanhan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CaiDat extends AppCompatActivity {

    TextView txt_danhmuc_caidat, tv_hanmuc, txt_name;
    static float hanmuctoida = 2000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);

        AnhXa();

        txt_name.setText(TrangChu.nguoidung.getHoten());

        txt_danhmuc_caidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDat.this, DanhMuc.class);
                startActivity(intent);
            }
        });
        tv_hanmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaLogHanmuc();
            }
        });
        bottomNavigation();


    }

    public void AnhXa() {
        txt_danhmuc_caidat = findViewById(R.id.txt_danhmuc_caidat);
        tv_hanmuc = findViewById(R.id.tv_hanmuc);
        txt_name = findViewById(R.id.txt_name);
    }

    private void DiaLogHanmuc(){
        Dialog dialog = new Dialog(CaiDat.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.diacustom_hanmuc);
        dialog.setCanceledOnTouchOutside(false);

        //ánh xạ
        EditText edt_hanmuc = dialog.findViewById(R.id.edt_hanmuc);
        Button btn_luuhanmuc = dialog.findViewById(R.id.luu_hanmuc);

        edt_hanmuc.setText(hanmuctoida+"");

        btn_luuhanmuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float a = Float.parseFloat(edt_hanmuc.getText().toString().trim());
                if(a >=0) {
                    Toast.makeText(CaiDat.this, "Thiết lập thành công!", Toast.LENGTH_SHORT).show();
                    hanmuctoida = a;
                }

                dialog.dismiss();

            }
        });

        dialog.show();
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