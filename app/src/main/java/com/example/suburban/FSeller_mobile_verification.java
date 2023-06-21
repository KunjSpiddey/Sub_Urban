package com.example.suburban;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Locale;
public class FSeller_mobile_verification extends Fragment {

    TextView new_acc;
    EditText login, password;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    ProgressDialog progressDialog;

    public FSeller_mobile_verification() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_seller_mobile_verification, container, false);

        AppCompatButton Seller_login = view.findViewById(R.id.login_button);
        new_acc = view.findViewById(R.id.new_acc);
        login = view.findViewById(R.id.login_mail);
        password = view.findViewById(R.id.login_password);

        new_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl(new Registration_seller(), 1);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mAuth.setLanguageCode(Locale.getDefault().getLanguage());


        Seller_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(getActivity(), "Logging in", "Please wait...", true);
                String email = login.getText().toString().trim();
                String pass = password.getText().toString().trim();

                // Check if email and password are empty
                if (email.isEmpty()) {
                    login.setError("Email cannot be empty");
                    login.requestFocus();
                    return;
                }

                if (pass.isEmpty()) {
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                    return;
                }

                mFirestore.collection("seller_login")
                        .whereEqualTo("Mail", email)
                        .whereEqualTo("Password", pass)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    QuerySnapshot querySnapshot = task.getResult();
                                    if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getContext(), "Succesfully Logged In", Toast.LENGTH_SHORT).show();
                                        fl(new FSeller_ProductDetails(),1);
                                    } else {
                                        progressDialog.dismiss();
                                        // Invalid credentials, show error message
                                        Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    progressDialog.dismiss();
                                    // Query failed, show error message
                                    Toast.makeText(getActivity(), "Error: Failed to retrieve user data", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        return view;
    }

    private void fl(Fragment fragment, int flag) {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.seller_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
