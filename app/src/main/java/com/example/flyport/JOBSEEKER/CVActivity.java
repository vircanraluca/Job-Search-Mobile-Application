package com.example.flyport.JOBSEEKER;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.flyport.R;


public class CVActivity extends AppCompatActivity {


    Button  uploadCVBtn;
    ImageView backBtnMyApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cvactivity);

        backBtnMyApp=findViewById(R.id.backBtnMyApp);
        backBtnMyApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FirstScreenActivity.class);
                startActivity(intent);
            }
        });



        uploadCVBtn = findViewById(R.id.uploadCVBtn);
        uploadCVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), UploadPDF.class);
                startActivity(intent);
            }
        });
    }



}
