package com.example.bigbusiness.Main.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bigbusiness.Main.ui.Finance.FinanceActivity;
import com.example.bigbusiness.Main.ui.Inventory.InventoryActivity;
import com.example.bigbusiness.Main.ui.Services.ServicesActivity;
import com.example.bigbusiness.Main.ui.Invoice.InvoiceManagementActivity;
import com.example.bigbusiness.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button invoiceBtn;
    private Button inventoryBtn;
    private Button financeBtn;
    private Button serviceBtn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        invoiceBtn = (Button) root.findViewById(R.id.invoice_btn_home);
        inventoryBtn = (Button) root.findViewById(R.id.inventory_btn_home);
        financeBtn = (Button) root.findViewById(R.id.finance_btn_home);

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        inventoryBtn.setOnClickListener(inventoryClick);
        financeBtn.setOnClickListener(financeClick);
        invoiceBtn.setOnClickListener(invoiceClick);
        serviceBtn.setOnClickListener(serviceClick);

        return root;
    }

    private View.OnClickListener invoiceClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), InvoiceManagementActivity.class);
                    startActivity(i);
                }
            };
    private View.OnClickListener inventoryClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), InventoryActivity.class);
                    startActivity(i);
                }
            };
    private View.OnClickListener financeClick =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), FinanceActivity.class);
                    startActivity(i);
                }
            };

    private View.OnClickListener serviceClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getActivity(), ServicesActivity.class);
            startActivity(i);
        }
    };


}