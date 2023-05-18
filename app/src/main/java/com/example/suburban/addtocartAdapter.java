package com.example.suburban;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class addtocartAdapter extends RecyclerView.Adapter<addtocartAdapter.MyViewHolder> {

    private List<addtoproductsITEM> itemList;
    private Context context;
    AddToCartDatabase db;
    private Cart_Fragment cartFragment;

    private SharedPreferences sharedPreferences;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, oprice, dprice, size, brand, minus, remove;
        public RoundedImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.brand);
            name = itemView.findViewById(R.id.name);
            size = itemView.findViewById(R.id.size);
            dprice = itemView.findViewById(R.id.dprice);
            oprice = itemView.findViewById(R.id.oprice);
            img = itemView.findViewById(R.id.cart_image);
            minus = itemView.findViewById(R.id.minus);
            remove = itemView.findViewById(R.id.remove);
        }
    }

    public addtocartAdapter(List<addtoproductsITEM> itemList, Context context, SharedPreferences sharedPreferences, Cart_Fragment cartFragment) {
        this.itemList = itemList;
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        this.cartFragment = cartFragment;
    }


    @NonNull
    @Override
    public addtocartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addtocartAdapter.MyViewHolder holder, int position) {
        addtoproductsITEM item = itemList.get(position);
        holder.brand.setText(item.getBrand());
        holder.name.setText(item.getName());
        holder.size.setText(item.getSize());
        double originalPrice = Double.parseDouble(item.getOprice());
        double discountPrice = Double.parseDouble(item.getDprice());
        holder.oprice.setText(String.format("₹. %.2f", originalPrice));
        holder.dprice.setText(String.format("₹. %.2f", discountPrice));
        Glide.with(context).load(item.getImage_uri()).into(holder.img);

        int oprice1 = Integer.parseInt(item.getOprice());
        int dprice1 = Integer.parseInt(item.getDprice());
        Double min = originalPrice + discountPrice;
        holder.minus.setText(String.format("₹. %.2f", min));

        db = new AddToCartDatabase(context);
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the item from the database
                db.deleteData(item.getId());
                itemList.remove(item);
                notifyDataSetChanged();

                // Update the button text in SharedPreferences
                saveButtonText(item.getId(), "Add to Bag");
                cartFragment.removeItemFromCart(item);
            }
        });

    }

    private void saveButtonText(String itemId, String buttonText) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("buttonText_" + itemId, buttonText);
        editor.apply();
    }

    private String retrieveButtonText(String itemId) {
        return sharedPreferences.getString("buttonText_" + itemId, "Add to Bag");
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
