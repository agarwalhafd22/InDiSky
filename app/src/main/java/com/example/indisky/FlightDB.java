package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FlightDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 5;
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

