package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.duanmau.Class.HoaDon;

public class QuanLySachAct extends AppCompatActivity {
    LinearLayout nguoidung;
    LinearLayout theloai,sach,hoadon,top,thongKe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sach);
        getSupportActionBar().setTitle("Quản lý sách");
        nguoidung=findViewById(R.id.user);
        theloai=findViewById(R.id.theLoai);
        sach=findViewById(R.id.sach);
        hoadon=findViewById(R.id.hoadon);
        top=findViewById(R.id.top);
        thongKe=findViewById(R.id.thongKe);
        nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuanLySachAct.this, UserAct.class);
                startActivity(intent);
            }
        });
        theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuanLySachAct.this,TheLoaiAct.class);
                startActivity(intent);
            }
        });
        sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuanLySachAct.this,TheLoaiAct.class);
                startActivity(intent);
            }
        });
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuanLySachAct.this,Top10SachAct.class);
                startActivity(intent);
            }
        });
        thongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuanLySachAct.this,ThongKeAct.class);
                startActivity(intent);
            }
        });
        hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuanLySachAct.this,HoaDonAct.class);
                startActivity(intent);
            }
        });
    }
}