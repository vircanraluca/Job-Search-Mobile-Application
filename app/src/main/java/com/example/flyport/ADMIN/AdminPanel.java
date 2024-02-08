package com.example.flyport.ADMIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.flyport.R;

public class AdminPanel extends AppCompatActivity {

    LinearLayout manageUsers, grantedAccesForUsers ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_panel);

        manageUsers=findViewById(R.id.manageUsers);
        manageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UsersDisplay.class);
                startActivity(intent);
            }
        });



        grantedAccesForUsers=findViewById(R.id.grantedAccesForUsers);
        grantedAccesForUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), GrantedAccesForUsers.class);
                startActivity(intent);
            }
        });



    }
}