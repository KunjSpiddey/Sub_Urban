package com.example.suburban;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Seller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        fragmentLoader(new FSeller_mobile_verification(), 1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void fragmentLoader(Fragment fragment, int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.seller_container, fragment);
        if (flag == 1) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }



    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            startActivity(new Intent(Seller.this, Home.class));
            finish();
        }
    }
}
