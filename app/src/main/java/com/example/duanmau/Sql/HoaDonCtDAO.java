package com.example.duanmau.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.Class.HoaDonChiTiet;
import com.example.duanmau.Class.SachClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDonCtDAO {
    Sqlite sqlite;
    SimpleDateFormat dateFormat=new SimpleDateFormat("dd-mm-yyyy");
    public HoaDonCtDAO(Sqlite sqlite){
        this.sqlite=sqlite;
    }
    public int insertHoadonCt(HoaDonChiTiet hoaDonChiTiet){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("mahoadon",hoaDonChiTiet.getHoaDon().getMaHoaDon());
        contentValues.put("masach",hoaDonChiTiet.getSachClass().getMaSach());
        contentValues.put("slmua",hoaDonChiTiet.getSlMua());
        sqLiteDatabase.insert("hoadonct",null,contentValues);
        return 1;
        //zz
    }
    public ArrayList<HoaDonChiTiet> getAllHoadonCt() throws ParseException {
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        ArrayList<HoaDonChiTiet> arrHdct=new ArrayList<>();
        String select="SELECT mahdct, hoadon.mahoadon, hoadon.ngaymua, sach.masach, sach.matl, sach.tensach, sach.tacgia, "+
                "sach.nxb, sach.giabia, sach.soluong, hoadonct.slmua FROM hoadonct INNER JOIN sach ON "+
                "sach.masach = hoadonct.masach "+
                "INNER JOIN hoadon ON hoadonct.mahoadon = hoadon.mahoadon";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                HoaDonChiTiet hoaDonChiTiet=new HoaDonChiTiet();
                hoaDonChiTiet.setMaHdct(cursor.getInt(0));
                hoaDonChiTiet.setHoaDon(new HoaDon(cursor.getString(1),dateFormat.parse(cursor.getString(2))));
                hoaDonChiTiet.setSachClass(new SachClass(cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getInt(9)));
                hoaDonChiTiet.setSlMua(cursor.getInt(10));
                arrHdct.add(hoaDonChiTiet);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrHdct;
    }
    public  ArrayList<HoaDonChiTiet> getHdctByMaHd(String maHoaDon) throws ParseException {
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        ArrayList<HoaDonChiTiet> arrHdct=new ArrayList<>();
        String select="SELECT mahdct, hoadon.mahoadon, hoadon.ngaymua, sach.masach, sach.matl, sach.tensach, sach.tacgia, "+
                "sach.nxb, sach.giabia, sach.soluong, hoadonct.slmua FROM hoadonct INNER JOIN sach ON "+
                "sach.masach = hoadonct.masach "+
                "INNER JOIN hoadon ON hoadonct.mahoadon = hoadon.mahoadon "+
                "WHERE hoadonct.mahoadon ='"+maHoaDon+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                HoaDonChiTiet hoaDonChiTiet=new HoaDonChiTiet();
                hoaDonChiTiet.setMaHdct(cursor.getInt(0));
                hoaDonChiTiet.setHoaDon(new HoaDon(cursor.getString(1),dateFormat.parse(cursor.getString(2))));
                hoaDonChiTiet.setSachClass(new SachClass(cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getInt(9)));
                hoaDonChiTiet.setSlMua(cursor.getInt(10));
                arrHdct.add(hoaDonChiTiet);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrHdct;
    }
    public HoaDonChiTiet getHoadonCtById(String mahdct) throws ParseException{
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        HoaDonChiTiet hoaDonChiTiet=new HoaDonChiTiet();
        String select="SELECT mahdct, hoadon.mahoadon, hoadon.ngaymua, sach.masach, sach.matl, sach.tensach, sach.tacgia, "+
                "sach.nxb, sach.giabia, sach.soluong FROM hoadonct INNER JOIN sach ON "+
                "sach.masach='"+mahdct+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                hoaDonChiTiet.setMaHdct(cursor.getInt(0));
                hoaDonChiTiet.setHoaDon(new HoaDon(cursor.getString(1),dateFormat.parse(cursor.getString(2))));
                hoaDonChiTiet.setSachClass(new SachClass(cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getInt(9)));
                hoaDonChiTiet.setSlMua(cursor.getInt(10));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return hoaDonChiTiet;
    }
    public int updateHoadonCt (HoaDonChiTiet hoaDonChiTiet){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("mahoadon",hoaDonChiTiet.getHoaDon().getMaHoaDon());
        contentValues.put("masach",hoaDonChiTiet.getSachClass().getMaSach());
        contentValues.put("slmua",hoaDonChiTiet.getSlMua());
        sqLiteDatabase.update("hoadonct",contentValues,"mahdct=?",new String[]{Integer.toString(hoaDonChiTiet.getMaHdct())});
        return 1;
    }
    public int deleteHoadonCt(String mahdct){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        sqLiteDatabase.delete("hoadonct","mahdct=?",new String[]{mahdct});
        return 1;
    }
    public double getDoanhThuTheoNgay(){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        double doanhThu=0;
        String select="SELECT SUM(tongtien) FROM (SELECT SUM(sach.giabia*hoadonct.slmua) AS 'tongtien' "+
                "FROM hoadon INNER JOIN hoadonct ON hoadon.mahoadon=hoadonct.mahoadon "+
                "INNER JOIN sach ON hoadonct.masach=sach.masach "+
                "WHERE hoadon.ngaymua = date('now') GROUP BY hoadonct.masach)tmp";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                doanhThu=cursor.getDouble(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang(){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        double doanhThu=0;
        String select="SELECT SUM(tongtien) FROM (SELECT SUM(sach.giabia*hoadonct.slmua) AS 'tongtien' "+
                "FROM hoadon INNER JOIN hoadonct ON hoadon.mahoadon=hoadonct.mahoadon "+
                "INNER JOIN sach ON hoadonct.masach=sach.masach "+
                "WHERE strftime('%m',hoadon.ngaymua)= strftime('%m','now') GROUP BY hoadonct.masach)tmp";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                doanhThu=cursor.getDouble(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        double doanhThu=0;
        String select="SELECT SUM(tongtien) FROM (SELECT SUM(sach.giabia*hoadonct.slmua) AS 'tongtien' "+
                "FROM hoadon INNER JOIN hoadonct ON hoadon.mahoadon=hoadonct.mahoadon "+
                "INNER JOIN sach ON hoadonct.masach=sach.masach "+
                "WHERE strftime('%Y',hoadon.ngaymua)= strftime('%Y','now') GROUP BY hoadonct.masach)tmp";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                doanhThu=cursor.getDouble(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return doanhThu;
    }
}
