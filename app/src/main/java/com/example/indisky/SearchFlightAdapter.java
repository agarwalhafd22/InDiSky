package com.example.indisky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchFlightAdapter extends RecyclerView.Adapter<SearchFlightViewHolder> {

    List<SearchFlightItems> dataList;

    public SearchFlightAdapter(List<SearchFlightItems> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public SearchFlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchflightlist, parent, false);
        return new SearchFlightViewHolder(itemView, context);    }

    @Override
    public void onBindViewHolder(@NonNull SearchFlightViewHolder holder, int position) {
        SearchFlightItems data = dataList.get(position);
        holder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
