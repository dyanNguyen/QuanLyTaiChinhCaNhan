package com.example.quanlytaichinhcanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChartUsing extends AppCompatActivity {

    //PieChart Usingpie;
    private static final int MY_CODE = 10;
    public int tienValue, tienIndex = -1;
    public Button Click, chart;
    public TextView tien;
    public SQLiteDatabase db;
    public String Tienne, Ctien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Usingpie=(PieChart)findViewById(R.id.Usingchart);


//        ArrayList<PieEntry> records=new ArrayList<>();
//        records.add(new PieEntry(32,"Quarter1"));
//        records.add(new PieEntry(14,"Quarter2"));
//        records.add(new PieEntry(34,"Quarter3"));
//        records.add(new PieEntry(38,"Quarter4"));
//
//        PieDataSet dataSet=new PieDataSet(records,"Report");
//        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        dataSet.setValueTextColor(Color.BLACK);
//        dataSet.setValueTextSize(22f);
//
//        PieData pieData=new PieData(dataSet);
//
//        Usingpie.setData(pieData);
//        Usingpie.getDescription().setEnabled(true);
//        Usingpie.setCenterText("Quarterly Revenue");
//        Usingpie.animate();
        tien=findViewById(R.id.tien);
        Click=findViewById(R.id.btnCT);
        chart = findViewById(R.id.btnchart);
        Ctien = getIntent().getStringExtra("key2");
        TraTien(Ctien);
        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChartUsing.this, NaptienCucmanh.class);
                startActivityForResult(intent,MY_CODE);
            }
        });
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ChartUsing.this, OnlyChart.class);
                intent2.putExtra("key3",Ctien);
                startActivity(intent2);
            }
        });
    }

    public void TraTien(String username) {
        try {
            db = openOrCreateDatabase(Login.dtbase, MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT Tien FROM dbUser WHERE Username=?", new String[]{username});
            if (cursor.moveToFirst()) {
                tienIndex = cursor.getColumnIndex("Tien");
                tienValue = Integer.parseInt(cursor.getString( tienIndex));
                Tienne = getString(R.string.Tienne, tienValue);
                tien.setText(Tienne);
            }
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "lỗi đây", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_CODE && resultCode == Activity.RESULT_OK) {
            int value = data.getIntExtra("key1", 0);
            value=tienValue-value;
            Tienne = getString(R.string.Tienne, value);
            truTien(Ctien,value);
            tien.setText(Tienne);
        }
    }

    public void truTien(String username, int newTienValue) {
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