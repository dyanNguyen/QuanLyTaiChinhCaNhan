package com.example.quanlytaichinhcanhan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listview;
    ArrayList<ItemMenu>arrayList;
    MenuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        actionToolBar();
        actionMenu();
    }

    private void actionMenu() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemMenu("Tài khoản",R.drawable.ic_action_itemmoney));
        arrayList.add(new ItemMenu("Biểu đồ",R.drawable.ic_action_itemchart));
        arrayList.add(new ItemMenu("Cài đặt",R.drawable.ic_action_itemsetting));
        arrayList.add(new ItemMenu("Tiền tệ",R.drawable.ic_action_itemcurrent));
        arrayList.add(new ItemMenu("Nhắc nhở",R.drawable.ic_action_itemnote));
        arrayList.add(new ItemMenu("Liên hệ với chúng tôi",R.drawable.ic_action_itemcontact));
        adapter = new MenuAdapter(this, R.layout.menuitem, arrayList);
        listview.setAdapter(adapter);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            toolbar.setNavigationIcon(R.drawable.ic_action_menu);
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    drawerLayout.openDrawer(GravityCompat.START);
//                }
//            });
//        } else {
//            Log.e("MainActivity", "Toolbar is null");
//        }
    }

    public void anhXa(){
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.NavigationView);
        listview =(ListView) findViewById(R.id.lv);
    }

}