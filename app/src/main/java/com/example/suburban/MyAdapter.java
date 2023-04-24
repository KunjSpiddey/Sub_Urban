package com.example.suburban;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<addedProducts> dataList;
    private Context context;
    LayoutInflater layoutInflater;

    public MyAdapter(Context context, ArrayList<addedProducts> dataList) {
        this.context = context;
        this.dataList = dataList;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = layoutInflater.inflate(R.layout.grid_item, null);
        }

        ImageView gridImage = view.findViewById(R.id.gridImage);
        TextView ProductName = view.findViewById(R.id.Product_Name);
        TextView Original_price = view.findViewById(R.id.original_price);
        TextView Discount_price = view.findViewById(R.id.discount_price);



        Glide.with(context).load(dataList.get(i).getImage_uri()).into(gridImage);
        ProductName.setText(dataList.get(i).getProductName());
        Original_price.setText("₹"+dataList.get(i).getProductOriginalPrice());
        Discount_price.setText("₹"+dataList.get(i).getProductDiscountPrice());

        return view;
    }
}