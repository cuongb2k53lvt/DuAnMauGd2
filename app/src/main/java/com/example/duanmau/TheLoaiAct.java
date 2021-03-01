package com.example.duanmau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duanmau.Class.TheLoaiClass;
import com.example.duanmau.ListViewAdapter.ListTheLoaiAdapter;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.Sql.TheLoaiDAO;

import java.util.ArrayList;
import java.util.zip.Inflater;

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
        Sqlite sqlite=new Sqlite(TheLoaiAct.this);
        TheLoaiDAO theLoaiDAO=new TheLoaiDAO(sqlite);
        arrTheloai=theLoaiDAO.getAllTheloai();
        ListTheLoaiAdapter listTheLoaiAdapter=new ListTheLoaiAdapter(TheLoaiAct.this,arrTheloai);
        lvTheloai.setAdapter(listTheLoaiAdapter);
        lvTheloai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(TheLoaiAct.this);
                View view1= LayoutInflater.from(TheLoaiAct.this).inflate(R.layout.theloai_info_layout,null);
                builder.setView(view1);
                TextView tvInfoMatl=view1.findViewById(R.id.tvInfoMatl);
                TextView tvInfoTentl=view1.findViewById(R.id.tvInfoTentl);
                TextView tvInfoVitri=view1.findViewById(R.id.tvInfoVitri);
                TextView tvInfoMota=view1.findViewById(R.id.tvInfoMota);
                tvInfoMatl.setText(arrTheloai.get(position).getMaTheLoai());
                tvInfoTentl.setText(arrTheloai.get(position).getTenTheLoai());
                tvInfoVitri.setText(Integer.toString(arrTheloai.get(position).getViTri()));
                tvInfoMota.setText(arrTheloai.get(position).getMoTa());
                builder.create().show();
                return false;
            }
        });
        btnAddtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TheLoaiAct.this,AddTheLoaiAct.class);
                Bundle bundle=new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}