package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class PassengerDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 11;
    protected static final String TABLE_NAME = "Passenger";
    protected static final String PASSENGER_ID = "Passenger_ID";
    protected static final String BOOKING_ID = "Booking_ID";
    protected static final String NAME = "Name";
    protected static final String AGE = "Age";
    protected static final String GENDER = "Gender";
    public PassengerDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + PASSENGER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BOOKING_ID + " INTEGER,"
                + NAME + " TEXT,"
                + AGE + " INTEGER,"
                + GENDER + " TEXT)";
        db.execSQL(query);
    }

    public void addNewPassenger(int booking_id, String name, int age, String gender) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BOOKING_ID, booking_id);
        values.put(NAME, name);
        values.put(AGE, age);
        values.put(GENDER, gender);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public String getNameByBookingID(int bookingID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name = null;

        // Define the columns you want to retrieve
        String[] columns = {NAME};

        // Define the selection criteria
        String selection = BOOKING_ID + " = ?";

        // Define the selection arguments
        String[] selectionArgs = {String.valueOf(bookingID)};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            // Retrieve the name from the cursor
            name = cursor.getString(cursor.getColumnIndex(NAME));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return name;
    }

    public void deletePassengerByBookingIDs(List<Integer> bookingIDs) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Iterate through the list of booking IDs
        for (int bookingID : bookingIDs) {
            // Define the where clause
            String selection = BOOKING_ID + " = ?";

            // Specify the selection arguments
            String[] selectionArgs = {String.valueOf(bookingID)};

            // Delete the row(s) for the current booking ID
            db.delete(TABLE_NAME, selection, selectionArgs);
        }

        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

