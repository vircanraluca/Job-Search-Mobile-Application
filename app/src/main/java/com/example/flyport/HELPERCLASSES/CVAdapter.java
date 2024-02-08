package com.example.flyport.HELPERCLASSES;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flyport.R;

import java.util.List;

public class CVAdapter extends RecyclerView.Adapter<CVAdapter.CVViewHolder> {

    private List<CV> cvList;

    public CVAdapter(List<CV> cvList) {
        this.cvList = cvList;
    }

    @NonNull
    @Override
    public CVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cv, parent, false);
        return new CVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CVViewHolder holder, int position) {
        CV cv = cvList.get(position);
        holder.nameTextView.setText(cv.getName());

        // Setează un listener de click pentru butonul cvButton
        holder.cvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aici puteți apela metoda openCV cu URL-ul corespunzător
                // în funcție de elementul selectat din RecyclerView
                int position = holder.getAdapterPosition();
                CV selectedCV = cvList.get(position);
                openCV(view.getContext(), selectedCV.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cvList.size();
    }

    public static class CVViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button cvButton;

        public CVViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            cvButton = itemView.findViewById(R.id.cvButton);
        }
    }

    private void openCV(Context context, String url) {
        if (url != null && !url.isEmpty()) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Tratează cazul în care nu există o aplicație pentru vizualizarea documentelor PDF instalată pe dispozitiv
            }
        }
    }



}
