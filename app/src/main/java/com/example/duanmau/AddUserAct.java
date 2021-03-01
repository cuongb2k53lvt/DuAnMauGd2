package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.Class.NguoiDungClass;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.Sql.UserDAO;

import java.util.ArrayList;

public class AddUserAct extends AppCompatActivity {
    Button btnCancel,btnAddUser;
    EditText edtUsername, edtPassword, edtRePassword, edtPhone, edtFullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        getSupportActionBar().setTitle("Book Manager");
        btnCancel=findViewById(R.id.btnCancel);
        btnAddUser=findViewById(R.id.btnAddUser);
        edtUsername=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassWord);
        edtRePassword=findViewById(R.id.edtRePassWord);
        edtPhone=findViewById(R.id.edtPhone);
        edtFullname=findViewById(R.id.edtFullname);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqlite sqlite=new Sqlite(AddUserAct.this);
                UserDAO userDAO=new UserDAO(sqlite);
                int n=CheckUser(edtUsername.getText().toString(),edtPassword.getText().toString(),edtRePassword.getText().toString(),edtPhone.getText().toString(),edtFullname.getText().toString());
                if(n==1){
                    NguoiDungClass user=new NguoiDungClass(edtUsername.getText().toString(),edtPassword.getText().toString(),edtPhone.getText().toString(),edtFullname.getText().toString());
                    userDAO.insertUser(user);
                    Toast.makeText(AddUserAct.this, "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void ListUser(View view) {
        Intent intent=new Intent(AddUserAct.this,UserAct.class);
        startActivity(intent);
    }

    public int CheckUser(String username, String password, String rePasword, String phone, String fullname){
        Sqlite sqlite=new Sqlite(AddUserAct.this);
        UserDAO userDAO=new UserDAO(sqlite);
        ArrayList<NguoiDungClass> arrUser=userDAO.getAllUser();
        if(username.length()==0||password.length()==0||phone.length()==0||fullname.length()==0){
            Toast.makeText(this, "Không để trống thông tin", Toast.LENGTH_SHORT).show();
            return 0;
        }
        for (int i=0;i<arrUser.size();i++){
            if(arrUser.get(i).getUsername().equalsIgnoreCase(username)){
                Toast.makeText(this, "Tên người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
        if(password.equalsIgnoreCase(rePasword)==false){
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }
}