package com.example.suburban;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Cart_Fragment extends Fragment {
    TextView total_item, total_price;

    RecyclerView rc;
    AppCompatButton checkout;
    List<addtoproductsITEM> addtoproductsITEMS;
    addtocartAdapter adapter;
    AddToCartDatabase db;

    SharedPreferences sharedPreferences;

    public Cart_Fragment() {
        // Required empty public constructor
    }

    public List<addtoproductsITEM> getCartItems() {
        return addtoproductsITEMS;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart_, container, false);

        total_item = view.findViewById(R.id.total_item);
        total_price = view.findViewById(R.id.total_price);
        rc = view.findViewById(R.id.cart_items_recyclerview);
        checkout = view.findViewById(R.id.checkout);

        rc.setLayoutManager(new LinearLayoutManager(getActivity()));
        db = new AddToCartDatabase(getActivity());
        addtoproductsITEMS = db.getallItems();
        sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        adapter = new addtocartAdapter(addtoproductsITEMS, getActivity() , sharedPreferences , this);



        // Set the total price to the TextView
//        total_price.setText(String.format("₹ %.2f", totalPrice));

       updateCartView();


        rc.setAdapter(adapter);




        // Add your logic for handling cart items, total price, and checkout button here

        return view;
    }

    private void updateCartView() {
        double totalPrice = calculateTotalPrice();
        int totalItemCount = addtoproductsITEMS.size();

        total_price.setText(String.format("₹ %.2f", totalPrice));
        total_item.setText("Total Item ("+String.valueOf(totalItemCount)+")");
        adapter.notifyDataSetChanged();
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (addtoproductsITEM item : addtoproductsITEMS) {
            double price = Double.parseDouble(item.getDprice());
            totalPrice += price;
        }
        return totalPrice;
    }

    // Call this method whenever an item is added to the cart
    private void addItemToCart(addtoproductsITEM item) {
        addtoproductsITEMS.add(item);
        adapter.notifyDataSetChanged();
        updateCartView();
    }

    // Call this method whenever an item is removed from the cart
    public void removeItemFromCart(addtoproductsITEM item) {
        addtoproductsITEMS.remove(item);
        adapter.notifyDataSetChanged();
        updateCartView();
    }




}
