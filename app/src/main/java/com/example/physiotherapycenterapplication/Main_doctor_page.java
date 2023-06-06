package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.*;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;


public class Main_doctor_page extends AppCompatActivity {


    public Button buttona;
    public Button buttonb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Main Doctor Page");

        buttona = (Button) findViewById(R.id.addNewPatientButton);
        buttonb = (Button) findViewById(R.id.patientsButton);
        //χρειαζομαι τουλαχιστον την αναζητηση των ασθενων για να συνδεσω το κουμπι "Ασθενεις"


        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_doctor_page.this, New_patient.class);
                startActivity(intent);
            }
        });

        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent1 = new Intent(getApplicationContext(), PatientSearchPage.class);
                    startActivity(intent1);
                }catch (ActivityNotFoundException e){
                    e.printStackTrace();
                }

            }
        });


        //apo ioanna:
        Bundle extras = getIntent().getExtras();
        ArrayList<String> doctorData = extras.getStringArrayList("userDataArrayList");
        String doctorName = doctorData.get(1);

        //Split name into lastname and firstname
        String[] doctorSplitName = doctorName.split(" ");
        //!!!Edw upirxe provlima, Mporei na proekupse se periptwsi pou den exei o physiothterapist 2 lexeis sto onoma
        //System.out.println("epitheto giatrou: "+ doctorSplitName[0] + ",ononoma giatrou: " + doctorSplitName[1]);
        TextView textView = findViewById(R.id.textView13);
        textView.setText(doctorSplitName[0] + "!");


        //gia na pernaw to id toy giatroy sto patient search kai na ginetai emfanish twn asthenwn me bash to doctor id
        Button astheneisButton  = (Button) findViewById(R.id.patientsButton);
        astheneisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PatientSearchPage.class);
                String doctorid = doctorData.get(0);
                intent.putExtra("doctorid", doctorid);
                startActivity(intent);
            }
        });


    }


    //Apo iwanna proswrino - gia thn metakinhsh sto page tou istorikou
    public void goToHistoryPage(View view){
        Intent intent = new Intent(getApplicationContext(), HistoryPage.class);
        startActivity(intent);
    }

    //Apo PM gia metafora stis aitiseis
    public void goToPatientsRequests(View view){
        Intent intent = new Intent(getApplicationContext(), MainDoctorRequestPage.class);
        startActivity(intent);
    }

}