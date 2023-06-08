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

import java.util.ArrayList;

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
                Bundle patData = getIntent().getExtras();
                ArrayList<String> arrayListData = patData.getStringArrayList("userDataArrayList");
                Intent intent = new Intent(getApplicationContext(), MainPatient.class);
                intent.putExtra("userDataArrayList",arrayListData);
                intent.putExtra("showPopPup",true);//Flag for show popup in central page
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
