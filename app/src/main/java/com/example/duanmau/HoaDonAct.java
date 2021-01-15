package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.ListViewAdapter.ListHoaDonAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonAct extends AppCompatActivity {
    ListView lvHd;
    Button btnAddHd;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        toolbar=findViewById(R.id.tbHoadon);
        btnAddHd=findViewById(R.id.btnAddHd);
        lvHd=findViewById(R.id.lvHoaDon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hóa đơn");
        ArrayList<HoaDon> arrHoadon=new ArrayList<>();
        SimpleDateFormat formater=new SimpleDateFormat("dd/mm/yyyy");
        String dateInString="15/01/2021";
        Date date= null;
        try {
            date = formater.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        HoaDon hd1=new HoaDon("HD1",date);
        arrHoadon.add(hd1);
        ListHoaDonAdapter listHoaDonAdapter=new ListHoaDonAdapter(HoaDonAct.this,arrHoadon);
        lvHd.setAdapter(listHoaDonAdapter);
        btnAddHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HoaDonAct.this,AddHoaDonAct.class);
                startActivity(intent);
            }
        });
    }
}