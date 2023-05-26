package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

public class MyDate extends AppCompatActivity{
    private Button confirm_button;
    CalendarView calendar;
    TextView selectDateField;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        calendar = findViewById(R.id.calendarView);
        selectDateField = findViewById(R.id.selectDate);

        getSupportActionBar().setTitle("Ραντεβού");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        confirm_button = (Button) findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PatientPagePopUp.class);
                startActivity(intent);
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String selectedDate = String.valueOf(day)+" "+String.valueOf(month+1)+" "+String.valueOf(year);
                selectDateField.setText(String.valueOf(selectedDate));
            }
        });
    }
}
