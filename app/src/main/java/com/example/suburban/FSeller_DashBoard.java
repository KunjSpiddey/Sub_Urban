package com.example.suburban;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FSeller_DashBoard extends Fragment {

    public FSeller_DashBoard() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_f_seller__dash_board, container, false);

        AppCompatButton button = (AppCompatButton)view.findViewById(R.id.dashboard_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "helooooooo", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}