package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

//PSFCentralPage
/*it was originally intended as the main page of the PSF,
however there were changes in the program and now it is the main page
of the physiotherapy clinics.
* */
public class PSFCentralPage extends AppCompatActivity {

    ArrayList<String> userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psfcentral_page);
        //Change Action Title
        getSupportActionBar().setTitle("Central Physiotherapy Clinics Page");
        Bundle extras = getIntent().getExtras();
        userData = extras.getStringArrayList("userDataArrayList");
    }


    public void goNextPage(View view){
        Intent intent;
        intent = new Intent(getApplicationContext(),CreatePSPage.class);
        startActivity(intent);

    }

    public void goCentralPage(View view){
        Intent intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
        intent.putExtra("userDataArrayList",userData);
        startActivity(intent);
    }

    //Block back button
    public void onBackPressed() {
        // do nothing.
    }
}