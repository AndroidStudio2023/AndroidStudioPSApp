package com.example.physiotherapycenterapplication;
//This activity create by Fwtios
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class AddNewVisitPage extends AppCompatActivity {

    Bundle extras;
    ArrayList<String> doctorData;
    ArrayList<String> patientData;
    public static ArrayList<String> selectServIDs = new ArrayList<>();
    TextView patName;
    TextView patAMKA;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_visit_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Σελίδα Προσθήκης Επίσκεψης");
        extras = getIntent().getExtras();
        doctorData = extras.getStringArrayList("userDataArrayList");
        patientData = extras.getStringArrayList("patientName");

        patName=findViewById(R.id.addVisitpagePatName);
        date=findViewById(R.id.addVisitpageDate);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //ZoneId zid = ZoneId.of("Europe/Greek");
            LocalDateTime now = LocalDateTime.now();
            String cDate=dtf.format(now);
            date.setText(cDate);
        }

        patName.setText(patientData.get(0)+"/"+patientData.get(1));


        loadServicesList();
    }

    public void goToPatientProfilePage(View view){
        Intent intent = new Intent(getApplicationContext(),PatientProfilePage.class);
        intent.putExtra("userDataArrayList",doctorData);
        intent.putExtra("patientName", patientData);
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
                    curBox.setText(curServ.get(1)+", "+curServ.get(3)+", €");//Set text
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
        System.out.println("======Selected ServIDs================\n");
        System.out.println(selectServIDs);
        String servIDs = selectServIDs.toString();

        String url;
        String cDate=null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //ZoneId zid = ZoneId.of("Europe/Greek");
            LocalDateTime now = LocalDateTime.now();
            cDate=dtf.format(now);
        }
        if(cDate!=null){
            url ="http://10.0.2.2/AndroidStudioProviders/addPatientVisit.php?amka="+patientData.get(0)+"&phyID="+doctorData.get(0)+"&time="+cDate+"&serv="+servIDs;
            if(selectServIDs.size()>0){
                System.out.println(url);
                OkHttpMediator mediator = new OkHttpMediator();
                try{
                    String mess = mediator.addVisit(url);
                    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(this, "Επιλέξτε Υπηρεσίες...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }
}