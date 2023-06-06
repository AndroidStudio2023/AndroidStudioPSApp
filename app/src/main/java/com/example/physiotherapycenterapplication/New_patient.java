package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class New_patient extends AppCompatActivity {

    String name, adress, amka;

    EditText nameInput;
    EditText adressInput;
    EditText amkaInput;

    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        nameInput = (EditText) findViewById(R.id.nameInput);
        adressInput = (EditText) findViewById(R.id.adressInput);
        amkaInput = (EditText) findViewById(R.id.amkaInput);

        button = (Button) findViewById(R.id.creation);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               name = nameInput.getText().toString();
               adress = adressInput.getText().toString();
               amka = amkaInput.getText().toString();


               showToast(name);
           }

           private void showToast(String text) {

               Toast.makeText(New_patient.this,text,Toast.LENGTH_SHORT).show();
           }
       });

    }
}