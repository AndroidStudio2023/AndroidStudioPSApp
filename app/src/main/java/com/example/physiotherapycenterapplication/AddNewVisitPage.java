package com.example.physiotherapycenterapplication;
//This activity create by Fwtios
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AddNewVisitPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_visit_page);
    }

    public void goToMainDoctorPage(View view){
        //Intent intent = new Intent(getApplicationContext(),Main_doctor_page.class);
        //startActivity(intent);
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }
}