package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class PatientSearchPage extends AppCompatActivity {

    SearchView searchView;
    ListView myListView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    //Apo PM
    Bundle extras;
    ArrayList<String> doctorData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_search_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Patient Search Page");

        //Apo PM
        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("userDataArrayList");

        searchView=findViewById(R.id.searchView);
        myListView=findViewById(R.id.listView);

        myListView.setVisibility(View.GONE);

        arrayList=new ArrayList<>();
        arrayList.add("Παπαδόπουλος Γρηγόριος");
        arrayList.add("Παπαδόπουλος Ευγένιος");
        arrayList.add("Tryal Patient");

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

        // Handle item click events in the myListView
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = adapter.getItem(position);
                // Display the selected item on the screen or perform any other action
                //Toast.makeText(PatientSearchPage.this, selectedItem, Toast.LENGTH_SHORT).show();
                goToPatientProfilePage(selectedItem);
            }
        });
    }



    public void goToPatientProfilePage(String patName){
        Intent intent = new Intent(getApplicationContext(),PatientProfilePage.class);
        intent.putExtra("userDataArrayList",doctorData);
        intent.putExtra("patientName",patName);
        startActivity(intent);
    }

    public void goToDoctorMainPage(View view){
        Intent intent = new Intent(getApplicationContext(),Main_doctor_page.class);
        intent.putExtra("userDataArrayList",doctorData);
        startActivity(intent);
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }

}

