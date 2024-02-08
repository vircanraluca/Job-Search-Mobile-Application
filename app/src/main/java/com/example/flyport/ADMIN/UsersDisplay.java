package com.example.flyport.ADMIN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.flyport.HELPERCLASSES.AdminAdapter;
import com.example.flyport.HELPERCLASSES.CandidateAdapter;
import com.example.flyport.HELPERCLASSES.CandidateModel;
import com.example.flyport.HELPERCLASSES.MainModel;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class UsersDisplay extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CandidateAdapter candidateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_users_display);

        recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CandidateModel> options =
                new FirebaseRecyclerOptions.Builder<CandidateModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users") , CandidateModel.class)
                        .build();



        candidateAdapter=new CandidateAdapter(options);
        recyclerView.setAdapter(candidateAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        candidateAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        candidateAdapter.stopListening();
    }
}