package com.example.suburban;

import static android.view.View.VISIBLE;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class FSeller_mobile_verification extends Fragment {
    FirebaseAuth mAuth;
    String otpid;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String number;

    public FSeller_mobile_verification() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_seller_mobile_verification, container, false);
        EditText mobile_number = view.findViewById(R.id.mobile_number);
        AppCompatButton SEND_OTP = view.findViewById(R.id.OTP);
        ImageView img = view.findViewById(R.id.verification_Image);
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        mAuth = FirebaseAuth.getInstance();



        Bundle bundle = new Bundle();
        bundle.putString("User_Number", number);
        SEND_OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mobile_number.getText().toString().isEmpty()) {
                    mobile_number.setError("Enter the Number");
                } else if (mobile_number.getText().toString().length() != 10) {
                    mobile_number.setError("Enter Valid Mobile Number");
                } else {

                    progressBar.setVisibility(VISIBLE);
                    SEND_OTP.setVisibility(View.GONE);

                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber("+91"+mobile_number.getText().toString())       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(requireActivity())                 // Activity (for callback binding)
                                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {

                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String otp,
                                                               @NonNull PhoneAuthProvider.ForceResendingToken token) {

                                            otpid = otp;
                                            number = mobile_number.getText().toString();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("OTP",otpid);
                                            bundle.putString("User_Number", number);
                                            FragmentManager fm = getParentFragmentManager();
                                            FragmentTransaction ft = fm.beginTransaction();
                                            OTPFragment otpFragment = new OTPFragment();
                                            otpFragment.setArguments(bundle);
                                            ft.replace(R.id.seller_container, otpFragment);
                                            ft.addToBackStack(null);
                                            ft.commit();

                                        }
                                    })
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
////////////////


///////////////////

                }

            }
        });


        return view;
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