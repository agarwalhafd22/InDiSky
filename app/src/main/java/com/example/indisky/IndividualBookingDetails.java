package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IndividualBookingDetails extends AppCompatActivity {

    String flightID, name, origin, dest, date, age, gender;

    int bookingID, passengerID, paymentID, amount;

    BookingDB bookingDB;
    PassengerDB passengerDB;

    PaymentDB paymentDB;
    FlightDB flightDB;

    TextView originTextViewIndividual, destTextViewIndividual, bookingIDTextViewIndividual, flightIDTextViewIndividual, bookingDateTextViewIndividual, nameTextViewIndividual, ageTextViewIndividual, genderTextViewIndividual, passengerIDTextViewIndividual, paymentIDTextViewIndividual, amountTextViewIndividual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_booking_details);

        originTextViewIndividual=findViewById(R.id.originTextViewIndividual);
        destTextViewIndividual=findViewById(R.id.destTextViewIndividual);
        bookingIDTextViewIndividual=findViewById(R.id.bookingIDTextViewIndividual);
        flightIDTextViewIndividual=findViewById(R.id.flightIDTextViewIndividual);
        bookingDateTextViewIndividual=findViewById(R.id.bookingDateTextViewIndividual);
        nameTextViewIndividual=findViewById(R.id.nameTextViewIndividual);
        ageTextViewIndividual=findViewById(R.id.ageTextViewIndividual);
        genderTextViewIndividual=findViewById(R.id.genderTextViewIndividual);
        passengerIDTextViewIndividual=findViewById(R.id.passengerIDTextViewIndividual);
        paymentIDTextViewIndividual=findViewById(R.id.paymentIDTextViewIndividual);
        amountTextViewIndividual=findViewById(R.id.amountTextViewIndividual);

        bookingDB = new BookingDB(IndividualBookingDetails.this);
        flightDB=new FlightDB(IndividualBookingDetails.this);
        passengerDB=new PassengerDB(IndividualBookingDetails.this);
        paymentDB= new PaymentDB(IndividualBookingDetails.this);

        Intent intent=getIntent();
        flightID=getIntent().getStringExtra("flightID");
        bookingID=getIntent().getIntExtra("bookingID", 0);

        origin=flightDB.getOriginByFlightID(flightID);
        dest=flightDB.getDestByFlightID(flightID);
        name=passengerDB.getNameByBookingID(bookingID);
        date=bookingDB.getBookingDateByBookingID(bookingID);
        age=passengerDB.getAgeByBookingID(bookingID);
        gender= passengerDB.getGenderByBookingID(bookingID);
        passengerID=passengerDB.getPassengerIDByBookingID(bookingID);
        paymentID=paymentDB.getPaymentIDByBookingID(bookingID);
        amount=paymentDB.getAmountByBookingID(bookingID);

        originTextViewIndividual.setText(origin);
        destTextViewIndividual.setText(dest);
        bookingIDTextViewIndividual.setText(Integer.toString(bookingID));
        flightIDTextViewIndividual.setText(flightID);
        bookingDateTextViewIndividual.setText(date);
        nameTextViewIndividual.setText(name);
        ageTextViewIndividual.setText(age);
        genderTextViewIndividual.setText(gender);
        passengerIDTextViewIndividual.setText(Integer.toString(passengerID));
        paymentIDTextViewIndividual.setText(Integer.toString(paymentID));
        amountTextViewIndividual.setText(Integer.toString(amount));
    }
}