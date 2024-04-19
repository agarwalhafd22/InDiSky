package com.example.indisky;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SearchFlightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView originDateTextView;
    TextView destDateTextView;
    TextView flightIDTextView;
    TextView priceSearchFlightTextView;
    String flightID, dateCheck;
    Context context;

    public SearchFlightViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        originDateTextView = itemView.findViewById(R.id.originDateTextView);
        destDateTextView = itemView.findViewById(R.id.destDateTextView);
        flightIDTextView = itemView.findViewById(R.id.flightIDTextView);
        priceSearchFlightTextView = itemView.findViewById(R.id.priceSearchFlightTextView);
        this.context = context;
        itemView.setOnClickListener(this);
    }

    public void bindData(SearchFlightItems data) {
        originDateTextView.setText(data.getOriginDate());
        destDateTextView.setText(data.getDestDate());
        flightIDTextView.setText(data.getFlightID());
        priceSearchFlightTextView.setText("₹"+String.valueOf(data.getPrice()));
        flightID = data.getFlightID();
        dateCheck = data.getOriginDate();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, PassengerDetails.class);
        intent.putExtra("flightID", flightID);
        intent.putExtra("dateCheck", dateCheck);
        context.startActivity(intent);
    }
}

