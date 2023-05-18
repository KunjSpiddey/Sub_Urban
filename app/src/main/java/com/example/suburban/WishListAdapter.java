package com.example.suburban;
// WishListAdapter.java

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WishListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<addedProducts> dataList;
    private ArrayList<WishListItem> wishListItems;
    WislhListDataBase db ;


    String name , imageuri , brand , oprice ,size , dprice;
    private SharedPreferences.Editor editor;

    public void setAddedProductsList(ArrayList<addedProducts> addedProductsList) {
        this.dataList = addedProductsList;
        notifyDataSetChanged();
    }

    public WishListAdapter(Context context, ArrayList<WishListItem> wishListItems) {
        this.context = context;
        this.wishListItems = wishListItems;
        this.editor = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit(); // Initialize the editor variable
    }

    public WishListAdapter(Context context) {
        this.context = context;
    }

    public void setData(addedProducts product) {
        dataList.clear(); // Clear the existing data
        if (product != null) {
            dataList.add(product); // Add the new product
        }
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }



    public void setDataList(ArrayList<addedProducts> dataList) {
        if (dataList != null) {
            this.dataList = dataList;
        } else {
            this.dataList = new ArrayList<>(); // Create a new empty list
        }
        notifyDataSetChanged();
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
        final  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
        Query query = databaseReference.orderByChild("id").equalTo(wishListItem.getId());

        Picasso.get().load(img).into(holder.pimg);
        holder.itemName.setText(wishListItem.getName());
        holder.oprice.setText(wishListItem.getOprice());
        holder.dprice.setText(wishListItem.getDprice());

        /////

        /////




        String buttonText = retrieveButtonText(wishListItem.getId());
        holder.addtocart.setText(buttonText);

        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.addtocart.getText().toString().equals("Add To Cart")) {
                    // Get the selected product from wishListItems
                    WishListItem selectedProduct = wishListItems.get(i);
                    String productId = selectedProduct.getId();

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
                    Query query = databaseReference.orderByChild("id").equalTo(productId);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                DataSnapshot productSnapshot = snapshot.getChildren().iterator().next();
                                addedProducts product = productSnapshot.getValue(addedProducts.class);
                                if (product != null) {
                                    // Create an instance of the local database
                                    AddToCartDatabase db = new AddToCartDatabase(context);
                                    // Convert addedProducts to addtoproductsITEM
                                    addtoproductsITEM convertedProduct = new addtoproductsITEM();
                                    convertedProduct.setId(product.getId());
                                    convertedProduct.setName(product.getProductName());
                                    convertedProduct.setOprice(product.getProductOriginalPrice());
                                    convertedProduct.setDprice(product.getProductDiscountPrice());
                                    convertedProduct.setSize(product.getProductSize());
                                    convertedProduct.setBrand(product.getBrand());
                                    convertedProduct.setImage_uri(product.getImage_uri());
                                    // Insert the converted product into the local database
                                    db.insertData(convertedProduct);
                                    // Update the button text and save it in SharedPreferences
                                    holder.addtocart.setText("Go to Cart");
                                    saveButtonText(productId, "Go to Cart");

                                    // Call the modified fl method
                                    fl(((AppCompatActivity) context).getSupportFragmentManager(), new Cart_Fragment(), 1);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (holder.addtocart.getText().toString().equals("Go to Cart")) {
                    // Call the modified fl method
                    fl(((AppCompatActivity) context).getSupportFragmentManager(), new Cart_Fragment(), 1);
                }
            }
        });



        db = new WislhListDataBase(context);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = wishListItems.indexOf(wishListItem);
                    db.deleteData(wishListItem.getId());
                if (index != -1) {
                    wishListItems.remove(index);
                    notifyDataSetChanged();
                }

                // Remove the checkbox state from SharedPreferences
                SharedPreferences.Editor editor = context.getSharedPreferences("checkbox_prefs", Context.MODE_PRIVATE).edit();
                editor.remove("checkbox_" + wishListItem.getId());
                editor.apply();
            }
        });







        return convertView;
    }

    private String retrieveButtonText(String id) {
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return prefs.getString("buttonText_" + id, "Add to Bag");
    }
    private void saveButtonText(String id, String text) {
        SharedPreferences.Editor editor = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit();
        editor.putString("buttonText_" + id, text);
        editor.apply();
    }

    static class ViewHolder {
        ImageView pimg;
        TextView itemName;
        TextView oprice;
        AppCompatButton addtocart;
        TextView dprice;
        ImageButton delete;

        ViewHolder(View view) {
            pimg = view.findViewById(R.id.fav_img);
            itemName = view.findViewById(R.id.fav_pname);
            oprice = view.findViewById(R.id.fav_o_price);
            dprice = view.findViewById(R.id.fav_d_price);
            delete = view.findViewById(R.id.fav_delete);
            addtocart = view.findViewById(R.id.fav_add_to_cart);
        }
    }
    private void fl(FragmentManager fragmentManager, Fragment fragment, int flag) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, fragment);
        if (flag == 1) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }


}
