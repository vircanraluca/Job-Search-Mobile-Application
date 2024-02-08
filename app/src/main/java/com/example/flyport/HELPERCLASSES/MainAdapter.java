package com.example.flyport.HELPERCLASSES;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flyport.JOBSEEKER.JobDetailsActivity;
import com.example.flyport.JOBSEEKER.pdfClass;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainAdapter extends FirebaseRecyclerAdapter<MainModel, MainAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private String searchName = "";


    private List<MainModel> originalList=new ArrayList<>();
    private List<MainModel> filteredList=new ArrayList<>();
    private List<MainModel> favoriteJobsList = new ArrayList<>();




    private List<String> favoriteJobKeys = new ArrayList<>();

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String currentUserId = firebaseAuth.getCurrentUser().getUid();
    private DatabaseReference favoriteJobsRef=FirebaseDatabase.getInstance().getReference().child("favoritejobs").child(currentUserId);;

    private List<MainModel> jobList;
    private List<MainModel>matchingJobs;

    // Constructor și alte metode ale adapterului

    // Metoda pentru actualizarea listei de joburi
    public void setJobList(List<MainModel> jobList) {
        this.jobList = jobList;
        notifyDataSetChanged();
    }


    public void setSearchName(String searchName) {
        this.searchName = searchName.trim().toLowerCase();
        applyFilters();
    }





    public void applyFilters() {
        filteredList.clear();

        for (MainModel item : originalList) {
            String denumireJob = item.getDenumireJob().toLowerCase();
            String numeCompanie = item.getNumeCompanie().toLowerCase();
            String oras = item.getOras().toLowerCase();
            String tara = item.getTara().toLowerCase();
            String tipJob=item.getTipJob().toLowerCase();

            if (denumireJob.contains(searchName)
                    || numeCompanie.contains(searchName)
                    || oras.contains(searchName)
                    || tara.contains(searchName)
                    || tipJob.contains(searchName)) {
                filteredList.add(item);
            }
        }

        notifyDataSetChanged();
    }




    private void updateFavoriteJobsList(MainModel model, boolean isChecked) {
        if (isChecked) {
            favoriteJobsList.add(model);
        } else {
            favoriteJobsList.remove(model);
        }
    }

    public void setFavoriteJobKeys(List<String> favoriteJobKeys) {
        this.favoriteJobKeys = favoriteJobKeys;
        notifyDataSetChanged();
    }

    public void updateFavoriteJobsList(List<MainModel> favoriteJobsList) {
        this.favoriteJobsList = favoriteJobsList;
        notifyDataSetChanged();
    }

    public void updateJobList(List<MainModel> matchingJobs) {
        this.matchingJobs= matchingJobs     ;
        notifyDataSetChanged();
    }






    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
        this.originalList = new ArrayList<>(options.getSnapshots());
        this.filteredList = new ArrayList<>(options.getSnapshots());
        this.favoriteJobsList = new ArrayList<>(options.getSnapshots());

        validateModelList();

    }

    public void validateModelList() {
        int itemCount = getItemCount();

        if (itemCount == getSnapshots().size()) {
            // Lista de modele este validă și numărul de elemente este corect
            Log.d("MainAdapter", "Lista de modele este validă. Numărul de elemente: " + itemCount);
        } else {
            // Lista de modele este invalidă sau numărul de elemente este incorect
            Log.d("MainAdapter", "Lista de modele este invalidă sau numărul de elemente este incorect");
        }
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainModel model) {

        String denumireJob = model.getDenumireJob();
        String oras = model.getOras();
        String tara=model.getTara();
        String tipJob=model.getTipJob();

        if (denumireJob != null && (denumireJob.toLowerCase().contains(searchName.toLowerCase())
                || oras!=null && oras.toLowerCase().contains(searchName.toLowerCase())|| tara!=null && tara.toLowerCase().contains(searchName.toLowerCase())
                || tipJob!=null &&tipJob.toLowerCase().contains(searchName.toLowerCase())))
        {
        int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition >= getItemCount()) {
            // Poziția este în afara intervalului valid al listei
            return;
        }

        holder.itemView.setVisibility(View.VISIBLE);
        holder.denumireJob.setText(model.getDenumireJob());
        holder.numeCompanie.setText(model.getNumeCompanie());
        holder.tara.setText(model.getTara());
        holder.oras.setText(model.getOras());
        holder.tipJob.setText(model.getTipJob());


            holder.checkBox.setChecked(model.isFavorite());
            holder.checkBox.setOnCheckedChangeListener(null);
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int adapterPosition = holder.getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        MainModel selectedModel = getItem(holder.getAdapterPosition());
                        selectedModel.setFavorite(isChecked);
                        updateFavoriteJobsList(selectedModel, isChecked);

                        if (isChecked) {
                            String jobKey = getRef(adapterPosition).getKey();
                            favoriteJobsRef.child(jobKey).setValue(selectedModel);

                        } else {
                            String jobKey = getRef(adapterPosition).getKey();
                            favoriteJobsRef.child(jobKey).removeValue();
                        }



                    }
                }
            });

            holder.applicationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userId = firebaseAuth.getCurrentUser().getUid();
                    String jobId = getRef(adapterPosition).getKey();
                    DatabaseReference uploadedCVRef = FirebaseDatabase.getInstance().getReference().child("uploadedCV").child(userId);
                    DatabaseReference jobApplicationRef = FirebaseDatabase.getInstance().getReference().child("job_application").child(userId);

                    uploadedCVRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot cvSnapshot : snapshot.getChildren()) {
                                    int adapterPosition = holder.getAdapterPosition();
                                    if (adapterPosition != RecyclerView.NO_POSITION) {
                                        MainModel selectedModel = getItem(adapterPosition);

                                        String userId = firebaseAuth.getCurrentUser().getUid();
                                        String jobId = getRef(adapterPosition).getKey();
                                        DatabaseReference uploadedCVRef = FirebaseDatabase.getInstance().getReference().child("uploadedCV").child(userId);
                                        DatabaseReference jobApplicationRef = FirebaseDatabase.getInstance().getReference().child("job_application").child(userId);



                                        String jobKey = getRef(adapterPosition).getKey();
                                        jobApplicationRef.child(jobKey).setValue(selectedModel);

                                        Toast.makeText(holder.itemView.getContext(), "Job application submitted", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                Toast.makeText(holder.itemView.getContext(), "Job application submitted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(holder.itemView.getContext(), "CV-ul nu a fost încă încărcat!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(holder.itemView.getContext(), "A apărut o eroare. Vă rugăm să încercați din nou!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });





        holder.detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deschide o nouă activitate
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, JobDetailsActivity.class);
                intent.putExtra("jobId", getRef(position).getKey()); // Transmite ID-ul jobului către activitate
                context.startActivity(intent);
            }
        });


        } else {
            holder.itemView.setVisibility(View.GONE);
        }


    }



    @Override
    public int getItemCount() {
        return getSnapshots().size();
    }

    public int getPositionOfFirstItem(String searchText) {
        for (int i = 0; i < getItemCount(); i++) {
            MainModel model = getItem(i);
            // Verificați dacă modelul corespunde criteriului de căutare și este diferit de null
            if (model != null && model.getDenumireJob().toLowerCase().contains(searchText.toLowerCase())) {
                return i;
            }
        }
        return -1; // Returnează -1 dacă nu se găsește niciun element
    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent,false);

        return new myViewHolder(view) ;
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView denumireJob, numeCompanie, oras, tipJob,tara;
        CheckBox checkBox;
        Button applicationBtn;
        Button detailsButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            denumireJob=(TextView) itemView.findViewById(R.id.denumireJobTxt);
            numeCompanie=(TextView) itemView.findViewById(R.id.numeCompanieTxt);
            oras=(TextView) itemView.findViewById(R.id.orasTxt);
            tipJob=(TextView) itemView.findViewById(R.id.tipJobTxt);

            tara=(TextView) itemView.findViewById(R.id.taraTxt);
            checkBox = itemView.findViewById(R.id.checkBox);
            applicationBtn=itemView.findViewById(R.id.applicationBtn);

            detailsButton=itemView.findViewById(R.id.viewCandBtn);
        }


    }
}
