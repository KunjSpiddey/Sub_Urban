package com.example.suburban;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity {

    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("admin_products");
    GridView gridView;
    adminadapter adapter;
    private ArrayList<addedProducts> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        gridView= findViewById(R.id.gridview);
        List<Fav_item> fav_items = new ArrayList<>();
        adapter = new adminadapter(getApplicationContext(),dataList,fav_items);
        gridView.setAdapter(adapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    addedProducts add = dataSnapshot.getValue(addedProducts.class);
                    dataList.add(add);
                }

                // Update the adapter or UI with the retrieved data
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors that occur
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item position
                addedProducts item = (addedProducts) parent.getAdapter().getItem(position);
                DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("admin_products");
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

                        Intent args = new Intent(Admin.this , adminitemdetail.class);
                        args.putExtra("name" , name);
                        args.putExtra("image_uri" , imageUri);
                        args.putExtra("brand" , brand);
                        args.putExtra("color" , color);
                        args.putExtra("contains" , contains);
                        args.putExtra("id" , id);
                        args.putExtra("product_category" , product_category);
                        args.putExtra("deliverycharge" , deliverycharge);
                        args.putExtra("desc" , desc);
                        args.putExtra("dprice" , dprice);
                        args.putExtra("oprice" , oprice);
                        args.putExtra("qnty" , qnty);
                        args.putExtra("size" , size);
                        args.putExtra("productType" , productType);
                        args.putExtra("Return" , Return);
                        startActivity(args);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}