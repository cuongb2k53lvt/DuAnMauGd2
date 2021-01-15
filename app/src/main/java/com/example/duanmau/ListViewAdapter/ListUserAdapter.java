package com.example.duanmau.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.Class.NguoiDungClass;
import com.example.duanmau.R;

import java.util.ArrayList;

public class ListUserAdapter extends BaseAdapter {
    Context context;
    ArrayList<NguoiDungClass> arrNguoiDung;
    public ListUserAdapter(Context context,ArrayList<NguoiDungClass> arrNguoiDung){
        this.context=context;
        this.arrNguoiDung=arrNguoiDung;
    }
    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ListUserViewHolder{
        ImageView imgUser;
        ImageView imgClose;
        TextView tvFullName;
        TextView tvPhone;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListUserViewHolder listUserViewHolder;
        if(convertView==null){
            listUserViewHolder=new ListUserViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_user_layout,parent,false);
            listUserViewHolder.imgUser=convertView.findViewById(R.id.imgUser);
            listUserViewHolder.imgClose=convertView.findViewById(R.id.imgClose);
            listUserViewHolder.tvFullName=convertView.findViewById(R.id.tvFullname);
            listUserViewHolder.tvPhone=convertView.findViewById(R.id.tvPhone);
            convertView.setTag(listUserViewHolder);
        }   else
            listUserViewHolder=(ListUserViewHolder) convertView.getTag();

        listUserViewHolder.imgUser.setImageResource(R.drawable.emone);
        listUserViewHolder.tvFullName.setText(arrNguoiDung.get(position).getHoten());
        listUserViewHolder.tvPhone.setText(arrNguoiDung.get(position).getPhone());
        return convertView;
    }
}
