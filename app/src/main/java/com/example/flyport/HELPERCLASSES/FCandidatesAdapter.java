package com.example.flyport.HELPERCLASSES;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flyport.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FCandidatesAdapter extends FirebaseRecyclerAdapter<CandidateModel, FCandidatesAdapter.myViewHolder> {

    public FCandidatesAdapter(@NonNull FirebaseRecyclerOptions<CandidateModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fake_candidates_application, parent,false);
        return new FCandidatesAdapter.myViewHolder(view) ;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CandidateModel model) {
        holder.fullName.setText(model.getFullName());
        holder.country.setText(model.getCountry());
        holder.city.setText(model.getCity());
        holder.email.setText(model.getEmail());
        holder.password.setText(model.getPassword());
        holder.phoneNumber.setText(model.getPhoneNumber());

//        holder.viewCVBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String cvUrl = model.getUrl(); // Obțineți URL-ul CV-ului din modelul CandidateModel
//                if (cvUrl != null) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(cvUrl));
//                    holder.itemView.getContext().startActivity(intent);
//                } else {
//                    Toast.makeText(holder.itemView.getContext(), "CV-ul nu este disponibil.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        holder.viewCVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cvUrl = "https://firebasestorage.googleapis.com/v0/b/flyport-3bf8f.appspot.com/o/uploadedCVs%2FsLLMuwWJ7Sawa1MMWTlp23GriAw1%2FsLLMuwWJ7Sawa1MMWTlp23GriAw1_1685872632561.pdf?alt=media&token=f5265fd1-0378-4db8-aca1-912484d7a50f";
                if (cvUrl != null) {
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(cvUrl));
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setTitle("Download CV");

                    // Setează destinația de salvare a fișierului descărcat
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "CV.pdf");

                    DownloadManager downloadManager = (DownloadManager) holder.itemView.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                    downloadManager.enqueue(request);
                } else {
                    Toast.makeText(holder.itemView.getContext(), "CV-ul nu este disponibil.", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }


    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView fullName,country,city,email, password,phoneNumber;
        Button viewCVBtn;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName=(TextView) itemView.findViewById(R.id.numeCandidateTxt);
            country=(TextView) itemView.findViewById(R.id.taraCandidatTxt);
            city=(TextView) itemView.findViewById(R.id.orasTxt);
            email=(TextView) itemView.findViewById(R.id.emailCandidatTxt);
            password=(TextView) itemView.findViewById(R.id.passwordCandidatTxt);
            phoneNumber=(TextView) itemView.findViewById(R.id.phoneCandidatTxt);

            viewCVBtn=(Button) itemView.findViewById(R.id.viewCVBtn);


        }
    }
}
