package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ServicesCentralPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_central_page);
        getSupportActionBar().setTitle("Central Physiotherapy Clinics Page");
    }

    public void moveToCreateServicePage(View view){
        Intent intent = new Intent(getApplicationContext(),CreateServicePage.class);
        startActivity(intent);
    }

    public void moveToCentralPage(View view){
        Intent intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
        startActivity(intent);
    }

    //Block back button
    public void onBackPressed() {
        // do nothing.
    }
}