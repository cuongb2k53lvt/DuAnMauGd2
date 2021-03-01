package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.Class.HoaDonChiTiet;
import com.example.duanmau.ListViewAdapter.ListHoaDonCtAdapter;
import com.example.duanmau.Sql.HoaDonCtDAO;
import com.example.duanmau.Sql.HoaDonDAO;
import com.example.duanmau.Sql.Sqlite;

import java.text.ParseException;
import java.util.ArrayList;

public class HoaDonCtAct extends AppCompatActivity {
    ListView lvHdct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_ct);
        getSupportActionBar().setTitle("Hóa đơn chi tiết");
        lvHdct=findViewById(R.id.lvHoaDonCt);
        Intent intent=getIntent();
        Bundle bundle=getIntent().getExtras();
        String maHd=bundle.getString("MAHD");
        Sqlite sqlite=new Sqlite(HoaDonCtAct.this);
        HoaDonDAO hoaDonDAO=new HoaDonDAO(sqlite);
        HoaDonCtDAO hoaDonCtDAO=new HoaDonCtDAO(sqlite);
        ArrayList<HoaDonChiTiet> arrHdct=null;
        try {
            arrHdct=hoaDonCtDAO.getHdctByMaHd(maHd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ListHoaDonCtAdapter listHoaDonCtAdapter=new ListHoaDonCtAdapter(HoaDonCtAct.this,arrHdct);
        lvHdct.setAdapter(listHoaDonCtAdapter);
    }
}