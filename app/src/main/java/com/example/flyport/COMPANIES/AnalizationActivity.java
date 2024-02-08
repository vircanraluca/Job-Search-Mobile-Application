package com.example.flyport.COMPANIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
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

public class AnalizationActivity extends AppCompatActivity {


    ImageView backButton;
    CardView usersByCountryCardView,usersByExperienceCardView,preferredJobTypeCardView, usersByEducationalLevelCardView,preferredIndustriesForUsersCardView, preferredDomainsForUsersCardViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_analization);




        backButton=findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RecruiterDashboard.class);
                startActivity(intent);
            }
        });

        usersByCountryCardView=findViewById(R.id.usersByCountryCardView);
        usersByCountryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UsersByCountry_Activity.class);
                startActivity(intent);
            }
        });

        usersByExperienceCardView=findViewById(R.id.usersByExperienceCardView);
        usersByExperienceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UsersByExperienceLevel.class);
                startActivity(intent);
            }
        });

        preferredJobTypeCardView=findViewById(R.id.preferredJobTypeCardView);
        preferredJobTypeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), PreferredJobType.class);
                startActivity(intent);
            }
        });

        usersByEducationalLevelCardView=findViewById(R.id.usersByEducationalLevelCardView);
        usersByEducationalLevelCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UsersByEducationalLevel.class);
                startActivity(intent);
            }
        });

        preferredIndustriesForUsersCardView=findViewById(R.id.preferredIndustriesForUsersCardView);
        preferredIndustriesForUsersCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PreferredIndustries.class);
                startActivity(intent);
            }
        });

        preferredDomainsForUsersCardViews=findViewById(R.id.preferredDomainsForUsersCardViews);
        preferredDomainsForUsersCardViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PreferredDomains.class);
                startActivity(intent);
            }
        });


    }
}