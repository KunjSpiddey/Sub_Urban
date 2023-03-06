package com.example.suburban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class Seller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
       fragmentLoader(new FSeller_mobile_verification(),1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }

    private void fragmentLoader(Fragment fragment, int flag) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.seller_container, new FSeller_mobile_verification());
        ft.replace(R.id.seller_container , new FSeller_mobile_verification());
        ft.addToBackStack(null);
        ft.commit();

    }


 public void onBackPressed() {

     if(getSupportFragmentManager().getBackStackEntryCount() > 0)
         getSupportFragmentManager().popBackStack();
     else {
        startActivity(new Intent(Seller.this,Home.class));
        finish();
     }
 }

    }
