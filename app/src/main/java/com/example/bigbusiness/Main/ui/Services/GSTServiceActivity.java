package com.example.bigbusiness.Main.ui.Services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class GSTServiceActivity extends AppCompatActivity{
    private TextInputEditText gstInField;
    private TextInputEditText phoneNoField;
    private TextInputEditText companyNameField;
    Spinner stateField;
    String selectedState;
    String enteredCompanyName;
    String enteredPhoneNo;
    String enteredGSTIN;

    //String states[]={"select state","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","jharkhand","Karnataka","kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram"};
   // ArrayAdapter statespinnerdapter;
    Button saveBtn, cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gstservices);

        companyNameField = findViewById(R.id.companyName);
        gstInField = findViewById(R.id.gstin);
        phoneNoField = findViewById(R.id.phonenumber);
        stateField = findViewById(R.id.statespinner);

        ArrayAdapter<CharSequence> statespinneradapter = ArrayAdapter.createFromResource(this,R.array.states, android.R.layout.simple_spinner_item);
        statespinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateField.setAdapter(statespinneradapter);
        stateField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedState = stateField.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveItemToSheet();
            }
        });
    }

    private void saveItemToSheet(){
        
        enteredCompanyName = companyNameField.getText().toString().trim();
        enteredGSTIN = gstInField.getText().toString().trim();
        enteredPhoneNo = phoneNoField.getText().toString().trim();
        
        if(enteredCompanyName.isEmpty() || enteredGSTIN.isEmpty() || enteredPhoneNo.isEmpty() || selectedState.isEmpty())
        {
            Toast.makeText(this, "Fill All Details !!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzgW0F5AJAaZV6dRRKIyKikRg1qDTL_hvvxZH878W88fTTyT9SkGXlRCkk9oK-Zxr3xbQ/exec", // charan : "https://script.google.com/macros/s/AKfycbyLMhfC0ums-TEwZH_kdYsbGPc_mhIGgRd4KHnIiqYPC3vrTGqOufyl8wvt_7Lw4tSEHA/exec",
                new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Intent intent = new Intent(getApplicationContext(),ServicesActivity.class);
                    startActivity(intent);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
        )
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("companyName", enteredCompanyName);
                parmas.put("gstinnumber",enteredGSTIN);
                parmas.put("state",selectedState);
                parmas.put("phonenumber",enteredPhoneNo);

                return parmas;
            }
        };
        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
}