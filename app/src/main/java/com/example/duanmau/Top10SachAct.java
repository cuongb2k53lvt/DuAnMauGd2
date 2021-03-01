package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.Class.HoaDonChiTiet;
import com.example.duanmau.Class.SachClass;
import com.example.duanmau.ListViewAdapter.ListSachAdapter;
import com.example.duanmau.ListViewAdapter.ListTopSachAdapter;
import com.example.duanmau.Sql.HoaDonCtDAO;
import com.example.duanmau.Sql.HoaDonDAO;
import com.example.duanmau.Sql.SachDAO;
import com.example.duanmau.Sql.Sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Top10SachAct extends AppCompatActivity {
    EditText edtTopThang;
    Button btnTop10;
    ListView lvTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10_sach);
        getSupportActionBar().setTitle("Top 10 Sách bán chạy nhất");
        edtTopThang=findViewById(R.id.edtTopThang);
        btnTop10=findViewById(R.id.btnTimTop);
        lvTop=findViewById(R.id.lvTop);
        Sqlite sqlite=new Sqlite(Top10SachAct.this);
        SachDAO sachDAO=new SachDAO(sqlite);
        HoaDonDAO hoaDonDAO=new HoaDonDAO(sqlite);
        HoaDonCtDAO hoaDonCtDAO=new HoaDonCtDAO(sqlite);
        btnTop10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                int chechThang= checkThang(edtTopThang.getText().toString());
                if(chechThang==1){
                    ArrayList<HoaDon> arrHd=null;
                    try {
                        arrHd=hoaDonDAO.getAllHoadon();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ArrayList<HoaDonChiTiet> arrHdct=null;
                    try {
                        arrHdct=hoaDonCtDAO.getAllHoadonCt();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ArrayList<SachClass> arrSach=sachDAO.getTop10Sach(edtTopThang.getText().toString());
                    ArrayList<SachClass> arrSach2=sachDAO.getAllSach();
                    ListTopSachAdapter listTopSachAdapter=new ListTopSachAdapter(Top10SachAct.this,arrSach);
                    Toast.makeText(Top10SachAct.this, "topsach "+arrSach.size(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Top10SachAct.this, "sach "+arrSach2.size(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Top10SachAct.this, "hoa don "+sdf.format(arrHd.get(0).getNgayMua()), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Top10SachAct.this, "hdct "+arrHdct.size(), Toast.LENGTH_SHORT).show();
                    lvTop.setAdapter(listTopSachAdapter);
                }
            }
        });
    }
    public int checkThang(String thang){
        if(thang.length()==0){
            Toast.makeText(this, "Không để trống", Toast.LENGTH_SHORT).show();
            return 0;
        }
        int j = 0;
        try {
            j=Integer.parseInt(thang);
        } catch (NumberFormatException e){
            Toast.makeText(this, "Tháng không hợp lệ", Toast.LENGTH_SHORT).show();
            return 0;
        }
        if (j<1||j>12){
            Toast.makeText(this, "Tháng không hợp lệ", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
}