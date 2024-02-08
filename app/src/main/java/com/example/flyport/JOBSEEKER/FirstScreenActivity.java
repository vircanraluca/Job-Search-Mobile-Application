package com.example.flyport.JOBSEEKER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.flyport.HELPERCLASSES.MainAdapter;
import com.example.flyport.HELPERCLASSES.MainModel;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirstScreenActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private MenuItem prevMenuItem;
    private Button searchJobsButton;
    private EditText searchEt;
    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private SearchView searchView;
    private ImageView filter_button;
    boolean isFavorite = false;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference jobRef = database.getReference("jobs");



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
        setContentView(R.layout.activity_first_screen);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.filterButton);


        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("jobs") , MainModel.class)
                        .build();



        mainAdapter=new MainAdapter(options);

        recyclerView.setAdapter(mainAdapter);


        searchView=findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mainAdapter.setSearchName(newText);
                mainAdapter.applyFilters(); // Adaugă acest rând pentru a aplica filtrarea
                return true;


            }
        });


        filter_button=findViewById(R.id.filter_button);
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ApplyFilterActivity.class);
                startActivity(intent);
            }
        });

        List<MainModel> matchingJobs = (List<MainModel>) getIntent().getSerializableExtra("matchingJobs");
        if (matchingJobs != null) {
            mainAdapter.updateJobList(matchingJobs);
        }


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

            case R.id.nav_application:

                Intent intentApp=new Intent(getApplicationContext(),MyApplicationActivity.class);
                startActivity(intentApp);
                break;
            case R.id.nav_cv:
                Intent intentCV=new Intent(getApplicationContext(),CVActivity.class);
                startActivity(intentCV);
                break;

            case R.id.nav_favorites:
                Intent intentFav=new Intent(getApplicationContext(), FavoriteJobsActivity.class);
                startActivity(intentFav);
                break;

            case R.id.nav_rubric:
                Intent intentRubric=new Intent(getApplicationContext(), FilterRubric.class);
                startActivity(intentRubric);
                break;

            case R.id.nav_log_out:
                Intent intentLogOut=new Intent(getApplicationContext(), LogInActivity.class);
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

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }




}