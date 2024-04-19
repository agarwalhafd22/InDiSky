package com.example.indisky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsViewHolder> {

    List<MyBookingItems> dataList;

    public MyBookingsAdapter(List<MyBookingItems> dataList) { this.dataList = dataList;}


    @NonNull
    @Override
    public MyBookingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybookingslist, parent, false);
        return new MyBookingsViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingsViewHolder holder, int position) {
        MyBookingItems data = dataList.get(position);
        holder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
