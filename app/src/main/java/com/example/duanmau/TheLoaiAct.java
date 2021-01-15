package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.duanmau.Class.TheLoaiClass;
import com.example.duanmau.ListViewAdapter.ListTheLoaiAdapter;

import java.util.ArrayList;

public class TheLoaiAct extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvTheloai;
    Button btnAddtl;
    ArrayList<TheLoaiClass> arrTheloai=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        toolbar=findViewById(R.id.tbTheloai);
        lvTheloai=findViewById(R.id.lvTheloai);
        btnAddtl=findViewById(R.id.btnAddTl);
        setSupportActionBar(toolbar);
        TheLoaiClass theLoai1=new TheLoaiClass("tl1","Công nghệ thông tin","Tài liệu",1);
        TheLoaiClass theLoai2=new TheLoaiClass("tl2","Kế toán","Tài liệu",2);
        arrTheloai.add(theLoai1);
        arrTheloai.add(theLoai2);
        ListTheLoaiAdapter listTheLoaiAdapter=new ListTheLoaiAdapter(TheLoaiAct.this,arrTheloai);
        lvTheloai.setAdapter(listTheLoaiAdapter);
        btnAddtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TheLoaiAct.this,AddTheLoaiAct.class);
                startActivity(intent);
            }
        });
    }
}