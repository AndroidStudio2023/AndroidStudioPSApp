package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

public class MainPatient extends AppCompatActivity{
    //public Button button1;
    public Button button2;
    TextView closePopUp;
    Button openPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patient);
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
    }
}
