package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;


public class ChooseCity extends AppCompatActivity {

    private ListView listView;
    private FlightDB flightDB;

    Toolbar searchToolbar;

    String[] originValues = new String[50];

    String[] destValues = new String[50];

    String origin;

    tempFlightDB tmpFlightDB;


    int Status = 0;


    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        tmpFlightDB = new tempFlightDB(this);


        Intent intent = getIntent();
        String data = intent.getStringExtra("type");

        listView = findViewById(R.id.listview);
        flightDB = new FlightDB(this);
        searchToolbar=findViewById(R.id.searchToolbar);

        setSupportActionBar(searchToolbar);
        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (Exception e){}

        if(data.equals("origin")) {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // Retrieve the clicked item from the adapter
                    String selectedItem = (String) adapterView.getItemAtPosition(position);
                    tmpFlightDB.addOrigin(selectedItem);
                    Intent intent1 = new Intent(ChooseCity.this, MainActivity.class);
                    intent.putExtra("fragmentIndex", 1);
                    startActivity(intent1);
                    finish();

                }
            });

            SQLiteDatabase db = flightDB.getReadableDatabase();
            Cursor cursor = db.rawQuery(
                    "SELECT DISTINCT Origin FROM Flight",
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                int i = 0;
                do {
                    int c = cursor.getColumnIndex("Origin");
                    String origin = "hello";
                    if (c >= 0)
                        origin = cursor.getString(c);
                    originValues[i++] = origin;
                } while (cursor.moveToNext());

                cursor.close();
            } else {
                if (cursor != null) {
                    cursor.close();
                }
            }



            int count = 0;

            for (String str : originValues) {
                if (str != null && !str.isEmpty()) {
                    count++;
                }
            }

            String[] mainList = new String[count];
            for(int i=0;i<count;i++){
                mainList[i] = originValues[i];
            }

            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mainList);
            listView.setAdapter(arrayAdapter);
        }

        else {

            String tempOrigin = tmpFlightDB.getFirstOrigin();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    // Retrieve the clicked item from the adapter
                    String selectedItem = (String) adapterView.getItemAtPosition(position);
                    tmpFlightDB.addDest(selectedItem);
                    Status = 1;
                    Intent intent1 = new Intent(ChooseCity.this, MainActivity.class);
                    intent.putExtra("fragmentIndex", 1);
                    startActivity(intent1);
                    finish();

                }
            });

            SQLiteDatabase db = flightDB.getReadableDatabase();
            Cursor cursor = db.rawQuery(
                    "SELECT DISTINCT Dest FROM Flight",
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                int i = 0;
                do {
                    int c = cursor.getColumnIndex("Dest");
                    String dest = "hello";
                    if (c >= 0)
                        dest = cursor.getString(c);
                    destValues[i++] = dest;
                } while (cursor.moveToNext());

                cursor.close();
            } else {
                if (cursor != null) {
                    cursor.close();
                }
            }

            int count = 0;

            for (String str : destValues) {
                if (str != null && !str.isEmpty()) {
                    count++;
                }
            }


            String[] mainList = new String[count];
            for(int i=0;i<count;i++){
                mainList[i] = destValues[i];
            }

            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mainList);
            listView.setAdapter(arrayAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search for cities...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public String originName() {
        return origin;
    }

    public int status() {
        return Status;
    }
}