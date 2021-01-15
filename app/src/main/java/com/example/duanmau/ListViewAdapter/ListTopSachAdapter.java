package com.example.duanmau.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.Class.SachClass;
import com.example.duanmau.R;

import java.util.ArrayList;

public class ListTopSachAdapter extends BaseAdapter {
    Context context;
    ArrayList<SachClass> arrSach;
    public ListTopSachAdapter(Context context,ArrayList<SachClass> arrSach){
        this.context=context;
        this.arrSach=arrSach;
    }
    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ListTopSachViewHolder{
        ImageView imgSach;
        ImageView imgDelete;
        TextView tvMasach;
        TextView tvSl;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListTopSachViewHolder listTopSachViewHolder;
        if(convertView==null){
            listTopSachViewHolder=new ListTopSachViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_topsach_layout,parent,false);
            listTopSachViewHolder.imgSach=convertView.findViewById(R.id.imgSach);
            listTopSachViewHolder.imgDelete=convertView.findViewById(R.id.imgDeleteSach);
            listTopSachViewHolder.tvMasach=convertView.findViewById(R.id.tvMasach);
            listTopSachViewHolder.tvSl=convertView.findViewById(R.id.tvSoLuong);
            convertView.setTag(listTopSachViewHolder);
        }   else
            listTopSachViewHolder=(ListTopSachViewHolder) convertView.getTag();
        listTopSachViewHolder.tvMasach.setText("Mã sách: "+arrSach.get(position).getMaSach());
        listTopSachViewHolder.tvSl.setText("Số lượng"+arrSach.get(position).getSoLuong());
        return convertView;
    }
}
