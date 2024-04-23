package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PaymentDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 13;
    protected static final String TABLE_NAME = "Payment";

    protected static final String PAYMENT_ID = "Payment_ID";
    protected static final String BOOKING_ID = "Booking_ID";
    protected static final String DATE = "Date";
    protected static final String METHOD = "Method";
    protected static final String AMOUNT = "Amount";
    public PaymentDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + PAYMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BOOKING_ID + " INTEGER,"
                + DATE + " TEXT,"
                + METHOD + " TEXT DEFAULT 'Online',"
                + AMOUNT + " INT)";
        db.execSQL(query);
    }

    public void addNewPayment(int booking_id, String date, int amount) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BOOKING_ID, booking_id);
        values.put(DATE, date);
        values.put(AMOUNT, amount);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void deletePaymentByBookingIDs(List<Integer> bookingIDs) {
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

    public int getPaymentIDByBookingID(int bookingID) {
        SQLiteDatabase db = this.getReadableDatabase();
        int paymentID = -1; // Default value if no matching record is found

        // Define the columns you want to retrieve
        String[] columns = {PAYMENT_ID};

        // Define the selection criteria
        String selection = BOOKING_ID + " = ?";

        // Define the selection arguments
        String[] selectionArgs = {String.valueOf(bookingID)};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            // Retrieve the payment ID from the cursor
            paymentID = cursor.getInt(cursor.getColumnIndex(PAYMENT_ID));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return paymentID;
    }

    public int getAmountByBookingID(int bookingID) {
        SQLiteDatabase db = this.getReadableDatabase();
        int amount = -1; // Default value if no matching record is found

        // Define the columns you want to retrieve
        String[] columns = {AMOUNT};

        // Define the selection criteria
        String selection = BOOKING_ID + " = ?";

        // Define the selection arguments
        String[] selectionArgs = {String.valueOf(bookingID)};

        // Execute the query
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has any rows
        if (cursor.moveToFirst()) {
            // Retrieve the amount from the cursor
            amount = cursor.getInt(cursor.getColumnIndex(AMOUNT));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        return amount;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

