package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.duanmau.Class.SachClass;
import com.example.duanmau.ListViewAdapter.ListSachAdapter;
import com.example.duanmau.Sql.SachDAO;
import com.example.duanmau.Sql.Sqlite;

import java.util.ArrayList;

public class SachAct extends AppCompatActivity {
    Toolbar tbSach;
    Button btnAddSach;
    ListView lvSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        tbSach=findViewById(R.id.tbSach);
        btnAddSach=findViewById(R.id.btnAddSach);
        lvSach=findViewById(R.id.lvSach);
        setSupportActionBar(tbSach);
        getSupportActionBar().setTitle("Sách");
        Sqlite sqlite=new Sqlite(SachAct.this);
        SachDAO sachDAO=new SachDAO(sqlite);
        ArrayList<SachClass> arrSach=sachDAO.getAllSach();
        ListSachAdapter listSachAdapter=new ListSachAdapter(SachAct.this,arrSach);
        lvSach.setAdapter(listSachAdapter);
        btnAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SachAct.this,AddSachAct.class);
                startActivity(intent);
            }
        });
    }
}