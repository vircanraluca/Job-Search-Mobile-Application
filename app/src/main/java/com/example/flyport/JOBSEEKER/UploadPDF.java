package com.example.flyport.JOBSEEKER;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flyport.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadPDF extends AppCompatActivity {

    private Button uploadCV;
    private EditText pdf_name;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_pdf);

        uploadCV=findViewById(R.id.uploadCV);
        pdf_name=findViewById(R.id.insertYourPDFNameEt);

        //database
        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference("uploadedCV");

        uploadCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdfName = pdf_name.getText().toString().trim();
                if (isValidPDFName(pdfName)) {
                    selectedFiles();
                } else {
                    Toast.makeText(UploadPDF.this, "Please use the format LastName_FirstName_CV", Toast.LENGTH_SHORT).show();
                }
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userID = currentUser.getUid();
        }
    }

    private void selectedFiles() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF Files..."),1);
    }

    // Funcție pentru validarea denumirii PDF-ului
    private boolean isValidPDFName(String pdfName) {
        // Verificați aici formatul dorit, de exemplu, Nume_Prenume_CV
        String pattern = "[A-Za-z]+_[A-Za-z]+_CV";
        return pdfName.matches(pattern);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData() !=null){
            UploadedFiles(data.getData());
        }
    }
    private void UploadedFiles(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        String fileName = userID + "_" + System.currentTimeMillis() + ".pdf";
        StorageReference reference = storageReference.child("uploadedCVs").child(userID).child(fileName);
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());

                Uri url = uriTask.getResult();

                pdfClass pdfClass = new pdfClass(pdf_name.getText().toString(), url.toString());
                String uploadId = databaseReference.child(userID).push().getKey();
                databaseReference.child(userID).child(uploadId).setValue(pdfClass);


                Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded: " + (int) progress + "%");
            }
        });
    }

}