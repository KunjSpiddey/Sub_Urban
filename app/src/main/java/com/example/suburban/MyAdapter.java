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
    private boolean deleteClicked;

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

    public void setDeleteClicked(boolean deleteClicked) {
        this.deleteClicked = deleteClicked;
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
        double originalPrice = Double.parseDouble(product.getProductOriginalPrice());
        double discountPrice = Double.parseDouble(product.getProductDiscountPrice());
        holder.o_price.setText(String.format("₹ %.2f", originalPrice));
        holder.d_price.setText(String.format("₹ %.2f", discountPrice));

        boolean isChecked = false;


        holder.fav.setChecked(isChecked);

        holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("checkbox_" + product.getId(), b);
                editor.apply();

                if (b) {
                    // Add the item to the wishlist database
                    WishListItem item = new WishListItem(product.getId(), product.getProductName(), product.getImage_uri(), product.getProductOriginalPrice(), product.getProductDiscountPrice());
                    db.insertData(item);
                } else {
                    // Remove the item from the wishlist database
                    db.deleteData(product.getId());
                }
            }
        });

        if (deleteClicked) {
            holder.fav.setChecked(false);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("checkbox_" + product.getId(), false);
            editor.apply();
        }

        return view;
    }

    public void setDataList(ArrayList<addedProducts> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    private static class ViewHolder {
        View itemView;
        ImageView item_image;
        TextView item_name;
        TextView o_price;
        TextView d_price;
        CheckBox fav;
    }
}
