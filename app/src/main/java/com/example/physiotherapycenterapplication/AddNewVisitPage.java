package com.example.physiotherapycenterapplication;
//This activity create by Fwtios
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewVisitPage extends AppCompatActivity {

    Bundle extras;
    ArrayList<String> doctorData;
    String patientName;
    public static ArrayList<String> selectServIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_visit_page);
        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("userDataArrayList");
        patientName = extras.getString("patientName");




        loadServicesList();
    }

    public void goToPatientProfilePage(View view){
        Intent intent = new Intent(getApplicationContext(),PatientProfilePage.class);
        intent.putExtra("userDataArrayList",doctorData);
        intent.putExtra("patientName",patientName);
        startActivity(intent);
    }

    public void loadServicesList(){
        OkHttpMediator mediator = new OkHttpMediator();
        LinearLayout list = findViewById(R.id.servicesList);
        ArrayList<ArrayList<String>> servicesArrays;

        String url =  "http://10.0.2.2/AndroidStudioProviders/getServices.php";
        boolean printCheckBoxes=false;
        try{
            servicesArrays = mediator.getServices(url);
            if(servicesArrays.size()>0){
                //if find services
                printCheckBoxes = true;
            }
            //If load services complete
            if(printCheckBoxes){

                //Print services in checkboxes
                for(int i=0; i<servicesArrays.size(); i++){
                    CheckBox curBox = new CheckBox(this);
                    ArrayList<String> curServ = servicesArrays.get(i);
                    //0: id, 1: Name, 2: description, 3: cost

                    //Fix checkBox
                    curBox.setText(curServ.get(1)+" "+curServ.get(3)+" €");//Set text
                    curBox.setGravity(Gravity.CENTER_VERTICAL|Gravity.START);//Gravity
                    curBox.setWidth(850);//Width
                    //Margin
                    LinearLayout.LayoutParams boxLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    boxLayoutParams.setMargins(0, 20, 0, 20);
                    curBox.setLayoutParams(boxLayoutParams);

                    //listener
                    curBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(b){
                                Toast.makeText(AddNewVisitPage.this, "Επιλογή "+curServ.get(1), Toast.LENGTH_SHORT).show();
                                selectServIDs.add(curServ.get(0));
                            }else{
                                Toast.makeText(AddNewVisitPage.this, "Από-επιλογη "+curServ.get(1), Toast.LENGTH_SHORT).show();
                                selectServIDs.remove(curServ.get(0));
                            }
                        }
                    });

                    list.addView(curBox);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    //add visit
    public void AddVisit(View view){
        for(int i=0; i<selectServIDs.size(); i++){
            Toast.makeText(this, selectServIDs.get(i), Toast.LENGTH_SHORT).show();
            selectServIDs.remove(i);
        }
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }
}