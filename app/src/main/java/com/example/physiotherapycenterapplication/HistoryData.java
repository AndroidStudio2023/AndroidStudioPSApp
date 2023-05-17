package com.example.physiotherapycenterapplication;

/** HistoryData class:
 * this java class acts as a structure for holding the
 * information for every item of the RecyclerView.*/

public class HistoryData {
    String paroxi;
    String date;

    public HistoryData(String paroxi, String date){
        this.paroxi = paroxi;
        this.date = date;
    }
}
