package com.example.flyport.COMPANIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.flyport.HELPERCLASSES.CandidateModel;
import com.example.flyport.HELPERCLASSES.FCandidatesAdapter;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewCandidatesPerJob extends AppCompatActivity {

    private RecyclerView allTheCandidatesRV;
    private FCandidatesAdapter fakeCandidatesAdapter;
    private ImageView backBtnMyApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_candidates_per_job);

        allTheCandidatesRV=findViewById(R.id.allTheCandidatesRV);
        allTheCandidatesRV.setLayoutManager(new LinearLayoutManager(this));



//        FirebaseRecyclerOptions<CandidateModel> options =
//                new FirebaseRecyclerOptions.Builder<CandidateModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").orderByChild("hasUploaded").equalTo("true"), CandidateModel.class)
//                        .build();

        FirebaseRecyclerOptions<CandidateModel> options =
                new FirebaseRecyclerOptions.Builder<CandidateModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").orderByChild("hasUploaded").equalTo(true), CandidateModel.class)
                        .build();




        backBtnMyApp=findViewById(R.id.backBtnMyApp);
        backBtnMyApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CandidatesActivity.class);
                startActivity(intent);
            }
        });

        fakeCandidatesAdapter=new FCandidatesAdapter(options);
        allTheCandidatesRV.setAdapter(fakeCandidatesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fakeCandidatesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fakeCandidatesAdapter.stopListening();
    }
}