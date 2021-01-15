package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Top10SachAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10_sach);
        getSupportActionBar().setTitle("Top 10 Sách bán chạy nhất");
    }
}