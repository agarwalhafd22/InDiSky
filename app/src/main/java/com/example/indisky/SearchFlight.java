package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class SearchFlight extends AppCompatActivity {


    RecyclerView recyclerView;

    String date, dateCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);

        recyclerView=findViewById(R.id.recyclerView);


        tempFlightDB tmpFlightDB = new tempFlightDB(SearchFlight.this);

        String firstOrigin = tmpFlightDB.getFirstOrigin();
        String firstDest = tmpFlightDB.getFirstDest();


        Intent intent = getIntent();
        date = getIntent().getStringExtra("date");
        dateCheck=getIntent().getStringExtra("dateCheck");

        FlightDB flightDB = new FlightDB(this);


        List<SearchFlightItems> flights = flightDB.getFlightsByOriginDestDate(firstOrigin, firstDest, dateCheck);


        SearchFlightAdapter adapter = new SearchFlightAdapter(flights);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}