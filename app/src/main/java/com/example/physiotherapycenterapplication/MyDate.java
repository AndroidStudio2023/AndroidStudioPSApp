package com.example.physiotherapycenterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyDate extends AppCompatActivity{
    private Button confirm_button;
    CalendarView calendar;
    TextView selectDateField;
    ArrayList<String> userData;//For save user Data
    ArrayList<String> doctordata = new ArrayList<>();//For Doctor Data
    Button btn1;
    Bundle bundle;
    String datehour1=" ";
    String datehour2=" ";
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        calendar = findViewById(R.id.calendarView);
        selectDateField = findViewById(R.id.selectDate);

        getSupportActionBar().setTitle("Ραντεβού");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bundle = getIntent().getExtras();

        userData = bundle.getStringArrayList("userDataArrayList");
        doctordata = bundle.getStringArrayList("doctordataArrayList");

        confirm_button = (Button) findViewById(R.id.confirm_button);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpMediator okHttpMediator = new OkHttpMediator();
                String url = "http://10.0.2.2/AndroidStudioProviders/addRequest.php?patient="+userData.get(0)+"&doctor="+doctordata.get(0)+"&id="+datehour1+datehour2;
                try {
                    String flag = okHttpMediator.addRequest(url);
                    if(!flag.equals("fail")){
                        Intent intent = new Intent(getApplicationContext(), MainPatient.class);
                        intent.putExtra("userDataArrayList",userData);
                        intent.putExtra("showPopPup",true);//Flag for show popup in central page
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String selectedDate = String.valueOf(day)+" "+String.valueOf(month+1)+" "+String.valueOf(year);
                datehour1 = String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(day)+" ";
                selectDateField.setText(String.valueOf(selectedDate));
            }
        });
    }
    public void getHour(View view){
        btn1 = (Button) view;
        String hour = btn1.getText().toString();
        datehour2 = hour;
    }
}
