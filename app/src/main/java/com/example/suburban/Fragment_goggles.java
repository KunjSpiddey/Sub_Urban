package com.example.suburban;
// Fragment_goggles.java

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Fragment_goggles extends Fragment {

    private MyAdapter adapter;
    private GridView gridView;

    private ArrayList<addedProducts> dataList = new ArrayList<>();

    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
    Query query = databaseReference.orderByChild("productType").equalTo("Goggles");

    public Fragment_goggles() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goggles, container, false);

        gridView = view.findViewById(R.id.grid_view);

        List<Fav_item> fav_items = new ArrayList<>();

        adapter = new MyAdapter(getContext(), dataList, fav_items);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addedProducts item = (addedProducts) parent.getAdapter().getItem(position);
                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("products");
                databaseReference1.child(item.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Retrieve the data from Firebase
                        String name = snapshot.child("productName").getValue(String.class);
                        String imageUri = snapshot.child("image_uri").getValue(String.class);
                        String brand = snapshot.child("brand").getValue(String.class);
                        String color = snapshot.child("color").getValue(String.class);
                        String contains = snapshot.child("contains").getValue(String.class);
                        String productId = snapshot.child("id").getValue(String.class);
                        String productCategory = snapshot.child("productCategory").getValue(String.class);
                        String deliveryCharge = snapshot.child("productDeliveryCharge").getValue(String.class);
                        String description = snapshot.child("productDescription").getValue(String.class);
                        String discountPrice = snapshot.child("productDiscountPrice").getValue(String.class);
                        String originalPrice = snapshot.child("productOriginalPrice").getValue(String.class);
                        String quantity = snapshot.child("productQuantity").getValue(String.class);
                        String size = snapshot.child("productSize").getValue(String.class);
                        String productType = snapshot.child("productType").getValue(String.class);
                        String returnPolicy = snapshot.child("return").getValue(String.class);

                        ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
                        Bundle args = new Bundle();
                        args.putString("name", name);
                        args.putString("image_uri", imageUri);
                        args.putString("brand", brand);
                        args.putString("color", color);
                        args.putString("contains", contains);
                        args.putString("id", productId);
                        args.putString("product_category", productCategory);
                        args.putString("deliverycharge", deliveryCharge);
                        args.putString("desc", description);
                        args.putString("dprice", discountPrice);
                        args.putString("oprice", originalPrice);
                        args.putString("qnty", quantity);
                        args.putString("size", size);
                        args.putString("productType", productType);
                        args.putString("Return", returnPolicy);
                        itemDetailsFragment.setArguments(args);

                        FragmentManager fragmentManager = getParentFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.container, itemDetailsFragment).addToBackStack(null).commit();



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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

                // Pass the data to the adapter
                adapter.setDataList(dataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }



}
