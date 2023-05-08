package com.example.suburban;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FSeller_mobile_verification extends Fragment {

TextView new_acc;
    public FSeller_mobile_verification() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_seller_mobile_verification, container, false);

        AppCompatButton Seller_login = view.findViewById(R.id.login_button);
        new_acc = view.findViewById(R.id.new_acc);

//        new_acc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fl(new Registration_seller() , 1);
//            }
//        });


        Seller_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl(new FSeller_Seller_info(),1);
            }
        });


        return view;//
    }
//////////

    private void fl(Fragment fragment, int flag) {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.seller_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }



}