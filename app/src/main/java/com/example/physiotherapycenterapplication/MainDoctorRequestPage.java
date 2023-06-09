package com.example.physiotherapycenterapplication;
//Main page with patients request for physiotherapist
//Create by Iliana

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class MainDoctorRequestPage extends AppCompatActivity {

    Bundle extras;
    ArrayList<String> doctorData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor_request_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Σελίδα Διαχείρισης Αιτημάτων");
        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("userDataArrayList");
        printRequests();
    }

    public void printRequests(){
        try{
            ArrayList<ArrayList<String>> requests = getDoctorRequests();
            for(int i=0; i<requests.size(); i++){
                ArrayList<String> current = requests.get(i);
                String id = current.get(0);
                String amka = current.get(1);
                String name = current.get(2);
                String dateTime = current.get(3);
                printARequest(id,amka,name,dateTime);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void printARequest(String id,String amka, String name, String date){
        LinearLayout requestList = findViewById(R.id.patientsRequestList);
        LinearLayout rowUp = new LinearLayout(this);
        LinearLayout rowBottom = new LinearLayout(this);
        rowBottom.setPadding(0,25,0,100);
        //TextViews Area
        TextView idArea = new TextView(this);
        idArea.setText(id+":");
        idArea.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        TextView amkaArea = new TextView(this);
        amkaArea.setText(amka);
        amkaArea.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        TextView nameArea = new TextView(this);
        nameArea.setText(", "+name);
        nameArea.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);
        TextView dateArea = new TextView(this);
        dateArea.setText(date);
        dateArea.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f);

        Space sp0 = new Space(this);
        sp0.setMinimumWidth(35);
        Space sp0d = new Space(this);
        sp0d.setMinimumWidth(45);
        Space sp1 = new Space(this);
        sp1.setMinimumWidth(60);
        Space sp2 = new Space(this);
        sp2.setMinimumWidth(80);
        Space sp3 = new Space(this);
        sp3.setMinimumWidth(40);
        //Images Buttons Area
        ImageButton checkButton = new ImageButton(this);
        checkButton.setImageResource(R.drawable.check_mark_3279);
        checkButton.setBackground(null);
        ImageButton unCheckButton = new ImageButton(this);
        unCheckButton.setImageResource(R.drawable.cancel_close_10373);
        unCheckButton.setBackground(null);

        //Connect Views
        rowUp.addView(sp0);
        rowUp.addView(idArea);
        rowUp.addView(sp1);
        rowUp.addView(amkaArea);
        rowUp.addView(nameArea);
        rowBottom.addView(sp0d);
        rowBottom.addView(dateArea);
        rowBottom.addView(sp2);
        rowBottom.addView(checkButton);
        rowBottom.addView(sp3);
        rowBottom.addView(unCheckButton);
        requestList.addView(rowUp);
        requestList.addView(rowBottom);
    }

    public ArrayList<ArrayList<String>> getDoctorRequests() throws Exception {
        OkHttpMediator mediator = new OkHttpMediator();
        String url = "http://10.0.2.2/AndroidStudioProviders/getRequest.php?id="+doctorData.get(0);
        ArrayList<ArrayList<String>> requests = mediator.getRequests(url);

        return requests;
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