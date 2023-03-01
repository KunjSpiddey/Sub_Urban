package com.example.suburban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {
TextView Create;
Button Login;
//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Create = findViewById(R.id.new_acc);
        Login = findViewById(R.id.login_button);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenet = new Intent(Login_Activity.this,Home.class);
                startActivity(intenet);
                finish();
            }
        });
        Create.setOnClickListener(v -> {
            Intent intent = new Intent(Login_Activity.this, Registration.class);
            startActivity(intent);
//           finish();
        });


    }
}