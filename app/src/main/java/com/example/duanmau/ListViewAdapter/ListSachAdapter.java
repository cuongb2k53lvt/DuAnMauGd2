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
import com.example.duanmau.Sql.SachDAO;
import com.example.duanmau.Sql.Sqlite;

import java.util.ArrayList;

public class ListSachAdapter extends BaseAdapter {
    Context context;
    ArrayList<SachClass> arrSach;
    public ListSachAdapter(Context context, ArrayList<SachClass> arrSach){
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
    public class ListSachViewHolder{
        ImageView imgSach;
        ImageView imgDelete;
        TextView tvTensach;
        TextView tvSl;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListSachViewHolder listSachViewHolder;
        if(convertView==null){
            listSachViewHolder=new ListSachViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_sach_layout,parent,false);
            listSachViewHolder.imgSach=convertView.findViewById(R.id.imgSach);
            listSachViewHolder.imgDelete=convertView.findViewById(R.id.imgDeleteSach);
            listSachViewHolder.tvTensach=convertView.findViewById(R.id.tvTensach);
            listSachViewHolder.tvSl=convertView.findViewById(R.id.tvSoLuong);
            convertView.setTag(listSachViewHolder);
        }   else
            listSachViewHolder=(ListSachViewHolder) convertView.getTag();
        listSachViewHolder.tvTensach.setText("Tên sách:"+arrSach.get(position).getTenSach());
        listSachViewHolder.tvSl.setText("Số lượng:"+Integer.toString(arrSach.get(position).getSoLuong()));
        listSachViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqlite sqlite=new Sqlite(context);
                SachDAO sachDAO=new SachDAO(sqlite);
                sachDAO.deleteSach(arrSach.get(position).getMaSach());
                arrSach.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
