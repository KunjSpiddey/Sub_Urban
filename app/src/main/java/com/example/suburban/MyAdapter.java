package com.example.suburban;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<Fav_item> fav_items;
    private ArrayList<addedProducts> dataList;

    private Context context;
    private String id;

    public MyAdapter() {
    }

    public MyAdapter(String id) {
        this.id = id;
    }
    public String sting (String id){
        this.id = id;
        return id;
    }

    LayoutInflater layoutInflater;
    WislhListDataBase db;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public MyAdapter(Context context, ArrayList<addedProducts> dataList, List<Fav_item> fav_items) {
        this.context = context;
        this.dataList = dataList;
        db = new WislhListDataBase(context);
        layoutInflater = LayoutInflater.from(context);
        sharedPreferences = context.getSharedPreferences("checkbox_prefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        this.fav_items = fav_items;
    }



    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }




    public void setFavItems(List<Fav_item> favItems) {
        this.fav_items = favItems;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;


        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = layoutInflater.inflate(R.layout.grid_item, null);
            holder = new ViewHolder();
            holder.item_image = view.findViewById(R.id.gridImage);
            holder.item_name = view.findViewById(R.id.Product_Name);
            holder.o_price = view.findViewById(R.id.original_price);
            holder.d_price = view.findViewById(R.id.discount_price);
            holder.fav = view.findViewById(R.id.fav_check);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        addedProducts product = dataList.get(i);

        Glide.with(context).load(product.getImage_uri()).into(holder.item_image);
        holder.item_name.setText(product.getProductName());
        holder.o_price.setText("₹" + product.getProductOriginalPrice());
        holder.d_price.setText("₹" + product.getProductDiscountPrice());

        // Retrieve the saved checkbox state from SharedPreferences
        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isChecked = preferences.getBoolean("checkbox_" + product.getId(), false);

        holder.fav.setChecked(isChecked);


        holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // Save the checkbox state to SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("checkbox_" + product.getId(), b);
                editor.apply();
                // Add or remove the product from the wishlist database based on checkbox state
                WishListItem item = new WishListItem(product.getId(), product.getProductName(), product.getProductOriginalPrice(),
                        product.getProductDiscountPrice(), product.getImage_uri());
                if (b) {
                    db.insertData(item);

                } else {
                    holder.fav.setChecked(false);
                    db.deleteData(item.getId());

                }
            }
        });

        // Check if the delete button was clicked in the WishlistFragment
        boolean deleteClicked = sharedPreferences.getBoolean("delete_clicked", false);
        if (deleteClicked) {
            // Get the saved state of the checkbox for this product
            boolean checkBoxState = preferences.getBoolean("checkbox_" + product.getId(), false);
            if (checkBoxState) {
                // If the checkbox is checked, uncheck it and save the new state
                holder.fav.setChecked(false);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("checkbox_" + product.getId(), false);
                editor.apply();
            }
        }

        return view;
    }





    private static class ViewHolder {
        View itemView;
        ImageView item_image;
        TextView item_name, o_price, d_price;
        CheckBox fav;

    }

}