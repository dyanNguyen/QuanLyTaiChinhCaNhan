package com.example.quanlytaichinhcanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlyChart extends AppCompatActivity {
    public SQLiteDatabase db;
    BarChart bar;
    public static int i=1;
    String name;
    public int tienValue, tienIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart);
        name = getIntent().getStringExtra("key3");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TraTien(name);
        bar = findViewById(R.id.barchart);
        ArrayList<BarEntry> information = new ArrayList<>();
        information.add(new BarEntry(1, 0));
        BarDataSet dataset = new BarDataSet(information, "Báo cáo");
        information.add(new BarEntry(i, tienValue));i++;
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData barData = new BarData(dataset);
        bar.setFitBars(true);
        bar.setData(barData);
        bar.getDescription().setText("Báo cáo thu nhập");
        bar.animateY(10000);
    }
    public void TraTien(String username) {
        try {
            db = openOrCreateDatabase(Login.dtbase, MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT Tien FROM dbUser WHERE Username=?", new String[]{username});
            if (cursor.moveToFirst()) {
                tienIndex = cursor.getColumnIndex("Tien");
                tienValue = Integer.parseInt(cursor.getString( tienIndex));
            }
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "lỗi đây", Toast.LENGTH_SHORT).show();
        }
    }
}