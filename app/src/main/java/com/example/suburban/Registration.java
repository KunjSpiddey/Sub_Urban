package com.example.suburban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;


import com.example.suburban.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {
    private ActivityRegistrationBinding binding;
    private FirebaseAuth firebaseAuth ;
    EditText username,email1,password1;
    private  String email= "",password = "";
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
        username = findViewById(R.id.registration_username);
        email1 = findViewById(R.id.registration_mail);
        password1 = findViewById(R.id.registration_password);

        binding.registerationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();
            }

        });

//      btn = findViewById(R.id.registeration_button);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
//
//    btn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//           Intent intent=new Intent(Registration.this,Home.class);
//           startActivity(intent);
//        }
//    });
    }
    private void validatedata(){
        //get data
        email=binding.registrationMail.getText().toString().trim();
        password= binding.registrationPassword.getText().toString().trim();

        //validate data

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // no password is entered
            binding.registerationButton.setError("Invalid email format");
        }
        else if(TextUtils.isEmpty(password)){
            binding.registrationMail.setError("Enter password");
        }
        else if(password.length()<6){
            //password less than 6

            binding.registrationPassword.setError("Password must atleast 6 character ");
        }
        else{
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
                        progressDialog.dismiss();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String email = firebaseUser.getEmail();
                        Toast.makeText(Registration.this, "Your Account Has Been Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registration.this,Home.class));
                        finish();
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



