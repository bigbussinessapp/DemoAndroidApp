package com.example.BigBusiness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {

    private RecyclerView myrecyclerview;
    private List<Transaction> lstBdata;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        lstBdata = new ArrayList<>();
        lstBdata.add(new Transaction("Swiggy","170098" , "1 day ago" , "Paid To" ,"Debited From"));
        lstBdata.add(new Transaction("Zomato","12665.678" , "5 days ago" , "Received From" ,"Credited To"));
        lstBdata.add(new Transaction("Amazon","170098" , "2 day ago" , "Paid To" ,"Debited From"));
        lstBdata.add(new Transaction("Paytm","12665.678" , "3 days ago" , "Received From" ,"Credited To"));
        lstBdata.add(new Transaction("Swiggy","170098" , "1 day ago" , "Paid To" ,"Debited From"));
        lstBdata.add(new Transaction("Zomato","12665.678" , "5 days ago" , "Received From" ,"Credited To"));
        lstBdata.add(new Transaction("Amazon","170098" , "2 day ago" , "Paid To" ,"Debited From"));
        lstBdata.add(new Transaction("Paytm","12665.678" , "3 days ago" , "Received From" ,"Credited To"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transaction_frag,container,false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.invoicemenu){
                    Toast.makeText(getActivity(), "You have Clicked On Invoice", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        myrecyclerview = (RecyclerView) v.findViewById(R.id.trans_recycler_id);
        TransactionAdapter transactionAdapter = new TransactionAdapter(getContext(),lstBdata);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(transactionAdapter);
        return v;
    }


    public TransactionFragment() {
    }
}
