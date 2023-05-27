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
    ArrayList<String> userData;//For save user Data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagepopup);
        popUp = findViewById(R.id.popUpArea);
        getSupportActionBar().setTitle("Αρχική Σελίδα");

        button2 = (Button) findViewById(R.id.date_button);

        //Get Patient Data (AMKA & NAME)
        Bundle patData = getIntent().getExtras();
        userData = patData.getStringArrayList("userDataArrayList");

        //Χρειάζεται ένας πάροχος που με βάση το Α.Μ.Κ.Α (1ο στοιχείο του userData)
        //Να βρίσκει σε πιο ιατρείο ανήκει
        //Ερώτημα αρχικά για τον πίνακα "patientsandclinicsconnection" για το ID του φυσικοθεραπευτή (physiotherapistID)
        //Μετά με βάση το ID βρίσκει το φυσικοθεραπευτήριο από τον πίνακα "physiotherapyclinics"



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
