package com.example.flyport.COMMON;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.flyport.JOBSEEKER.LogInActivity;
import com.example.flyport.COMPANIES.LoginRecruiterPage;
import com.example.flyport.R;

public class MainActivity extends AppCompatActivity {

      ConstraintLayout jobSeekerLayout, recruiterLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

       jobSeekerLayout=findViewById(R.id.jobSeekerLayout);

       jobSeekerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
       });


       recruiterLayout=findViewById(R.id.recruiterLayout);
       recruiterLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(), LoginRecruiterPage.class);
               startActivity(intent);
           }
       });






    }
}