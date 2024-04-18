package com.example.indisky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {

    private static final String DB_NAME="indisky";

    private static final int DB_VERSION=9;

    protected static final String USERS_TABLE="Users";

    protected static final String ID_COL="ID";

    protected static final String NAME="Name";

    protected static final String EMAIL="Email_ID";

    protected static final String PASSWORD="Password";

    protected static final String SESSION_TABLE = "Session";
    protected static final String SESSION_ID_COL = "SessionID";
    protected static final String SESSION_USER_EMAIL_COL = "UserEmail";
    protected static final String SESSION_USER_ID_COL = "User_ID";


    public UserDB (Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        String createUserTableQuery = "CREATE TABLE " + USERS_TABLE + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + EMAIL + " TEXT,"
                + PASSWORD + " TEXT)";
        db.execSQL(createUserTableQuery);

        String createSessionTableQuery = "CREATE TABLE " + SESSION_TABLE + " ("
                + SESSION_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SESSION_USER_EMAIL_COL + " TEXT, "
                + SESSION_USER_ID_COL + " INTEGER)";
        db.execSQL(createSessionTableQuery);

    }

    public void addNewUser(String name, String email, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);

        db.insert(USERS_TABLE, null,values);
        db.close();
    }

    public int loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the query string with placeholders for selection arguments
        String query = "SELECT * FROM Users WHERE Email_ID = ? AND Password = ?";

        // Specify the selection arguments
        String[] selectionArgs = {email, password};

        // Execute the raw query
        Cursor cursor = db.rawQuery(query, selectionArgs);

        int userID=0;

        if(cursor.moveToFirst())
        {
            int idColumnIndex=cursor.getColumnIndex(ID_COL);
            if(idColumnIndex>=0)
                userID = cursor.getInt(idColumnIndex);

            cursor.close();
            db.close();
            return userID;
        }


        cursor.close();
        db.close();

        return 0;
    }

    public void createSession(String userEmail, int userID)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SESSION_USER_EMAIL_COL, userEmail);
        values.put(SESSION_USER_ID_COL, userID);
        db.insert(SESSION_TABLE, null, values);
        db.close();
    }

    public boolean isUserLoggedIn()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        boolean loggedIn=false;
        try {
            String query = "SELECT * FROM " + SESSION_TABLE;
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

        String query = "SELECT *FROM "+SESSION_TABLE;

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

    public String getLoggedInUserName(String userEmail)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        String getNameQuery = "SELECT " + NAME + " FROM " + USERS_TABLE + " WHERE " + EMAIL + " = '" + userEmail + "'";

        Cursor cursor = db.rawQuery(getNameQuery, null);

        String userName = null;

        if (cursor != null && cursor.moveToFirst())
        {
            int nameColumnIndex=cursor.getColumnIndex(NAME);
            if(nameColumnIndex>=0)
                userEmail = cursor.getString(nameColumnIndex);
            cursor.close(); // Close the cursor after use
        }

        return userEmail;
    }

    public  void logout()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SESSION_TABLE, null, null);
        db.close();
    }

    public int getSessionUserID() {
        SQLiteDatabase db = this.getReadableDatabase();
        int userID = 0;

        try {
            // Define the query to retrieve the user ID from the session table
            String query = "SELECT " + SESSION_USER_ID_COL + " FROM " + SESSION_TABLE;

            // Execute the query
            Cursor cursor = db.rawQuery(query, null);

            // Check if the cursor has any rows
            if (cursor.moveToFirst()) {
                // Retrieve the user ID from the cursor
                userID = cursor.getInt(cursor.getColumnIndex(SESSION_USER_ID_COL));
            }

            cursor.close();
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        } finally {
            db.close();
        }

        return userID;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 3 && newVersion == 3) {
            db.execSQL("ALTER TABLE " + SESSION_TABLE + " ADD COLUMN " + SESSION_USER_ID_COL + " INTEGER");
        }

    }
}
