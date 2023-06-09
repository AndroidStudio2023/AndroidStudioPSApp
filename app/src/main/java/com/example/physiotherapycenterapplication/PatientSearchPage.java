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
    ArrayList<ArrayList<String>> patientsData;
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
        //----------------------------------------------------------------//

        searchView=findViewById(R.id.searchView);
        myListView=findViewById(R.id.listView);

        myListView.setVisibility(View.GONE);

        arrayList=new ArrayList<>();
        //Mediator for load patients
        String url ="http://10.0.2.2/AndroidStudioProviders/findPatient.php?docID="+doctorData.get(0);
        OkHttpMediator mediator = new OkHttpMediator();
        try {
            patientsData = mediator.getDoctorPatients(url);
            for(int i=0; i<patientsData.size(); i++){
                ArrayList<String> current = patientsData.get(i);
                //0: AMKA, 1: Name, 2: Address
                arrayList.add(current.get(0)+", "+current.get(1)+", "+current.get(2));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


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
                String splitText[] = selectedItem.split(", ");
                ArrayList<String> parseData = new ArrayList<>();
                parseData.add(splitText[0]);//Amka
                parseData.add(splitText[1]);//name
                parseData.add(splitText[2]);//address
                goToPatientProfilePage(parseData);
            }
        });
    }



    public void goToPatientProfilePage(ArrayList<String> patData){
        Intent intent = new Intent(getApplicationContext(),PatientProfilePage.class);
        intent.putExtra("userDataArrayList",doctorData);
        intent.putExtra("patientName",patData);
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

