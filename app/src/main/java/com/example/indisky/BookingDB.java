package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BookingDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 13;
    protected static final String TABLE_NAME = "Booking";
    protected static final String BOOKING_ID = "Booking_ID";
    protected static final String USER_ID = "User_ID";
    protected static final String FLIGHT_ID = "Flight_ID";
    protected static final String BOOKING_DATE = "bookingDate";
    protected static final String PRICE = "Price";
    public BookingDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_ID + " INTEGER,"
                + FLIGHT_ID + " TEXT,"
                + BOOKING_DATE + " TEXT,"
                + PRICE + " INT)";
        db.execSQL(query);
    }

    public void addNewBooking(int user_id, String flight_id, String booking_date, int price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_ID, user_id);
        values.put(FLIGHT_ID, flight_id);
        values.put(BOOKING_DATE, booking_date);
        values.put(PRICE, price);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }


    public int getLastInsertedBookingID() {
        SQLiteDatabase db = this.getReadableDatabase();
        int bookingID = -1;

        // Query to retrieve the maximum value of the Booking_ID column
        Cursor cursor = db.rawQuery("SELECT MAX(" + BOOKING_ID + ") FROM " + TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            bookingID = cursor.getInt(0);
            cursor.close();
        }

        db.close();

        return bookingID;
    }

    public List<Integer> getBookingIDsByUserID(int userID) {
        List<Integer> bookingIDs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{BOOKING_ID},
                USER_ID + "=?", new String[]{String.valueOf(userID)},
                null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int bookingID = cursor.getInt(cursor.getColumnIndex(BOOKING_ID));
                    bookingIDs.add(bookingID);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        db.close();
        return bookingIDs;
    }

    public String getFlightIDByBookingID(int bookingID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String flightID = null;
        Cursor cursor = db.query(TABLE_NAME, new String[]{FLIGHT_ID},
                BOOKING_ID + "=?", new String[]{String.valueOf(bookingID)},
                null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                flightID = cursor.getString(cursor.getColumnIndex(FLIGHT_ID));
            }
            cursor.close();
        }

        db.close();
        return flightID;
    }

    public int getBookingIDbyFlightID(String flightID) {
        SQLiteDatabase db = this.getReadableDatabase();
        int bookingID = 0;
        Cursor cursor = db.query(TABLE_NAME, new String[]{BOOKING_ID},
                FLIGHT_ID + "=?", new String[]{String.valueOf(flightID)},
                null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                bookingID = cursor.getInt(cursor.getColumnIndex(BOOKING_ID));
            }
            cursor.close();
        }

        db.close();
        return bookingID;
    }

    public List<Integer> deleteBookingByUserID(int userID) {
        List<Integer> deletedBookingIDs = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the where clause
        String selection = USER_ID + " = ?";

        // Specify the selection arguments
        String[] selectionArgs = {String.valueOf(userID)};

        // Query the database to get the booking IDs of the bookings being deleted
        Cursor cursor = db.query(TABLE_NAME, new String[]{BOOKING_ID}, selection, selectionArgs, null, null, null);

        // Iterate through the cursor to retrieve the booking IDs
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int bookingID = cursor.getInt(cursor.getColumnIndex(BOOKING_ID));
                deletedBookingIDs.add(bookingID);
            } while (cursor.moveToNext());
            cursor.close();
        }

        // Delete the row(s)
        db.delete(TABLE_NAME, selection, selectionArgs);

        db.close();

        // Return the list of deleted booking IDs
        return deletedBookingIDs;
    }

    public String getBookingDateByBookingID(int bookingID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String bookingDate = null;

        // Define the columns you want to retrieve
        String[] columns = {BOOKING_DATE};

        // Define the selection criteria
        String selection = BOOKING_ID + " = ?";

        // Define the selection arguments
        String[] selectionArgs = {String.valueOf(bookingID)};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            // Retrieve the booking date from the cursor
            bookingDate = cursor.getString(cursor.getColumnIndex(BOOKING_DATE));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return bookingDate;
    }







    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

