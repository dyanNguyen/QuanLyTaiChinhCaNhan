package com.example.quanlytaichinhcanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartSalary extends AppCompatActivity {

    BarChart bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_salary);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bar=(BarChart)findViewById(R.id.barchart);


        ArrayList<BarEntry> information=new ArrayList<>();
        information.add(new BarEntry(2010,1000));
        information.add(new BarEntry(2011,1200));
        information.add(new BarEntry(2012,1400));
        information.add(new BarEntry(2013,1700));
        information.add(new BarEntry(2014,1300));
        information.add(new BarEntry(2015,1100));
        information.add(new BarEntry(2016,1000));
        information.add(new BarEntry(2017,1700));
        information.add(new BarEntry(2018,1900));
        information.add(new BarEntry(2019,2200));

        BarDataSet dataset=new BarDataSet(information,"Report");
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        dataset.setValueTextColor(Color.BLACK);
        dataset.setValueTextSize(20f);

        BarData barData=new BarData(dataset);

        bar.setFitBars(true);
        bar.setData(barData);
        bar.getDescription().setText("Bar Report Demo");
        bar.animateY(2000);
    }
}