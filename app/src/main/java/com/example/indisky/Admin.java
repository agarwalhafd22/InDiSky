package com.example.indisky;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    Button addNewFlightButton, managerUsersButton;
    private UserDB userDB;
    private FlightDB flightDB;
    private TableLayout tableLayoutUsers, tableLayoutSession, tableLayoutFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userDB = new UserDB(this);
        flightDB = new FlightDB(this);
        tableLayoutUsers = findViewById(R.id.tableLayoutUsers);
        tableLayoutSession = findViewById(R.id.tableLayoutSession);
        tableLayoutFlight = findViewById(R.id.tableLayoutFlight);
        addNewFlightButton=findViewById(R.id.addNewFlightButton);
        managerUsersButton=findViewById(R.id.manageUsersButton);

        addNewFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Admin.this, NewFlight.class);
                startActivity(intent);
            }
        });

        managerUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Admin.this, ManageUsers.class);
                startActivity(intent);
            }
        });


        displayUsers();
        displaySession();
        displayFlight();


    }

    private void displayFlight() {
        SQLiteDatabase db = flightDB.getReadableDatabase();
        String[] projection = {
                FlightDB.FLIGHT_ID,
                FlightDB.AIRPORT_ID,
                FlightDB.ORIGIN,
                FlightDB.DEST,
                FlightDB.DEPART_DATE,
                FlightDB.ARRIVAL_DATE,
                FlightDB.PRICE,
                FlightDB.SEAT_AVAIL
        };

        Cursor cursor = db.query(FlightDB.TABLE_NAME, projection, null, null, null, null, null);

        while(cursor.moveToNext()){
            String flightID = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.FLIGHT_ID));
            String airportID = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.AIRPORT_ID));
            String to = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.ORIGIN));
            String from = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.DEST));
            String depart = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.DEPART_DATE));
            String arrival = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.ARRIVAL_DATE));
            int price = cursor.getInt(cursor.getColumnIndexOrThrow(FlightDB.PRICE));
            int seat = cursor.getInt(cursor.getColumnIndexOrThrow(FlightDB.SEAT_AVAIL));

            TableRow row = new TableRow(this);

            TextView flightTableIDTextView = new TextView(this);
            flightTableIDTextView.setText(flightID);
            flightTableIDTextView.setPadding(8, 8, 8, 8);
            row.addView(flightTableIDTextView);

            TextView flightTableAirportIDTextView = new TextView(this);
            flightTableAirportIDTextView.setText(airportID);
            flightTableAirportIDTextView.setPadding(8, 8, 8, 8);
            row.addView(flightTableAirportIDTextView);

            TextView toTextView = new TextView(this);
            toTextView.setText(to);
            toTextView.setPadding(8, 8, 8, 8);
            row.addView(toTextView);

            TextView fromTextView = new TextView(this);
            fromTextView.setText(from);
            fromTextView.setPadding(8, 8, 8, 8);
            row.addView(fromTextView);

            TextView departTextView = new TextView(this);
            departTextView.setText(depart);
            departTextView.setPadding(8, 8, 8, 8);
            row.addView(departTextView);

            TextView arrivalTextView = new TextView(this);
            arrivalTextView.setText(arrival);
            arrivalTextView.setPadding(8, 8, 8, 8);
            row.addView(arrivalTextView);

            TextView priceTextView = new TextView(this);
            priceTextView.setText(String.valueOf(price));
            priceTextView.setPadding(8, 8, 8, 8);
            row.addView(priceTextView);

            TextView seatTextView = new TextView(this);
            seatTextView.setText(String.valueOf(seat));
            seatTextView.setPadding(8, 8, 8, 8);
            row.addView(seatTextView);


            tableLayoutFlight.addView(row);
        }

        cursor.close();
        db.close();
    }

    private void displaySession() {
        SQLiteDatabase db = userDB.getReadableDatabase();
        String[] projection = {
                UserDB.SESSION_ID_COL,
                UserDB.SESSION_USER_EMAIL_COL,
                UserDB.SESSION_USER_ID_COL
        };

        Cursor cursor = db.query(UserDB.SESSION_TABLE, projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int session_id = cursor.getInt(cursor.getColumnIndexOrThrow(UserDB.SESSION_ID_COL));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UserDB.SESSION_USER_EMAIL_COL));
            int user_id = cursor.getInt(cursor.getColumnIndexOrThrow(UserDB.SESSION_USER_ID_COL));

            TableRow row = new TableRow(this);

            TextView sessionIDTextView = new TextView(this);
            sessionIDTextView.setText(String.valueOf(session_id));
            sessionIDTextView.setPadding(8, 8, 8, 8);
            row.addView(sessionIDTextView);

            TextView sessionEmailTextView = new TextView(this);
            sessionEmailTextView.setText(email);
            sessionEmailTextView.setPadding(8, 8, 8, 8);
            row.addView(sessionEmailTextView);

            TextView sessionUserIDTextView = new TextView(this);
            sessionUserIDTextView.setText(String.valueOf(user_id));
            sessionUserIDTextView.setPadding(8, 8, 8, 8);
            row.addView(sessionUserIDTextView);

            tableLayoutSession.addView(row);
        }

        cursor.close();
        db.close();

    }

    private void displayUsers() {
        SQLiteDatabase db = userDB.getReadableDatabase();
        String[] projection = {
                UserDB.ID_COL,
                UserDB.NAME,
                UserDB.EMAIL,
                UserDB.PASSWORD
        };

        Cursor cursor = db.query(UserDB.USERS_TABLE, projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(UserDB.ID_COL));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserDB.NAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UserDB.EMAIL));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserDB.PASSWORD));

            TableRow row = new TableRow(this);

            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(id));
            idTextView.setPadding(8, 8, 8, 8);
            row.addView(idTextView);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(name);
            nameTextView.setPadding(8, 8, 8, 8);
            row.addView(nameTextView);

            TextView emailTextView = new TextView(this);
            emailTextView.setText(email);
            emailTextView.setPadding(8, 8, 8, 8);
            row.addView(emailTextView);

            TextView passwordTextView = new TextView(this);
            passwordTextView.setText(password);
            passwordTextView.setPadding(8, 8, 8, 8);
            row.addView(passwordTextView);

            tableLayoutUsers.addView(row);
        }

        cursor.close();
        db.close();
    }
}
