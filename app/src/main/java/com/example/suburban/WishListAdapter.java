package com.example.suburban;

import android.content.Context;
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
    LayoutInflater layoutInflater;
    private List<WishListItem> wishListItems;
    public WishListAdapter(Context context, List<WishListItem> wishListItems) {
        this.context = context;
        this.wishListItems = wishListItems;
        db = new WislhListDataBase(context);
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
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null){
            view = layoutInflater.inflate(R.layout.wishlist_item_layout, null);
        }

        ImageButton delete = view.findViewById(R.id.fav_delete);

        final WishListItem wishListItem = wishListItems.get(i);
        ImageView pimg = view.findViewById(R.id.fav_img);

        TextView itemName = view.findViewById(R.id.fav_pname);
        if (itemName != null) {
            itemName.setText(wishListItem.getName());
        }

        TextView oprice = view.findViewById(R.id.fav_o_price);
        if (oprice != null) {
            oprice.setText(wishListItem.getOprice());
        }
        TextView dprice = view.findViewById(R.id.fav_d_price);
        if (dprice != null) {
            dprice.setText(wishListItem.getDprice());
        }
        if (pimg != null) {

            Glide.with(context).load(wishListItems.get(i).getImg()).into(pimg);
        }
        delete.setTag(i);
        int position = (Integer) delete.getTag();
        WishListItem item = wishListItems.get(position);
        delete.getTag();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteData(item.getId());
                wishListItems.remove(item); // remove the deleted item from the list
                notifyDataSetChanged(); // update the adapter with the updated list
            }
        });



//        Glide.with(context).load(dataList.get(i).getImage_uri()).into(holder.item_image);


        return view;
    }
}
