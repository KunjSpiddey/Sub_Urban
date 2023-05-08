package com.example.suburban;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemDetailsFragment extends Fragment implements PaymentResultListener {

private ImageView img;
private TextView brand1 , title1 , oprice1 , dprice1 , off1 , color1 , size1 , contains1 , desc1 , Return1 , category1 , producttype1 ,dc , Color;
String email;
AppCompatButton add_to_cart , buy_now;

WislhListDataBase db;

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

        add_to_cart = view.findViewById(R.id.add_to_bag);
        buy_now = view.findViewById(R.id.buy_now);
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
        Color = view.findViewById(R.id.color);

        Checkout checkout = new Checkout();
        Checkout.preload(getContext());
        Checkout.sdkCheckIntegration(getActivity());

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
        producttype1.setText("Product Type - " + productType);
        Return1.setText(Return);
        Color.setText("Color - " + color);

        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
             email = user.getEmail();
        }
        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are getting
                // amount that is entered by user.
                String samount = dprice1.getText().toString();

                // rounding off the amount.
                int amount = Math.round(Float.parseFloat(samount) * 100);

                // initialize Razorpay account.
                Checkout checkout = new Checkout();

                // set your id as below
                checkout.setKeyID("rzp_test_ATdCLj1YsJoL8T");

                // set image
                checkout.setImage(R.drawable.subbb);

                // initialize json object
                try {
                JSONObject object = new JSONObject();
                    // to put name
                    object.put("name", title1.getText().toString());

                    // put description
                    object.put("description", "Payment");

                    // to set theme color
                    object.put("theme.color", "#25383C");

                    // put the currency
                    object.put("currency", "INR");

                    // put amount
                    object.put("amount", amount);

                    // put mobile number
                    object.put("prefill.contact", "");

                    // put email
                    object.put("prefill.email", email);

                    // open razorpay to checkout activity
                    checkout.open((Activity) getContext(), object);
                } catch (JSONException e) {
                    Log.e(TAG,"Error",e);
                }
            }
        });




        return view;
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getContext(), "Successs"+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getContext(), "Failed"+s, Toast.LENGTH_SHORT).show();
    }
}