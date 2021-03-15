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
import com.example.bigbusiness.Services.UserDataService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionHistoryFragment newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionHistoryFragment extends Fragment {

    private RecyclerView transactionHistoryRecyclerView;
    private List<TransactionHistoryItem> lstBdata;
    DatabaseReference transactionReference;
    UserDataService userDataService;
    FirebaseDatabase database;

    public TransactionHistoryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstBdata = new ArrayList<>();
        setHasOptionsMenu(true);
       // userDataService = UserDataService.getInstance();
        // database = FirebaseDatabase.getInstance();
        // transactionReference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("TransactionHistory");

//        this.addTransactionItem(new TransactionHistoryItem("Swiggy", "170098", "March 2, 2021, 03: 27", "Paid To" , "Debited From", "invoice1"));
//        this.addTransactionItem(new TransactionHistoryItem("Swiggy","170098" , "January 2, 2021, 04: 38" , "Paid To" ,"Debited From", "-MVT8MI73wp-mv1yUhgT"));
//        this.addTransactionItem(new TransactionHistoryItem("Zomato","12665.678" , "April 2, 2021, 05: 57" , "Received From" ,"Credited To", "invoice 2"));
//        this.addTransactionItem(new TransactionHistoryItem("Amazon","170098" , "May 2, 2021, 13: 47" , "Paid To" ,"Debited From", "invoice 4"));
//        this.addTransactionItem(new TransactionHistoryItem("Paytm","12665.678" , "June 2, 2021, 14: 27" , "Received From" ,"Credited To", "invoice 5"));
//        this.addTransactionItem(new TransactionHistoryItem("Swiggy","170098" , "July 2, 2021, 23: 57" , "Paid To" ,"Debited From", "invoice 6"));
        lstBdata.add(new TransactionHistoryItem("Swiggy", "170098", "March 2, 2021, 03: 27", "Paid To" , "Debited From", "invoice1"));
        lstBdata.add(new TransactionHistoryItem("Swiggy","170098" , "January 2, 2021, 04: 38" , "Paid To" ,"Debited From", "-MVT8MI73wp-mv1yUhgT"));
        lstBdata.add(new TransactionHistoryItem("Zomato","12665.678" , "April 2, 2021, 05: 57" , "Received From" ,"Credited To", "invoice 2"));
        lstBdata.add(new TransactionHistoryItem("Amazon","170098" , "May 2, 2021, 13: 47" , "Paid To" ,"Debited From", "invoice 4"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_history,container,false);

        transactionHistoryRecyclerView = (RecyclerView) v.findViewById(R.id.transaction_history_recyclerList);
        TransactionAdapter transactionAdapter = new TransactionAdapter(getContext(),lstBdata, TransactionHistoryFragment.this);
        transactionHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        transactionHistoryRecyclerView.setAdapter(transactionAdapter);

//        transactionReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                List<TransactionHistoryItem> lstBdata = new ArrayList<>();
////                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
////                    TransactionHistoryItem Transaction = dataSnapshot.getValue(TransactionHistoryItem.class);
////                    lstBdata.add(Transaction);
////                }
//                TransactionAdapter transactionAdapter = new TransactionAdapter(getContext(),lstBdata, TransactionHistoryFragment.this);
//                transactionHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                transactionHistoryRecyclerView.setAdapter(transactionAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        return v;
    }

    public Button.OnClickListener checkInvoice =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(getContext(), "Checking Invoice...", Toast.LENGTH_SHORT);
                    toast.show();
                }
            };

//    public void addTransactionItem(TransactionHistoryItem item)
//    {
//        transactionReference.child(item.getInvoiceId()).setValue(item);
//    }

    public long getDifferenceInDays(Date formattedDate) {
        long diffInMilliSecs = Calendar.getInstance().getTime().getTime() - formattedDate.getTime();

        long diffInDays = TimeUnit.DAYS.toDays(diffInMilliSecs) % 365;
        return diffInDays;
    }
}