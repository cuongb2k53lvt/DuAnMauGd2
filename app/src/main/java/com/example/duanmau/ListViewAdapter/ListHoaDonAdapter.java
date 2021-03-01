package com.example.duanmau.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;

import com.example.duanmau.Class.HoaDon;
import com.example.duanmau.Class.HoaDonChiTiet;
import com.example.duanmau.R;
import com.example.duanmau.Sql.HoaDonCtDAO;
import com.example.duanmau.Sql.HoaDonDAO;
import com.example.duanmau.Sql.Sqlite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
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
        listHdViewHolder.tvNgay.setText(sdf.format(arrHoadon.get(position).getNgayMua()));
        listHdViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqlite sqlite=new Sqlite(context);
                HoaDonDAO hoaDonDAO=new HoaDonDAO(sqlite);
                HoaDonCtDAO hoaDonCtDAO=new HoaDonCtDAO(sqlite);
                ArrayList<HoaDonChiTiet> arrHdct= null;
                try {
                    arrHdct = hoaDonCtDAO.getHdctByMaHd(arrHoadon.get(position).getMaHoaDon());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(arrHdct.size()==0){
                    hoaDonDAO.deleteHoadon(arrHoadon.get(position).getMaHoaDon());
                    arrHoadon.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Xóa hết hóa đơn chi tiết trước khi xóa hóa đơn", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }
}
