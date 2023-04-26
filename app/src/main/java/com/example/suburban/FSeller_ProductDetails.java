package com.example.suburban;

import static androidx.transition.R.layout.support_simple_spinner_dropdown_item;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FSeller_ProductDetails extends Fragment {

    private String Selected_Category;
    ArrayAdapter<CharSequence> Adapter_Product_type;
    private ProgressDialog progressDialog;
    private Uri imageUri;

    ProgressBar progressBar;
    ImageView img;
    EditText pname , desc , discount_price , size , quantity , d_charge , original_price;
    Spinner category , p_type;
    AppCompatButton add_product;

    public FSeller_ProductDetails() {

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


     View view = inflater.inflate(R.layout.fragment_f_seller__product_details, container, false);
    img = view.findViewById(R.id.uploadImage);
    pname = view.findViewById(R.id.seller_pname);
     desc = view.findViewById(R.id.description);
     progressBar = view.findViewById(R.id.progressBar);
     size= view.findViewById(R.id.size);
     discount_price = view.findViewById(R.id.discount_price);
     original_price = view.findViewById(R.id.original_price);
     quantity = view.findViewById(R.id.quantity);
     d_charge = view.findViewById(R.id.delivery_chrg);
     category = view.findViewById(R.id.category);
     p_type = (Spinner)view.findViewById(R.id.p_type);
     add_product = view.findViewById(R.id.Add_product);
     progressDialog = new ProgressDialog(getContext());
     progressDialog.setTitle("Please Wait");
     progressDialog.setCanceledOnTouchOutside(false);





        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            imageUri = data.getData();
                            img.setImageURI(imageUri);
                        } else {
                            Toast.makeText(getContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });





        String [] Spinner_category = getResources().getStringArray(R.array.catagories);
        String [] Spinner_Cloths = getResources().getStringArray(R.array.product_type_clothes);
        String [] Spinner_Accessories = getResources().getStringArray(R.array.product_type_accessories);

        ArrayAdapter<CharSequence> Adapter_Category = new ArrayAdapter(getContext(), support_simple_spinner_dropdown_item,Spinner_category);
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

//                    Adapter_Product_type.setDropDownViewResource(support_simple_spinner_dropdown_item);
//                   Adapter_Product_type.setDropDownViewResource(androidx.transition.R.layout.support_simple_spinner_dropdown_item);
                   p_type.setAdapter(Adapter_Product_type);///////
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    add_product.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (imageUri != null){
                ADDPRODUCT(imageUri);
            } else  {
                Toast.makeText(getContext(), "Please select image", Toast.LENGTH_SHORT).show();
            }

        }
    });


        return view;
    }



    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = requireActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }

    private String Image_uri ,productTitle , productDescription , productQuantity , productOriginalPrice , productDeliveryCharge , productSize , productDiscountPrice , productCategory, productType ;

    private void ADDPRODUCT(Uri uri) {

        productTitle = pname.getText().toString().trim();
        productDescription = desc.getText().toString().trim();
        productQuantity = quantity.getText().toString().trim();
        productOriginalPrice = original_price.getText().toString().trim();
        productDeliveryCharge = d_charge.getText().toString().trim();
        productSize = size.getText().toString().trim();
        productDiscountPrice = discount_price.getText().toString().trim();
        productCategory = category.getSelectedItem().toString();
        productType = p_type.getSelectedItem().toString();

        if (productTitle.isEmpty() || productDescription.isEmpty() || productQuantity.isEmpty() || productOriginalPrice.isEmpty() || productDeliveryCharge.isEmpty() || productSize.isEmpty() || productDiscountPrice.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Adding product...");
        progressDialog.show();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("product_images");
        final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileReference.putFile(uri)
                .addOnSuccessListener(taskSnapshot -> {
                    fileReference.getDownloadUrl().addOnSuccessListener(uri1 -> {
                        Image_uri = uri1.toString();

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");
                        String Id = databaseReference.push().getKey();
                        addedProducts product = new addedProducts(Id, Image_uri, productTitle, productDescription, productQuantity, productOriginalPrice, productDeliveryCharge, productSize, productDiscountPrice, productCategory, productType);
                        databaseReference.child(Id).setValue(product)
                                .addOnSuccessListener(aVoid -> {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Product added successfully", Toast.LENGTH_SHORT).show();
                                    clearFields();
                                })
                                .addOnFailureListener(e -> {
                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Error adding product", Toast.LENGTH_SHORT).show();
                                });
                    });
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Error uploading image", Toast.LENGTH_SHORT).show();
                })
                .addOnProgressListener(taskSnapshot -> {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploading image: " + (int) progress + "%");
                });

    }

    private void clearFields() {
        pname.setText("");
        desc.setText("");
        quantity.setText("");
        original_price.setText("");
        d_charge.setText("");
        size.setText("");
        discount_price.setText("");
        category.setSelection(0);
        p_type.setSelection(0);
        img.setImageResource(R.drawable.seller);
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