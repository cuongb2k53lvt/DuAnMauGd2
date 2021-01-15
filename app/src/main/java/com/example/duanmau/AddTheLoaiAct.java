package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddTheLoaiAct extends AppCompatActivity {
    Button btnShowtl,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_loai);
        btnShowtl=findViewById(R.id.btnShowTl);
        btnCancel=findViewById(R.id.btnCancel);
        getSupportActionBar().setTitle("Thêm thể loại");
        btnShowtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddTheLoaiAct.this,TheLoaiAct.class);
                startActivity(intent);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}