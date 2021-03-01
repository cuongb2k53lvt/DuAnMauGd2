package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau.Class.SachClass;
import com.example.duanmau.Class.TheLoaiClass;
import com.example.duanmau.ListViewAdapter.SpinnerTheloaiAdapter;
import com.example.duanmau.Sql.SachDAO;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.Sql.TheLoaiDAO;

import java.text.ParseException;
import java.util.ArrayList;

public class AddSachAct extends AppCompatActivity {
    ImageView imgAddtl;
    Button btnShowSach,btnHuy,btnAddSach;
    Spinner spnTl;
    EditText edtMaSach, edtTenSach, edtTacGia, edtNxb, edtGiaSach, edtSlSach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sach);
        getSupportActionBar().setTitle("Thêm sách");
        imgAddtl=findViewById(R.id.imgAddTl);
        btnShowSach=findViewById(R.id.btnShowSach);
        btnAddSach=findViewById(R.id.btnAddSach);
        spnTl=findViewById(R.id.spnTl);
        btnHuy=findViewById(R.id.btnCancel);
        edtMaSach=findViewById(R.id.edtMaSach);
        edtTenSach=findViewById(R.id.edtTensach);
        edtTacGia=findViewById(R.id.edtTacgia);
        edtNxb=findViewById(R.id.edtNxb);
        edtGiaSach=findViewById(R.id.edtGiaSach);
        edtSlSach=findViewById(R.id.edtSlSach);

        Sqlite sqlite=new Sqlite(AddSachAct.this);
        TheLoaiDAO theLoaiDAO= new TheLoaiDAO(sqlite);
        ArrayList<TheLoaiClass> arrTheloai= theLoaiDAO.getAllTheloai();
        SpinnerTheloaiAdapter spinnerTheloaiAdapter=new SpinnerTheloaiAdapter(AddSachAct.this,arrTheloai);
        spnTl.setAdapter(spinnerTheloaiAdapter);
        btnAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSach=edtMaSach.getText().toString();
                String tenSach=edtTenSach.getText().toString();
                String tacGia=edtTacGia.getText().toString();
                String nxb=edtNxb.getText().toString();
                String giaSach=edtGiaSach.getText().toString();
                String slSach=edtSlSach.getText().toString();
                int checkAddSach= checkAddSach(maSach,tenSach,tacGia,nxb,giaSach,slSach);
                if(checkAddSach==1){
                    String theLoai=spnTl.getSelectedItem().toString();
                    SachClass sach=new SachClass(maSach,theLoai,tenSach,tacGia,nxb,Double.parseDouble(giaSach),Integer.parseInt(slSach));
                    SachDAO sachDAO=new SachDAO(sqlite);
                    sachDAO.insertSach(sach);
                    Toast.makeText(AddSachAct.this, "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddSachAct.this,SachAct.class);
                    startActivity(intent);
                }
            }
        });
        imgAddtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddSachAct.this,AddTheLoaiAct.class);
                Bundle bundle=new Bundle();
                bundle.putString("ADDSACH","1");
                intent.putExtras(bundle);
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
    public int checkAddSach(String maSach, String tenSach, String tacGia, String nxb, String giaSach, String slSach){
        Sqlite sqlite=new Sqlite(AddSachAct.this);
        SachDAO sachDAO=new SachDAO(sqlite);
        ArrayList<SachClass> arrSach=sachDAO.getAllSach();
        if(maSach.length()==0||tenSach.length()==0||tacGia.length()==0||nxb.length()==0||giaSach.length()==0||slSach.length()==0){
            Toast.makeText(this, "Chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return 0;
        }
        for(int i=0;i<arrSach.size();i++){
            if(arrSach.get(i).getMaSach().equalsIgnoreCase(maSach)){
                Toast.makeText(this, "Mã sách đã tồn tại", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
        try {
            Double.parseDouble(giaSach);
        } catch (NumberFormatException e){
            Toast.makeText(this, "Giá sách không hợp lệ", Toast.LENGTH_SHORT).show();
            return 0;
        }
        try {
            Integer.parseInt(slSach);
        } catch (NumberFormatException e){
            Toast.makeText(this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
}