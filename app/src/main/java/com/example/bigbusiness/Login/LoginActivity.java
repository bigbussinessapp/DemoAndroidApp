package com.example.bigbusiness.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bigbusiness.Main.Main1Activity;
import com.example.bigbusiness.Models.User;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText inputMobile;
    Button buttonGetOtp;
    CountryCodePicker countryCodePicker;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = mAuth.getInstance();
        if(mAuth.getCurrentUser() != null)
        {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("Users");
            String id = mAuth.getCurrentUser().getUid();
            reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        UserDataService userDataService = UserDataService.getInstance();
                        userDataService.setLoggedInUser(snapshot.getValue(User.class));
                        Intent i = new Intent(LoginActivity.this , Main1Activity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "User not found in database", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        inputMobile = findViewById(R.id.inputMobile);
        buttonGetOtp = findViewById(R.id.buttonGetOtp);
        countryCodePicker = findViewById(R.id.country_code_picker);

        getSupportActionBar().hide();

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        buttonGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputMobile.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(LoginActivity.this , "Enter Mobile Number!" , Toast.LENGTH_SHORT).show();
                    return;
                }
                String enteredPhoneNumber = inputMobile.getText().toString().trim();

                //Remove first zero if entered!
                if (enteredPhoneNumber.charAt(0) == '0') {
                    enteredPhoneNumber = enteredPhoneNumber.substring(1);
                }

                //Complete phone number
                final String _phoneNo = "+" + countryCodePicker.getFullNumber() + enteredPhoneNumber;

                progressBar.setVisibility(View.VISIBLE);
                buttonGetOtp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        _phoneNo,
                        60,
                        TimeUnit.SECONDS,
                        LoginActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOtp.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOtp.setVisibility(View.VISIBLE);
                                Toast.makeText(LoginActivity.this , e.getMessage() , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                buttonGetOtp.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(LoginActivity.this , VerifyOTPActivity.class);
                                intent.putExtra("phoneNo" , _phoneNo);
                                intent.putExtra("verificationId" , verificationId);
                                startActivity(intent);
                            }
                        }
                );

            }
        });
    }
}