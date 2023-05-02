package com.example.suburban;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Registration_seller extends Fragment {

    AppCompatButton reg;
    public Registration_seller() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_registration_seller, container, false);
        reg = getView().findViewById(R.id.registeration_button);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl(new FSeller_Seller_info() , 1);
            }
        });

        return view;
    }

    private void fl(Fragment fragment, int flag){


        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.seller_container,  fragment);
        ft.replace(R.id.seller_container , fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}