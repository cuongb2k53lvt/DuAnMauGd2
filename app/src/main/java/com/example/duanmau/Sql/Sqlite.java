package com.example.duanmau.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper {
    public Sqlite(Context context){
        super(context,"duanmau1.sql",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String table_user="create table user "+
            "(username nvarchar(50) primary key,"+
            "password nvarchar(50) not null,"+
            "phone nchar(10) not null,"+
            "hoten nvarchar(50))";
    db.execSQL(table_user);
    String  table_theloai="create table theloai "+
            "(matl char(5) primary key not null,"+
            "tentl nvarchar(50) not null,"+
            "mota nvarchar(255),"+
            "vitri int)";
    db.execSQL(table_theloai);
    String table_sach="create table sach "+
            "(masach nchar(5) primary key not null,"+
            "matl char(5),"+
            "tensach nvarchar(50),"+
            "tacgia nvarchar(50),"+
            "nxb nvarchar(50),"+
            "giabia float not null,"+
            "soluong int not null,"+
            "foreign key(matl) references theloai(matl))";
    db.execSQL(table_sach);
    String table_hoadon="create table hoadon "+
            "(mahoadon nchar(7) primary key not null,"+
            "ngaymua date not null)";
    db.execSQL(table_hoadon);
    String table_hoadonct="create table hoadonct "+
            "(mahdct integer primary key autoincrement,"+
            "mahoadon nchar(7) not null,"+
            "masach nchar(5) not null,"+
            "slmua int not null,"+
            "foreign key(mahoadon) references hoadon(mahoadon),"+
            "foreign key(masach) references sach(masach))";
    db.execSQL(table_hoadonct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
