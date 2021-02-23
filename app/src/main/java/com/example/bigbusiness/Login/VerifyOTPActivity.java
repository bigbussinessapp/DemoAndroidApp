package com.example.bigbusiness.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.bigbusiness.Main.Main1Activity;
import com.example.bigbusiness.Models.User;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {

    PinView pinView;
    TextView textMobile , timerOtp;
    ProgressBar progressBar;
    Button buttonVerify;
    private String verificationId;
    public int counter = 60;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDb;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        mAuth = FirebaseAuth.getInstance();
        firebaseDb = FirebaseDatabase.getInstance();
        pinView = findViewById(R.id.pin_view);
        timerOtp = findViewById(R.id.timerofOtp);
        textMobile = findViewById(R.id.TextMobile);
        String enteredPhoneNumber = getIntent().getStringExtra("phoneNo");
        textMobile.setText(enteredPhoneNumber);

        progressBar = findViewById(R.id.progressBar);

        buttonVerify = findViewById(R.id.buttonVerify);
        verificationId = getIntent().getStringExtra("verificationId");

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pinView == null)
                {
                    Toast.makeText(VerifyOTPActivity.this , "Please Enter Valid Code!" , Toast.LENGTH_SHORT).show();
                    return;
                }

                String code = pinView.getText().toString();

                if(verificationId != null){
                    progressBar.setVisibility(View.VISIBLE);
                    buttonVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId , code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    buttonVerify.setVisibility(View.VISIBLE);
                                    if(task.isSuccessful()){
                                        userId =  mAuth.getCurrentUser().getUid();
                                        firebaseDb.getReference("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Intent i;
                                                if(snapshot.exists()){
                                                    i = new Intent(getApplicationContext() , Main1Activity.class);
                                                    Toast.makeText(VerifyOTPActivity.this , "Verification Successful" , Toast.LENGTH_SHORT).show();
                                                    UserDataService userDataService = UserDataService.getInstance();
                                                    User user = snapshot.getValue(User.class);
                                                    userDataService.setLoggedInUser(user);
                                                }
                                                else
                                                {
                                                    i = new Intent(getApplicationContext() , RegistrationActivity.class);
                                                    i.putExtra("phoneNo", enteredPhoneNumber);
                                                }
                                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(i);
                                                finish();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(VerifyOTPActivity.this , error.getMessage() , Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Toast.makeText(VerifyOTPActivity.this , "The Verification Code Entered is Invalid!!" , Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        final TextView resendOtp = findViewById(R.id.textResendOTP);
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CountDownTimer(60000 , 1000){
                    public void onTick(long millisUntilFinished){
                        timerOtp.setText(String.valueOf(counter));
                        resendOtp.setEnabled(false);
                        resendOtp.setTextColor(Color.parseColor("#757575"));
                        timerOtp.setVisibility(View.VISIBLE);
                        counter--;
                    }
                    public void onFinish(){
                        resendOtp.setEnabled(true);
                        resendOtp.setTextColor(Color.parseColor("#F44336"));
                        timerOtp.setVisibility(View.GONE);
                        counter = 60;
                    }
                }.start();

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        getIntent().getStringExtra("phoneNo"),
                        60,
                        TimeUnit.SECONDS,
                        VerifyOTPActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(VerifyOTPActivity.this , e.getMessage() , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newverificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationId = newverificationId;
                                Toast.makeText(VerifyOTPActivity.this , "OTP Sent" , Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    public void checkUser(){
    }
}