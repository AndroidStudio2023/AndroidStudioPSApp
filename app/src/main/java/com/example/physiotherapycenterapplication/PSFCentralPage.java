package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

//PSFCentralPage
/*it was originally intended as the main page of the PSF,
however there were changes in the program and now it is the main page
of the physiotherapy clinics.
* */
public class PSFCentralPage extends AppCompatActivity {

    ArrayList<String> userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psfcentral_page);
        //Change Action Title
        getSupportActionBar().setTitle("Central Physiotherapy Clinics Page");
        Bundle extras = getIntent().getExtras();
        userData = extras.getStringArrayList("userDataArrayList");

        //Call Function for print physicotherapist Clinics
        printClinics();
    }


    public void goNextPage(View view){
        Intent intent;
        intent = new Intent(getApplicationContext(),CreatePSPage.class);
        startActivity(intent);

    }

    public void goCentralPage(View view){
        Intent intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
        intent.putExtra("userDataArrayList",userData);
        startActivity(intent);
    }


    //Print Clinics Data
    public void printClinics(){
        OkHttpMediator mediator = new OkHttpMediator();
        String url = "http://10.0.2.2/AndroidStudioProviders/getClinics.php";
        try{
            //Call mediator method (it's returns clinics data)
            HashMap<Integer,ArrayList<String>> clinics = mediator.getClinicsData(url);
            if(clinics.size()>0){
               printClinicsDataTable(clinics);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //Print clinics
    public void printClinicsDataTable(HashMap<Integer , ArrayList<String>> clinics){
        //For any clinic
        for(int i=0; i<clinics.size(); i++){
            //Get clinic data
            ArrayList<String> currentClinic = clinics.get(i);
            printRow(currentClinic.get(0),currentClinic.get(2),currentClinic.get(1));
        }
    }

    //Print a clinic dynamically
    public void printRow(String afm,String address, String name) {
        //Get dataTable
        TableLayout allDataTable = findViewById(R.id.DataTable);
        //Create new row
        TableRow aRow = new TableRow(this);
        //Create 3 TextViews -> are columns
        TextView nameCol = new TextView(this);
        TextView addCol = new TextView(this);
        TextView afmCol = new TextView(this);

        //Set texts
        nameCol.setText(name);
        addCol.setText(address);
        afmCol.setText(afm);

        //Set colors
        nameCol.setBackgroundResource(R.drawable.table_fields_bordersv1);
        addCol.setBackgroundResource(R.drawable.table_fields_bordersv1);
        afmCol.setBackgroundResource(R.drawable.table_fields_bordersv1);

        //Set padding
        nameCol.setPadding(0,25,5,25);
        addCol.setPadding(0,25,5,25);
        afmCol.setPadding(0,25,5,25);


        //Set width
        nameCol.setWidth(452);
        addCol.setWidth(592);
        afmCol.setWidth(385);


        //Set text size and color
        nameCol.setTextSize(TypedValue.COMPLEX_UNIT_SP,14f);
        addCol.setTextSize(TypedValue.COMPLEX_UNIT_SP,14f);
        afmCol.setTextSize(TypedValue.COMPLEX_UNIT_SP,14f);

        nameCol.setTextColor(getResources().getColor(R.color.black));
        addCol.setTextColor(getResources().getColor(R.color.black));
        afmCol.setTextColor(getResources().getColor(R.color.black));

        //Set alignment and gravity
        nameCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        addCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        afmCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        nameCol.setGravity(Gravity.CENTER_VERTICAL);
        addCol.setGravity(Gravity.CENTER_VERTICAL);
        afmCol.setGravity(Gravity.CENTER_VERTICAL);


        //Connect Views
        aRow.addView(nameCol);
        aRow.addView(addCol);
        aRow.addView(afmCol);
        allDataTable.addView(aRow);
    }

    //Block back button
    public void onBackPressed() {
        // do nothing.
    }
}