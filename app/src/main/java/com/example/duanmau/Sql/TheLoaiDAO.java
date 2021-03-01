package com.example.duanmau.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Class.TheLoaiClass;

import java.util.ArrayList;

public class TheLoaiDAO {
    Sqlite sqlite;
    public TheLoaiDAO(Sqlite sqlite){
        this.sqlite=sqlite;
    }
    public int insertTheloai(TheLoaiClass theLoaiClass){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("matl",theLoaiClass.getMaTheLoai());
        contentValues.put("tentl",theLoaiClass.getTenTheLoai());
        contentValues.put("mota",theLoaiClass.getMoTa());
        contentValues.put("vitri",theLoaiClass.getViTri());
        sqLiteDatabase.insert("theloai",null,contentValues);
        return 1;
    }
    public ArrayList<TheLoaiClass> getAllTheloai(){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        String select="SELECT * FROM theloai";
        ArrayList<TheLoaiClass> arrTheloai=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                TheLoaiClass theloai=new TheLoaiClass();
                theloai.setMaTheLoai(cursor.getString(0));
                theloai.setTenTheLoai(cursor.getString(1));
                theloai.setMoTa(cursor.getString(2));
                theloai.setViTri(cursor.getInt(3));
                arrTheloai.add(theloai);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrTheloai;
    }
    public int updateTheloai(TheLoaiClass theloai){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tentl",theloai.getTenTheLoai());
        contentValues.put("mota",theloai.getMoTa());
        contentValues.put("vitri",theloai.getViTri());
        sqLiteDatabase.update("theloai",contentValues,"matl=?",new String[]{theloai.getMaTheLoai()});
        return 1;
    }
    public int deleteTheloai(String matl){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        sqLiteDatabase.delete("theloai","matl=?",new String[]{matl});
        return 1;
    }
}
