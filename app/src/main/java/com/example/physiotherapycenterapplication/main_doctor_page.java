package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

import java.io.IOException;
import java.util.Objects;


public class main_doctor_page extends AppCompatActivity {


    public Button buttona;
    public Button buttonb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Main Doctor Page");

        buttona = (Button) findViewById(R.id.button5);
        buttonb = (Button) findViewById(R.id.button4);
        //χρειαζομαι τουλαχιστον την αναζητηση των ασθενων για να συνδεσω το κουμπι "Ασθενεις"


        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_doctor_page.this,new_patient.class);
                startActivity(intent);
            }
        });

    }


    //Apo iwanna proswrino - gia thn metakinhsh sto page tou istorikou
    public void goToHistoryPage(View view){
        Intent intent = new Intent(getApplicationContext(), HistoryPage.class);
        startActivity(intent);
    }

}