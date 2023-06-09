package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    Bundle extras;
    ArrayList<String> doctorData;
    ArrayList<String> patientData;

    TextView patientNameView;
    TextView patientAMKAView;

    String patientName;
    String patientid;
    String doctorid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Σελίδα Προβολής Ιστορικού");

        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("doctorDataArrayList");
        patientData = extras.getStringArrayList("patientDataArrayList");
        patientName = extras.getString("patientName");

        patientNameView = findViewById(R.id.patientName);
        patientAMKAView = findViewById(R.id.patientAmka);

        patientNameView.setText(patientData.get(1));
        patientAMKAView.setText(patientData.get(0));

        //save IDs
        doctorid = doctorData.get(0);
        patientid = patientData.get(0);


        List<String> list = new ArrayList<>();
        //Get history data from database
        try {
            list = getHistoryData(patientid, doctorid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Separate data from list, into Services and Dates
        int listHalfSize = list.size()/2;
        List<String> listServices = new ArrayList<>();
        List<String> listDates = new ArrayList<>();

        for(int i=0; i<list.size();i++)
        {
            //η list περιέχει μέχρι το 1ο μισό της όλες τις υπηρεσίες του ιστορικού του
            //συγκεκριμένου ασθενή, γι'αυτό και μέχρι εκεί τις αποθηκεύω στη λίστα listServices
            if (i < listHalfSize) {
                listServices.add(list.get(i));
            }
            //ενώ στο 2ο μισό της list, περιέχονται όλες οι ημερομηνίες των υπηρεσιών του ασθενή,
            //γι'αυτό μόλις φτάσει ο μετρητής στη μέση, απο κει τις αποθηκεύω στη λίστα listDates
            else {
                listDates.add(list.get(i));
            }
        }

        //Send data from list to the recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(listServices, listDates, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryPage.this));
    }


    //Get history data from server for patient with the given patientid
    public List<String> getHistoryData (String patientid, String doctorid) throws IOException {
        //Create url (parse the patientid with GET method)
        String url = "http://10.0.2.2/AndroidStudioProviders/patientHistory.php?patientid="+patientid+"&doctorid="+doctorid;

        //Search for patient and save history data to history ArrayList
        OkHttpMediator mediator = new OkHttpMediator();
        ArrayList<String> history = mediator.patientHistory(url);

        return history;
    }

    public void goToPatientProfilePage(View view){
        Intent intent = new Intent(getApplicationContext(),PatientProfilePage.class);
        intent.putExtra("userDataArrayList",doctorData);
        intent.putExtra("patientName",patientData);
        startActivity(intent);
    }


    //Block back button
        @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // do nothing.
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

}