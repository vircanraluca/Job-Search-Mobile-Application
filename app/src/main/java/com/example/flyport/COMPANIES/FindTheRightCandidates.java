package com.example.flyport.COMPANIES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.flyport.HELPERCLASSES.CandidateModel;
import com.example.flyport.HELPERCLASSES.FindTheRightCandidatesAdapter;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FindTheRightCandidates extends AppCompatActivity {

    private RecyclerView allTheCandidatesRV;
    private FindTheRightCandidatesAdapter findTheRightCandidatesAdapter;
    private ImageView filter_button;
    private List<CandidateModel> filteredList=new ArrayList<>();

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_find_the_right_candidates);

        allTheCandidatesRV=findViewById(R.id.allTheCandidatesRV);
        allTheCandidatesRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CandidateModel> options =
                new FirebaseRecyclerOptions.Builder<CandidateModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").orderByChild("tipUtilizator").equalTo("solicitant") , CandidateModel.class)
                        .build();





        findTheRightCandidatesAdapter=new FindTheRightCandidatesAdapter(options);
        allTheCandidatesRV.setAdapter(findTheRightCandidatesAdapter);

        filter_button=findViewById(R.id.filter_button);
        filter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FilterCandidates.class);
                startActivity(intent);
            }
        });



        searchView=findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                findTheRightCandidatesAdapter.setSearchName(newText);
                findTheRightCandidatesAdapter.applyFilters(); // Adaugă acest rând pentru a aplica filtrarea
                return true;


            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        findTheRightCandidatesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        findTheRightCandidatesAdapter.stopListening();
    }
}