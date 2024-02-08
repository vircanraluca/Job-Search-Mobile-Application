package com.example.flyport.COMPANIES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flyport.ADMIN.AdminPanel;
import com.example.flyport.COMMON.MainActivity;
import com.example.flyport.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginRecruiterPage extends AppCompatActivity {

    private ImageView backButton4;
    private Button loginButton;
    private TextView signHereTv2;
    private EditText email;
    private EditText password;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://flyport-3bf8f-default-rtdb.europe-west1.firebasedatabase.app/");
    boolean passwordVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_recruiter_page);

        backButton4=findViewById(R.id.backButton4);
        backButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction()== MotionEvent.ACTION_UP){
                    if(event.getRawX()>=password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=password.getSelectionEnd();
                        if(passwordVisibility){
                            //set drawable image ee
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_password_toggle,0);

                            //for hide
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisibility=false;
                        }else{
                            //set drawable image ee
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_visibility_off_24,0);

                            //for hide
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisibility=true;

                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

        loginButton=findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();

                if (emailTxt.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your email or password", Toast.LENGTH_SHORT).show();
                } else if (emailTxt.equals("admin@gmail.com")) {
                    Intent intent = new Intent(getApplicationContext(), AdminPanel.class);
                    startActivity(intent);
                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailTxt, passwordTxt)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid = user.getUid();

                                        databaseReference.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()) {
                                                    boolean isApproved = snapshot.child("isApproved").getValue(Boolean.class);

                                                    if (isApproved) {
                                                        String getPassword = snapshot.child("password").getValue(String.class);

                                                        if (getPassword.equals(passwordTxt)) {
                                                            Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(getApplicationContext(), RecruiterDashboard.class);
                                                            startActivity(intent);
                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), "Access not yet granted by admin", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                // Handle database error
                                            }
                                        });
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        signHereTv2=findViewById(R.id.signHereTv2);
        signHereTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RegisterRecruiterPage.class);
                startActivity(intent);
            }
        });
    }
}