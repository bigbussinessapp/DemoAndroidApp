package com.example.bigbusiness.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bigbusiness.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity {

    EditText inputMobile;
    Button buttonGetOtp;
    CountryCodePicker countryCodePicker;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth = mAuth.getInstance();
//        if(mAuth.getCurrentUser() != null)
//        {
//            database = FirebaseDatabase.getInstance();
//            String id = mAuth.getCurrentUser().getUid();
//            reference = database.getReference("Users").child(id);
//            reference.child("Profile").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(snapshot.exists())
//                    {
//                        UserDataService userDataService = UserDataService.getInstance();
//                        User loggedInUser = snapshot.getValue(User.class);
//                        userDataService.setLoggedInUser(loggedInUser);
//                        Intent i = new Intent(LoginActivity.this , Main1Activity.class);
//                        startActivity(i);
//                        finish();
//                    }
//                    else
//                    {
//                        Toast.makeText(LoginActivity.this, "User not found in database", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_send_otp);
//
//        inputMobile = findViewById(R.id.inputMobile);
//        buttonGetOtp = findViewById(R.id.buttonGetOtp);
//        countryCodePicker = findViewById(R.id.country_code_picker);
//
//        getSupportActionBar().hide();
//
//        final ProgressBar progressBar = findViewById(R.id.progressBar);
//
//        buttonGetOtp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(inputMobile.getText().toString().trim().isEmpty())
//                {
//                    Toast.makeText(LoginActivity.this , "Enter Mobile Number!" , Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                String enteredPhoneNumber = inputMobile.getText().toString().trim();
//
//                //Remove first zero if entered!
//                if (enteredPhoneNumber.charAt(0) == '0') {
//                    enteredPhoneNumber = enteredPhoneNumber.substring(1);
//                }
//
//                //Complete phone number
//                final String _phoneNo = "+" + countryCodePicker.getFullNumber() + enteredPhoneNumber;
//
//                progressBar.setVisibility(View.VISIBLE);
//                buttonGetOtp.setVisibility(View.INVISIBLE);
//
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        _phoneNo,
//                        60,
//                        TimeUnit.SECONDS,
//                        LoginActivity.this,
//                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
//
//                            @Override
//                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                progressBar.setVisibility(View.GONE);
//                                buttonGetOtp.setVisibility(View.VISIBLE);
//                            }
//
//                            @Override
//                            public void onVerificationFailed(@NonNull FirebaseException e) {
//                                progressBar.setVisibility(View.GONE);
//                                buttonGetOtp.setVisibility(View.VISIBLE);
//                                Toast.makeText(LoginActivity.this , e.getMessage() , Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                progressBar.setVisibility(View.GONE);
//                                buttonGetOtp.setVisibility(View.VISIBLE);
//                                Intent intent = new Intent(LoginActivity.this , VerifyOTPActivity.class);
//                                intent.putExtra("phoneNo" , _phoneNo);
//                                intent.putExtra("verificationId" , verificationId);
//                                startActivity(intent);
//                            }
//                        }
//                );
//
//            }
//        });
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login_activity);
        countryCodePicker = findViewById(R.id.country_code_picker);
        inputMobile = findViewById(R.id.inputMobile);
        buttonGetOtp = findViewById(R.id.getOtpBtn);

        buttonGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumber = inputMobile.getText().toString();
                if(mobileNumber.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Enter a mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(LoginActivity.this, VerifyOTPActivity.class);
                i.putExtra("mobileNumber", mobileNumber);
                startActivity(i);
                finish();
            }
        });
    }
}