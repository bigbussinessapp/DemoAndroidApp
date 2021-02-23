package com.example.bigbusiness.Main.ui.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bigbusiness.Models.User;
import com.example.bigbusiness.R;
import com.example.bigbusiness.Services.UserDataService;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    TextInputEditText user_name , user_email , user_mobilenumber , user_business_name , user_business_type;
    UserDataService userDataService;
    User user;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        user_name = (TextInputEditText) v.findViewById(R.id.user_name);
        user_email = (TextInputEditText) v.findViewById(R.id.user_email);
        user_mobilenumber = (TextInputEditText) v.findViewById(R.id.user_mobileNo);
        user_business_name = (TextInputEditText) v.findViewById(R.id.user_business_name);
        user_business_type = (TextInputEditText) v.findViewById(R.id.user_business_type);

        userDataService = UserDataService.getInstance();
        User user = userDataService.getLoggedInUser();

        user_name.setText(user.getName());
        user_email.setText(user.getEmail_id());
        user_mobilenumber.setText(user.getPhoneNo());
        user_business_name.setText(user.getOrganizationName());
        user_business_type.setText(user.getOrganizationType());

        return v;
    }

}