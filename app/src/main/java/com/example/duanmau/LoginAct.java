package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.Class.NguoiDungClass;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.Sql.UserDAO;

import java.util.ArrayList;

public class LoginAct extends AppCompatActivity {
    Button btnLogin,btnCancel;
    EditText edtUsername, edtPassword;
    CheckBox chkRememberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Đăng nhập");
        btnLogin=findViewById(R.id.btnLogin);
        edtUsername=findViewById(R.id.edtUserName);
        edtPassword=findViewById(R.id.edtPassWord);
        chkRememberPass=findViewById(R.id.chkRememberPass);
        Sqlite sqlite=new Sqlite(LoginAct.this);
        UserDAO userDAO=new UserDAO(sqlite);
        ArrayList<NguoiDungClass> arrUser=new ArrayList<>();
        arrUser=userDAO.getAllUser();
        SharedPreferences sharedPreferences=getSharedPreferences("USER",MODE_PRIVATE);
        edtUsername.setText(sharedPreferences.getString("USERNAME",""));
        edtPassword.setText(sharedPreferences.getString("PASSWORD",""));
        Boolean check=sharedPreferences.getBoolean("REMEMBER",false);
        chkRememberPass.setChecked(check);
        chkRememberPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkRememberPass.isChecked()){
                    rememberUser(edtUsername.getText().toString(),edtPassword.getText().toString(),true);
                }else {
                    rememberUser(edtUsername.getText().toString(),edtPassword.getText().toString(),false);
                }
            }
        });
        NguoiDungClass admin=new NguoiDungClass("admin","admin","0367212838","admin");
        int j=0;
        for (int i=0;i<arrUser.size();i++){
            if (arrUser.get(i).getUsername().equalsIgnoreCase(admin.getUsername())){
                j++;
            }
        }
        if (j==0){
            userDAO.insertUser(admin);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername=edtUsername.getText().toString();
                String strPassword=edtPassword.getText().toString();
                if(strUsername.isEmpty()||strPassword.isEmpty()){
                    Toast.makeText(LoginAct.this, "Không để trống tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    if(checkLogin(strUsername,strPassword)){
                        Toast.makeText(LoginAct.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginAct.this,QuanLySachAct.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("USERID",strUsername);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginAct.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void rememberUser(String username, String password, boolean status){
        SharedPreferences sharedPreferences=getSharedPreferences("USER",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(status==false){
            editor.clear();
        }else {
            editor.putString("USERNAME",username);
            editor.putString("PASSWORD",password);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
    public boolean checkLogin(String username,String password){
        ArrayList<NguoiDungClass> arrUser=new ArrayList<>();
        Sqlite sqlite=new Sqlite(LoginAct.this);
        UserDAO userDAO=new UserDAO(sqlite);
        arrUser=userDAO.getAllUser();
        for (int i=0;i<arrUser.size();i++){
            if(arrUser.get(i).getUsername().equalsIgnoreCase(username)&&arrUser.get(i).getPassword().equalsIgnoreCase(password)){
                return true;
            }
        }
        return false;
    }
}