package com.example.quanlytaichinhcanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChartSalary extends AppCompatActivity {

    BarChart bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_salary);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bar = findViewById(R.id.barchart);


        ArrayList<BarEntry> information = new ArrayList<>();
        information.add(new BarEntry(2010, 1000));
        information.add(new BarEntry(2011, 1200));
        information.add(new BarEntry(2012, 1400));
        information.add(new BarEntry(2013, 1700));

        BarDataSet dataset = new BarDataSet(information, "Báo cáo");

        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        /*List<Integer> colors = dataset.getColors();
        String[] labels = new String[colors.size()];

        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i) == ColorTemplate.MATERIAL_COLORS[0]) {
                labels[i] = "Tiền lương";
            } else if (colors.get(i) == ColorTemplate.MATERIAL_COLORS[1]) {
                labels[i] = "Tiền quà cáp";
            } else if (colors.get(i) == ColorTemplate.MATERIAL_COLORS[2]) {
                labels[i] = "Mẹ cho";
            } else if (colors.get(i) == ColorTemplate.MATERIAL_COLORS[3]) {
                labels[i] = "Ba cho";
            }
        }
            int[] colorArray = new int[colors.size()];
            for (int a = 0; a < colors.size(); a++) {
                colorArray[a] = colors.get(a);
            }
            dataset.setStackLabels(labels);
            BarData barData = new BarData(dataset);
            bar.setData(barData);

            Legend legend = bar.getLegend();
            legend.setExtra(colorArray, labels);
            bar.getLegend().setEnabled(true);
            bar.getLegend().setExtra(colorArray, labels);

        *//*dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        dataset.setValueTextColor(Color.WHITE);
        dataset.setValueTextSize(20f);*/
            BarData barData = new BarData(dataset);
            bar.setFitBars(true);
            bar.setData(barData);
            bar.getDescription().setText("Báo cáo thu nhập");
            bar.animateY(2000);

    }
}