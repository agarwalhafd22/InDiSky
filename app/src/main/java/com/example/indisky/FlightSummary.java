package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Locale;

public class FlightSummary extends AppCompatActivity {

    TextView originTextViewSummary, destTextViewSummary, priceTextViewSummary, nameTextViewSummary, ageTextViewSummary, genderTextViewSummary, dateTextViewSummary;

    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_summary);

        originTextViewSummary=findViewById(R.id.originTextViewSummary);
        destTextViewSummary=findViewById(R.id.destTextViewSummary);
        priceTextViewSummary=findViewById(R.id.priceTextViewSummary);
        nameTextViewSummary=findViewById(R.id.nameTextViewSummary);
        ageTextViewSummary=findViewById(R.id.ageTextViewSummary);
        genderTextViewSummary=findViewById(R.id.genderTextViewSummary);
        dateTextViewSummary=findViewById(R.id.dateTextViewSummary);
        confirmButton=findViewById(R.id.confirmButton);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        int age = intent.getIntExtra("age", 18);
        String date = intent.getStringExtra("date");
        String dateCheck = intent.getStringExtra("dateCheck");
        String flightID = intent.getStringExtra("flightID");


        UserDB userDB = new UserDB(FlightSummary.this);
        tempFlightDB tmpFlightDB = new tempFlightDB(FlightSummary.this);
        FlightDB flightDB = new FlightDB(FlightSummary.this);
        BookingDB bookingDB = new BookingDB(FlightSummary.this);
        PassengerDB passengerDB = new PassengerDB(FlightSummary.this);
        PaymentDB paymentDB = new PaymentDB(FlightSummary.this);

        String firstOrigin = tmpFlightDB.getFirstOrigin();
        String firstDest = tmpFlightDB.getFirstDest();
        int price = flightDB.getPriceByFlightID(flightID);
        int seats = flightDB.getSeatsByFlightID(flightID);
        int user_id = userDB.getSessionUserID();
        originTextViewSummary.setText(firstOrigin);
        destTextViewSummary.setText(firstDest);
        nameTextViewSummary.setText(name);
        genderTextViewSummary.setText(gender);
        ageTextViewSummary.setText(Integer.toString(age));
        dateTextViewSummary.setText(date);

        if(price==-1)
            priceTextViewSummary.setText("No Flights");
        else
            priceTextViewSummary.setText(Integer.toString(price));

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and show an alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(FlightSummary.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to pay ₹"+Integer.toString(price)+" and confirm?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH) + 1;
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        String formattedMonth = String.format(Locale.US, "%02d", month);
                        String currentDate = Integer.toString(day)+"/"+formattedMonth+"/"+Integer.toString(year);
                        bookingDB.addNewBooking(user_id, flightID, currentDate, price);

                        int bookingID = bookingDB.getLastInsertedBookingID();
                        passengerDB.addNewPassenger(bookingID, name, age, gender);
                        paymentDB.addNewPayment(bookingID, currentDate, price);
                        flightDB.reduceSeatAvailability(firstOrigin, firstDest, dateCheck, seats);
                        Toast.makeText(FlightSummary.this, "Booking Made", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(FlightSummary.this, MainActivity.class);
                        intent.putExtra("fragmentIndex", 1);
                        startActivity(intent1);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



    }
}