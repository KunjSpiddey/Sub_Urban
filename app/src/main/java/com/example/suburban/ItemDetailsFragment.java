package com.example.suburban;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItemDetailsFragment extends Fragment implements PaymentResultListener {

    private  String IDD;
    private ImageView img;
private TextView brand1 , title1 , oprice1 , dprice1 , off1 , color1 , size1 , contains1 , desc1 , Return1 , category1 , producttype1 ,dc , Color;
String email;

String IDDDDDD;
AppCompatButton add_to_cart , buy_now;
    private Context context;

    AddToCartDatabase adb;
    SharedPreferences sharedPrefs;

WislhListDataBase db;

    private ArrayList<addedProducts> dataList;

    public ItemDetailsFragment() {
        // Required empty public constructor
    }

    public void setDataList(ArrayList<addedProducts> dataList) {
        this.dataList = dataList;
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
        sharedPrefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
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
        IDDDDDD = id;
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
        double Dprice = Double.parseDouble(dprice);
        double Oprice = Double.parseDouble(oprice);
        dprice1.setText(String.format("₹ %.2f",Dprice));
        oprice1.setText(String.format("₹ %.2f",Oprice));
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
                String numericAmount = samount.replaceAll("[^\\d.]", ""); // Remove non-numeric characters
                float parsedAmount = Float.parseFloat(numericAmount);
                int amount = Math.round(parsedAmount * 100);


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
        addtoproductsITEM item = new addtoproductsITEM(id, name, brand, dprice, oprice, image_uri, size);
        IDD = item.getId();
    String buttonText = retrieveButtonText(IDD);
    add_to_cart.setText(buttonText);

//       String ID = "";
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (add_to_cart.getText().toString().equals("Add to Bag")) {
                    // Add the item to the database
                    adb = new AddToCartDatabase(requireContext());
                    adb.insertData(item);
                    // Change the button text to "Go to Cart"
                    add_to_cart.setText("Go to Cart");
                    // Update the shared preference to reflect the change in button text
                    saveButtonText(IDD,"Go to Cart");
                } else if (add_to_cart.getText().toString().equals("Go to Cart")) {
                    // Open the Cart_Fragment
                    Fragment cartFragment = new Cart_Fragment();
                    fl(cartFragment, 1);
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

    private void saveButtonText(String id, String text) {
        SharedPreferences.Editor editor = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit();
        editor.putString("buttonText_" + id, text);
        editor.apply();
    }


    private String retrieveButtonText(String id) {
        SharedPreferences prefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        return prefs.getString("buttonText_" + id, "Add to Bag");
    }



    private void fl(Fragment fragment, int flag) {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, fragment);
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }



    public void seller (){
        String id = IDDDDDD;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sellerId = snapshot.child("randomKey").getValue(String.class);
                dashboard_seller dashboard_seller = new dashboard_seller(id , sellerId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        // get the item id
        //  get the seller id
        // with the help of seller id we will send the data to the seller dashboard with our adapter

    }





}