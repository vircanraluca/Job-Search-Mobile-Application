package com.example.flyport.JOBSEEKER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.flyport.HELPERCLASSES.MainAdapter;
import com.example.flyport.HELPERCLASSES.MainModel;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.DataSnapshot;

public class MyApplicationActivity extends AppCompatActivity {

    ImageView backbtn;
    RecyclerView applicationjobsRv;
    MainAdapter adapter;

    DatabaseReference databaseRef;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_application);

        backbtn = findViewById(R.id.backBtnMyApp);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FirstScreenActivity.class);
                startActivity(intent);
            }
        });

        applicationjobsRv = findViewById(R.id.applicationjobsRv);
        applicationjobsRv.setLayoutManager(new LinearLayoutManager(this));


        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String currentUserId = firebaseAuth.getCurrentUser().getUid();
        databaseRef= FirebaseDatabase.getInstance().getReference().child("job_application").child(currentUserId);


        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(databaseRef, MainModel.class)
                        .build();

        Log.d("FavoriteJobsActivity", "Options count: " + options.getSnapshots().size());

        adapter = new MainAdapter(options);  // Înlocuiți null cu instanța interfeței RecyclerViewInterface, dacă este necesară
        applicationjobsRv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
