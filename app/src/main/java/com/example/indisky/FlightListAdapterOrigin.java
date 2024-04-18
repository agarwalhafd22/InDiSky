package com.example.indisky;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class FlightListAdapterOrigin extends CursorAdapter {

    public FlightListAdapterOrigin(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.flight_list_origin, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView originTextView = view.findViewById(R.id.originTextView);
//        TextView destinationTextView = view.findViewById(R.id.destinationTextView);
//        TextView departDateTextView = view.findViewById(R.id.departDateTextView);
//        TextView arrivalDateTextView = view.findViewById(R.id.arrivalDateTextView);
//        TextView priceTextView = view.findViewById(R.id.priceTextView);
//        TextView seatsAvailableTextView = view.findViewById(R.id.seatsAvailableTextView);

        String origin = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.ORIGIN));
//        String destination = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.DEST));
//        String departDate = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.DEPART_DATE));
//        String arrivalDate = cursor.getString(cursor.getColumnIndexOrThrow(FlightDB.ARRIVAL_DATE));
//        int price = cursor.getInt(cursor.getColumnIndexOrThrow(FlightDB.PRICE));
//        int seatsAvailable = cursor.getInt(cursor.getColumnIndexOrThrow(FlightDB.SEAT_AVAIL));

        originTextView.setText(origin);
//        destinationTextView.setText(destination);
//        departDateTextView.setText(departDate);
//        arrivalDateTextView.setText(arrivalDate);
//        priceTextView.setText(String.valueOf(price));
//        seatsAvailableTextView.setText(String.valueOf(seatsAvailable));
    }
}
