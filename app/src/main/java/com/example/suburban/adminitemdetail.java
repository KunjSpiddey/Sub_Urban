package com.example.suburban;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class adminitemdetail extends AppCompatActivity {



    ImageView img;
    TextView brand1 , title1 , oprice1 , dprice1 , color1 , size1 , contains1 , desc1 , Return1 , category1 , producttype1 ,dc , Color;

    Button accept , decline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminitemdetail);

        img = findViewById(R.id.detailsdetail_image);
        brand1 = findViewById(R.id.detailsbrand);
        title1 = findViewById(R.id.detailstittle);
        oprice1 = findViewById(R.id.detailso_price);
        dprice1 = findViewById(R.id.detailsd_price);
        color1 = findViewById(R.id.detailsprd_color);
        size1 = findViewById(R.id.detailssize);
        contains1 = findViewById(R.id.detailscontains);
        desc1 = findViewById(R.id.detailsproduct_desc);
        Return1 = findViewById(R.id.detailsReturn);
        category1 = findViewById(R.id.detailsprd_category);
        producttype1 = findViewById(R.id.detailsprd_type);
        dc = findViewById(R.id.detailsprd_dc);
        Color = findViewById(R.id.detailscolor);
        accept = findViewById(R.id.detailsaccept);
        decline = findViewById(R.id.detailsdecline);

        Intent args = getIntent();
        String name = args.getStringExtra("name");
        String image_uri = args.getStringExtra("image_uri");
        String brand = args.getStringExtra("brand");
        String color = args.getStringExtra("color");
        String contains = args.getStringExtra("contains");
        String id = args.getStringExtra("id");
        String product_category = args.getStringExtra("product_category");
        String deliverycharge = args.getStringExtra("deliverycharge");
        String desc = args.getStringExtra("desc");
        String dprice = args.getStringExtra("dprice");
        String oprice = args.getStringExtra("oprice");
        String qnty = args.getStringExtra("qnty");
        String size = args.getStringExtra("size");
        String productType = args.getStringExtra("productType");
        String Return = args.getStringExtra("Return");


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


// public addedProducts(String Id ,String Image_uri, String productName, String productDescription, String productQuantity, String productOriginalPrice, String productDeliveryCharge, String productSize, String productDiscountPrice
//                , String productCategory, String productType, String brand , String color , String contains , String Return) {

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get a reference to the Firebase Realtime Database


                // Get the ID of the product
                String productId = id;

                // Create a new product object with the data from admin_products
                addedProducts product = new addedProducts(id , image_uri , name , desc , qnty , oprice ,  deliverycharge , size , dprice , product_category , productType , brand , color , contains , Return);

                // Set the value of the new product object to the products table with the product ID as the key
                databaseRef.child("products").child(productId).setValue(product);

                // Remove the product from the admin_products table
                databaseRef.child("admin_products").child(productId).removeValue();

                // Finish the activity
                finish();
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseRef.child("admin_products").child(id).removeValue();
            }
        });



    }
}