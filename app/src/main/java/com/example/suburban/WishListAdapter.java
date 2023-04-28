package com.example.suburban;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WishListAdapter extends BaseAdapter {

    private Context context;
    WislhListDataBase db;
    MyAdapter adapter = new MyAdapter();
    LayoutInflater layoutInflater;
    private List<WishListItem> wishListItems;
    private boolean[] checkedStates; // add a boolean array to store the checkbox states

    public WishListAdapter(Context context, List<WishListItem> wishListItems) {
        this.context = context;
        this.wishListItems = wishListItems;
        db = new WislhListDataBase(context);
        layoutInflater = LayoutInflater.from(context);
        checkedStates = new boolean[wishListItems.size()];
        SharedPreferences prefs = context.getSharedPreferences("checkbox_prefs", Context.MODE_PRIVATE);
        for (int i = 0; i < wishListItems.size(); i++) {
            boolean isChecked = prefs.getBoolean("checkbox_" + wishListItems.get(i).getId(), false);
            checkedStates[i] = isChecked;
        }
    }



    @Override
    public int getCount() {
        return wishListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return wishListItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(wishListItems.get(i).getId());
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.wishlist_item_layout, viewGroup, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WishListItem wishListItem = wishListItems.get(i);
        holder.itemName.setText(wishListItem.getName());
        holder.oprice.setText(wishListItem.getOprice());
        holder.dprice.setText(wishListItem.getDprice());
        Glide.with(context).load(wishListItem.getImg()).into(holder.pimg);

        holder.delete.setTag(wishListItem);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = context.getSharedPreferences("checkbox_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("checkbox_" + wishListItem.getId(), false);
                editor.apply();
                adapter.sting(wishListItem.getId());
                db.deleteData(wishListItem.getId());
                wishListItems.remove(wishListItem);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView pimg;
        TextView itemName;
        TextView oprice;
        TextView dprice;
        ImageButton delete;

        ViewHolder(View view) {
            pimg = view.findViewById(R.id.fav_img);
            itemName = view.findViewById(R.id.fav_pname);
            oprice = view.findViewById(R.id.fav_o_price);
            dprice = view.findViewById(R.id.fav_d_price);
            delete = view.findViewById(R.id.fav_delete);
        }
    }

}