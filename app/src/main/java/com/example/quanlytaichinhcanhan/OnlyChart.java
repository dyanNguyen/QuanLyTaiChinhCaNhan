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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlyChart extends AppCompatActivity {
    public SQLiteDatabase db;
    PieChart pie;
    public static int i=1;
    String name;
    public int tienValue, tienIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_using);
        name = getIntent().getStringExtra("key3");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TraTien(name);
        pie = findViewById(R.id.Usingchart);
        ArrayList<PieEntry> records=new ArrayList<>();
        records.add(new PieEntry(1, 0));
        PieDataSet dataSet=new PieDataSet(records,"Báo cáo");
        records.add(new PieEntry(i, tienValue));i++;
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(22f);
        PieData pieData=new PieData(dataSet);
        pie.getDescription().setEnabled(true);
        pie.setData(pieData);
        pie.getDescription().setText("Báo cáo thu nhập");
        pie.setCenterText("Quarterly Revenue");
        pie.animateY(10000);
        //ne
//        records.add(new PieEntry(32,"Quarter1"));
//        records.add(new PieEntry(14,"Quarter2"));
//        records.add(new PieEntry(34,"Quarter3"));
//        records.add(new PieEntry(38,"Quarter4"));

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