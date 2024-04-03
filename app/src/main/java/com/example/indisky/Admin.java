package com.example.indisky;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    private UserDB userDB; // Assuming UserDB is your SQLiteOpenHelper class
    private TableLayout tableLayoutUsers, tableLayoutSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userDB = new UserDB(this);
        tableLayoutUsers = findViewById(R.id.tableLayoutUsers);
        tableLayoutSession = findViewById(R.id.tableLayoutSession);

        displayUsers();
        displaySession();
    }

    private void displaySession() {
        SQLiteDatabase db = userDB.getReadableDatabase();
        String[] projection = {
                UserDB.SESSION_ID_COL,
                UserDB.SESSION_USER_EMAIL_COL,
                UserDB.SESSION_USER_ID_COL
        };

        Cursor cursor = db.query(UserDB.SESSION_TABLE, projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int session_id = cursor.getInt(cursor.getColumnIndexOrThrow(UserDB.SESSION_ID_COL));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UserDB.SESSION_USER_EMAIL_COL));
            int user_id = cursor.getInt(cursor.getColumnIndexOrThrow(UserDB.SESSION_USER_ID_COL));

            TableRow row = new TableRow(this);

            TextView sessionIDTextView = new TextView(this);
            sessionIDTextView.setText(String.valueOf(session_id));
            sessionIDTextView.setPadding(8, 8, 8, 8);
            row.addView(sessionIDTextView);

            TextView sessionEmailTextView = new TextView(this);
            sessionEmailTextView.setText(email);
            sessionEmailTextView.setPadding(8, 8, 8, 8);
            row.addView(sessionEmailTextView);

            TextView sessionUserIDTextView = new TextView(this);
            sessionUserIDTextView.setText(String.valueOf(user_id));
            sessionUserIDTextView.setPadding(8, 8, 8, 8);
            row.addView(sessionUserIDTextView);

            tableLayoutSession.addView(row);
        }

        cursor.close();
        db.close();

    }

    private void displayUsers() {
        SQLiteDatabase db = userDB.getReadableDatabase();
        String[] projection = {
                UserDB.ID_COL,
                UserDB.NAME,
                UserDB.EMAIL,
                UserDB.PASSWORD
        };

        Cursor cursor = db.query(UserDB.USERS_TABLE, projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(UserDB.ID_COL));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserDB.NAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UserDB.EMAIL));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserDB.PASSWORD));

            TableRow row = new TableRow(this);

            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(id));
            idTextView.setPadding(8, 8, 8, 8);
            row.addView(idTextView);

            TextView nameTextView = new TextView(this);
            nameTextView.setText(name);
            nameTextView.setPadding(8, 8, 8, 8);
            row.addView(nameTextView);

            TextView emailTextView = new TextView(this);
            emailTextView.setText(email);
            emailTextView.setPadding(8, 8, 8, 8);
            row.addView(emailTextView);

            TextView passwordTextView = new TextView(this);
            passwordTextView.setText(password);
            passwordTextView.setPadding(8, 8, 8, 8);
            row.addView(passwordTextView);

            tableLayoutUsers.addView(row);
        }

        cursor.close();
        db.close();
    }
}
