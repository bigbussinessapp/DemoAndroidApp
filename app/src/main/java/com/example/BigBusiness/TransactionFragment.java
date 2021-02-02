package com.example.BigBusiness;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment {

    private RecyclerView myrecyclerview;
    private List<BusinessData> lstBdata;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstBdata = new ArrayList<>();
        lstBdata.add(new BusinessData("Business 1","12665.366"));
        lstBdata.add(new BusinessData("Business 2","12665.366"));
        lstBdata.add(new BusinessData("Business 3","12665.366"));
        lstBdata.add(new BusinessData("Business 4","12665.366"));
        lstBdata.add(new BusinessData("Business 5","12665.366"));
        lstBdata.add(new BusinessData("Business 6","12665.366"));
        lstBdata.add(new BusinessData("Business 7","12665.366"));
        lstBdata.add(new BusinessData("Business 8","12665.366"));
        lstBdata.add(new BusinessData("Business 1","12665.366"));
        lstBdata.add(new BusinessData("Business 2","12665.366"));
        lstBdata.add(new BusinessData("Business 3","12665.366"));
        lstBdata.add(new BusinessData("Business 4","12665.366"));
        lstBdata.add(new BusinessData("Business 5","12665.366"));
        lstBdata.add(new BusinessData("Business 6","12665.366"));
        lstBdata.add(new BusinessData("Business 7","12665.366"));
        lstBdata.add(new BusinessData("Business 8","12665.366"));
        lstBdata.add(new BusinessData("Business 1","12665.366"));
        lstBdata.add(new BusinessData("Business 2","12665.366"));
        lstBdata.add(new BusinessData("Business 3","12665.366"));
        lstBdata.add(new BusinessData("Business 4","12665.366"));
        lstBdata.add(new BusinessData("Business 5","12665.366"));
        lstBdata.add(new BusinessData("Business 6","12665.366"));
        lstBdata.add(new BusinessData("Business 7","12665.366"));
        lstBdata.add(new BusinessData("Business 8","12665.366"));
        lstBdata.add(new BusinessData("Business 1","12665.366"));
        lstBdata.add(new BusinessData("Business 2","12665.366"));
        lstBdata.add(new BusinessData("Business 3","12665.366"));
        lstBdata.add(new BusinessData("Business 4","12665.366"));
        lstBdata.add(new BusinessData("Business 5","12665.366"));
        lstBdata.add(new BusinessData("Business 6","12665.366"));
        lstBdata.add(new BusinessData("Business 7","12665.366"));
        lstBdata.add(new BusinessData("Business 8","12665.366"));
        lstBdata.add(new BusinessData("Business 1","12665.366"));
        lstBdata.add(new BusinessData("Business 2","12665.366"));
        lstBdata.add(new BusinessData("Business 3","12665.366"));
        lstBdata.add(new BusinessData("Business 4","12665.366"));
        lstBdata.add(new BusinessData("Business 5","12665.366"));
        lstBdata.add(new BusinessData("Business 6","12665.366"));
        lstBdata.add(new BusinessData("Business 7","12665.366"));
        lstBdata.add(new BusinessData("Business 8","12665.366"));
        lstBdata.add(new BusinessData("Business 1","12665.366"));
        lstBdata.add(new BusinessData("Business 2","12665.366"));
        lstBdata.add(new BusinessData("Business 3","12665.366"));
        lstBdata.add(new BusinessData("Business 4","12665.366"));
        lstBdata.add(new BusinessData("Business 5","12665.366"));
        lstBdata.add(new BusinessData("Business 6","12665.366"));
        lstBdata.add(new BusinessData("Business 7","12665.366"));
        lstBdata.add(new BusinessData("Business 8","12665.366"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transactio_frag,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.trans_recycler_id);
        TransactionAdapter transactionAdapter = new TransactionAdapter(getContext(),lstBdata);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(transactionAdapter);
        return v;
    }

    public TransactionFragment() {
    }
}
