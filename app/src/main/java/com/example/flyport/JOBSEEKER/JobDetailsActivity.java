package com.example.flyport.JOBSEEKER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flyport.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobDetailsActivity extends AppCompatActivity {

    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        backBtn=findViewById(R.id.backBtnMyApp);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FirstScreenActivity.class);
                startActivity(intent);
            }
        });

        // Obține ID-ul jobului din intent
        String jobId = getIntent().getStringExtra("jobId");

// Obține o referință către baza de date Firebase și la nodul "jobs"
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("jobs");

// Caută jobul cu ID-ul corespunzător în baza de date Firebase
        databaseRef.child(jobId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Obține informațiile despre job din snapshot-ul datelor
                    String denumireJob = dataSnapshot.child("denumireJob").getValue(String.class);
                    String numeCompanie = dataSnapshot.child("numeCompanie").getValue(String.class);
                    String oras = dataSnapshot.child("oras").getValue(String.class);
                    String descriere = dataSnapshot.child("descriere").getValue(String.class);
                    String tipJob = dataSnapshot.child("tipJob").getValue(String.class);

                    // Afișează informațiile în TextView-urile corespunzătoare
                    TextView denumireJobTxt = findViewById(R.id.denumireJobTxt);
                    TextView numeCompanieTxt = findViewById(R.id.numeCompanieTxt);
                    TextView orasTxt = findViewById(R.id.orasTxt);
                    TextView descriereTxt = findViewById(R.id.descriereTxt);
                    TextView tipJobTxt = findViewById(R.id.tipJobTxt);

                    denumireJobTxt.setText(denumireJob);
                    numeCompanieTxt.setText(numeCompanie);
                    orasTxt.setText(oras);
                    descriereTxt.setText(descriere);
                    tipJobTxt.setText(tipJob);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gestionează cazul în care citirea din baza de date a fost anulată sau a eșuat
            }
        });



    }
}