package com.example.suburban;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OTPFragment extends Fragment {

    String otp;
    EditText ed1;
    EditText ed2;
    EditText ed3;
    EditText ed4;
    EditText ed5;
    EditText ed6;

    public OTPFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_o_t_p, container, false);

        TextView tv = view.findViewById(R.id.mobile_veri_next);
        TextView user_number = view.findViewById(R.id.user_mobile_number);
        ImageView imageView = view.findViewById(R.id.imageView);
        ed1 = view.findViewById(R.id.ed1);
        ed2 = view.findViewById(R.id.ed2);
        ed3 = view.findViewById(R.id.ed3);
        ed4 = view.findViewById(R.id.ed4);
        ed5 = view.findViewById(R.id.ed5);
        ed6 = view.findViewById(R.id.ed6);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        Bundle bundle = getArguments();
        String User_number = bundle.getString("User_Number");
        String User_otp = bundle.getString("OTP");


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!ed1.getText().toString().isEmpty() && !ed2.getText().toString().isEmpty() && !ed3.getText().toString().isEmpty() && !ed4.getText().toString().isEmpty() && !ed5.getText().toString().isEmpty() && !ed6.getText().toString().isEmpty()) {


                    String entered_otp = ed1.getText().toString()
                            + ed2.getText().toString()
                            + ed3.getText().toString()
                            + ed4.getText().toString()
                            + ed5.getText().toString()
                            + ed6.getText().toString();

                    if (User_otp != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                        tv.setVisibility(View.GONE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                User_otp, entered_otp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        imageView.setVisibility(View.VISIBLE);
                                        tv.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            fl(new FSeller_ProductDetails(), 1);
                                        } else {
                                            Toast.makeText(getContext(), "Enter the correct OTP ", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });


                    } else {
                        Toast.makeText(getContext(), "Error While generating the OTP", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please fill all the boxes", Toast.LENGTH_SHORT).show();
                }
                fl(new FSeller_Seller_info(), 1);
            }
        });

        user_number.setText(User_number);


        Next_box();
        return view;
    }

    private void Next_box() {
        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    ed1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    ed2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    ed3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    ed4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    ed5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    ed6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void fl(Fragment fragment, int flag) {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.seller_container, fragment);
        ft.replace(R.id.seller_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}