package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class PatientSearchPage extends AppCompatActivity {

    SearchView searchView;
    ListView myListView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Patient Search Page");

        searchView=findViewById(R.id.searchView);
        myListView=findViewById(R.id.listView);

        myListView.setVisibility(View.GONE);

        arrayList=new ArrayList<>();
        arrayList.add("Παπαδόπουλος Γρηγόριος");
        arrayList.add("Παπαδόπουλος Ευγένιος");

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);

        myListView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myListView.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(s);
                return false;
            }
        });

    }

}

