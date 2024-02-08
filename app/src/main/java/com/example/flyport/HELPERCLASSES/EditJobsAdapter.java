package com.example.flyport.HELPERCLASSES;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flyport.COMPANIES.AddNewJobPostActivity;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class EditJobsAdapter extends FirebaseRecyclerAdapter<MainModel, EditJobsAdapter.myViewHolder> {

    public EditJobsAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EditJobsAdapter.myViewHolder holder, int position, @NonNull MainModel model) {
        holder.denumireJob.setText(model.getDenumireJob());
        holder.numeCompanie.setText(model.getNumeCompanie());
        holder.oras.setText(model.getOras());
        holder.tipJob.setText(model.getTipJob());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = holder.getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.denumireJob.getContext());
                builder.setTitle("Are you sure you want to delete?");
                builder.setMessage("Deleted data can't be undone");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("jobs").child(getRef(pos).getKey()).removeValue();
                        notifyItemRemoved(pos);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.denumireJob.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                final DialogPlus dialogPlus=DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1700)
                        .create();

                //dialogPlus.show();

                View view=dialogPlus.getHolderView();
                EditText txtJobTitle=view.findViewById(R.id.txtJobTitle);
                EditText txtCompanyName=view.findViewById(R.id.txtCompanyName);
                EditText txtCountry=view.findViewById(R.id.txtCountry);
                EditText txtCity=view.findViewById(R.id.txtCity);
                EditText txtDescription=view.findViewById(R.id.txtDescription);
                EditText txtJobType=view.findViewById(R.id.txtJobType);
                EditText txtMinimumSalary=view.findViewById(R.id.txtMinimumSalary);
                EditText txtMaximumSalary=view.findViewById(R.id.txtMaximumSalary);
                EditText txtBenefits=view.findViewById(R.id.txtBenefits);
                EditText txtExperienceLevel=view.findViewById(R.id.txtExperienceLevel);
                EditText txtRequiredEducation=view.findViewById(R.id.txtRequiredEducation);
                EditText txtLanguageRequierments=view.findViewById(R.id.txtLanguageRequierments);
                EditText txtIndustry=view.findViewById(R.id.txtIndustry);

                Button btnUpdate=view.findViewById(R.id.btnUpdate);

                txtJobTitle.setText(model.getDenumireJob());
                txtCompanyName.setText(model.getNumeCompanie());
                txtCountry.setText(model.getTara());
                txtCity.setText(model.getOras());
                txtDescription.setText(model.getDescriere());
                txtJobType.setText(model.getTipJob());
                txtMinimumSalary.setText(model.getMinimumSalary());
                txtMaximumSalary.setText(model.getMaximumSalary());
                txtBenefits.setText(model.getBenefits());
                txtExperienceLevel.setText(model.getExperienceLevel());
                txtRequiredEducation.setText(model.getRequiredEducation());
                txtLanguageRequierments.setText(model.getLanguageRequirements());
                txtIndustry.setText(model.getIndustry());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("denumireJob", txtJobTitle.getText().toString());
                        map.put("tara", txtCountry.getText().toString());
                        map.put("oras", txtCity.getText().toString());
                        map.put("descriere", txtDescription.getText().toString());
                        map.put("tipJob", txtJobType.getText().toString());
                        map.put("minimumSalary", txtMinimumSalary.getText().toString());
                        map.put("maximumSalary", txtMaximumSalary.getText().toString());
                        map.put("benefits", txtBenefits.getText().toString());
                        map.put("experienceLevel", txtExperienceLevel.getText().toString());
                        map.put("requiredEducation", txtRequiredEducation.getText().toString());
                        map.put("languageRequirements", txtLanguageRequierments.getText().toString());
                        map.put("industry", txtIndustry.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("jobs").child(getRef(position).getKey()).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Data updated successfully ", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Error when updated data ", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });




            }
        });




    }

    @NonNull
    @Override
    public EditJobsAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_job_post_item, parent,false);
        return new EditJobsAdapter.myViewHolder(view) ;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView denumireJob, numeCompanie, oras, tipJob;
        Button deleteBtn, editBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            denumireJob=(TextView) itemView.findViewById(R.id.denumireJobTxt);
            numeCompanie=(TextView) itemView.findViewById(R.id.numeCompanieTxt);
            oras=(TextView) itemView.findViewById(R.id.orasTxt);
            tipJob=(TextView) itemView.findViewById(R.id.tipJobTxt);

            deleteBtn=(Button) itemView.findViewById(R.id.viewCVBtn);
            editBtn=(Button) itemView.findViewById(R.id.editBtn);
        }
    }
}
