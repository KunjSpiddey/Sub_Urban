package com.example.suburban;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Fragment_goggles extends Fragment {

    public Fragment_goggles() {

    }


    private MyAdapter adapter;
    private GridView gridView;

    private ArrayList<addedProducts> dataList;

    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goggles,container,false);

         gridView = view.findViewById(R.id.grid_view);
         adapter = new MyAdapter(getContext(),dataList);
         gridView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    addedProducts addedProducts = dataSnapshot.getValue(addedProducts.class);
                    dataList.add(addedProducts);

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;

    }


}