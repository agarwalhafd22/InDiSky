package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookingDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 9;
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



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

