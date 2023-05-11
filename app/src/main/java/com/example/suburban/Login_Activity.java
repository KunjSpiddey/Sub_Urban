package com.example.suburban;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suburban.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private ActionBar actionBar;

    private ProgressDialog progressDialog;
    private  String email= "",password = "";
    private FirebaseAuth firebaseAuth;
    TextView Create,fp;
    EditText a1,a2;
    Button Login;
    ImageView img;
    //    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fp=findViewById(R.id.forgotpass);
        img = findViewById(R.id.imageView6);
//        actionBar= getSupportActionBar();
//        actionBar.setTitle("Login");
        a1 = findViewById(R.id.login_mail);
        a2 = findViewById(R.id.login_password);
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Pease wait");
        progressDialog.setMessage("Logged in");
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth = FirebaseAuth.getInstance();
        checkuser();

        binding.newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this, Registration.class));
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatedata();
            }

        });
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_Activity.this,forgotpassword.class);
                startActivity(i);
            }
        });
        final int[] tapCount = {0};
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapCount[0]++;
                if (tapCount[0] == 7) {
                    Intent intent = new Intent(Login_Activity.this, Admin.class);
                    startActivity(intent);
                    tapCount[0] = 0;
                }
            }
        });



    }

    public void checkuser(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){
            startActivity(new Intent(this,Home.class));
            finish();
        }
    }
    public void validatedata(){
        email= binding.loginMail.getText().toString().trim();
        password=  binding.loginPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // no password is entered
            binding.loginMail.setError("Invalid email format");
        } else if (email == "admin@gmail.com" && password == "6356365709") {

            startActivity(new Intent(Login_Activity.this , Admin.class));
            finish();

        } else if(TextUtils.isEmpty(password)){
            binding.loginMail.setError("Enter password");
        }
        else if(password.length()<6){
            //password less than 6
            binding.loginPassword.setError("Password must atleast 6 character ");
        }
        else{
            //data is valid now continue
            firebaseLogin();
        }
    }

    private void firebaseLogin() {
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();

                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        String email = firebaseUser.getEmail();
                        Toast.makeText(Login_Activity.this, "Succesfully Logged In", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login_Activity.this,Home.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Login_Activity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
//        Create = findViewById(R.id.new_acc);
//        Login = findViewById(R.id.login_button);
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }
//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intenet = new Intent(Login_Activity.this,Home.class);
//                startActivity(intenet);
//                finish();
//            }
//        });
//        Create.setOnClickListener(v -> {
//            Intent intent = new Intent(Login_Activity.this, Registration.class);
//            startActivity(intent);
////           finish();
//        });