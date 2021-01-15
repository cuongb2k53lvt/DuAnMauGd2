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

import java.util.Calendar;

public class AddHoaDonAct extends AppCompatActivity {
    Button btnChonDate, btnChon;
    EditText edtNgaymua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don);
        getSupportActionBar().setTitle("Thêm hóa đơn");
        btnChonDate=findViewById(R.id.btnChonDate);
        edtNgaymua=findViewById(R.id.edtNgaymua);
        btnChon=findViewById(R.id.btnChon);
        btnChonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddHoaDonAct.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtNgaymua.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddHoaDonAct.this,ChiTietHoaDonAct.class);
                startActivity(intent);
            }
        });
    }
}