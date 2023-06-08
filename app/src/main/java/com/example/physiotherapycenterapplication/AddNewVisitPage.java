package com.example.physiotherapycenterapplication;
//This activity create by Fwtios
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class AddNewVisitPage extends AppCompatActivity {

    Bundle extras;
    ArrayList<String> doctorData;
    String patientName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_visit_page);
        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("userDataArrayList");
        patientName = extras.getString("patientName");
    }

    public void goToPatientProfilePage(View view){
        Intent intent = new Intent(getApplicationContext(),PatientProfilePage.class);
        intent.putExtra("userDataArrayList",doctorData);
        intent.putExtra("patientName",patientName);
        startActivity(intent);
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }
}