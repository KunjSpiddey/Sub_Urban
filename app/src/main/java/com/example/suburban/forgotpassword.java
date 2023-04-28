package com.example.suburban;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email1;
// inside onCreate() or onCreateView()


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        mAuth = FirebaseAuth.getInstance();
        Button resetButton = findViewById(R.id.forgotpassbtn);
        EditText et= findViewById(R.id.email_edit_text);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    et.setError("Email is required.");
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
//                                    Toast.makeText(forgotpassword.this, "Check your email account", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(forgotpassword.this, "link successfully sent to you mail", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(forgotpassword.this, Login_Activity.class));
                                    finish();
                                } else {
                                    Toast.makeText(forgotpassword.this, "Enter Your Mail Properly", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });

    }
}