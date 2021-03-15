package com.example.bigbusiness.Main.ui.Profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bigbusiness.R;

public class NewProfileActivity extends AppCompatActivity {

    TextView name, emailId, mobileNumber, orgName, orgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_profile_activity);
        name = findViewById(R.id.name);
        emailId = findViewById(R.id.email_id);
        mobileNumber = findViewById(R.id.mobile_number);
        orgName = findViewById(R.id.org_name);
        orgType = findViewById(R.id.org_type);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        name.setText("Aarihanth Aaryan");
        emailId.setText("aarihanthaaryan@gmail.com");
        mobileNumber.setText("+917993478539");
        orgName.setText("Big Business");
        orgType.setText("Product Based");
    }

}