package com.example.duanmau.ListViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.Class.HoaDonChiTiet;
import com.example.duanmau.R;
import com.example.duanmau.Sql.HoaDonCtDAO;
import com.example.duanmau.Sql.Sqlite;

import java.util.ArrayList;

public class ListHoaDonCtAdapter extends BaseAdapter {
    Context context;
    ArrayList<HoaDonChiTiet> arrHoaDonCt;
    public ListHoaDonCtAdapter(Context context, ArrayList<HoaDonChiTiet> arrHoaDonCt){
        this.context=context;
        this.arrHoaDonCt=arrHoaDonCt;
    }
    @Override
    public int getCount() {
        return arrHoaDonCt.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDonCt.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ListHdctViewHolder{
        TextView tvMaSachHdct;
        TextView tvSlHdct;
        TextView tvGiaSachHdct;
        TextView tvThanhTien;
        ImageView imgDeleteHdct;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListHdctViewHolder listHdctViewHolder;
        if(convertView==null){
            listHdctViewHolder=new ListHdctViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_hoadonct_layout,parent,false);
            listHdctViewHolder.tvMaSachHdct=convertView.findViewById(R.id.tvMaSachhdct);
            listHdctViewHolder.tvSlHdct=convertView.findViewById(R.id.tvSoluongHdct);
            listHdctViewHolder.tvGiaSachHdct=convertView.findViewById(R.id.tvGiasachHdct);
            listHdctViewHolder.tvThanhTien=convertView.findViewById(R.id.tvThanhTien);
            listHdctViewHolder.imgDeleteHdct=convertView.findViewById(R.id.imgDeleteHdct);
            convertView.setTag(listHdctViewHolder);
        }   else
            listHdctViewHolder = (ListHdctViewHolder) convertView.getTag();
        listHdctViewHolder.tvMaSachHdct.setText("Mã sách: "+arrHoaDonCt.get(position).getSachClass().getMaSach());
        listHdctViewHolder.tvSlHdct.setText("Số lượng: "+arrHoaDonCt.get(position).getSlMua());
        listHdctViewHolder.tvGiaSachHdct.setText("Giá bìa: "+arrHoaDonCt.get(position).getSachClass().getGiaBia()+" vnđ");
        listHdctViewHolder.tvThanhTien.setText("Thành tiền: "+arrHoaDonCt.get(position).getSlMua()*arrHoaDonCt.get(position).getSachClass().getGiaBia()+" vnđ");
        listHdctViewHolder.imgDeleteHdct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sqlite sqlite=new Sqlite(context);
                HoaDonCtDAO hoaDonCtDAO=new HoaDonCtDAO(sqlite);
                hoaDonCtDAO.deleteHoadonCt(Integer.toString(arrHoaDonCt.get(position).getMaHdct()));
                arrHoaDonCt.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
