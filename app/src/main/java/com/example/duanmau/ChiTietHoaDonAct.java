package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChiTietHoaDonAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        getSupportActionBar().setTitle("Chi tiết hóa đơn");
    }
}