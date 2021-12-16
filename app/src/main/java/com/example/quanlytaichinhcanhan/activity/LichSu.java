package com.example.quanlytaichinhcanhan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.quanlytaichinhcanhan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LichSu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);

        bottomNavigation();
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
}