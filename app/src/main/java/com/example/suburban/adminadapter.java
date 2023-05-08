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
import java.util.List;

public class adminadapter extends BaseAdapter {

    Context context;

    private List<Fav_item> fav_items;
    private ArrayList<addedProducts> dataList;
    LayoutInflater layoutInflater;

    public adminadapter(Context context, ArrayList<addedProducts> dataList, List<Fav_item> fav_items) {
        this.context = context;
        this.dataList = dataList;
        this.fav_items = fav_items;
        layoutInflater = LayoutInflater.from(context);
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
        View view1 = layoutInflater.inflate(R.layout.products, null);

        addedProducts product = dataList.get(i);

        ImageView img = view1.findViewById(R.id.image);
        TextView name = view1.findViewById(R.id.pname);
        TextView o_price = view1.findViewById(R.id.oprice);
        TextView d_price = view1.findViewById(R.id.dprice);

        Glide.with(context).load(product.getImage_uri()).into(img);
        name.setText(product.getProductName());
        o_price.setText("₹" + product.getProductOriginalPrice());
        d_price.setText("₹" + product.getProductDiscountPrice());

        return view1;
    }


}
