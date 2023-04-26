package com.example.suburban;


import android.content.Context;
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

    private ArrayList<addedProducts> dataList;

    private List<Fav_item> fav_items;
    
    private Context context;
    LayoutInflater layoutInflater;
    WislhListDataBase db;


    public MyAdapter(Context context, ArrayList<addedProducts> dataList, List<Fav_item> fav_items) {
        this.context = context;
        this.dataList = dataList;
        db = new WislhListDataBase(context);
        layoutInflater = LayoutInflater.from(context);
        this.fav_items = new ArrayList<>();
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
        ViewHolder holder = new ViewHolder();

        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = layoutInflater.inflate(R.layout.grid_item, null);
        }

      holder.item_image = view.findViewById(R.id.gridImage);
        holder.item_name = view.findViewById(R.id.Product_Name);
holder.o_price = view.findViewById(R.id.original_price);
        holder.d_price = view.findViewById(R.id.discount_price);
        holder.fav = view.findViewById(R.id.fav_check);
        view.setTag(holder);
        Glide.with(context).load(dataList.get(i).getImage_uri()).into(holder.item_image);
        holder.item_name.setText(dataList.get(i).getProductName());
        holder.o_price.setText("₹"+dataList.get(i).getProductOriginalPrice());
        holder.d_price.setText("₹"+dataList.get(i).getProductDiscountPrice());

        holder.fav.setTag(i);
        holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int position = (int) compoundButton.getTag();
                addedProducts product = dataList.get(position);
                Fav_item favItem = new Fav_item(product.getId(), product.getProductName(), product.getProductOriginalPrice(), product.getProductDiscountPrice(), product.getImage_uri());
                fav_items.add(favItem);
                    if (holder.fav.isChecked()){
                        db.insertData(
                                favItem.getId(),
                                favItem.getName(),
                                favItem.getOprice(),
                                favItem.getDprice(),
                                favItem.getImage_uri()
                        );
                }
                else {
                    db.deleteData(favItem.getId());
                }
            }
        });



        return view;
    }

    private static class ViewHolder {
        ImageView item_image;
        TextView item_name, o_price, d_price;
        CheckBox fav;

    }

}