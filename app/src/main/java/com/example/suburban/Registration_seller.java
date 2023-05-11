package com.example.suburban;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Registration_seller extends Fragment {

    AppCompatButton reg;
    EditText mail , password , confirm;

    String MAIL , PASSWORD , CONFIRM;

    private ProgressDialog progressDialog;
    public Registration_seller() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_registration_seller, container, false);
        reg = view.findViewById(R.id.registeration_button);
        mail  = view.findViewById(R.id.registration_mail);
        password  = view.findViewById(R.id.registration_password);
        confirm  = view.findViewById(R.id.confirm_password);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(mail.getText().toString().trim())){
                    mail.setError("Mail is required");
                } else if (TextUtils.isEmpty(password.getText().toString().trim())) {
                    mail.setError("Password is required");
                } else if (!password.getText().toString().trim().equals(confirm.getText().toString().trim())) {
                    confirm.setError("Password do not matched");
                }
                else {
                    MAIL = mail.getText().toString().trim();
                    PASSWORD = password.getText().toString().trim();
                    checkEmailExists();
                }
            }
        });

        return view;
    }

    private void checkEmailExists() {
        progressDialog.show();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("seller_login")
                .whereEqualTo("Mail", MAIL)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    progressDialog.dismiss();

                    if (!querySnapshot.isEmpty()) {
                        // Email already exists
                        Toast.makeText(getContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        // Email doesn't exist, insert data
                        insertData();
                    }
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Error occurred", Toast.LENGTH_SHORT).show();
                });
    }

    private void insertData() {
        progressDialog.show();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> data = new HashMap<>();
        data.put("Mail", MAIL);
        data.put("Password", PASSWORD);

        db.collection("seller_login")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    if (documentReference != null) {
                        fl(new FSeller_Seller_info(), 1);
                    }
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                });
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