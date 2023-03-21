package com.example.suburban;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.text.CharSequenceKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class FSeller_ProductDetails extends Fragment {


    private static  final int camera_request_code=200;
    private static final int storage_request_code=100;
    private static final int image_picked_gallery=400;
    private static final int image_picked_camera_code=500;
    private String[]  camera_permission;
    private String[]  storage_permission;
    private Uri  imageuri;
    private String Selected_Category;
    ArrayAdapter<CharSequence> Adapter_Product_type;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    ImageView img;
    EditText pname , desc , price , size , quantity , d_charge , original_price;
    Spinner category , p_type;
    AppCompatButton add_product;

    public FSeller_ProductDetails() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.fragment_f_seller__product_details, container, false);
    img = view.findViewById(R.id.seller_image);
    pname = view.findViewById(R.id.seller_pname);
     desc = view.findViewById(R.id.description);
     size= view.findViewById(R.id.size);
     price = view.findViewById(R.id.price);
     original_price = view.findViewById(R.id.original_price);
     quantity = view.findViewById(R.id.quantity);
     d_charge = view.findViewById(R.id.delivery_chrg);
     category = view.findViewById(R.id.category);
     p_type = view.findViewById(R.id.p_type);
     add_product = view.findViewById(R.id.Add_product);


     firebaseAuth = FirebaseAuth.getInstance();
     progressDialog = new ProgressDialog(getContext());
     progressDialog.setTitle("Please Wait");
     progressDialog.setCanceledOnTouchOutside(false);



        String [] Spinner_category = getResources().getStringArray(R.array.catagories);
        String [] Spinner_Cloths = getResources().getStringArray(R.array.product_type_clothes);
        String [] Spinner_Accessories = getResources().getStringArray(R.array.product_type_accessories);

        ArrayAdapter<CharSequence> Adapter_Category = new ArrayAdapter(getContext(), androidx.transition.R.layout.support_simple_spinner_dropdown_item,Spinner_category);
        category.setAdapter(Adapter_Category);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selected_Category = category.getSelectedItem().toString();
                int parentid = adapterView.getId();
                if (parentid == R.id.category){
                    switch(Selected_Category){
                        case "Cloths" :
                            Adapter_Product_type = new ArrayAdapter<>(adapterView.getContext(), com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,Spinner_Cloths);
                            break;
                        case "Accessories":
                            Adapter_Product_type = new ArrayAdapter<>(adapterView.getContext(),com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item,Spinner_Accessories);
                            break;
                        default:break;
                    }

                   Adapter_Product_type.setDropDownViewResource(com.tbuonomo.viewpagerdotsindicator.R.layout.support_simple_spinner_dropdown_item);
//                 Adapter_Product_type.setDropDownViewResource(androidx.transition.R.layout.support_simple_spinner_dropdown_item);
                   p_type.setAdapter(Adapter_Product_type);///////
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    camera_permission=new String[]{
            Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    storage_permission=new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    img.setOnClickListener(view1 -> showimage());


    add_product.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            inputData();
        }
    });


        return view;
    }
    private String productTitle , productDescription , productQuantity , productOriginalPrice , productDeliveryCharge , productSize , productDiscountPrice , productCategory, productType ;
    private void inputData() {

        productTitle = pname.getText().toString().trim();
        productDescription = desc.getText().toString().trim();
        productQuantity = quantity.getText().toString().trim();
        productOriginalPrice = original_price.getText().toString().trim(); // aa main proce che product ni
        productDeliveryCharge = d_charge.getText().toString().trim();
        productSize = size.getText().toString().trim();
        productDiscountPrice = price.getText().toString().trim(); // ane a discount price che product ni --------> apde a price batavani che
        productCategory = category.getSelectedItem().toString().trim();
        productType = p_type.getSelectedItem().toString().trim();

            ADDPRODUCT();

    }

    private void ADDPRODUCT() {


        progressDialog.setMessage("Adding Product....");
        progressDialog.show();

        String timeStamp = ""+System.currentTimeMillis();
        if (imageuri == null){

        }
        else {
            String filePathAndName = "product_images/" + "" +timeStamp;

            StorageReference storageReference = FirebaseStorage.getInstance().getReference(filePathAndName);
            storageReference.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uriTask.isSuccessful());
                            Uri downloadImaheUri = uriTask.getResult();

                            if (uriTask.isSuccessful()){

                                HashMap<String , Object> hashMap = new HashMap<>();
                                hashMap.put("productId" , ""+timeStamp);
                                hashMap.put("productTitle" , ""+productTitle);
                                hashMap.put("productDescription" ,""+productDescription);
                                hashMap.put("productImage" ,""+downloadImaheUri);
                                hashMap.put("productDeliveryCharge" , ""+productDeliveryCharge);
                                hashMap.put("productSize" , ""+productSize);
                                hashMap.put("productType", ""+productType);
                                hashMap.put("productCategory" , ""+productCategory);
                                hashMap.put("productQuantity" , ""+productQuantity);
                                hashMap.put("productOriginalPrice" , ""+productOriginalPrice);
                                hashMap.put("productDiscountPrice" , ""+productDiscountPrice);
                                hashMap.put("timeStamp" , ""+timeStamp);
                                hashMap.put("uid" , ""+firebaseAuth.getUid());


                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                                databaseReference.child(firebaseAuth.getUid()).child("Products").child(timeStamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(), "Product Added", Toast.LENGTH_SHORT).show();
                                        clearData();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    }

    private void clearData() {

        pname.setText("");
        desc.setText("");
        price.setText("");
        size.setText("");
        quantity.setText("");
        d_charge.setText("");
        original_price.setText("");
        img.setImageResource(R.drawable.subbb);
        category.setSelected(false);
        p_type.setSelected(false);
        imageuri = null;

         }

    private void showimage() {


        String [] options = {
                "Camera" , "Gallery"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Pick Image")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            if (CheckCameraPermission()){
                                pickfromcamera();
                            }
                            else{
                                CameraPermission();
                            }

                        }
                        else{
                            if (cheackStoragePerrmmision()){
                                pickfromgallery();
                            }
                            else{
                                requestStragePermission();
                            }
                        }
                    }
                }).show();



    }


    private void pickfromgallery(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,image_picked_gallery);


    }
    private void pickfromcamera(){
        ContentValues contentValues=new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,"Temp_Image_title");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Temp_Image_Description");
        imageuri=getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
        startActivityForResult(intent , image_picked_camera_code);
    }


    private boolean cheackStoragePerrmmision() {

        boolean result = ContextCompat.checkSelfPermission(getContext() , Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                (PackageManager.PERMISSION_GRANTED);

        return result;

    }

    private void requestStragePermission () {
        ActivityCompat.requestPermissions((Activity) getContext(), storage_permission, storage_request_code);
    }

    private boolean CheckCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(getContext() , Manifest.permission.CAMERA) ==
                (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(getContext() , Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void CameraPermission () {

        ActivityCompat.requestPermissions((Activity) getContext(), camera_permission,camera_request_code);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch(requestCode){
            case camera_request_code:
                if (grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAcepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAcepted) {
                        pickfromcamera();

                    }
                    else{

                        Toast.makeText(getContext(), "Camera and Storage Permission are Required ", Toast.LENGTH_SHORT).show();
                    }
                }
            case storage_request_code:
                if (grantResults.length>0){

                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted){
                        pickfromgallery();
                    }

                    else{
                        Toast.makeText(getContext(), "Storage Permission are Required ", Toast.LENGTH_SHORT).show();
                    }


                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK){
            if (requestCode == image_picked_gallery){
                imageuri = data.getData();
                img.setImageURI(imageuri);
            }

            else if(requestCode == image_picked_camera_code){
                img.setImageURI(imageuri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    private void fl(Fragment fragment, int flag){


        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.seller_container,  fragment);
        ft.replace(R.id.seller_container , fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}