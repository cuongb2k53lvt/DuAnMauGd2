package com.example.duanmau.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.Class.TheLoaiClass;
import com.example.duanmau.R;

import java.util.ArrayList;

public class ListTheLoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<TheLoaiClass> arrTheloai;
    public ListTheLoaiAdapter(Context context,ArrayList<TheLoaiClass> arrTheloai){
        this.context=context;
        this.arrTheloai=arrTheloai;
    }
    @Override
    public int getCount() {
        return arrTheloai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheloai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ListTheLoaiViewHolder{
        ImageView imgTheloai;
        ImageView imgDelete;
        TextView tvVitri;
        TextView tvTentl;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListTheLoaiViewHolder listTheLoaiViewHolder;
        if(convertView==null){
            listTheLoaiViewHolder=new ListTheLoaiViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_theloai_layout,parent,false);
            listTheLoaiViewHolder.imgTheloai=convertView.findViewById(R.id.imgTheloai);
            listTheLoaiViewHolder.tvVitri=convertView.findViewById(R.id.tvVitri);
            listTheLoaiViewHolder.tvTentl=convertView.findViewById(R.id.tvTentheloai);
            listTheLoaiViewHolder.imgDelete=convertView.findViewById(R.id.imgDeleteTheloai);
            convertView.setTag(listTheLoaiViewHolder);
        }   else
            listTheLoaiViewHolder=(ListTheLoaiViewHolder) convertView.getTag();
        listTheLoaiViewHolder.tvVitri.setText(Integer.toString(arrTheloai.get(position).getViTri()));
        listTheLoaiViewHolder.tvTentl.setText(arrTheloai.get(position).getTenTheLoai());
        return convertView;
    }
}
