package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.Class.NguoiDungClass;
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.Sql.UserDAO;

import java.util.ArrayList;

public class DoiMatKhauAct extends AppCompatActivity {
    EditText edtCurrentPw,edtNewPw,edtReNewPw;
    Sqlite sqlite=new Sqlite(DoiMatKhauAct.this);
    UserDAO userDAO=new UserDAO(sqlite);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        getSupportActionBar().setTitle("Đổi mật khẩu");
        edtCurrentPw=findViewById(R.id.edtCurrentPw);
        edtNewPw=findViewById(R.id.edtNewPw);
        edtReNewPw=findViewById(R.id.edtReNewPw);
    }
    public void changePassword(View view) {
        String userId=getIntent().getExtras().getString("USERID");
        ArrayList<NguoiDungClass> arrUser=userDAO.getAllUser();
        String userPassword="";
        for(int i=0;i<arrUser.size();i++){
            if (arrUser.get(i).getUsername().equalsIgnoreCase(userId)){
                userPassword=arrUser.get(i).getPassword();
            }
        }
        if(edtCurrentPw.getText().length()==0||edtNewPw.getText().length()==0||edtReNewPw.getText().length()==0){
            Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            int k=0;
            if(edtCurrentPw.getText().toString().equalsIgnoreCase(userPassword)){
                k++;
            }else {
                Toast.makeText(this, "Sai mật khẩu hiện tại", Toast.LENGTH_SHORT).show();
            }
            if (edtNewPw.getText().toString().equalsIgnoreCase(edtReNewPw.getText().toString())){
                k++;
            } else {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            }
            if (k==2){
                for(int j=0;j<arrUser.size();j++){
                    if (arrUser.get(j).getUsername().equalsIgnoreCase(userId)){
                        arrUser.get(j).setPassword(edtNewPw.getText().toString());
                        userDAO.changePasswordUser(arrUser.get(j));
                    }
                }
                Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Cancel(View view) {
        finish();
    }
}