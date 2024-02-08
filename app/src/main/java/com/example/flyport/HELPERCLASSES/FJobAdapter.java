package com.example.flyport.HELPERCLASSES;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flyport.COMPANIES.ViewCandidatesPerJob;
import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class FJobAdapter extends FirebaseRecyclerAdapter<MainModel, FJobAdapter.myViewHolder> {


    public FJobAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.itemView.setVisibility(View.VISIBLE);
        holder.denumireJob.setText(model.getDenumireJob());
        holder.oras.setText(model.getOras());
        holder.tipJob.setText(model.getTipJob());

        holder.viewCandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ViewCandidatesPerJob.class);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fake_job_post, parent,false);
        return new FJobAdapter.myViewHolder(view) ;
    }




    public class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView denumireJob, oras, tipJob;
        Button viewCandBtn;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            denumireJob=(TextView) itemView.findViewById(R.id.denumireJobTxt);
            oras=(TextView) itemView.findViewById(R.id.orasTxt);
            tipJob=(TextView) itemView.findViewById(R.id.tipJobTxt);

            viewCandBtn=(Button)itemView.findViewById(R.id.viewCandBtn);


        }
    }
}
