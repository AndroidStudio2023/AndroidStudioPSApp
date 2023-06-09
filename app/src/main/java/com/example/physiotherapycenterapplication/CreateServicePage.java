package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CreateServicePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_page);
        getSupportActionBar().setTitle("Σελίδα Προσθήκης Νέας Παροχής");
    }
}