package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.Class.HoaDonChiTiet;
import com.example.duanmau.Class.SachClass;
import com.example.duanmau.ListViewAdapter.ListHoaDonCtAdapter;
import com.example.duanmau.Sql.HoaDonCtDAO;
import com.example.duanmau.Sql.HoaDonDAO;
import com.example.duanmau.Sql.SachDAO;
import com.example.duanmau.Sql.Sqlite;

import java.text.ParseException;
import java.util.ArrayList;

public class ChiTietHoaDonAct extends AppCompatActivity {
    TextView tvMahd,tvTongTien;
    EditText edtMaSach, edtSlSach;
    Button btnThem, btnThanhToan;
    ListView lvAddHdct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        getSupportActionBar().setTitle("Chi tiết hóa đơn");
        tvMahd=findViewById(R.id.tvMaHd);
        edtMaSach=findViewById(R.id.edtMaSach);
        edtSlSach=findViewById(R.id.edtSlSach);
        btnThem=findViewById(R.id.btnThem);
        btnThanhToan=findViewById(R.id.btnThanhToan);
        lvAddHdct=findViewById(R.id.lvAddHdct);
        tvTongTien=findViewById(R.id.tvTongTien);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String maHd=bundle.getString("MAHD");
        tvMahd.setText(maHd);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSach=edtMaSach.getText().toString();
                String slSach=edtSlSach.getText().toString();
                Sqlite sqlite=new Sqlite(ChiTietHoaDonAct.this);
                HoaDonDAO hoaDonDAO=new HoaDonDAO(sqlite);
                SachDAO sachDAO=new SachDAO(sqlite);
                HoaDonCtDAO hoaDonCtDAO=new HoaDonCtDAO(sqlite);
                ArrayList<SachClass> arrSach=sachDAO.getAllSach();
                HoaDon hoaDon=null;
                SachClass sach=null;
                ArrayList<HoaDon> arrHd= null;
                try {
                    arrHd = hoaDonDAO.getAllHoadon();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<arrHd.size();i++){
                    if(maHd.equalsIgnoreCase(arrHd.get(i).getMaHoaDon())){
                        hoaDon=arrHd.get(i);
                    }
                }
                for (int i=0;i<arrSach.size();i++){
                    if(maSach.equalsIgnoreCase(arrSach.get(i).getMaSach())){
                        sach=arrSach.get(i);
                    }
                }
                Toast.makeText(ChiTietHoaDonAct.this, hoaDon.getMaHoaDon(), Toast.LENGTH_SHORT).show();
                Toast.makeText(ChiTietHoaDonAct.this, sach.getMaSach(), Toast.LENGTH_SHORT).show();
                int checkHdct=checkAddSach(maSach,slSach);
                if(checkHdct==1){
                    HoaDonChiTiet hdct=new HoaDonChiTiet(1,hoaDon,sach,Integer.parseInt(slSach));
                    hoaDonCtDAO.insertHoadonCt(hdct);
                    ArrayList<HoaDonChiTiet> arrHdct=null;
                    try {
                        arrHdct=hoaDonCtDAO.getHdctByMaHd(hoaDon.getMaHoaDon());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ListHoaDonCtAdapter listHoaDonCtAdapter=new ListHoaDonCtAdapter(ChiTietHoaDonAct.this,arrHdct);
                    lvAddHdct.setAdapter(listHoaDonCtAdapter);
                }
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqlite sqlite=new Sqlite(ChiTietHoaDonAct.this);
                HoaDonCtDAO hoaDonCtDAO=new HoaDonCtDAO(sqlite);
                ArrayList<HoaDonChiTiet> arrHdct=null;
                try {
                    arrHdct= hoaDonCtDAO.getHdctByMaHd(maHd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                double tongTien=0;
                for (int i=0;i<arrHdct.size();i++){
                    tongTien+=arrHdct.get(i).getSachClass().getGiaBia()*arrHdct.get(i).getSlMua();
                }
                tvTongTien.setText("Tổng tiền :"+tongTien+" vnđ");
            }
        });
    }
    public int checkAddSach(String maSach, String sl){
        Sqlite sqlite=new Sqlite(ChiTietHoaDonAct.this);
        SachDAO sachDAO=new SachDAO(sqlite);
        ArrayList<SachClass> arrSach=sachDAO.getAllSach();
        int j=0;
        if(maSach.length()==0||sl.length()==0){
            Toast.makeText(this, "Chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return 0;
        }
        for (int i=0;i<arrSach.size();i++){
            if(arrSach.get(i).getMaSach().equalsIgnoreCase(maSach)){
                j++;
            }
        }
        if(j==0){
            Toast.makeText(this, "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
            return 0;
        }
        try {
            Integer.parseInt(sl);
        } catch (NumberFormatException e){
            Toast.makeText(this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
}