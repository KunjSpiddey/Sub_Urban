package com.example.suburban;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.suburban.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Registration extends AppCompatActivity {
    private ActivityRegistrationBinding binding;
    private FirebaseAuth firebaseAuth ;
    EditText confirmpass2,email1,password1;
    private  String email= "",password = "", confirmpass ="" ,mobile ="";
    private ProgressDialog progressDialog;
    private ActionBar actionBar;
    //    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("UerInformation");
//    TextView create;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait for registration");
        progressDialog.setMessage("Creating your account...");
        progressDialog.setCanceledOnTouchOutside(false);

//        actionBar = getSupportActionBar();
//        actionBar.setTitle("Sign up");
//        actionBar.setDisplayShowHomeEnabled(true);



        Button btn = findViewById(R.id.registeration_button);
        confirmpass2 = findViewById(R.id.confirm_password);
        email1 = findViewById(R.id.registration_mail);
        password1 = findViewById(R.id.registration_password);

        binding.registerationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();
            }

        });

    }
    private void validatedata(){
        //get data

        email=binding.registrationMail.getText().toString().trim();
        password= binding.registrationPassword.getText().toString().trim();
        confirmpass = confirmpass2.getText().toString().trim();
        mobile = binding.registrationMobile.getText().toString().trim();
        //validate data

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && TextUtils.isEmpty(password)) {
            // no password is entered
            binding.invalidMail.setVisibility(View.VISIBLE);
        }
        else if(TextUtils.isEmpty(mobile)){
            binding.invalidMobile.setVisibility(View.VISIBLE);
        }
        else if(password.length()<6){
            //password less than 6

            binding.lettersError.setVisibility(View.VISIBLE);
        } else if (!password.equals(confirmpass)) {

            binding.notMatch.setVisibility(View.VISIBLE);

        } else{
            binding.invalidMail.setVisibility(View.GONE);
            binding.invalidMobile.setVisibility(View.GONE);
            binding.lettersError.setVisibility(View.GONE);
            binding.notMatch.setVisibility(View.GONE);
            //data is valid now continue
            firebaseSignup();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    private void firebaseSignup() {
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(mobile)
                                .build();

                        firebaseUser.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressDialog.dismiss();
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Registration.this, "Your Account Has Been Created", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Registration.this,Home.class));
                                            finish();
                                        } else {
                                            Toast.makeText(Registration.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //signup falied
                        progressDialog.dismiss();
                        Toast.makeText(Registration.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}



