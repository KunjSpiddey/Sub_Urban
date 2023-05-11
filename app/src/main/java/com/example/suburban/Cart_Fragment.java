package com.example.suburban;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Cart_Fragment extends Fragment {

    TextView total_item, total_price;
    RecyclerView rc;
    AppCompatButton checkout;

    public Cart_Fragment() {
        // Required empty public constructor
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

        // Add your logic for handling cart items, total price, and checkout button here

        return view;
    }
}
