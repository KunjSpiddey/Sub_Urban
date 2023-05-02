package com.example.suburban;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WishListAdapter extends BaseAdapter {

    private Context context;
    WislhListDataBase db;

    LayoutInflater layoutInflater;
    private ArrayList<addedProducts> dataList;
    private ArrayList<WishListItem> wishListItems;
    private boolean[] checkedStates; // add a boolean array to store the checkbox states

    public WishListAdapter(Context context, ArrayList<WishListItem> wishListItems) {
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
        try {
            return Long.parseLong(wishListItems.get(i).getId());
        } catch (NumberFormatException e) {
            // Handle the error gracefully (e.g., log the error, return a default value)
            return -1;
        }

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
        String img = wishListItem.getImg();

        Picasso.get().load(img).into(holder.pimg);
        holder.itemName.setText(wishListItem.getName());
        holder.oprice.setText(wishListItem.getOprice());
        holder.dprice.setText(wishListItem.getDprice());


//        Bitmap bitmap = downloadBitmap(img);
//        holder.pimg.setImageBitmap(bitmap);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = wishListItems.indexOf(wishListItem);
                if (index != -1) {
                    checkedStates[index] = false;
                }
                SharedPreferences prefs = context.getSharedPreferences("checkbox_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("checkbox_" + wishListItem.getId(), false);
                editor.apply();
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
        CheckBox checkBox;

        ViewHolder(View view) {
            pimg = view.findViewById(R.id.fav_img);
            itemName = view.findViewById(R.id.fav_pname);
            oprice = view.findViewById(R.id.fav_o_price);
            dprice = view.findViewById(R.id.fav_d_price);
            delete = view.findViewById(R.id.fav_delete);

        }
    }

}