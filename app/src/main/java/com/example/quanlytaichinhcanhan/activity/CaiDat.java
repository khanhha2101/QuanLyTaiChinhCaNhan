package com.example.quanlytaichinhcanhan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quanlytaichinhcanhan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CaiDat extends AppCompatActivity {

    TextView txt_danhmuc_caidat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);

        txt_danhmuc_caidat = findViewById(R.id.txt_danhmuc_caidat);
        txt_danhmuc_caidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaiDat.this, DanhMuc.class);
                startActivity(intent);
            }
        });


    }


}