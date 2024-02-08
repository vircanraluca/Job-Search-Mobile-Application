package com.example.flyport.COMPANIES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.flyport.R;
import com.google.android.material.navigation.NavigationView;

public class RecruiterDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    private MenuItem prevMenuItem;
    private CardView jobpostCardView, candidatesCardView, findTheRightCanditatesCardView,analizationCardView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recruiter_dashboard);



        drawerLayout=findViewById(R.id.drawer_layout_recruiter);
        navigationView=findViewById(R.id.nav_view_recruiter);
        toolbar=findViewById(R.id.toolbarRecruiter);


        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        jobpostCardView=findViewById(R.id.usersByExperienceCardView);
        jobpostCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AllTheJobPostActivity.class);
                startActivity(intent);
            }
        });

        candidatesCardView=findViewById(R.id.usersByCountryCardView);
        candidatesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CandidatesActivity.class);
                startActivity(intent);
            }
        });

        findTheRightCanditatesCardView=findViewById(R.id.preferredJobTypeCardView);
        findTheRightCanditatesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FindTheRightCandidates.class);
                startActivity(intent);
            }
        });

        analizationCardView=findViewById(R.id.preferredDomainsForUsersCardViews);
        analizationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AnalizationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Uncheck previously selected item
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        }

        switch (item.getItemId()) {

            case R.id.nav_jobposts:
                Intent intentJobPosts=new Intent(getApplicationContext(),JobPostsActivity.class);
                startActivity(intentJobPosts);
                break;

            case R.id.nav_recruiter_log_out:
                Intent intentLogOut=new Intent(getApplicationContext(), LoginRecruiterPage.class);
                startActivity(intentLogOut);
                break;
        }

        // Highlight the selected item in the navigation view
        item.setChecked(true);
        prevMenuItem = item;

        // Close the navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}