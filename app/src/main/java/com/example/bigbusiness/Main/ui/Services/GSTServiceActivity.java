package com.example.bigbusiness.Main.ui.Services;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bigbusiness.Main.ui.Inventory.AddInventoryItem;
import com.example.bigbusiness.R;
import com.google.android.material.textfield.TextInputEditText;

public class GSTServiceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextInputEditText gstin;
    private TextInputEditText state;
    private TextInputEditText phoneno;
    Spinner state_spinner;
    String getstate;
    String states[]={"select state","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","jharkhand","Karnataka","kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram"};
    ArrayAdapter statespinnerdapter;
    Button saveBtn, cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gstservices);


        gstin = findViewById(R.id.gstin);
        phoneno = findViewById(R.id.phonenumber);
        state_spinner = findViewById(R.id.unitspinner);
        state_spinner.setOnItemSelectedListener(GSTServiceActivity.this);
        statespinnerdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item,states);
        state_spinner.setAdapter(statespinnerdapter);
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