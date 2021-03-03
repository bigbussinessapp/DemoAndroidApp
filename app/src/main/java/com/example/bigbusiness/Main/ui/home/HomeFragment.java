package com.example.bigbusiness.Main.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bigbusiness.Login.VerifyOTPActivity;
import com.example.bigbusiness.Main.ui.Finance.FinanceActivity;
import com.example.bigbusiness.Main.ui.Inventory.InventoryActivity;
import com.example.bigbusiness.Main.ui.Invoice.InvoiceManagement;
import com.example.bigbusiness.Main.ui.StaffMangement.StaffActivity;
import com.example.bigbusiness.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button invoiceBtn;
    private Button inventoryBtn;
    private Button financeBtn;
    private Button staffManagementBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        invoiceBtn = (Button) root.findViewById(R.id.invoice_btn);
        inventoryBtn = (Button) root.findViewById(R.id.inventory_btn);
        financeBtn = (Button) root.findViewById(R.id.finance_btn);
        staffManagementBtn =(Button) root.findViewById(R.id.StaffMangementbtn);


//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        inventoryBtn.setOnClickListener(inventoryClick);
        financeBtn.setOnClickListener(financeClick);
        invoiceBtn.setOnClickListener(invoiceClick);
        staffManagementBtn.setOnClickListener(staffManagementClick);

        return root;
    }

    private OnClickListener invoiceClick =
            new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), InvoiceManagement.class);
                    startActivity(i);
                }
            };
    private OnClickListener inventoryClick =
            new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), InventoryActivity.class);
                    startActivity(i);
                }
            };
    private OnClickListener financeClick =
            new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), FinanceActivity.class);
                    startActivity(i);
                }
            };
    private OnClickListener staffManagementClick =
            new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), StaffActivity.class);
                    startActivity(i);
                }

            };

}