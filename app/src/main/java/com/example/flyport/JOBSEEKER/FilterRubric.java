package com.example.flyport.JOBSEEKER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.flyport.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FilterRubric extends AppCompatActivity {

    ImageView backBtnMyApp;
    private Spinner filterSpinnerSelectDomain, filterSpinnerIndustry, filterSpinnerSelectJobType, filterSpinnerExLevel,filterSpinnerRecEducation;
    Button saveInterestsBtn;
    EditText languageEt, countryAnswEt, cityAnswEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_filter_rubric);

        backBtnMyApp=findViewById(R.id.backBtnMyApp);
        backBtnMyApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FirstScreenActivity.class);
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

        languageEt= findViewById(R.id.languageEt);
        countryAnswEt= findViewById(R.id.countryAnswEt);
        cityAnswEt=findViewById(R.id.cityAnswEt);


        saveInterestsBtn=findViewById(R.id.saveInterestsBtn);

        saveInterestsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obțineți valorile selectate din spinners și edittext
                String selectedJobType = filterSpinnerSelectJobType.getSelectedItem().toString();
                String selectedIndustry = filterSpinnerIndustry.getSelectedItem().toString();
                String selectedDomain = filterSpinnerSelectDomain.getSelectedItem().toString();
                String selectedExperienceLevel = filterSpinnerExLevel.getSelectedItem().toString();
                String selectedEducationLevel = filterSpinnerRecEducation.getSelectedItem().toString();
                String selectedLanguage = languageEt.getText().toString();
                String selectedCountry = countryAnswEt.getText().toString();
                String selectedCity = cityAnswEt.getText().toString();

                // Obțineți o referință către baza de date Firebase și tabela "users"
                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    String userId = user.getUid();

                    Map<String, Object> userData = new HashMap<>();
                    userData.put("selectedJobType", selectedJobType);
                    userData.put("selectedIndustry", selectedIndustry);
                    userData.put("selectedDomain", selectedDomain);
                    userData.put("selectedExperienceLevel", selectedExperienceLevel);
                    userData.put("selectedEducationLevel", selectedEducationLevel);
                    userData.put("selectedLanguage", selectedLanguage);
                    userData.put("selectedCountry", selectedCountry);
                    userData.put("selectedCity", selectedCity);

                    // Actualizați doar câmpurile specifice utilizând metoda updateChildren
                    usersRef.child(userId).updateChildren(userData)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Actualizarea a fost realizată cu succes
                                        Toast.makeText(getApplicationContext(), "Interests saved successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        // A apărut o eroare la actualizare
                                        Toast.makeText(getApplicationContext(), "Failed to save interests", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Utilizatorul nu este autentificat. Gestionati acest caz corespunzător (exemplu: afișare unui mesaj de eroare)
                    Toast.makeText(getApplicationContext(), "User is not authenticated", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}