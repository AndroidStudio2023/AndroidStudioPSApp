package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;




public class main_doctor_page extends AppCompatActivity {

    public Button buttona;
    public Button buttonb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor_page);

        buttona = (Button) findViewById(R.id.button5);
        buttonb = (Button) findViewById(R.id.button4);


        buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_doctor_page.this,new_patient.class);
                startActivity(intent);
            }
        });

        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new  Intent(main_doctor_page.this,)
            }
        });
    }




}