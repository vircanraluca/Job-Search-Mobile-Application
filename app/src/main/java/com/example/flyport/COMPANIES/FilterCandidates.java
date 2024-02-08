package com.example.flyport.COMPANIES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.flyport.HELPERCLASSES.MainModel;
import com.example.flyport.JOBSEEKER.FirstScreenActivity;
import com.example.flyport.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FilterCandidates extends AppCompatActivity {

    ImageView backButton;
    private Spinner filterSpinnerSelectDomain, filterSpinnerIndustry, filterSpinnerSelectJobType, filterSpinnerExLevel,filterSpinnerRecEducation;
    private Button applyFilterBtn;
    private List<MainModel> matchingJobs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_filter_candidates);

        backButton=findViewById(R.id.backButton4);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RecruiterDashboard.class);
                startActivity(intent);
            }
        });

        filterSpinnerSelectDomain=findViewById(R.id.filterSpinnerSelectDomain);

        ArrayAdapter<CharSequence> adapterSelectDomain = ArrayAdapter.createFromResource(getApplicationContext(),R.array.domain, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        filterSpinnerSelectDomain.setAdapter(adapterSelectDomain);

        filterSpinnerIndustry=findViewById(R.id. filterSpinnerIndustry);
        ArrayAdapter<CharSequence> adapterIndustry = ArrayAdapter.createFromResource(getApplicationContext(),R.array.industry, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        filterSpinnerIndustry.setAdapter(adapterIndustry);


        filterSpinnerSelectJobType=findViewById(R.id.filterSpinnerSelectJobType);
        ArrayAdapter<CharSequence> adapterJobType = ArrayAdapter.createFromResource(getApplicationContext(),R.array.job_type, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        filterSpinnerSelectJobType.setAdapter(adapterJobType);

        filterSpinnerExLevel=findViewById(R.id.filterSpinnerExLevel);
        ArrayAdapter<CharSequence> adapterExLevel = ArrayAdapter.createFromResource(getApplicationContext(),R.array.experience_level, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        filterSpinnerExLevel.setAdapter(adapterExLevel);


        filterSpinnerRecEducation=findViewById(R.id.filterSpinnerRecEducation);
        ArrayAdapter<CharSequence> adapterRecEd = ArrayAdapter.createFromResource(getApplicationContext(),R.array.education_level, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item);
        filterSpinnerRecEducation.setAdapter(adapterRecEd);

        applyFilterBtn=findViewById(R.id.applyFilterBtn);

        applyFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDomain = filterSpinnerSelectDomain.getSelectedItem().toString();
                String selectedIndustry = filterSpinnerIndustry.getSelectedItem().toString();
                String selectedJobType = filterSpinnerSelectJobType.getSelectedItem().toString();
                String selectedExperienceLevel = filterSpinnerExLevel.getSelectedItem().toString();
                String selectedEducationLevel = filterSpinnerRecEducation.getSelectedItem().toString();


                DatabaseReference jobsRef = FirebaseDatabase.getInstance().getReference("jobs");
                Query filterQuery = jobsRef.orderByChild("domain").equalTo(selectedDomain);

                filterQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        matchingJobs.clear();

                        for (DataSnapshot jobSnapshot : dataSnapshot.getChildren()) {
                            String domain = jobSnapshot.child("domain").getValue(String.class);
                            String industry = jobSnapshot.child("industry").getValue(String.class);
                            String jobType = jobSnapshot.child("tipJob").getValue(String.class);
                            String experienceLevel = jobSnapshot.child("experienceLevel").getValue(String.class);
                            String educationLevel = jobSnapshot.child("requiredEducation").getValue(String.class);

                            // Verifică celelalte criterii de filtrare și adaugă joburile potrivite în lista matchingJobs
                            if (selectedIndustry.equals(industry) && selectedJobType.equals(jobType) &&
                                    selectedExperienceLevel.equals(experienceLevel) && selectedEducationLevel.equals(educationLevel)) {

                                MainModel job = new MainModel();
                                job.setDomain(domain);
                                job.setIndustry(industry);
                                job.setTipJob(jobType);
                                job.setExperienceLevel(experienceLevel);
                                job.setRequiredEducation(educationLevel);

                                matchingJobs.add(job);
                            }
                        }



                        Intent intent = new Intent(getApplicationContext(), FindTheRightCandidates.class);
                        intent.putExtra("matchingJobs", new ArrayList<>(matchingJobs)); // Convertim lista într-un ArrayList serializabil
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Gestionarea erorilor de bază de date
                    }
                });

            }
        });
    }
}