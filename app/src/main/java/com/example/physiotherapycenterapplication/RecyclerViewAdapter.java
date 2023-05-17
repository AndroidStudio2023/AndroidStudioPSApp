package com.example.physiotherapycenterapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/** RecyclerViewAdapter class:
 * This class implements the adapter which includes the main code responsible for RecyclerView.
 * It holds all the important methods dealing with the implementation of RecyclerView.*/

public class RecyclerViewAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    List<HistoryData> list = Collections.emptyList();
    Context context;

    public RecyclerViewAdapter(List<HistoryData> list, Context context){
        this.list = list;
        this.context = context;
    }

    //onCreateViewHolder: deals with the inflation of the card layout as an item for the RecyclerView.
    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the layout
        View view = inflater.inflate(R.layout.history_card_layout, parent, false);
        HistoryViewHolder viewHolder = new HistoryViewHolder(view);
        return viewHolder;
    }

    //onBindViewHolder: deals with the setting of different data and
    //                  methods related to clicks on particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(final HistoryViewHolder viewHolder, final int position) {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.paroxi.setText(list.get(position).paroxi);
        viewHolder.date.setText(list.get(position).date);
    }

    //getItemCount: returns the length of the RecyclerView.
    @Override
    public int getItemCount() {
        return list.size();
    }

    //onAttachedToRecyclerView: attaches the adapter to the RecyclerView.
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
