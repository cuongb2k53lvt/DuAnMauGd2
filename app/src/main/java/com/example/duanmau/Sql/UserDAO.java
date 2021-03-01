package com.example.duanmau.Sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Class.NguoiDungClass;

import java.util.ArrayList;

public class UserDAO {
    Sqlite sqlite;
    public UserDAO(Sqlite sqlite){
        this.sqlite=sqlite;
    }
    public int insertUser(NguoiDungClass nguoiDungClass){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",nguoiDungClass.getUsername());
        contentValues.put("password",nguoiDungClass.getPassword());
        contentValues.put("phone",nguoiDungClass.getPhone());
        contentValues.put("hoten",nguoiDungClass.getHoten());
        sqLiteDatabase.insert("user",null,contentValues);
        return 1;
    }
    public ArrayList<NguoiDungClass> getAllUser(){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        String select="SELECT * FROM user";
        Cursor cursor=sqLiteDatabase.rawQuery(select,null);
        ArrayList<NguoiDungClass> arrUser=new ArrayList<>();
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                NguoiDungClass user=new NguoiDungClass();
                user.setUsername(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                user.setPhone(cursor.getString(2));
                user.setHoten(cursor.getString(3));
                arrUser.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrUser;
    }
    public int updateUser(NguoiDungClass user){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",user.getPassword());
        contentValues.put("phone",user.getPhone());
        contentValues.put("hoten",user.getHoten());
        sqLiteDatabase.update("user",contentValues,"username=?",new String[]{user.getUsername()});
        return 1;
    }
    public int changePasswordUser(NguoiDungClass user){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",user.getPassword());
        sqLiteDatabase.update("user",contentValues,"username=?",new String[]{user.getUsername()});
        return 1;
    }
    public int updateInfoUser(NguoiDungClass user){
        SQLiteDatabase sqLiteDatabase=sqlite.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("phone",user.getPhone());
        contentValues.put("hoten",user.getHoten());
        sqLiteDatabase.update("user",contentValues,"username=?",new String[]{user.getUsername()});
        return 1;
    }
    public int deleteUser(String username){
        SQLiteDatabase sqLiteDatabase=sqlite.getReadableDatabase();
        sqLiteDatabase.delete("user","username=?",new String[]{username});
        return 1;
    }
}
