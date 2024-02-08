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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class NewUserAdapter extends FirebaseRecyclerAdapter<CompanyModel, NewUserAdapter.myViewHolder> {

    private List<String> approvedUsers;


    public NewUserAdapter(@NonNull FirebaseRecyclerOptions<CompanyModel> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull NewUserAdapter.myViewHolder holder, int position, @NonNull CompanyModel model) {

        if (model.isApproved()) {
            // Ascunde elementul sau setează vizibilitatea ca GONE pentru a evita afișarea companiilor aprobate
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
        holder.companyName.setText(model.getCompanyName());
        holder.country.setText(model.getCountry());
        holder.city.setText(model.getCity());
        holder.email.setText(model.getEmail());
        holder.password.setText(model.getPassword());
        holder.phoneNumber.setText(model.getPhoneNumber());

        holder.deniedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = holder.getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.companyName.getContext());
                builder.setTitle("Are you sure you want to deny?");
                builder.setMessage("Denied data cannot be undone");

                builder.setPositiveButton("DENY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(getRef(pos).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.companyName.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = holder.getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.companyName.getContext());
                builder.setTitle("Are you sure you want to grant access?");
                builder.setMessage("Access granted can't be undone");

                builder.setPositiveButton("GRANT ACCESS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userId = getRef(pos).getKey();
                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                        userRef.child("isApproved").setValue(true);
                        // Dă acces companiei aici
                        // ...
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.companyName.getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grand_acces_users_item, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView companyName, country, city, email, password, phoneNumber;

        Button acceptBtn, deniedBtn;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = (TextView) itemView.findViewById(R.id.numeCandidateTxt);
            country = (TextView) itemView.findViewById(R.id.taraCandidatTxt);
            city = (TextView) itemView.findViewById(R.id.orasTxt);
            email = (TextView) itemView.findViewById(R.id.emailCandidatTxt);
            password = (TextView) itemView.findViewById(R.id.passwordCandidatTxt);
            phoneNumber = (TextView) itemView.findViewById(R.id.phoneCandidatTxt);

            acceptBtn = (Button) itemView.findViewById(R.id.acceptBtn);
            deniedBtn = (Button) itemView.findViewById(R.id.deniedBtn);
        }
    }
}
