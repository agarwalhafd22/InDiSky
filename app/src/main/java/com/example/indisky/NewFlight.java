package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class NewFlight extends AppCompatActivity {

    EditText flightIDEditText, originEditText, destinationEditText, departDateEditText, arrivalDateEditText, priceEditText, seatEditText;

    Button addButton;

    private FlightDB flightDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flight);

        flightDB = new FlightDB(NewFlight.this);

        flightIDEditText=findViewById(R.id.flightIDEditText);
        originEditText=findViewById(R.id.originEditText);
        destinationEditText=findViewById(R.id.destinationEditText);
        departDateEditText=findViewById(R.id.departDateEditText);
        arrivalDateEditText=findViewById(R.id.arrivalDateEditText);
        priceEditText=findViewById(R.id.priceEditText);
        seatEditText=findViewById(R.id.seatEditText);
        addButton=findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=flightIDEditText.getText().toString();
                String origin=originEditText.getText().toString();
                String dest=destinationEditText.getText().toString();
                String depart=departDateEditText.getText().toString();
                String arrival=arrivalDateEditText.getText().toString();
                String priceString=priceEditText.getText().toString();
                String seatString=seatEditText.getText().toString();
                if(id.isEmpty()||origin.isEmpty()||dest.isEmpty()||depart.isEmpty()||arrival.isEmpty()||priceString.isEmpty()||seatString.isEmpty())
                    Toast.makeText(NewFlight.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                else
                {
                    int price = Integer.parseInt(priceString);
                    int seat = Integer.parseInt(seatString);
                    flightDB.addNewFlightDetail(id, origin, dest, depart, arrival, price, seat);
                    Toast.makeText(NewFlight.this, "Flight info added", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}