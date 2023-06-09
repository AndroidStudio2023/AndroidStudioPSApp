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
import java.util.Objects;

public class DailyAppointments extends AppCompatActivity {

    Bundle extras;
    String day;
    ArrayList<String> doctorData;
    TextView myDate;
    ListView listView;
    ArrayList<ArrayList<String>> dailyAppointments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_appointments);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Σελίδα Προβολής Ημερησίων Ραντεβού");
        extras = getIntent().getExtras();
        day = extras.getString("date");
        doctorData = extras.getStringArrayList("userDataArrayList");
        myDate = findViewById(R.id.dayTextView);
        myDate.setText("Τα ραντεβούσας για \n          '"+day+"'");

        listView = (ListView) findViewById(R.id.myList);

        ArrayList<String> arrayList = new ArrayList<>();
        //Connection....
        OkHttpMediator mediator = new OkHttpMediator();
        String url = "http://10.0.2.2/AndroidStudioProviders/getDailyAppointments.php?docID="+doctorData.get(0)+"&date="+day;
        System.out.println("\n============================URL DATEAPPOINTMENTS ======================\n");
        System.out.println(url);
        try {
            dailyAppointments = mediator.getDailyAppointments(url);
            for(int i=0; i<dailyAppointments.size(); i++){
                ArrayList<String> cApp = dailyAppointments.get(i);
                arrayList.add(cApp.get(0)+", "+cApp.get(1)+" "+cApp.get(2));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) listView.getItemAtPosition(position);
                //Toast.makeText(DailyAppointments.this,clickedItem,Toast.LENGTH_LONG).show();
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