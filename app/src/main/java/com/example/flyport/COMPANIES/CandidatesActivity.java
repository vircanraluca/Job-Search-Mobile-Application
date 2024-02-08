package com.example.flyport.COMPANIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.flyport.HELPERCLASSES.FJobAdapter;
import com.example.flyport.HELPERCLASSES.MainModel;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CandidatesActivity extends AppCompatActivity {

    private RecyclerView allTheCandidatesRV;
    private FJobAdapter fakeJobAdapter;
    private ImageView backBtnMyApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_candidates);


        allTheCandidatesRV=findViewById(R.id.allTheCandidatesRV);
        allTheCandidatesRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String userID = currentUser.getUid();



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("jobs");
        Query query = databaseReference.orderByChild("userID").equalTo(userID);

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(query, MainModel.class)
                        .build();


        fakeJobAdapter=new FJobAdapter(options);
        allTheCandidatesRV.setAdapter(fakeJobAdapter);

        backBtnMyApp=findViewById(R.id.backBtnMyApp);
        backBtnMyApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RecruiterDashboard.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        fakeJobAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fakeJobAdapter.stopListening();
    }
}