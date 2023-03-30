package com.example.quanlytaichinhcanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
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

public class ChartSalary extends AppCompatActivity {

    PieChart salarypie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_salary);
        salarypie=(PieChart)findViewById(R.id.Salarychart);

        ArrayList<PieEntry> records=new ArrayList<>();
        records.add(new PieEntry(32,"Quarter1"));
        records.add(new PieEntry(14,"Quarter2"));
        records.add(new PieEntry(34,"Quarter3"));
        records.add(new PieEntry(38,"Quarter4"));

        PieDataSet dataSet=new PieDataSet(records,"Pie Chart Report");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(22f);

        PieData pieData=new PieData(dataSet);

        salarypie.setData(pieData);
        salarypie.getDescription().setEnabled(true);
        salarypie.setCenterText("Quarterly Revenue");
        salarypie.animate();
    }
}