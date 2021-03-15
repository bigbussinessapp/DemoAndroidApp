package com.example.bigbusiness.Main.ui.Finance.RemindersAndDues;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbusiness.Models.Dues;
import com.example.bigbusiness.Models.Reminder;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RemindersAndDuesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView reminderScrollView, duesCardView;
    FloatingActionButton floatingActionButton;
    ReminderCardsManager reminderCardsManager;
    DuesCardsManager duesCardsManager;
    FirebaseDatabase database;
    DatabaseReference reminderReference, duesReference;
    UserDataService userDataService;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RemindersAndDuesFragment() {
        // Required empty public constructor
//        userDataService = UserDataService.getInstance();
//        database = FirebaseDatabase.getInstance();
//        reminderReference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Reminders");
//        duesReference = database.getReference("Users").child(userDataService.getLoggedInUser().getUid()).child("Dues");
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

        View v = inflater.inflate(R.layout.fragment_randd,container,false);
        reminderScrollView = (RecyclerView) v.findViewById(R.id.reminderScrollView);
        duesCardView = (RecyclerView) v.findViewById(R.id.duesCardView);

        reminderCardsManager = ReminderCardsManager.getInstance();
        duesCardsManager = DuesCardsManager.getInstance();
        ArrayList<Reminder> list =new ArrayList<>();
        ArrayList<Dues> list1=new ArrayList<>();
        list=reminderCardsManager.getCards();
        ReminderAdapter reminderAdapter = new ReminderAdapter(getContext(), list, RemindersAndDuesFragment.this);
        reminderScrollView.setAdapter(reminderAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false);
        reminderScrollView.setLayoutManager(layoutManager);

//        reminderReference.addValueEventListener(new ValueEventListener() {
////            private ReminderCardsManager reminderCardsManager;
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                List<Reminder> list = new ArrayList<>();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Reminder reminder = dataSnapshot.getValue(Reminder.class);
//                    list.add(reminder);
//                }
//                ReminderAdapter reminderAdapter = new ReminderAdapter(getContext(), list, RemindersAndDuesFragment.this);
//                reminderScrollView.setAdapter(reminderAdapter);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false);
//                reminderScrollView.setLayoutManager(layoutManager);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        list1=duesCardsManager.getCards();
        DuesAdapter duesAdapter = new DuesAdapter(getContext(), list1, RemindersAndDuesFragment.this);
        duesCardView.setAdapter(duesAdapter);
        LinearLayoutManager dueslayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false);
        duesCardView.setLayoutManager(dueslayoutManager);
//        duesReference.addValueEventListener(new ValueEventListener() {
////            private ReminderCardsManager reminderCardsManager;
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                List<Dues> list = new ArrayList<>();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Dues dues = dataSnapshot.getValue(Dues.class);
////                    dues.setTitle(dataSnapshot);
//                    list.add(dues);
//                }
//                DuesAdapter duesAdapter = new DuesAdapter(getContext(), list, RemindersAndDuesFragment.this);
//                duesCardView.setAdapter(duesAdapter);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false);
//                duesCardView.setLayoutManager(layoutManager);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        DuesAdapter duesAdapter = new DuesAdapter(requireContext(), this, this.duesCardsManager);
//        ReminderAdapter reminderAdapter = new ReminderAdapter(requireContext(), this, this.reminderCardsManager, this.duesCardsManager, duesAdapter);

        //setting reminder recycler view
//        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL , false);
//        reminderScrollView.setLayoutManager(layoutManager);
//        reminderScrollView.setAdapter(reminderAdapter);
//
        //setting dues recycler view
//        duesCardView = (RecyclerView) v.findViewById(R.id.duesCardView);
//        duesCardView.setAdapter(duesAdapter);
//        LinearLayoutManager duesLayoutManager = new LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false);
//        duesCardView.setLayoutManager(duesLayoutManager);

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
        Intent i = new Intent(getActivity() , ReminderFormActivity.class);
        i.putExtra("editCard", cardToBeEdited);
        startActivity(i);
    }
}