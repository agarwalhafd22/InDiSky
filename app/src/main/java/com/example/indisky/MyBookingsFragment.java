package com.example.indisky;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyBookingsFragment extends Fragment {

    RecyclerView recyclerViewMyBookings;

    MyBookingsAdapter adapter;

    int user_id;


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_bookings, container, false);

        UserDB userDB = new UserDB(getActivity());
        user_id=userDB.getSessionUserID();

        List<MyBookingItems> dataList = MyBookingsFetchData.fetchData(getActivity(), user_id);
        adapter = new MyBookingsAdapter(dataList);


        recyclerViewMyBookings=view.findViewById(R.id.recyclerViewMyBookings);
        recyclerViewMyBookings.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMyBookings.setAdapter(adapter);

        return view;
    }
}