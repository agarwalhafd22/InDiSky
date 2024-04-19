package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchFlight extends AppCompatActivity {

    TextView originDateTextView, destDateTextView, flightIDTextView, priceSearchFlightTextView;

    CardView cardview;

    String date, dateCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flight);

        cardview=findViewById(R.id.cardview);
        originDateTextView=findViewById(R.id.originEditText);
        destDateTextView=findViewById(R.id.destDateTextView);
        flightIDTextView=findViewById(R.id.flightIDEditText);
        priceSearchFlightTextView=findViewById(R.id.priceTextViewSummary);

        tempFlightDB tmpFlightDB = new tempFlightDB(SearchFlight.this);

        String firstOrigin = tmpFlightDB.getFirstOrigin();
        String firstDest = tmpFlightDB.getFirstDest();


        Intent intent = getIntent();
        date = getIntent().getStringExtra("date");
        dateCheck=getIntent().getStringExtra("dateCheck");


    }
}