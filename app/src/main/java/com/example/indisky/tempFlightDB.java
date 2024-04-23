package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class tempFlightDB extends SQLiteOpenHelper {

    protected static final String DB_NAME = "indisky";

    private static final int DB_VERSION = 13;
    protected static final String TABLE_NAME = "TempFlight";
    protected static final String ORIGIN = "Origin";
    protected static final String DEST = "Dest";

    public tempFlightDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ORIGIN + " TEXT,"
                + DEST + " TEXT)";
        db.execSQL(query);
    }

    public void addOrigin(String originName) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if any data exists in the first row of the origin column
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{ORIGIN},
                null,
                null,
                null,
                null,
                null,
                "1" // Limit to 1 row
        );

        boolean dataExists = cursor.moveToFirst();
        cursor.close();

        ContentValues values = new ContentValues();
        values.put(ORIGIN, originName);

        if (dataExists) {
            // Update the first row
            db.update(TABLE_NAME, values, null, null);
        } else {
            // Insert a new row
            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }


    public void addDest(String destName) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if any data exists in the first row of the origin column
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{DEST},
                null,
                null,
                null,
                null,
                null,
                "1" // Limit to 1 row
        );

        boolean dataExists = cursor.moveToFirst();
        cursor.close();

        ContentValues values = new ContentValues();
        values.put(DEST, destName);

        if (dataExists) {
            // Update the first row
            db.update(TABLE_NAME, values, null, null);
        } else {
            // Insert a new row
            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

    public String getFirstOrigin() {
        SQLiteDatabase db = this.getReadableDatabase();
        String origin = null;

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{ORIGIN},
                null,
                null,
                null,
                null,
                null,
                "1" // Limit to 1 row
        );

        if (cursor.moveToFirst()) {
            origin = cursor.getString(cursor.getColumnIndex(ORIGIN));
        }

        cursor.close();
        db.close();

        return origin;
    }

    public String getFirstDest() {
        SQLiteDatabase db = this.getReadableDatabase();
        String dest = null;

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{DEST},
                null,
                null,
                null,
                null,
                null,
                "1" // Limit to 1 row
        );

        if (cursor.moveToFirst()) {
            dest = cursor.getString(cursor.getColumnIndex(DEST));
        }

        cursor.close();
        db.close();

        return dest;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

