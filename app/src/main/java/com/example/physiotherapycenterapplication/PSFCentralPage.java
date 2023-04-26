package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PSFCentralPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psfcentral_page);
        //Change Action Title
        getSupportActionBar().setTitle("Central Page");
    }

    //Analoga me to koumpi pou epilegei o xristis
    //Ton pigainei stin antoistixi selida
    public void LogOutORnextPage(View view){
        //get button name
        Button aButton= (Button)view;
        String buttonName = aButton.getText().toString();
        Intent intent;

        if(buttonName.equals("Νέο Φυσικοθεραπευτήριο")){
            intent = new Intent(getApplicationContext(),CreatePSPage.class);
            startActivity(intent);
        } else if (buttonName.equals("Log Out")) {
            intent = new Intent(getApplicationContext(),LoginPageActivity.class);
            intent.putExtra("TypeUser","ΠΣΦ");
            startActivity(intent);
        }else{
            Toast.makeText(this, "Coming Soon!!", Toast.LENGTH_SHORT).show();
        }
    }
}