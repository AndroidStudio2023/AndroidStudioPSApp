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
    ArrayList<String> patientData;
    TextView patientNameView;
    TextView patientAMKAView;
    TextView patientAddressView;
    ArrayList<String> doctorData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Σελίδα Προφίλ Ασθενή");
        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("userDataArrayList");

        //Print patient name
        patientData = extras.getStringArrayList("patientName");

        patientAMKAView = findViewById(R.id.amkaTextView);
        patientNameView = findViewById(R.id.firstLastNamePatient);
        patientAddressView = findViewById(R.id.AddressTextView);
        String s1 = patientAMKAView.getText().toString();
        patientAMKAView.setText(s1+" "+patientData.get(0));
        patientNameView.setText(patientData.get(1));
        String s2 = patientAddressView.getText().toString();
        patientAddressView.setText(s2+" "+patientData.get(2));

    }


    public void goToHistoryPage(View view) throws IOException {
        Intent intent = new Intent(getApplicationContext(),HistoryPage.class);
        intent.putExtra("doctorDataArrayList",doctorData);
        intent.putExtra("patientDataArrayList",patientData);
        startActivity(intent);
    }

    public void goToSearchPatientPage(View view){
        Intent intent = new Intent(getApplicationContext(),PatientSearchPage.class);
        intent.putExtra("userDataArrayList",doctorData);
        startActivity(intent);
    }

    public void goToAddVisitPage(View view){
        Intent intent = new Intent(getApplicationContext(),AddNewVisitPage.class);
        intent.putExtra("userDataArrayList",doctorData);
        intent.putExtra("patientName",patientData);
        startActivity(intent);
    }
    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }

}
