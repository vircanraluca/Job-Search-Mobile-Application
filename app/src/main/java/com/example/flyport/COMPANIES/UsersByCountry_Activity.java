package com.example.flyport.COMPANIES;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.flyport.R;
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

public class UsersByCountry_Activity extends AppCompatActivity {

    BarChart bar_chart;
    PieChart pie_chart;

    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_users_by_country);

        bar_chart=findViewById(R.id.bar_chart);
        pie_chart=findViewById(R.id.pie_chart);


        ArrayList<BarEntry> barEntries=new ArrayList<>();
        ArrayList<PieEntry> pieEntries=new ArrayList<>();

//        for(int i=1;i<10;i++){
//
//            float value=(float)(i*10.0);
//
//            BarEntry barEntry=new BarEntry(i, value);
//
//            PieEntry pieEntry=new PieEntry(i, value);
//
//            barEntries.add(barEntry);
//            pieEntries.add(pieEntry);
//        }

        barEntries.add(new BarEntry(0, 4)); // România
        barEntries.add(new BarEntry(1, 4)); // Germania
        barEntries.add(new BarEntry(2, 2)); // Spania
        barEntries.add(new BarEntry(3, 2)); // Portugalia
        barEntries.add(new BarEntry(4, 1)); // Regatul Unit

        pieEntries.add(new PieEntry(4, "Romania"));
        pieEntries.add(new PieEntry(4, "Germany"));
        pieEntries.add(new PieEntry(2, "Spain"));
        pieEntries.add(new PieEntry(2, "Portugal"));
        pieEntries.add(new PieEntry(1, "United Kingdom"));


        BarDataSet barDataSet=new BarDataSet(barEntries, "Users");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barDataSet.setDrawValues(false);

        bar_chart.setData(new BarData(barDataSet));

        bar_chart.animateY(5000);

        bar_chart.getDescription().setText("Users Chart");
        bar_chart.getDescription().setTextColor(Color.BLUE);


        PieDataSet pieDataSet=new PieDataSet(pieEntries, "Users");

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pie_chart.setData(new PieData(pieDataSet));

        pie_chart.animateXY(5000, 5000);


        pie_chart.getDescription().setEnabled(false);


        backButton=findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AnalizationActivity.class);
                startActivity(intent);
            }
        });


    }
}