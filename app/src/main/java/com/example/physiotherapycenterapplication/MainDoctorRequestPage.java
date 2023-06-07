package com.example.physiotherapycenterapplication;
//Main page with patients request for physiotherapist
//Create by Iliana

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainDoctorRequestPage extends AppCompatActivity {

    Bundle extras;
    ArrayList<String> doctorData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor_request_page);
        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("userDataArrayList");
    }

    public void goToDoctorMainPage(View view){
        Intent intent = new Intent(getApplicationContext(),Main_doctor_page.class);
        intent.putExtra("userDataArrayList",doctorData);
        startActivity(intent);
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }
}