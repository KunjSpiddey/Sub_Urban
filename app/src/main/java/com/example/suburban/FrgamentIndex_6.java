package com.example.suburban;

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


public class FrgamentIndex_6 extends Fragment {


    public FrgamentIndex_6() {
        // Required empty public constructor
    }

    private MyAdapter adapter;
    private GridView gridView;

    private ArrayList<addedProducts> dataList = new ArrayList<>();
    private List<WishListItem> wishListItems = new ArrayList<>(); // initialize wishListItems

    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

    Query query = databaseReference.orderByChild("productType").equalTo("Shoes");







    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frgament_index_6, container, false);

        gridView = view.findViewById(R.id.grid_view);

        List<Fav_item> fav_items = new ArrayList<>();

        adapter = new MyAdapter(getContext(), dataList, fav_items);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item position
                addedProducts item = (addedProducts) parent.getAdapter().getItem(position);
                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("products");
                databaseReference1.child(item.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("productName").getValue(String.class);
                        String imageUri = snapshot.child("image_uri").getValue(String.class);
                        String brand = snapshot.child("brand").getValue(String.class);
                        String color = snapshot.child("color").getValue(String.class);
                        String contains = snapshot.child("contains").getValue(String.class);
                        String id = snapshot.child("id").getValue(String.class);
                        String product_category = snapshot.child("productCategory").getValue(String.class);
                        String deliverycharge = snapshot.child("productDeliveryCharge").getValue(String.class);
                        String desc = snapshot.child("productDescription").getValue(String.class);
                        String dprice = snapshot.child("productDiscountPrice").getValue(String.class);
                        String oprice = snapshot.child("productOriginalPrice").getValue(String.class);
                        String qnty = snapshot.child("productQuantity").getValue(String.class);
                        String size = snapshot.child("productSize").getValue(String.class);
                        String productType = snapshot.child("productType").getValue(String.class);
                        String Return = snapshot.child("return").getValue(String.class);

                        ItemDetailsFragment itemDetailsFragment = new ItemDetailsFragment();
                        Bundle args = new Bundle();
                        args.putString("name" , name);
                        args.putString("image_uri" , imageUri);
                        args.putString("brand" , brand);
                        args.putString("color" , color);
                        args.putString("contains" , contains);
                        args.putString("id" , id);
                        args.putString("product_category" , product_category);
                        args.putString("deliverycharge" , deliverycharge);
                        args.putString("desc" , desc);
                        args.putString("dprice" , dprice);
                        args.putString("oprice" , oprice);
                        args.putString("qnty" , qnty);
                        args.putString("size" , size);
                        args.putString("productType" , productType);
                        args.putString("Return" , Return);
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

                List<Fav_item> favItems = new ArrayList<>();
                for (addedProducts products : dataList) {
                    Fav_item favItem = new Fav_item(
                            products.getId(),
                            products.getProductName(),
                            products.getImage_uri(),
                            products.getProductOriginalPrice(),
                            products.getProductDiscountPrice());
                    fav_items.add(favItem);

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
