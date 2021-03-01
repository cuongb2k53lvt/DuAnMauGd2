package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.ListViewAdapter.ListHoaDonAdapter;
import com.example.duanmau.Sql.HoaDonDAO;
import com.example.duanmau.Sql.Sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonAct extends AppCompatActivity {
    ListView lvHd;
    Button btnAddHd;
    Toolbar toolbar;
    EditText edtSearchHd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        toolbar=findViewById(R.id.tbHoadon);
        btnAddHd=findViewById(R.id.btnAddHd);
        lvHd=findViewById(R.id.lvHoaDon);
        edtSearchHd=findViewById(R.id.edtSearchHd);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hóa đơn");
        Sqlite sqlite=new Sqlite(HoaDonAct.this);
        HoaDonDAO hoaDonDAO=new HoaDonDAO(sqlite);
        ArrayList<HoaDon> arrHoadon= null;
        try {
            arrHoadon = hoaDonDAO.getAllHoadon();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ListHoaDonAdapter listHoaDonAdapter=new ListHoaDonAdapter(HoaDonAct.this,arrHoadon);
        lvHd.setAdapter(listHoaDonAdapter);
        lvHd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<HoaDon> arrHd1=null;
                try {
                    arrHd1=hoaDonDAO.getAllHoadon();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(HoaDonAct.this,HoaDonCtAct.class);
                Bundle bundle=new Bundle();
                bundle.putString("MAHD",arrHd1.get(position).getMaHoaDon());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnAddHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HoaDonAct.this,AddHoaDonAct.class);
                startActivity(intent);
            }
        });
        edtSearchHd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Sqlite sqlite1=new Sqlite(HoaDonAct.this);
                HoaDonDAO hoaDonDAO1=new HoaDonDAO(sqlite1);
                ArrayList<HoaDon> arrHoadon= null;
                ArrayList<HoaDon> arrSearchHd=new ArrayList<>();
                int textSize= edtSearchHd.getText().toString().length();
                try {
                    arrHoadon = hoaDonDAO1.getAllHoadon();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for (int i=0;i<arrHoadon.size();i++){
                    if (arrHoadon.get(i).getMaHoaDon().length()>=textSize){
//                        Toast.makeText(HoaDonAct.this, "z"+arrHoadon.size(), Toast.LENGTH_SHORT).show();
                        if(arrHoadon.get(i).getMaHoaDon().substring(0,textSize).equalsIgnoreCase(edtSearchHd.getText().toString())){
                            arrSearchHd.add(arrHoadon.get(i));
                            Toast.makeText(HoaDonAct.this, "z"+arrSearchHd.size(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                if(textSize==0){
                    ListHoaDonAdapter listHoaDonAdapter1=new ListHoaDonAdapter(HoaDonAct.this,arrHoadon);
                    lvHd.setAdapter(listHoaDonAdapter1);
                } else {
                    ListHoaDonAdapter listHoaDonAdapter1=new ListHoaDonAdapter(HoaDonAct.this,arrSearchHd);
                    lvHd.setAdapter(listHoaDonAdapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}