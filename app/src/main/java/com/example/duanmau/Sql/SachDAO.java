package com.example.duanmau.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Class.SachClass;

import java.util.ArrayList;

public class SachDAO {
    Sqlite sqlite;
    public SachDAO(Sqlite sqlite){
        this.sqlite=sqlite;
    }
    public int insertSach(SachClass sach){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("masach",sach.getMaSach());
        contentValues.put("matl",sach.getMaTheLoai());
        contentValues.put("tensach",sach.getTenSach());
        contentValues.put("tacgia",sach.getTacGia());
        contentValues.put("nxb",sach.getNxb());
        contentValues.put("giabia",sach.getGiaBia());
        contentValues.put("soluong",sach.getSoLuong());
        sqLiteDatabase.insert("sach",null,contentValues);
        return 1;
    }
    public ArrayList<SachClass> getAllSach(){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        String select="SELECT * FROM sach";
        ArrayList<SachClass> arrSach=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                SachClass sach=new SachClass();
                sach.setMaSach(cursor.getString(0));
                sach.setMaTheLoai(cursor.getString(1));
                sach.setTenSach(cursor.getString(2));
                sach.setTacGia(cursor.getString(3));
                sach.setNxb(cursor.getString(4));
                sach.setGiaBia(cursor.getFloat(5));
                sach.setSoLuong(cursor.getInt(6));
                arrSach.add(sach);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrSach;
    }
    public int updateSach(SachClass sach){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("matl",sach.getMaTheLoai());
        contentValues.put("tensach",sach.getTenSach());
        contentValues.put("tacgia",sach.getTacGia());
        contentValues.put("nxb",sach.getNxb());
        contentValues.put("giabia",sach.getGiaBia());
        contentValues.put("soluong",sach.getSoLuong());
        sqLiteDatabase.update("sach",contentValues,"masach=?",new String[]{sach.getMaSach()});
        return 1;
    }
    public int deleteSach(String masach){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        sqLiteDatabase.delete("sach","masach=?",new String[]{masach});
        return 1;
    }
    public SachClass checkSach(String masach){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        String select="SELECT * FROM sach";
        SachClass sach=new SachClass();
        Cursor cursor=sqLiteDatabase.query("sach",new String[]{"masach"},"masach=?",new String[]{masach},null,null,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                sach.setMaSach(cursor.getString(0));
                sach.setMaTheLoai(cursor.getString(1));
                sach.setTenSach(cursor.getString(2));
                sach.setTacGia(cursor.getString(3));
                sach.setNxb(cursor.getString(4));
                sach.setGiaBia(cursor.getFloat(5));
                sach.setSoLuong(cursor.getInt(6));
                break;
            }
        }
        cursor.close();
        return sach;
    }
    public ArrayList<SachClass> getTop10Sach(String month){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        ArrayList<SachClass> dsSach = new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month = "0"+month;
        }
        String sSQL = "SELECT masach, SUM(slmua) as soLuong FROM hoadonct INNER JOIN hoadon " +
        "ON hoadon.mahoadon = hoadonct.mahoadon WHERE strftime('%m',hoadon.ngaymua) = '"+month+"' " +
        "GROUP BY masach ORDER BY soLuong DESC LIMIT 10";
//        String sSQL="SELECT masach FROM hoadonct INNER JOIN hoadon "+
//                "ON hoadon.mahoadon = hoadonct.mahoadon " +
//                "WHERE STRFTIME('%m',hoadon.ngaymua) = '"+"02"+"' "+
//                "GROUP BY masach";
//        String sSQL="SELECT strftime('%m',hoadon.ngaymua) FROM hoadon ";
//                "WHERE strftime('%m',ngaymua)='2';";
        Cursor c = sqLiteDatabase.rawQuery(sSQL, null);
        if(c.getCount()>0){
            c.moveToFirst();
            while (c.isAfterLast()==false){
                SachClass s = new SachClass();
                s.setMaSach(c.getString(0));
                s.setSoLuong(c.getInt(1));
                s.setGiaBia(0);
                s.setMaTheLoai("");
                s.setTenSach("");
                s.setTacGia("");
                s.setNxb("");
                dsSach.add(s);
                c.moveToNext();
            }
        }
        c.close();
        return dsSach;
    }
}
