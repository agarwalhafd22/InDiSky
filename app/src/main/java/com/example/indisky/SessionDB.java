package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SessionDB extends SQLiteOpenHelper {

    private static final String DB_NAME="indisky2";

    private static final int DB_VERSION=1;

    private static final String TABLE_NAME = "Session";
    private static final String SESSION_ID_COL = "SessionID";
    private static final String SESSION_USER_EMAIL_COL = "UserEmail";


    public SessionDB (Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String querySession = "CREATE TABLE " + TABLE_NAME + " ("
                + SESSION_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +SESSION_USER_EMAIL_COL + " TEXT)";

        db.execSQL(querySession);
    }
    public void createSession(String userEmail)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SESSION_USER_EMAIL_COL, userEmail);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean isUserLoggedIn()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        boolean loggedIn=false;
        try {
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(query, null);

            loggedIn = cursor.moveToFirst();

            cursor.close();
        }
        catch (Exception e){}
        finally {
            db.close();
        }

        return loggedIn;
    }

    public String getLoggedInUserEmail()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        String query = "SELECT *FROM "+TABLE_NAME;

        Cursor cursor =db.rawQuery(query, null);
        String userEmail = null;
        if(cursor.moveToFirst())
        {
            int emailColumnIndex=cursor.getColumnIndex(SESSION_USER_EMAIL_COL);
            if(emailColumnIndex>=0)
                userEmail = cursor.getString(emailColumnIndex);
        }
        cursor.close();
        db.close();

        return userEmail;
    }

    public  void logout()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
