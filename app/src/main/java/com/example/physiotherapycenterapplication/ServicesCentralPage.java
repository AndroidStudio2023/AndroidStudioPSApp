package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ServicesCentralPage extends AppCompatActivity {

    ArrayList<String> userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_central_page);
        getSupportActionBar().setTitle("Central Physiotherapy Clinics Page");
        Bundle extras = getIntent().getExtras();
        userData = extras.getStringArrayList("userDataArrayList");
    }

    public void moveToCreateServicePage(View view){
        Intent intent = new Intent(getApplicationContext(),CreateServicePage.class);
        startActivity(intent);
    }

    public void moveToCentralPage(View view){
        Intent intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
        intent.putExtra("userDataArrayList",userData);
        startActivity(intent);
    }

    //Block back button
    public void onBackPressed() {
        // do nothing.
    }
}