package com.example.restapi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RandomPersonsFragment extends Fragment {

    SwipeRefreshLayout startRandom;
    TextView name;
    TextView email;
    TextView gender;
    ImageView photo;
    TextView birthdate;
    TextView age;
    TextView phone;
    TextView country;
    TextView city;
    TextView street;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RandomPersonsFragment() {}

    public static RandomPersonsFragment newInstance(String param1, String param2) {
        RandomPersonsFragment fragment = new RandomPersonsFragment();
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

        View view = inflater.inflate(R.layout.fragment_random_persons, container, false);

        name = view.findViewById(R.id.name);

        email = view.findViewById(R.id.email);

        gender = view.findViewById(R.id.gender);

        photo = view.findViewById(R.id.photo);

        birthdate = view.findViewById(R.id.birthdate);

        age = view.findViewById(R.id.age);

        phone = view.findViewById(R.id.phone);

        country = view.findViewById(R.id.country);

        city = view.findViewById(R.id.city);

        street = view.findViewById(R.id.street);

        startRandom = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        startRandom.setOnRefreshListener(() -> {
            RandomPersonTask asyncTask = new RandomPersonTask(name, email, gender, photo, birthdate, age, phone, country, city, street);
            asyncTask.execute();

            startRandom.setRefreshing(false);
        });

        return view;
    }
}