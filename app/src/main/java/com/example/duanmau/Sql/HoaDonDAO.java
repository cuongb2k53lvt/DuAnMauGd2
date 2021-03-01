package com.example.duanmau.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Class.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonDAO {
    Sqlite sqlite;
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonDAO(Sqlite sqlite){
        this.sqlite=sqlite;
    }
    public int insertHoadon(HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("mahoadon",hoaDon.getMaHoaDon());
        contentValues.put("ngaymua",dateFormat.format(hoaDon.getNgayMua()));
        sqLiteDatabase.insert("hoadon",null,contentValues);
        return 1;
    }
    public ArrayList<HoaDon> getAllHoadon() throws ParseException {
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        ArrayList<HoaDon> arrHoadon=new ArrayList<>();
        String select="SELECT * FROM hoadon";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                HoaDon hoaDon=new HoaDon();
                hoaDon.setMaHoaDon(cursor.getString(0));
                hoaDon.setNgayMua(dateFormat.parse(cursor.getString(1)));
                arrHoadon.add(hoaDon);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrHoadon;
    }
    public int updateHoadon (HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("ngaymua",dateFormat.format(hoaDon.getNgayMua()));
        sqLiteDatabase.update("hoadon",contentValues,"mahoadon=?",new String[]{hoaDon.getMaHoaDon()});
        return 1;
    }
    public int deleteHoadon(String mahoadon){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        sqLiteDatabase.delete("hoadon","mahoadon=?",new String[]{mahoadon});
        return 1;
    }
}
