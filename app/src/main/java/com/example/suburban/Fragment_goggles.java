package com.example.suburban;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Fragment_goggles extends Fragment {

    public Fragment_goggles() {

    }

    private MyAdapter adapter;
    private GridView gridView;

    private ArrayList<addedProducts> dataList = new ArrayList<>();
    private List<WishListItem> wishListItems = new ArrayList<>(); // initialize wishListItems

    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

    Query query = databaseReference.orderByChild("productType").equalTo("Goggles");

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goggles, container, false);

        gridView = view.findViewById(R.id.grid_view);

        List<Fav_item> fav_items = new ArrayList<>();

        adapter = new MyAdapter(getContext(), dataList, fav_items);
        gridView.setAdapter(adapter);

        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "hello"+i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    addedProducts add = dataSnapshot.getValue(addedProducts.class);
                    dataList.add(add);

                }

                List<Fav_item> favItems = new ArrayList<>();

                for (addedProducts products : dataList) {
                    Fav_item favItem = new Fav_item(
                            products.getId(),
                            products.getProductName(),
                            products.getImage_uri(),
                            products.getProductOriginalPrice(),
                            products.getProductDiscountPrice());
                    fav_items.add(favItem);
                    adapter.setFavItems(favItems);
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
