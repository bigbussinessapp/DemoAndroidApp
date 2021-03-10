package com.example.bigbusiness.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bigbusiness.Main.Main1Activity;
import com.example.bigbusiness.Models.User;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button submit;
    TextInputLayout username , email_id , phoneNo , OrganizationName , OrganizationType;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference usersDatabase;
    UserDataService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userService = UserDataService.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);


        firebaseDatabase = FirebaseDatabase.getInstance();
        usersDatabase = firebaseDatabase.getReference("Users");

        phoneNo = findViewById(R.id.mobileNumber);
        submit = findViewById(R.id.submit_login_form);
        firebaseAuth = firebaseAuth.getInstance();

        Intent i = getIntent();
        phoneNo.getEditText().setText(i.getStringExtra("phoneNo"));
        phoneNo.getEditText().setFocusable(false);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // store in firebase here only
                User user = getUserData();
                usersDatabase.child(user.getUid()).child("Profile").setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            userService.setLoggedInUser(user);// = user;
                            Toast.makeText(RegistrationActivity.this, "Registration Successful! Welcome "+user.getName(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegistrationActivity.this, Main1Activity.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this, "Error while adding user", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private User getUserData() {
        String uid = firebaseAuth.getUid();

        username = (TextInputLayout) findViewById(R.id.UserName);
        String userFullName = username.getEditText().getText().toString();

        email_id = (TextInputLayout) findViewById(R.id.email_id);
        String emailid = email_id.getEditText().getText().toString();

        phoneNo = (TextInputLayout) findViewById(R.id.mobileNumber);
        String mobileNo = phoneNo.getEditText().getText().toString();

        OrganizationName= (TextInputLayout) findViewById(R.id.BusinessName);
        String BusinessName = OrganizationName.getEditText().getText().toString();

        OrganizationType = (TextInputLayout) findViewById(R.id.BusinessType);
        String BusinessType = OrganizationType.getEditText().getText().toString();

        User new_user = new User(uid , userFullName , emailid , mobileNo , BusinessName , BusinessType);
        return new_user;
    }
}