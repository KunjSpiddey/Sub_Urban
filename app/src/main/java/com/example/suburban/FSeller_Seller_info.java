package com.example.suburban;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class FSeller_Seller_info extends Fragment {



    public FSeller_Seller_info() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_f_seller__seller_info, container, false);
        RadioButton R1 = (RadioButton)view.findViewById(R.id.suburban_delivery);
        RadioButton R2 = (RadioButton)view.findViewById(R.id.self_delivery);
       Button button = (Button)view.findViewById(R.id.seller_info_next);

        R1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    R1.setChecked(true);
                    R2.setChecked(false);
                }
            }
        });
        R2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    R1.setChecked(false);
                    R2.setChecked(true);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.seller_container, new FSeller_ProductDetails());
                ft.replace(R.id.seller_container , new FSeller_ProductDetails());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }
}