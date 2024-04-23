package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AirportDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 13;
    protected static final String TABLE_NAME = "Airport";
    protected static final String AIRPORT_ID = "Airport_ID";

    protected static final String FLIGHT_ID = "Flight_ID";
    protected static final String CITY = "City";
    protected static final String COUNTRY = "Country";

    protected static final String AIRPORT_NAME = "Airport_Name";

    public AirportDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + AIRPORT_ID + " TEXT,"
                + FLIGHT_ID + " TEXT, "
                + CITY + " TEXT,"
                + COUNTRY + " TEXT DEFAULT 'India',"
                + AIRPORT_NAME + " TEXT)";
        db.execSQL(query);
    }

    public void addNewFlightDetail(String airportID, String flightID, String city, String airportName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AIRPORT_ID, airportID);
        values.put(FLIGHT_ID, flightID);
        values.put(CITY, city);
        values.put(AIRPORT_NAME, airportName);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

