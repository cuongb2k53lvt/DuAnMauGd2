package com.example.duanmau.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.R;

import java.util.ArrayList;

public class ListHoaDonAdapter extends BaseAdapter {
    Context context;
    ArrayList<HoaDon> arrHoadon;
    public ListHoaDonAdapter(Context context, ArrayList<HoaDon> arrHoadon){
        this.context=context;
        this.arrHoadon=arrHoadon;
    }
    @Override
    public int getCount() {
        return arrHoadon.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoadon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ListHdViewHolder{
        ImageView imgHd;
        ImageView imgDelete;
        TextView tvMaHd;
        TextView tvNgay;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListHdViewHolder listHdViewHolder;
        if(convertView==null){
            listHdViewHolder=new ListHdViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_hoadon_layout,parent,false);
            listHdViewHolder.imgHd=convertView.findViewById(R.id.imgHd);
            listHdViewHolder.imgDelete=convertView.findViewById(R.id.imgDeleteHd);
            listHdViewHolder.tvMaHd=convertView.findViewById(R.id.tvMaHd);
            listHdViewHolder.tvNgay=convertView.findViewById(R.id.tvNgayHd);
            convertView.setTag(listHdViewHolder);
        }   else
            listHdViewHolder=(ListHdViewHolder) convertView.getTag();
        listHdViewHolder.tvMaHd.setText(arrHoadon.get(position).getMaHoaDon());
        listHdViewHolder.tvNgay.setText(arrHoadon.get(position).getNgayMua().toString());
        return convertView;
    }
}
