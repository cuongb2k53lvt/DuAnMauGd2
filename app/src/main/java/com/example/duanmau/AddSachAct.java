package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.duanmau.Class.TheLoaiClass;
import com.example.duanmau.ListViewAdapter.SpinnerTheloaiAdapter;

import java.util.ArrayList;

public class AddSachAct extends AppCompatActivity {
    ImageView imgAddtl;
    Button btnShowSach,btnHuy;
    Spinner spnTl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sach);
        getSupportActionBar().setTitle("Thêm sách");
        imgAddtl=findViewById(R.id.imgAddTl);
        btnShowSach=findViewById(R.id.btnShowSach);
        spnTl=findViewById(R.id.spnTl);
        btnHuy=findViewById(R.id.btnCancel);
        ArrayList<TheLoaiClass> arrTheloai=new ArrayList<>();
        TheLoaiClass tl1=new TheLoaiClass("TL01","CNTT","Đọc thể loại này bị ngu",1);
        arrTheloai.add(tl1);
        SpinnerTheloaiAdapter spinnerTheloaiAdapter=new SpinnerTheloaiAdapter(AddSachAct.this,arrTheloai);
        spnTl.setAdapter(spinnerTheloaiAdapter);
        imgAddtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddSachAct.this,AddTheLoaiAct.class);
                startActivity(intent);
            }
        });
        btnShowSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddSachAct.this,SachAct.class);
                startActivity(intent);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}