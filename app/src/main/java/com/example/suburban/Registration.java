package com.example.suburban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
import android.view.View;

public class Registration extends AppCompatActivity {
//    TextView create;
  AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
      btn = findViewById(R.id.registeration_button);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Intent intent=new Intent(Registration.this,Home.class);
           startActivity(intent);
        }
    });

    }
}