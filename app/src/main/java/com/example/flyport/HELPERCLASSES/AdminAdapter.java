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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminAdapter extends FirebaseRecyclerAdapter<AdminModel,AdminAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private String searchName = "";

    public void setSearchName(String searchName) {
        this.searchName = searchName;
        notifyDataSetChanged();
    }

    private List<MainModel> jobList=new ArrayList<>();

    public AdminAdapter(@NonNull FirebaseRecyclerOptions<AdminModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull AdminModel model) {
        if (model.getDenumireJob().toLowerCase().contains(searchName.toLowerCase())) {
            holder.itemView.setVisibility(View.VISIBLE);
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
        } else {
            holder.itemView.setVisibility(View.GONE);
        }

    }




    @NonNull
    @Override
    public AdminAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_item, parent,false);
        return new AdminAdapter.myViewHolder(view) ;
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView denumireJob, numeCompanie, oras, tipJob;

        Button deleteBtn;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img =(CircleImageView) itemView.findViewById(R.id.img1);
            denumireJob=(TextView) itemView.findViewById(R.id.denumireJobTxt);
            numeCompanie=(TextView) itemView.findViewById(R.id.numeCompanieTxt);
            oras=(TextView) itemView.findViewById(R.id.orasTxt);
            tipJob=(TextView) itemView.findViewById(R.id.tipJobTxt);



            deleteBtn=(Button) itemView.findViewById(R.id.viewCVBtn);


        }
    }
}
