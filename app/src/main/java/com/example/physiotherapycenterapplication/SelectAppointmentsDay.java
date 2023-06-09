package com.example.physiotherapycenterapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class SelectAppointmentsDay extends AppCompatActivity {

    CalendarView calendar = findViewById(R.id.calendarView1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_appointments_day);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String selectedDate = day + "/"+(month+1)+"/"+year;
                goNextPage(selectedDate);
            }
        });
    }

    public void goNextPage(String s){
        Intent intent = new Intent(getApplicationContext(),DailyAppointments.class);
        intent.putExtra("date",s);
        startActivity(intent);
    }
}