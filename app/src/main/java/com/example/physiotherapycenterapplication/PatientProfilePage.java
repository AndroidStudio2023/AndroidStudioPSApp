package com.example.physiotherapycenterapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PatientProfilePage extends AppCompatActivity {

    Bundle extras;
    //Allazei otan exoume ton Provider
    String patientName;
    TextView patientNameView;


    ArrayList<String> doctorData;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Patient Profile Page");
        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("userDataArrayList");
        //Print patient name
        patientName = extras.get("patientName").toString();
        patientNameView = findViewById(R.id.firstLastNamePatient);
        patientNameView.setText(patientName);
    }


    public void goToHistoryPage(View view) throws IOException {
        Intent intent = new Intent(getApplicationContext(),HistoryPage.class);
        intent.putExtra("userDataArrayList",doctorData);
        startActivity(intent);
    }

    public void goToSearchPatientPage(View view){
        Intent intent = new Intent(getApplicationContext(),PatientSearchPage.class);
        intent.putExtra("userDataArrayList",doctorData);
        startActivity(intent);
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }

}
