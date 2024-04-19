package com.example.indisky;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyBookingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView nameTextViewMyBookings, originTextViewMyBookings, destTextViewMyBookings, dateTextViewMyBookings, priceTextViewMyBookings;

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
    }


    @Override
    public void onClick(View view) {

    }
}
