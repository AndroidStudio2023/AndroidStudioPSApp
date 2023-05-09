package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//PSFCentralPage
/*it was originally intended as the main page of the PSF,
however there were changes in the program and now it is the main page
of the physiotherapy clinics.
* */
public class PSFCentralPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psfcentral_page);
        //Change Action Title
        getSupportActionBar().setTitle("Central Physiotherapy Clinic Page");
    }


    public void goNextPage(View view){
        Intent intent;
        intent = new Intent(getApplicationContext(),CreatePSPage.class);
        startActivity(intent);

    }

    public void goCentralPage(View view){
        Intent intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
        //intent.putExtra("TypeUser","ΠΣΦ");
        startActivity(intent);
    }
}