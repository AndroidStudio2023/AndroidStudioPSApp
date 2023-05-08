package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * NewPSFCentralPage
 * This page is the central page for PSF user.
 * This page will has 2 choices...
 * visit to physiotherapy clinics page and visit to physiotherapy services page
 */

public class NewPSFCentralPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_psfcentral_page);
        getSupportActionBar().setTitle("Central Page");
    }

    //Log Out function
    public void LogOut(View view){
        Intent intent = new Intent(getApplicationContext(),LoginPageActivity.class);
        intent.putExtra("TypeUser","ΠΣΦ");
        startActivity(intent);
    }

    //Next Page Function
    public void NextPage(View view){
        int buttonID;
        //Get ID button
        buttonID = view.getId();

        Intent intent;
        //2131296468 -> id from the button for move to Physiotherapy Clinics Page
        if(buttonID==2131296468){
            intent = new Intent(getApplicationContext(),PSFCentralPage.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
        }
    }
}