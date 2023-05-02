package com.example.suburban;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class ItemDetailsFragment extends Fragment {

private ImageView img;
private TextView brand1 , title1 , oprice1 , dprice1 , off1 , color1 , size1 , contains1 , desc1 , Return1 , category1 , producttype1 ,dc , Color;
private ImageButton like,share;

WislhListDataBase db;
AppCompatButton bag;
    public ItemDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new WislhListDataBase(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        img = view.findViewById(R.id.detail_image);
        brand1 = view.findViewById(R.id.brand);
        title1 = view.findViewById(R.id.tittle);
        oprice1 = view.findViewById(R.id.o_price);
        dprice1 = view.findViewById(R.id.d_price);
        off1 = view.findViewById(R.id.off);
        color1 = view.findViewById(R.id.prd_color);
        size1 = view.findViewById(R.id.size);
        contains1 = view.findViewById(R.id.contains);
        desc1 = view.findViewById(R.id.product_desc);
        Return1 = view.findViewById(R.id.Return);
        category1 = view.findViewById(R.id.prd_category);
        producttype1 = view.findViewById(R.id.prd_type);
        dc = view.findViewById(R.id.prd_dc);
        like = view.findViewById(R.id.like);
        share = view.findViewById(R.id.share);
        bag = view.findViewById(R.id.add_to_bag);
        Color = view.findViewById(R.id.color);


        Bundle args = getArguments();
            String name = args.getString("name");
            String image_uri = args.getString("image_uri");
            String brand = args.getString("brand");
            String color = args.getString("color");
            String contains = args.getString("contains");
            String id = args.getString("id");
            String product_category = args.getString("product_category");
            String deliverycharge = args.getString("deliverycharge");
            String desc = args.getString("desc");
            String dprice = args.getString("dprice");
            String oprice = args.getString("oprice");
            String qnty = args.getString("qnty");
            String size = args.getString("size");
            String productType = args.getString("productType");
            String Return = args.getString("Return");

            Picasso.get().load(image_uri).into(img);
            brand1.setText(brand);
            title1.setText(name);
            color1.setText(color);
            contains1.setText(contains);
            category1.setText("Category - " + product_category);
            dc.setText("Delivery Charge - " + deliverycharge);
            desc1.setText(desc);
            dprice1.setText(dprice);
            oprice1.setText(oprice);
            size1.setText(size);
            producttype1.setText("Product Type - "+productType);
            Return1.setText(Return);
            Color.setText("Color - "+color);


        like.setOnClickListener(new View.OnClickListener() {
            boolean isLiked = false;
            @Override
            public void onClick(View view) {
                if (!isLiked) {
                    Toast.makeText(getContext(), "Added To WishLIst", Toast.LENGTH_SHORT).show();
                    like.setImageResource(R.drawable.favorite_red);
                    WishListItem wishListItem = new WishListItem(id,name,image_uri,oprice,dprice);
                    db.insertData(wishListItem);
                    isLiked = true;
                } else {
                    Toast.makeText(getContext(), "Removed from WishList", Toast.LENGTH_SHORT).show();
                    like.setImageResource(R.drawable.favorite_details);
                    isLiked = false;
                }
            }
        });


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Link Shared", Toast.LENGTH_SHORT).show();
            }
        });

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Added to Bag", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }
}