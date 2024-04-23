package com.example.indisky;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyBookingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView nameTextViewMyBookings, originTextViewMyBookings, destTextViewMyBookings, dateTextViewMyBookings, priceTextViewMyBookings;

    String flightID;

    int bookingID;

    Context context;

    public MyBookingsViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        nameTextViewMyBookings=itemView.findViewById(R.id.nameTextViewMyBookings);
        originTextViewMyBookings=itemView.findViewById(R.id.originTextViewMyBookings);
        destTextViewMyBookings=itemView.findViewById(R.id.destTextViewMyBookings);
        dateTextViewMyBookings=itemView.findViewById(R.id.dateTextViewMyBookings);
        priceTextViewMyBookings=itemView.findViewById(R.id.priceTextViewMyBookings);
        this.context = context;
        itemView.setOnClickListener(this);
    }

    public void bindData(MyBookingItems data) {
        nameTextViewMyBookings.setText(data.getName());
        originTextViewMyBookings.setText(data.getOrigin());
        destTextViewMyBookings.setText(data.getDest());
        dateTextViewMyBookings.setText(data.getDate());
        priceTextViewMyBookings.setText("₹"+Integer.toString(data.getPrice()));
        flightID=data.getFlightID();
        bookingID=data.getBookingID();
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, IndividualBookingDetails.class);
        intent.putExtra("flightID", flightID);
        intent.putExtra("bookingID", bookingID);
        context.startActivity(intent);
    }
}
