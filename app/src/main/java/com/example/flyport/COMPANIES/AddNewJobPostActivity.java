package com.example.flyport.COMPANIES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flyport.HELPERCLASSES.MainAdapter;
import com.example.flyport.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AddNewJobPostActivity extends AppCompatActivity {
    EditText jobTitleEt, countryEt, cityEt, jobTypeEt, descriptionEt, minSalaryEt, maxSalaryEt, benefitsEt, experienceEt, requiredEducationEt, languageRequirementsEt, industryEt;
    Button createJobPost;
    private MainAdapter mainAdapter;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_new_job_post);

        jobTitleEt=(EditText) findViewById(R.id.jobTitleEt);
        countryEt=(EditText) findViewById(R.id.countryEt);
        cityEt=(EditText) findViewById(R.id.cityEt);
        jobTypeEt=(EditText) findViewById(R.id.jobTypeEt);
        descriptionEt=(EditText) findViewById(R.id.descriptionEt);

        minSalaryEt=(EditText)findViewById(R.id.minSalaryEt);
        maxSalaryEt=(EditText)findViewById(R.id.maxSalaryEt);
        benefitsEt=(EditText)findViewById(R.id.benefitsEt);
        experienceEt=(EditText)findViewById(R.id.experienceEt);
        requiredEducationEt=(EditText)findViewById(R.id.requiredEducationEt);
        languageRequirementsEt=(EditText)findViewById(R.id.languageRequirementsEt);
        industryEt=(EditText)findViewById(R.id.industryEt);

        createJobPost=(Button)findViewById(R.id.createJobPost);



        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        userID = currentUser.getUid();


        createJobPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }

    private void insertData() {
        String jobTitle = jobTitleEt.getText().toString().trim();
        String country = countryEt.getText().toString().trim();
        String city = cityEt.getText().toString().trim();
        String jobType = jobTypeEt.getText().toString().trim();
        String description = descriptionEt.getText().toString().trim();
        String minSalary = minSalaryEt.getText().toString().trim();
        String maxSalary = maxSalaryEt.getText().toString().trim();
        String benefits = benefitsEt.getText().toString().trim();
        String experience = experienceEt.getText().toString().trim();
        String requiredEducation = requiredEducationEt.getText().toString().trim();
        String languageRequirements = languageRequirementsEt.getText().toString().trim();
        String industry = industryEt.getText().toString().trim();

        // Verifică dacă oricare dintre câmpuri este gol
        if (jobTitle.isEmpty() || country.isEmpty() || city.isEmpty() || jobType.isEmpty() || description.isEmpty() ||
                minSalary.isEmpty() || maxSalary.isEmpty() || benefits.isEmpty() || experience.isEmpty() ||
                requiredEducation.isEmpty() || languageRequirements.isEmpty() || industry.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return; // Întrerupeți execuția metodei în cazul în care există câmpuri goale
        }





        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("denumireJob", jobTitleEt.getText().toString());
        map.put("tara", countryEt.getText().toString());
        map.put("oras", cityEt.getText().toString());
        map.put("tipJob", jobTypeEt.getText().toString());
        map.put("descriere", descriptionEt.getText().toString());
        map.put("minimumSalary", minSalaryEt.getText().toString());
        map.put("maximumSalary", maxSalaryEt.getText().toString());
        map.put("benefits", benefitsEt.getText().toString());
        map.put("experienceLevel", experienceEt.getText().toString());
        map.put("requiredEducation", requiredEducationEt.getText().toString());
        map.put("languageRequirements", languageRequirementsEt.getText().toString());
        map.put("industry", industryEt.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("jobs").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();

                        // Deschide activitatea AllTheJobPostActivity după ce jobul este adăugat cu succes
                        Intent intent = new Intent(AddNewJobPostActivity.this, AllTheJobPostActivity.class);
                        startActivity(intent);
                        finish(); // Opriți activitatea curentă pentru a reveni la AllTheJobPostActivity
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });


    }



}