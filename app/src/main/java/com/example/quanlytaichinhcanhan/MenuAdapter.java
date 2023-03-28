package com.example.quanlytaichinhcanhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemMenu>list;

    public MenuAdapter(Context context, int layout, List<ItemMenu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(int position, View Convertview, ViewGroup parent) {
        ViewHolder viewHolder;
        if(Convertview == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Convertview = inflater.inflate(layout,null);

            viewHolder = new ViewHolder();

            viewHolder.tv = (TextView) Convertview.findViewById(R.id.tvItem);
            viewHolder.img = (ImageView) Convertview.findViewById(R.id.imgIcon);

            Convertview.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) Convertview.getTag();
        }
        viewHolder.tv.setText(list.get(position).ItemName);
        viewHolder.img.setImageResource(list.get(position).icon);
        return Convertview;
    }
}
