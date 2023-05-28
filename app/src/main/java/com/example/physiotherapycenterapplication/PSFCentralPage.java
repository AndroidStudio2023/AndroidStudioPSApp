package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

//PSFCentralPage
/*it was originally intended as the main page of the PSF,
however there were changes in the program and now it is the main page
of the physiotherapy clinics.
* */
public class PSFCentralPage extends AppCompatActivity {

    ArrayList<String> userData;

    //HashMap with all clinics data
    HashMap<Integer,ArrayList<String>> clinics;
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
            clinics = mediator.getClinicsData(url);
            if(clinics.size()>0){
               printClinicsDataTable();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public String getTextFromSearchBar() {
        EditText searchBar = findViewById(R.id.searchPSFFiled);
        String searchValue = searchBar.getText().toString();
        return searchValue;
    }

    public int getSelectColumnIDFromSpinner() {
        //Returns 0 if user select AFM column
        //Returns 1 if user select Name column
        //Returns 2 if user select Address column
        //Returns 3 if user wont select column
        //Because the provider getClinics.php returns a total of lists with contents (AFM,Name,Address)

        Spinner mySpin = findViewById(R.id.spinner);
        //Column name from activity
        String frontNameCol = mySpin.getSelectedItem().toString();

        //Column name code to name from back
        if(frontNameCol.equals("Α.Φ.Μ")){
            return 0;
        }else if(frontNameCol.equals("Όνομα")){
            return 1;
        } else if (frontNameCol.equals("Διέυθυνση")) {
            return 2;
        }
        return 3;

    }

    public void onClickSearchButton(View view){
        int selColumnCode = getSelectColumnIDFromSpinner();
        String searchValue = getTextFromSearchBar();
        //Clear Table
        clearClinicsTableData();
        //If user do search based to the column
        if(userDoSearch(selColumnCode)){
            printSearchDataTable(selColumnCode,searchValue);
        }else{
            printClinicsDataTable();
        }
    }

    public boolean userDoSearch(int selColumnCode) {
        //SelColumnCode<3 => spinner select item: Α.Φ.Μ or Όνομα or Διεύθυνση
        if(selColumnCode<3 ){
            return true;
        }else{
            return false;
        }

    }

    public void clearClinicsTableData(){
        TableLayout aTable = findViewById(R.id.DataTable);
        aTable.removeAllViews();
    }
    //Print clinics
    public void printClinicsDataTable(){
        //For any clinic
        for(int i=0; i<clinics.size(); i++){
            //Get clinic data
            ArrayList<String> currentClinic = clinics.get(i);
            printRow(currentClinic.get(0),currentClinic.get(2),currentClinic.get(1));
        }
    }

    //Same with printClinicsDataTable but print clinics based on a selected column and search text
    public void printSearchDataTable(int colNum, String value){
        //If searchField has text
        if(!value.equals("")){
            //For any clinic
            for(int i=0; i<clinics.size(); i++){
                //Get clinic data
                ArrayList<String> currentClinic = clinics.get(i);
                //check data
                if(currentClinic.get(colNum).startsWith(value)){
                    //If data is correct, print clinic
                    printRow(currentClinic.get(0),currentClinic.get(2),currentClinic.get(1));
                }
            }
        }else{
            printNotFoundClinicsRow();
        }

    }

    public void printNotFoundClinicsRow() {

        //Get dataTable
        TableLayout allDataTable = findViewById(R.id.DataTable);
        //Create new row
        TableRow aRow = new TableRow(this);
        //Create a TextView
        TextView rowContent = new TextView(this);

        //Draw textview
        rowContent.setText("Not Found Clinics");
        rowContent.setWidth(1429);
        rowContent.setTextColor(getResources().getColor(R.color.black));
        rowContent.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        rowContent.setTextSize(TypedValue.COMPLEX_UNIT_SP,34f);
        rowContent.setPadding(0,250,0,0);

        //Connect views
        aRow.addView(rowContent);
        allDataTable.addView(aRow);
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