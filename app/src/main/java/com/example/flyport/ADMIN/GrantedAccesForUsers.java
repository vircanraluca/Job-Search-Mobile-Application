package com.example.flyport.ADMIN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


import com.example.flyport.HELPERCLASSES.CandidateModel;
import com.example.flyport.HELPERCLASSES.CompanyModel;
import com.example.flyport.HELPERCLASSES.NewUserAdapter;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GrantedAccesForUsers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewUserAdapter newUserAdapter;
    private ImageView backBtnMyApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_granted_acces_for_users);

        recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CompanyModel> options =
                new FirebaseRecyclerOptions.Builder<CompanyModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").orderByChild("tipUtilizator").equalTo("companie"), CompanyModel.class)
                        .build();


        newUserAdapter=new NewUserAdapter(options);
        recyclerView.setAdapter(newUserAdapter);


        backBtnMyApp=findViewById(R.id.backBtnMyApp);
        backBtnMyApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AdminPanel.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        newUserAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        newUserAdapter.stopListening();
    }


}