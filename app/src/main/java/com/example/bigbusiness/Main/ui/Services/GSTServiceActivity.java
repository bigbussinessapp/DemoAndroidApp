package com.example.bigbusiness.Main.ui.Services;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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

public class GSTServiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextInputEditText gstin;
    private TextInputEditText state;
    private TextInputEditText phoneno;
    Spinner state_spinner;
    String getstate;
    //String states[]={"select state","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","jharkhand","Karnataka","kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram"};
   // ArrayAdapter statespinnerdapter;
    Button saveBtn, cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gstservices);


        gstin = findViewById(R.id.gstin);
        phoneno = findViewById(R.id.phonenumber);
        state = findViewById(R.id.state);
        state_spinner = findViewById(R.id.statespinner);
        //statespinnerdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item,states);
        ArrayAdapter<CharSequence> statespinneradapter =ArrayAdapter.createFromResource(this,R.array.states, android.R.layout.simple_spinner_item);
        statespinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_spinner.setAdapter(statespinneradapter);
        state_spinner.setOnItemSelectedListener(GSTServiceActivity.this);

        saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                saveItemToSheet();
            }
        });
    }

    private void saveItemToSheet(){
        final String gst = gstin.getText().toString().trim();
        final String phone = phoneno.getText().toString().trim();
        final String state_item = state.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyLMhfC0ums-TEwZH_kdYsbGPc_mhIGgRd4KHnIiqYPC3vrTGqOufyl8wvt_7Lw4tSEHA/exec",
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
        ) {
            @Override
            protected Map<String, String> getParams() {
            Map<String, String> parmas = new HashMap<>();

            //here we pass params
            parmas.put("action","addItem");
            parmas.put("gstinnumber",gst);
            parmas.put("state",state_item);
            parmas.put("phonenumber",phone);

            return parmas;
        }
        };
        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }

        @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        getstate = state_spinner.getSelectedItem().toString();
        state.setText(getstate);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}