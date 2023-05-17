package com.example.physiotherapycenterapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("History Page");

        List<HistoryData> list = new ArrayList<>();
        list = getData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new RecyclerViewAdapter(list, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryPage.this));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //Sample data for RecyclerView. Will be deleted.
    private List<HistoryData> getData(){
        List<HistoryData> list = new ArrayList<>();
        list.add(new HistoryData("Αθλητική Μάλαξη", "3/4/23"));
        list.add(new HistoryData("Μυοχαλαρωτικό Μασάζ", "3/4/23"));
        list.add(new HistoryData("Πρεσσοθεραπεία", "5/11/22"));
        list.add(new HistoryData("Αθλητική Μάλαξη", "21/10/22"));
        list.add(new HistoryData("Αθλητική Μάλαξη", "10/7/22"));
        list.add(new HistoryData("Μυοχαλαρωτικό Μασάζ", "17/5/22"));
        list.add(new HistoryData("Πρεσσοθεραπεία", "1/3/22"));
        list.add(new HistoryData("Αθλητική Μάλαξη", "13/12/21"));
        return list;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}