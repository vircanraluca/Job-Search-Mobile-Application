package com.example.flyport.HELPERCLASSES;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class CandidateAdapter extends FirebaseRecyclerAdapter<CandidateModel,CandidateAdapter.myViewHolder>{

    public CandidateAdapter(@NonNull FirebaseRecyclerOptions<CandidateModel> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CandidateModel model) {
        holder.fullName.setText(model.getFullName());
        holder.country.setText(model.getCountry());
        holder.city.setText(model.getCity());
        holder.email.setText(model.getEmail());
        holder.password.setText(model.getPassword());
        holder.phoneNumber.setText(model.getPhoneNumber());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = holder.getAdapterPosition();
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.fullName.getContext());
                builder.setTitle("Are you sure you want to delete?");
                builder.setMessage("Deleted data can't be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(getRef(pos).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.fullName.getContext(),"Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public CandidateAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.candidate_item, parent,false);
        return new CandidateAdapter.myViewHolder(view) ;
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView fullName,country,city,email, password,phoneNumber;

        Button deleteBtn;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName=(TextView) itemView.findViewById(R.id.numeCandidateTxt);
            country=(TextView) itemView.findViewById(R.id.taraCandidatTxt);
            city=(TextView) itemView.findViewById(R.id.orasTxt);
            email=(TextView) itemView.findViewById(R.id.emailCandidatTxt);
            password=(TextView) itemView.findViewById(R.id.passwordCandidatTxt);
            phoneNumber=(TextView) itemView.findViewById(R.id.phoneCandidatTxt);

            deleteBtn=(Button) itemView.findViewById(R.id.viewCVBtn);


        }
    }
}
