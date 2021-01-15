package com.example.duanmau.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duanmau.Class.TheLoaiClass;
import com.example.duanmau.R;

import java.util.ArrayList;

public class SpinnerTheloaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<TheLoaiClass> arrTheLoai;
    public SpinnerTheloaiAdapter(Context context, ArrayList<TheLoaiClass> arrTheLoai){
        this.context=context;
        this.arrTheLoai=arrTheLoai;
    }
    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class SpinnerTlViewHolder{
        TextView tvSpinnerTl;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerTlViewHolder spinnerTlViewHolder;
        if(convertView==null){
            spinnerTlViewHolder=new SpinnerTlViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.spinner_theloai_layout,parent,false);
            spinnerTlViewHolder.tvSpinnerTl=convertView.findViewById(R.id.tvSpinnerTl);
            convertView.setTag(spinnerTlViewHolder);
        }   else
            spinnerTlViewHolder=(SpinnerTlViewHolder) convertView.getTag();
        spinnerTlViewHolder.tvSpinnerTl.setText(arrTheLoai.get(position).getMaTheLoai()+" | "+arrTheLoai.get(position).getTenTheLoai());
        return convertView;
    }
}
