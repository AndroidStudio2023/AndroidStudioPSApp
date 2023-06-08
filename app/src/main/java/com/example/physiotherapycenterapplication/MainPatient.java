package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class MainPatient extends AppCompatActivity{
    //public Button button1;
    public Button button2;

    Boolean showPopup;
    ImageButton imgButton;
    ArrayList<String> userData;//For save user Data
    ArrayList<String> doctordata = new ArrayList<>();//For Doctor Data
    Bundle patData;
    LinearLayout popupArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);
        getSupportActionBar().setTitle("Αρχική Σελίδα");
        //Get Patient Data (AMKA & NAME)
        patData = getIntent().getExtras();
        userData = patData.getStringArrayList("userDataArrayList");
        button2 = (Button) findViewById(R.id.date_button);
        imgButton = (ImageButton) findViewById(R.id.imageButtonPatientOut);
        popupArea = findViewById(R.id.popUpArea);
        OkHttpMediator okHttpMediator = new OkHttpMediator();
        String url = "http://10.0.2.2/AndroidStudioProviders/docinfo.php?patient="+userData.get(0);
        try {
            doctordata = okHttpMediator.patientDoctorInfo(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginPageActivity.class);
                intent.putExtra("userDataArrayList",userData);
                startActivity(intent);
            }

        });

        //Hide/show popup area
        showPopup = patData.getBoolean("showPopPup");
        //ShowPopup = true -> show popup message
        //1st time is false, (Login page)
        if(!showPopup){
            popupArea.setVisibility(View.GONE);
        }else{
            popupArea.setVisibility(View.VISIBLE);
        }
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
                intent.putExtra("userDataArrayList",userData);
                intent.putExtra("doctordataArrayList", doctordata);
                startActivity(intent);
            }
        });
    }

    public void closePopUpMessage(View view){
        if(popupArea.getVisibility()==View.VISIBLE){
            popupArea.setVisibility(View.GONE);
        }
    }
    public void onBackPressed(){

    }
}
