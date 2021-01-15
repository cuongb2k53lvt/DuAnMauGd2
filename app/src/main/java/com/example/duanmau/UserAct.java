package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.duanmau.Class.NguoiDungClass;
import com.example.duanmau.ListViewAdapter.ListUserAdapter;

import java.util.ArrayList;

public class UserAct extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvUser;
    ArrayList<NguoiDungClass> arrNguoiDung=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar=findViewById(R.id.toolbarUser);
        lvUser=findViewById(R.id.lvNguoiDung);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Người Dùng");
        NguoiDungClass nguoidung1=new NguoiDungClass("cuong12867","1234","036721222","Dương Biên Cương");
        NguoiDungClass nguoidung2=new NguoiDungClass("nothing","1234","325412444","aaaaa");
        arrNguoiDung.add(nguoidung1);
        arrNguoiDung.add(nguoidung2);
        ListUserAdapter listUserAdapter=new ListUserAdapter(UserAct.this,arrNguoiDung);
        lvUser.setAdapter(listUserAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.themUser){
            Intent intent=new Intent(UserAct.this, AddUserAct.class);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.doiPass){
            Intent intent=new Intent(UserAct.this,DoiMatKhauAct.class);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.dangXuat){
            Intent intent=new Intent(UserAct.this,LoginAct.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}