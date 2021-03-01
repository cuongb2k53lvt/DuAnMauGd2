package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Class.NguoiDungClass;
import com.example.duanmau.ListViewAdapter.ListUserAdapter;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.Sql.UserDAO;

import java.util.ArrayList;

public class UserAct extends AppCompatActivity {
    Toolbar toolbar;
    ListView lvUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar=findViewById(R.id.toolbarUser);
        lvUser=findViewById(R.id.lvNguoiDung);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Người Dùng");
        Sqlite sqlite=new Sqlite(UserAct.this);
        UserDAO userDAO=new UserDAO(sqlite);
        ArrayList<NguoiDungClass> arrNguoiDung=userDAO.getAllUser();
        ListUserAdapter listUserAdapter=new ListUserAdapter(UserAct.this,arrNguoiDung);
        lvUser.setAdapter(listUserAdapter);
        lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<NguoiDungClass> arrUser=userDAO.getAllUser();
                AlertDialog.Builder builder=new AlertDialog.Builder(UserAct.this);
                View view1=LayoutInflater.from(UserAct.this).inflate(R.layout.user_info_layout,null);
                builder.setView(view1);
                TextView tvInfoUsername=view1.findViewById(R.id.tvInfoUsername);
                TextView tvInfoFullname=view1.findViewById(R.id.tvInfoFullname);
                TextView tvInfoPhone=view1.findViewById(R.id.tvInfoPhone);
                tvInfoUsername.setText(arrUser.get(position).getUsername());
                tvInfoFullname.setText(arrUser.get(position).getHoten());
                tvInfoPhone.setText(arrUser.get(position).getPhone());
                builder.create().show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String userId=getIntent().getExtras().getString("USERID");
        if (item.getItemId()==R.id.themUser){
            Intent intent=new Intent(UserAct.this, AddUserAct.class);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.doiPass){
            Intent intent=new Intent(UserAct.this,DoiMatKhauAct.class);
            Bundle bundle=new Bundle();
            bundle.putString("USERID",userId);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.dangXuat){
            Intent intent=new Intent(UserAct.this,LoginAct.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}