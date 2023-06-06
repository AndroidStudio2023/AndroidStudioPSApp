package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class MainPatient extends AppCompatActivity{
    //public Button button1;
    public Button button2;

    ImageButton imgButton;
    ArrayList<String> userData;//For save user Data
    Bundle patData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);
        getSupportActionBar().setTitle("Αρχική Σελίδα");
        button2 = (Button) findViewById(R.id.date_button);

        /*imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginPageActivity.class);
                startActivity(intent);
            }

        });*/
        //Get Patient Data (AMKA & NAME)
        patData = getIntent().getExtras();
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
    }
    public void onBackPressed(){

    }
}
