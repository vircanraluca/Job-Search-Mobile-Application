package com.example.flyport.JOBSEEKER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.flyport.HELPERCLASSES.MainAdapter;
import com.example.flyport.HELPERCLASSES.MainModel;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FavoriteJobsActivity extends AppCompatActivity   {

    private RecyclerView recyclerView;
    private MainAdapter mainAdapter;
    private DatabaseReference favoriteJobsRef;
    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_favorite_jobs);


        backBtn=findViewById(R.id.backBtnMyApp);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FirstScreenActivity.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.favoriteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserId = firebaseAuth.getCurrentUser().getUid();
        favoriteJobsRef = FirebaseDatabase.getInstance().getReference().child("favoritejobs").child(currentUserId);

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(favoriteJobsRef, MainModel.class)
                        .build();

        Log.d("FavoriteJobsActivity", "Options count: " + options.getSnapshots().size());

        mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);


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