package com.example.BigBusiness;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemindersAndDuesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class RemindersAndDuesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView reminderScrollView, duesCardView;
    FloatingActionButton floatingActionButton;
    ReminderCardsManager reminderCardsManager;
    DuesCardsManager duesCardsManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RemindersAndDuesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemindersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemindersAndDuesFragment newInstance(String param1, String param2) {
        RemindersAndDuesFragment fragment = new RemindersAndDuesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_reminderanddues,container,false);
        reminderScrollView = (RecyclerView) v.findViewById(R.id.reminderScrollView);

         reminderCardsManager = ReminderCardsManager.getInstance();
        duesCardsManager = DuesCardsManager.getInstance();

        DuesAdapter duesAdapter = new DuesAdapter(requireContext(), this, this.duesCardsManager);
        ReminderAdapter reminderAdapter = new ReminderAdapter(requireContext(), this, this.reminderCardsManager, this.duesCardsManager, duesAdapter);

        //setting reminder recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false);
        reminderScrollView.setLayoutManager(layoutManager);
        reminderScrollView.setAdapter(reminderAdapter);
//
        //setting dues recycler view
        duesCardView = (RecyclerView) v.findViewById(R.id.duesCardView);
        duesCardView.setAdapter(duesAdapter);
        LinearLayoutManager duesLayoutManager = new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false);
        duesCardView.setLayoutManager(duesLayoutManager);

        floatingActionButton = v.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity() , ReminderFormActivity.class);
                startActivity(i);
            }
        });

        reminderScrollView.invalidate();
       // reminderAdapter.notifyDataSetChanged();
        return v;

    }
    public void editRemainderCard(Reminder cardToBeEdited)
    {
        //Done
        Intent i = new Intent(getActivity() , ReminderFormActivity.class);
        i.putExtra("editCard", cardToBeEdited);
        startActivity(i);
    }
}