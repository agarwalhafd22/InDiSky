package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PassengerDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 9;
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


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

