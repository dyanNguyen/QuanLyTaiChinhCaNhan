package com.example.quanlytaichinhcanhan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.savedstate.SavedStateRegistry;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    Toolbar toolbar;
    public SQLiteDatabase db;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listview;
    int tienValue, value;
    ArrayList<ItemMenu>arrayList;
    MenuAdapter adapter;
    String message;
    String Tienne;
    TextView LuuUserName, LuuTien;
    String valueUserName;
    public Login login = new Login();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        anhXa();
        actionToolBar();
        actionMenu();
        actionadd();
        LuuUserName = findViewById(R.id.UserNameTextView);
        LuuTien = findViewById(R.id.MoneyTextView);
        LuuUserName.setText(message);
        valueUserName=LuuUserName.getText().toString();
        IsProletariat();
    }

    private void actionadd() {
        FloatingActionButton addButton = findViewById(R.id.add_money_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, NaptienCucmanh.class);
                        startActivityForResult(intent,MY_REQUEST_CODE);
                    }
        });
    }

private void actionMenu() {
    arrayList = new ArrayList<>();
    arrayList.add(new ItemMenu("Biểu đồ thu nhập", R.drawable.ic_action_itemchart));
    arrayList.add(new ItemMenu("Biểu đồ tiêu dùng", R.drawable.ic_action_itemchart2));
    arrayList.add(new ItemMenu("Tài khoản", R.drawable.ic_action_itemmoney));
    arrayList.add(new ItemMenu("Cài đặt", R.drawable.ic_action_itemsetting));
    arrayList.add(new ItemMenu("Tiền tệ", R.drawable.ic_action_itemcurrent));
    arrayList.add(new ItemMenu("Nhắc nhở", R.drawable.ic_action_itemnote));
    arrayList.add(new ItemMenu("Liên hệ với chúng tôi", R.drawable.ic_action_itemcontact));
    adapter = new MenuAdapter(this, R.layout.menuitem, arrayList);
    listview.setAdapter(adapter);
    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch(position) {
                case 0:
                    Intent intent1 = new Intent(MainActivity.this, ChartSalary.class);
                    startActivity(intent1);
                    break;
                case 1:
                    Intent intent2 = new Intent(MainActivity.this, ChartUsing.class);
                    intent2.putExtra("key2",message);
                    startActivity(intent2);
                    break;
                case 2:
                    Intent intent3 = new Intent(MainActivity.this, Account.class);
                    startActivity(intent3);
                    break;
                case 3:
                    Intent intent4 = new Intent(MainActivity.this, Setting.class);
                    startActivity(intent4);
                    break;

                case 4:
                    Intent intent5 = new Intent(MainActivity.this, TienTe.class);
                    intent5.putExtra("key2",message);
                    startActivity(intent5);
                    break;

                case 5:
                    Intent intent6 = new Intent(MainActivity.this, NoteActivity.class);
                    startActivity(intent6);
                    break;
                case 6:
                    Intent intent7 = new Intent(MainActivity.this, ContactActivity.class);
                    startActivity(intent7);
                    break;
            }
        }
    });
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
    }

    public void anhXa(){
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.NavigationView);
        listview =(ListView) findViewById(R.id.lv);

    };


    public void IsProletariat() {
        db = openOrCreateDatabase(Login.dtbase, MODE_PRIVATE, null);
        try (Cursor cursor = db.rawQuery("SELECT Tien FROM dbUser WHERE Username=?", new String[]{valueUserName})) {
            if (cursor.moveToFirst()) {
                int tienIndex = cursor.getColumnIndex("Tien");
                if (tienIndex != -1) {
                    tienValue = Integer.parseInt(cursor.getString(tienIndex));
                    Tienne = getString(R.string.Tienne, tienValue);
                    LuuTien.setText(Tienne);
                    if (tienValue == 0) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("Nạp tiền");
                        alertDialog.setMessage("Bạn muốn nạp tiền ?");
                        alertDialog.setCancelable(false);
                        alertDialog.setPositiveButton("Nạp tiền", (dialogInterface, i) -> {
                            Intent intent = new Intent(MainActivity.this, NaptienCucmanh.class);
                            startActivityForResult(intent,MY_REQUEST_CODE);
                        });
                        alertDialog.setNegativeButton("Không", (dialogInterface, i) -> dialogInterface.dismiss());
                        AlertDialog alertDialogBuilder = alertDialog.create();
                        alertDialogBuilder.show();
                    }
                    else {
                    Tienne = getString(R.string.Tienne, tienValue);
                    LuuTien.setText(Tienne);
                    }
                }
                else {
                tienValue = Integer.parseInt(cursor.getString(tienIndex));
                Tienne = getString(R.string.Tienne, tienValue);
                LuuTien.setText(Tienne);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi 00: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            value = data.getIntExtra("key1", 0);
            value+=tienValue;
            insertMoney(message,value);
            Tienne = getString(R.string.Tienne, value);
            LuuTien.setText(Tienne);
        }
    }
    public void insertMoney(String username, int newTienValue) {
        try {
            db = openOrCreateDatabase(Login.dtbase, MODE_PRIVATE, null);
            String sql = "UPDATE dbUser SET Tien = ? WHERE Username = ?";
            db.execSQL(sql, new String[]{String.valueOf(newTienValue), username});
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "lỗi đây", Toast.LENGTH_SHORT).show();
        }
    }
}