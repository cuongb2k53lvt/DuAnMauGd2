package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.Sql.HoaDonCtDAO;
import com.example.duanmau.Sql.HoaDonDAO;
import com.example.duanmau.Sql.Sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddHoaDonAct extends AppCompatActivity {
    Button btnChonDate, btnChon;
    EditText edtNgaymua,edtMaHd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don);
        getSupportActionBar().setTitle("Thêm hóa đơn");
        btnChonDate=findViewById(R.id.btnChonDate);
        edtNgaymua=findViewById(R.id.edtNgaymua);
        btnChon=findViewById(R.id.btnChon);
        edtMaHd=findViewById(R.id.edtMaHd);
        btnChonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddHoaDonAct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                        Calendar cal=new GregorianCalendar(year,month,dayOfMonth);
                        edtNgaymua.setText(simpleDateFormat.format(cal.getTime()));
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maHd=edtMaHd.getText().toString();
                String date=edtNgaymua.getText().toString();
                int chechHd=checkHoaDon(maHd,date);
                if(chechHd==1){
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
//                    Date date1=null;
//                    try {
//                        date1=simpleDateFormat.parse(date);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                    Sqlite sqlite=new Sqlite(AddHoaDonAct.this);
                    HoaDonDAO hoaDonDAO=new HoaDonDAO(sqlite);
                    HoaDon hoaDon= null;
                    try {
                        hoaDon = new HoaDon(maHd,simpleDateFormat.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    hoaDonDAO.insertHoadon(hoaDon);
                    Intent intent=new Intent(AddHoaDonAct.this,ChiTietHoaDonAct.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("MAHD",maHd);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    public int checkHoaDon(String mahd, String date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
        if(mahd.length()==0||date.length()==0){
            Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return 0;
        }
        try {
            Date date1=simpleDateFormat.parse(date);
        } catch (ParseException e) {
            Toast.makeText(this, "Ngày không đúng định dạng", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
}