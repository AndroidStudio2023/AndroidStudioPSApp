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

    //Analoga me to koumpi pou epilegei o xristis
    //Ton pigainei stin antoistixi selida
    public void GoPreviousORNextPage(View view){
        //get button name
        Button aButton= (Button)view;
        String buttonName = aButton.getText().toString();
        Intent intent;

        if(buttonName.equals("Νέο Φυσικοθεραπευτήριο")){
            intent = new Intent(getApplicationContext(),CreatePSPage.class);
            startActivity(intent);
        } else if (buttonName.equals("Central Page")) {
            intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
            //intent.putExtra("TypeUser","ΠΣΦ");
            startActivity(intent);
        }
    }
}