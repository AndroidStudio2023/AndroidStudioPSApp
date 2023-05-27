package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PatientPagePopUp extends AppCompatActivity {
    //public Button button1;
    public Button button2;
    TextView closePopUp;
    Button openPopUp;
    LinearLayout popUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagepopup);
        popUp = findViewById(R.id.popUpArea);
        getSupportActionBar().setTitle("Αρχική Σελίδα");

        button2 = (Button) findViewById(R.id.date_button);




        /*button1 = (Button) findViewById(R.id.eco_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Eco.class);
                startActivity(intent);
            }
        });*/

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyDate.class);
                startActivity(intent);
            }
        });

        closePopUp = findViewById(R.id.okTextView);
        closePopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popUp.getVisibility() == View.VISIBLE) {
                    popUp.setVisibility(View.GONE);
                } else {
                    Toast.makeText(PatientPagePopUp.this, "ERRRRRR", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*openPopUp = findViewById(R.id.eco_button);
        openPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popUp.getVisibility() == View.GONE) {
                    popUp.setVisibility(View.VISIBLE);
                }
            }
        });*/
    }
}
