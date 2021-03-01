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
import com.example.duanmau.Sql.Sqlite;
import com.example.duanmau.Sql.TheLoaiDAO;

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
        TextView tvMatl;
        TextView tvTentl;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListTheLoaiViewHolder listTheLoaiViewHolder;
        if(convertView==null){
            listTheLoaiViewHolder=new ListTheLoaiViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_theloai_layout,parent,false);
            listTheLoaiViewHolder.imgTheloai=convertView.findViewById(R.id.imgTheloai);
            listTheLoaiViewHolder.tvMatl=convertView.findViewById(R.id.tvMatl);
            listTheLoaiViewHolder.tvTentl=convertView.findViewById(R.id.tvTentheloai);
            listTheLoaiViewHolder.imgDelete=convertView.findViewById(R.id.imgDeleteTheloai);
            convertView.setTag(listTheLoaiViewHolder);
        }   else
            listTheLoaiViewHolder=(ListTheLoaiViewHolder) convertView.getTag();
        listTheLoaiViewHolder.tvMatl.setText("Mã thể loại:"+arrTheloai.get(position).getMaTheLoai());
        listTheLoaiViewHolder.tvTentl.setText("Tên thể loại:"+arrTheloai.get(position).getTenTheLoai());
        listTheLoaiViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqlite sqlite=new Sqlite(context);
                TheLoaiDAO theLoaiDAO=new TheLoaiDAO(sqlite);
                theLoaiDAO.deleteTheloai(arrTheloai.get(position).getMaTheLoai());
                arrTheloai.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
