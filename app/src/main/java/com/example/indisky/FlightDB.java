package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FlightDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 9;
    protected static final String TABLE_NAME = "Flight";
    protected static final String FLIGHT_ID = "Flight_ID";
    protected static final String ORIGIN = "Origin";
    protected static final String DEST = "Dest";
    protected static final String DEPART_DATE = "Depart_Date";

    protected static final String ARRIVAL_DATE = "Arrival_Date";
    protected static final String PRICE = "Price";
    protected static final String SEAT_AVAIL = "Seat_Avail";
    public FlightDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + FLIGHT_ID + " TEXT PRIMARY KEY, "
                + ORIGIN + " TEXT,"
                + DEST + " TEXT,"
                + DEPART_DATE + " TEXT,"
                + ARRIVAL_DATE + " TEXT,"
                + PRICE + " INT,"
                + SEAT_AVAIL + " INT)";
        db.execSQL(query);
    }

    public void addNewFlightDetail(String id, String origin, String dest, String depart, String arrive, int price, int seat) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FLIGHT_ID, id);
        values.put(ORIGIN, origin);
        values.put(DEST, dest);
        values.put(DEPART_DATE, depart);
        values.put(ARRIVAL_DATE, arrive);
        values.put(PRICE, price);
        values.put(SEAT_AVAIL, seat);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public int getPriceByOriginAndDestAndDate(String origin, String dest, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        int price = -1; // Default value if no matching record is found

        // Define the columns you want to retrieve
        String[] columns = {PRICE};

        // Define the selection criteria
        String selection = ORIGIN + " = ? AND " + DEST + " = ? AND " + DEPART_DATE + " = ?";

        // Define the selection arguments
        String[] selectionArgs = {origin, dest, date};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            // Retrieve the price from the cursor
            price = cursor.getInt(cursor.getColumnIndex(PRICE));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return price;
    }

    public int getPriceByFlightID(String flightID) {
        SQLiteDatabase db = this.getReadableDatabase();
        int price = -1; // Default value if no matching record is found

        // Define the columns you want to retrieve
        String[] columns = {PRICE};

        // Define the selection criteria
        String selection = FLIGHT_ID+ " = ?";

        // Define the selection arguments
        String[] selectionArgs = {flightID};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            // Retrieve the price from the cursor
            price = cursor.getInt(cursor.getColumnIndex(PRICE));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return price;
    }


    public int getSeatsByFlightID(String flightID) {
        SQLiteDatabase db = this.getReadableDatabase();
        int seats = -1; // Default value if no matching record is found

        // Define the columns you want to retrieve
        String[] columns = {SEAT_AVAIL};

        // Define the selection criteria
        String selection = FLIGHT_ID + " = ?";

        // Define the selection arguments
        String[] selectionArgs = {flightID};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            // Retrieve the price from the cursor
            seats = cursor.getInt(cursor.getColumnIndex(SEAT_AVAIL));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return seats;
    }

    public String getFlightIDByOriginAndDestAndDate(String origin, String dest, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String flightID=null;

        // Define the columns you want to retrieve
        String[] columns = {FLIGHT_ID};

        // Define the selection criteria
        String selection = ORIGIN + " = ? AND " + DEST + " = ? AND " + DEPART_DATE + " = ?";

        // Define the selection arguments
        String[] selectionArgs = {origin, dest, date};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            // Retrieve the price from the cursor
            flightID = cursor.getString(cursor.getColumnIndex(FLIGHT_ID));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return flightID;
    }

    public void reduceSeatAvailability(String origin, String dest, String date, int currentSeats) {
        SQLiteDatabase db = this.getWritableDatabase();


        // Reduce the available seats by one
        int updatedSeats = currentSeats - 1;
        if (updatedSeats < 0) {
            // Ensure that the number of available seats does not go below zero
            updatedSeats = 0;
        }

        // Prepare content values with the updated seat availability
        ContentValues values = new ContentValues();
        values.put(SEAT_AVAIL, updatedSeats);

        // Define the update criteria
        String selection = ORIGIN + " = ? AND " + DEST + " = ? AND " + DEPART_DATE + " = ?";
        String[] selectionArgs = {origin, dest, date};

        // Execute the update query
        db.update(TABLE_NAME, values, selection, selectionArgs);

        // Close the database
        db.close();
    }

    public List<SearchFlightItems> getFlightsByOriginDestDate(String origin, String dest, String date) {
        List<SearchFlightItems> flights = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {FLIGHT_ID, DEPART_DATE, ARRIVAL_DATE, PRICE};
        String selection = ORIGIN + " = ? AND " + DEST + " = ? AND " + DEPART_DATE + " = ?";
        String[] selectionArgs = {origin, dest, date};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String flightID = cursor.getString(cursor.getColumnIndex(FLIGHT_ID));
                String flightDepartDate = cursor.getString(cursor.getColumnIndex(DEPART_DATE));
                String flightArrivalDate = cursor.getString(cursor.getColumnIndex(ARRIVAL_DATE));
                int flightPrice = cursor.getInt(cursor.getColumnIndex(PRICE));

                SearchFlightItems flight = new SearchFlightItems(flightDepartDate, flightArrivalDate, flightID,  flightPrice);
                flights.add(flight);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return flights;
    }


    public Cursor getOriginFlights(SQLiteDatabase db) {
        return db.rawQuery("SELECT Origin FROM " + TABLE_NAME, null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

