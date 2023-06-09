package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DailyAppointments extends AppCompatActivity {

    Bundle extras;
    String day;
    ArrayList<String> doctorData;
    TextView myDate;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_appointments);
        extras = getIntent().getExtras();
        day = extras.getString("date");
        doctorData = extras.getStringArrayList("userDataArrayList");
        myDate = findViewById(R.id.dayTextView);
        myDate.setText(day);

        listView = (ListView) findViewById(R.id.myList);

        ArrayList<String> arrayList = new ArrayList<>();
        //Connection....
        arrayList.add("Patient A, 10:00");
        arrayList.add("Patient B, 12:00");
        arrayList.add("Patient C, 14:00");
        arrayList.add("Patient D, 16:00");
        arrayList.add("Patient E, 20:00");
        arrayList.add("Patient F, 21:00");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) listView.getItemAtPosition(position);
                Toast.makeText(DailyAppointments.this,clickedItem,Toast.LENGTH_LONG).show();
            }
        });

    }

    public void goToCalendar(View v){
        Intent intent = new Intent(getApplicationContext(),SelectAppointmentsDay.class);
        intent.putExtra("userDataArrayList",doctorData);
        startActivity(intent);
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }
}