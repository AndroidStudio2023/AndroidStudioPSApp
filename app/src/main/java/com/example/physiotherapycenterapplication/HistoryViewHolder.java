package com.example.physiotherapycenterapplication;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/** HistoryViewHolder class:
 * this java class  stores the reference to the card layout views that have
 * to be dynamically modified during the execution of the program by a list
 * of data obtained either by online databases or added in some other way.*/

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    TextView paroxi;
    TextView date;
    View view;

    HistoryViewHolder(View itemView){
        super(itemView);
        paroxi = (TextView) itemView.findViewById(R.id.paroxi);
        date = (TextView) itemView.findViewById(R.id.date);
        view = itemView;
    }

}
