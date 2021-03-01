package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.Class.TheLoaiClass;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.Sql.TheLoaiDAO;

import java.util.ArrayList;

public class AddTheLoaiAct extends AppCompatActivity {
    Button btnShowtl,btnCancel,btnAddtl;
    EditText edtMaTheLoai, edtTenTheLoai, edtViTriTl, edtMotaTl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_loai);
        btnShowtl=findViewById(R.id.btnShowTl);
        btnCancel=findViewById(R.id.btnCancel);
        btnAddtl=findViewById(R.id.btnAddTl);
        edtMaTheLoai=findViewById(R.id.edtMaTheloai);
        edtTenTheLoai=findViewById(R.id.edtTenTheloai);
        edtViTriTl=findViewById(R.id.edtVitriTl);
        edtMotaTl=findViewById(R.id.edtMotaTl);
        Sqlite sqlite=new Sqlite(AddTheLoaiAct.this);
        TheLoaiDAO theLoaiDAO=new TheLoaiDAO(sqlite);
        Intent intent=getIntent();
        Bundle bundle=getIntent().getExtras();
        String addSach=bundle.getString("ADDSACH","0");
        btnAddtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int check = CheckTheLoai(edtMaTheLoai.getText().toString(), edtTenTheLoai.getText().toString(), edtViTriTl.getText().toString(), edtMotaTl.getText().toString());
               if(check == 1){
                   TheLoaiClass theLoai=new TheLoaiClass(edtMaTheLoai.getText().toString(),edtTenTheLoai.getText().toString(),edtMotaTl.getText().toString(),Integer.parseInt(edtViTriTl.getText().toString()));
                   theLoaiDAO.insertTheloai(theLoai);
                   Toast.makeText(AddTheLoaiAct.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                   if(addSach.equalsIgnoreCase("1")){
                       Intent intent1=new Intent(AddTheLoaiAct.this,AddSachAct.class);
                       startActivity(intent1);
                   }
               }
            }
        });
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

    public int CheckTheLoai(String maTl, String tenTl, String viTri, String moTa){
        Sqlite sqlite=new Sqlite(AddTheLoaiAct.this);
        TheLoaiDAO theLoaiDAO=new TheLoaiDAO(sqlite);
        ArrayList<TheLoaiClass> arrTheLoai=theLoaiDAO.getAllTheloai();
        if(maTl.length()==0||tenTl.length()==0||viTri.length()==0||moTa.length()==0){
            Toast.makeText(this, "Không đẻ trống thông tin", Toast.LENGTH_SHORT).show();
            return 0;
        }
        for (int i=0;i<arrTheLoai.size();i++){
            if(maTl.equalsIgnoreCase(arrTheLoai.get(i).getMaTheLoai())){
                Toast.makeText(this, "Mã thể loại đã tồn tại", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
        try {
            Integer.parseInt(viTri);
        } catch (NumberFormatException e){
            Toast.makeText(this, "Vị trí phải là số nguyên", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
}