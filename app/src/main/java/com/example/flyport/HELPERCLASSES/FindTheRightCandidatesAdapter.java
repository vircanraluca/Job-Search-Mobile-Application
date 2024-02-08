package com.example.flyport.HELPERCLASSES;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class FindTheRightCandidatesAdapter extends FirebaseRecyclerAdapter<CandidateModel,FindTheRightCandidatesAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    private String searchName = "";
    private List<CandidateModel> filteredList=new ArrayList<>();
    private List<CandidateModel> originalList=new ArrayList<>();

    public void setSearchName(String searchName) {
        this.searchName = searchName.trim().toLowerCase();
        applyFilters();
    }

    public void applyFilters() {
        filteredList.clear();

        for (CandidateModel item : originalList) {
            String city = item.getCity().toLowerCase();
            String country = item.getCountry().toLowerCase();

            if (city.contains(searchName)
                    || country.contains(searchName)) {
                filteredList.add(item);
            }
        }

        notifyDataSetChanged();
    }

    public FindTheRightCandidatesAdapter(@NonNull FirebaseRecyclerOptions<CandidateModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull CandidateModel model) {

        String oras = model.getCity();
        String tara = model.getCountry();

        if ((oras != null && oras.toLowerCase().contains(searchName.toLowerCase())) || (tara != null && tara.toLowerCase().contains(searchName.toLowerCase()))) {

            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition >= getItemCount()) {
                // Poziția este în afara intervalului valid al listei
                return;
            }

            holder.fullName.setText(model.getFullName());
            holder.country.setText(model.getCountry());
            holder.city.setText(model.getCity());
            holder.email.setText(model.getEmail());
            holder.phoneNumber.setText(model.getPhoneNumber());

            holder.email_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailAddress = model.getEmail(); // Preiați adresa de e-mail a candidatului curent

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + emailAddress));

                    v.getContext().startActivity(Intent.createChooser(emailIntent, "Send email"));
                }
            });
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
    }


    @NonNull
    @Override
    public FindTheRightCandidatesAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.find_the_right_candidate_layout, parent,false);
        return new FindTheRightCandidatesAdapter.myViewHolder(view) ;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView fullName,country,city,email, password,phoneNumber;
        ImageView email_img;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName=(TextView) itemView.findViewById(R.id.numeCandidateTxt);
            country=(TextView) itemView.findViewById(R.id.taraCandidatTxt);
            city=(TextView) itemView.findViewById(R.id.orasTxt);
            email=(TextView) itemView.findViewById(R.id.emailCandidatTxt);
            phoneNumber=(TextView) itemView.findViewById(R.id.phoneCandidatTxt);

            email_img=(ImageView) itemView.findViewById(R.id.email_img);


        }


    }


}
