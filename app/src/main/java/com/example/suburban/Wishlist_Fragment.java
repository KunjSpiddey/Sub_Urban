package com.example.suburban;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class Wishlist_Fragment extends Fragment {

   private GridView gridView;
   private Context context;

    public Wishlist_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_wishlist_, container, false);

        gridView = view.findViewById(R.id.wishlist_grid_view);
        WislhListDataBase db = new WislhListDataBase(getContext());
        ArrayList<WishListItem> wishListItems = (ArrayList<WishListItem>) db.getAllItems();
        ArrayList<addedProducts> addedProductsList = new ArrayList<>();
        WishListAdapter wishListAdapter = new WishListAdapter(getContext(),wishListItems);
        gridView.setAdapter(wishListAdapter);





        return view;
    }


    }






















