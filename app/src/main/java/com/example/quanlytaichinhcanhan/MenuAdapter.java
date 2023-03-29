package com.example.quanlytaichinhcanhan;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.app.Activity;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

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

//        if (list.get(position).isSelected) {
//            Convertview.setBackgroundColor(context.getResources().getColor(R.color.selected_item_color));
//        } else {
//            Convertview.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
//        }

        /*Convertview.setOnClickListener(new View.OnClickListener() {
            boolean isPressed = false;
            @Override
            public void onClick(View v) {
                isPressed = !isPressed;
                if (isPressed) {
                    // Change background color when pressed
                    v.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_item_color));
                } else {
                    // Reset background color when released
                    v.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
                }
                // Perform action on click
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(context, ChartSalary.class);
                        context.startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(context, ChartUsing.class);
                        context.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(context, Account.class);
                        context.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(context, Setting.class);
                        context.startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(context, Moneys.class);
                        context.startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(context, NoteActivity.class);
                        context.startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(context, ContactActivity.class);
                        context.startActivity(intent6);
                        break;
                    default:
                        break;
                }
            }
        });*/

        return Convertview;
    }
}
