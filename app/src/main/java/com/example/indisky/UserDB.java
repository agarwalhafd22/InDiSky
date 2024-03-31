package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {

    private static final String DB_NAME="indisky";

    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="Users";

    private static final String ID_COL="ID";

    private static final String NAME="Name";

    private static final String EMAIL="Email_ID";

    private static final String PASSWORD="Password";


    public UserDB (Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + EMAIL + " TEXT,"
                + PASSWORD + " TEXT)";

        db.execSQL(query);
    }

    public void addNewUser(String name, String email, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);

        db.insert(TABLE_NAME, null,values);
        db.close();
    }

    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the query string with placeholders for selection arguments
        String query = "SELECT * FROM Users WHERE Email_ID = ? AND Password = ?";

        // Specify the selection arguments
        String[] selectionArgs = {email, password};

        // Execute the raw query
        Cursor cursor = db.rawQuery(query, selectionArgs);

        // Check if the cursor contains any rows
        boolean loggedIn = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return loggedIn;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
