package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PatientProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Patient Profile Page");
    }


    public void goToHistoryPage(View view) throws IOException {
        Intent intent = new Intent(getApplicationContext(),HistoryPage.class);
        startActivity(intent);
    }

}
