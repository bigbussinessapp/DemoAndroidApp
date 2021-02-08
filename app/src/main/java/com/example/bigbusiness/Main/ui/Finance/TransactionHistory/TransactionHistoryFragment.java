package com.example.bigbusiness.Main.ui.Finance.TransactionHistory;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigbusiness.Models.TransactionHistoryItem;
import com.example.bigbusiness.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionHistoryFragment newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionHistoryFragment extends Fragment {

    private RecyclerView transactionHistoryRecyclerView;
    private List<TransactionHistoryItem> lstBdata;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        lstBdata = new ArrayList<>();
        lstBdata.add(new TransactionHistoryItem("Swiggy","170098" , "1 day ago" , "Paid To" ,"Debited From"));
        lstBdata.add(new TransactionHistoryItem("Zomato","12665.678" , "5 days ago" , "Received From" ,"Credited To"));
        lstBdata.add(new TransactionHistoryItem("Amazon","170098" , "2 day ago" , "Paid To" ,"Debited From"));
        lstBdata.add(new TransactionHistoryItem("Paytm","12665.678" , "3 days ago" , "Received From" ,"Credited To"));
        lstBdata.add(new TransactionHistoryItem("Swiggy","170098" , "1 day ago" , "Paid To" ,"Debited From"));
        lstBdata.add(new TransactionHistoryItem("Zomato","12665.678" , "5 days ago" , "Received From" ,"Credited To"));
        lstBdata.add(new TransactionHistoryItem("Amazon","170098" , "2 day ago" , "Paid To" ,"Debited From"));
        lstBdata.add(new TransactionHistoryItem("Paytm","12665.678" , "3 days ago" , "Received From" ,"Credited To"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_history,container,false);

        transactionHistoryRecyclerView = (RecyclerView) v.findViewById(R.id.transaction_history_recyclerList);
        TransactionAdapter transactionAdapter = new TransactionAdapter(getContext(),lstBdata, this);
        transactionHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        transactionHistoryRecyclerView.setAdapter(transactionAdapter);
        return v;
    }


    public TransactionHistoryFragment() {
    }

    public Button.OnClickListener checkInvoice =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(getContext(), "Checking Invoice...", Toast.LENGTH_SHORT);
                    toast.show();
                }
            };
}