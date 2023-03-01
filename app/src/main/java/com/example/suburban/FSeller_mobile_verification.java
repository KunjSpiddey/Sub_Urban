package com.example.suburban;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FSeller_mobile_verification extends Fragment {

    public FSeller_mobile_verification() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_seller_mobile_verification, container, false);

        AppCompatButton SEND_OTP = view.findViewById(R.id.OTP);
        ImageView img = view.findViewById(R.id.verification_Image);

      TextView mobile_veri_next = view.findViewById(R.id.mobile_veri_next);

      mobile_veri_next.setOnClickListener(view1 -> {
          fl(new FSeller_Seller_info(),1);
      });

      SEND_OTP.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Toast.makeText(getContext(),  "OTP Sent", Toast.LENGTH_SHORT).show();
          }
      });

        return view;
    }


   


    private void fl(Fragment fragment, int flag){
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.seller_container, fragment);
        ft.replace(R.id.seller_container , fragment);
        ft.addToBackStack(null);
        ft.commit();




    }







}