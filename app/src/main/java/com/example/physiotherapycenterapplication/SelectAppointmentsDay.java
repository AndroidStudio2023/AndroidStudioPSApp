package com.example.physiotherapycenterapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Objects;

public class SelectAppointmentsDay extends AppCompatActivity {

    Bundle extras;
    ArrayList<String> doctorData;
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_appointments_day);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Σελίδα Επιλογής Ημερομηνίας");
        calendar = findViewById(R.id.calendarView1);
        //Bundle
        extras = getIntent().getExtras();
        doctorData=extras.getStringArrayList("userDataArrayList");
        //
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String selectedDate = year + "-"+(month+1)+"-"+day;
                goNextPage(selectedDate);
            }
        });
    }

    public void goNextPage(String s){
        Intent intent = new Intent(getApplicationContext(),DailyAppointments.class);
        intent.putExtra("date",s);
        intent.putExtra("userDataArrayList",doctorData);
        startActivity(intent);
    }

    public void goMainDocPage(View v){
        Intent intent = new Intent(getApplicationContext(),Main_doctor_page.class);
        intent.putExtra("userDataArrayList",doctorData);
        startActivity(intent);
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }
}