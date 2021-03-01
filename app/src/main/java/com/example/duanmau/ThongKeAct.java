package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.duanmau.Sql.HoaDonCtDAO;
import com.example.duanmau.Sql.Sqlite;

public class ThongKeAct extends AppCompatActivity {
    TextView tvTkNgay,tvTkThang,tvTkNam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        getSupportActionBar().setTitle("Doanh thu");
        tvTkNgay=findViewById(R.id.tvTkNgay);
        tvTkThang=findViewById(R.id.tvTkThang);
        tvTkNam=findViewById(R.id.tvTkNam);
        Sqlite sqlite=new Sqlite(ThongKeAct.this);
        HoaDonCtDAO hoaDonCtDAO=new HoaDonCtDAO(sqlite);
        tvTkNgay.setText("Ngày: "+hoaDonCtDAO.getDoanhThuTheoNgay());
        tvTkThang.setText("Tháng: "+hoaDonCtDAO.getDoanhThuTheoThang());
        tvTkNam.setText("Năm: "+hoaDonCtDAO.getDoanhThuTheoNam());
    }
}