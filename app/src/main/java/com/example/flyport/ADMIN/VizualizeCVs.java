package com.example.flyport.ADMIN;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flyport.HELPERCLASSES.CandidateModel;
import com.example.flyport.HELPERCLASSES.FCandidatesAdapter;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class VizualizeCVs extends AppCompatActivity {

    private RecyclerView vizualizeCVsRv;
//    private CVAdapter cvAdapter;
//    private List<CV> cvList;
    private FCandidatesAdapter fakeCandidatesAdapter;
    private ImageView filter_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vizualize_cvs);

        vizualizeCVsRv = findViewById(R.id.vizualizeCVsRv);
        vizualizeCVsRv.setLayoutManager(new LinearLayoutManager(this));

//        cvList = new ArrayList<>();
//        cvAdapter = new CVAdapter(cvList);
//        vizualizeCVsRv.setAdapter(cvAdapter);
//
//        // Obține o referință la baza de date Firebase
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//
//        // Obține o referință la nodul "uploadedCV" în baza de date Firebase
//        DatabaseReference cvReference = firebaseDatabase.getReference("uploadedCV");
//
//        // Adaugă un listener pentru a citi datele din baza de date
//        cvReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // Curăță lista de CV-uri existente
//                cvList.clear();
//
//                // Parcurge toate CV-urile din baza de date
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    // Obține detaliile CV-ului
//                    String name = snapshot.child("name").getValue(String.class);
//                    String url = snapshot.child("url").getValue(String.class);
//
//                    // Creează un obiect CV și adaugă-l în lista
//                    CV cv = new CV(name, url);
//                    cvList.add(cv);
//                }
//
//                // Notifică adapterul că s-au modificat datele și actualizează RecyclerView-ul
//                cvAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Tratează cazul în care apare o eroare la citirea datelor
//            }
//        });

        FirebaseRecyclerOptions<CandidateModel> options =
                new FirebaseRecyclerOptions.Builder<CandidateModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users") , CandidateModel.class)
                        .build();



        fakeCandidatesAdapter=new FCandidatesAdapter(options);
        vizualizeCVsRv.setAdapter(fakeCandidatesAdapter);


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
