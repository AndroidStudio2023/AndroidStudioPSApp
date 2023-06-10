package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ServicesCentralPage extends AppCompatActivity {

    ArrayList<ArrayList<String>> allServices;
    ArrayList<String> userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_central_page);
        getSupportActionBar().setTitle("Κεντρική Σελίδα Παροχών/Υπηρεσιών");
        Bundle extras = getIntent().getExtras();
        userData = extras.getStringArrayList("userDataArrayList");
        printAllServices();
    }

    public void printAllServices(){
        OkHttpMediator mediator = new OkHttpMediator();
        String url = "http://10.0.2.2/AndroidStudioProviders/getServices.php";
        try{
            allServices = mediator.getServices(url);
            if(allServices.size()>0){
                for(int i=0; i<allServices.size(); i++){
                    ArrayList<String> current = allServices.get(i);
                    printService(current.get(0),current.get(1),current.get(3));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void printService(String code, String name, String cost){
        TableLayout servicesTable = findViewById(R.id.servicesTable);
        TableRow service = new TableRow(this);
        TextView codCol = new TextView(this);
        TextView nameCol = new TextView(this);
        TextView desCol = new TextView(this);
        TextView costCol = new TextView(this);

        //texts
        codCol.setText(code);
        nameCol.setText(name);
        desCol.setText(" .... ");
        costCol.setText(cost);

        //colors
        codCol.setBackgroundColor(getResources().getColor(R.color.PrimariesColor));
        nameCol.setBackgroundColor(getResources().getColor(R.color.PrimariesColor));
        desCol.setBackgroundColor(getResources().getColor(R.color.PrimariesColor));
        costCol.setBackgroundColor(getResources().getColor(R.color.PrimariesColor));

        codCol.setTextColor(getResources().getColor(R.color.buttonTextColor));
        nameCol.setTextColor(getResources().getColor(R.color.buttonTextColor));
        desCol.setTextColor(getResources().getColor(R.color.buttonTextColor));
        costCol.setTextColor(getResources().getColor(R.color.buttonTextColor));

        //sizes
        codCol.setWidth(90);
        nameCol.setWidth(250);
        desCol.setWidth(50);
        costCol.setWidth(90);

        codCol.setHeight(190);
        nameCol.setHeight(190);
        desCol.setHeight(190);
        costCol.setHeight(190);

        codCol.setPadding(5,10,5,10);
        nameCol.setPadding(5,5,5,5);
        desCol.setPadding(5,10,5,10);
        costCol.setPadding(5,10,5,10);

        costCol.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);
        nameCol.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);
        desCol.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);
        costCol.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f);

        //Aligments
        costCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        desCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        nameCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        codCol.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        //Gravities
        costCol.setGravity(Gravity.CENTER_VERTICAL);
        desCol.setGravity(Gravity.CENTER_VERTICAL);
        nameCol.setGravity(Gravity.CENTER_VERTICAL);
        codCol.setGravity(Gravity.CENTER_VERTICAL);
        //connects
        service.addView(codCol);
        service.addView(nameCol);
        service.addView(desCol);
        service.addView(costCol);
        servicesTable.addView(service);
    }

    public void moveToCreateServicePage(View view){
        Intent intent = new Intent(getApplicationContext(),CreateServicePage.class);
        startActivity(intent);
    }

    public void moveToCentralPage(View view){
        Intent intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
        intent.putExtra("userDataArrayList",userData);
        startActivity(intent);
    }

    //Block back button
    public void onBackPressed() {
        // do nothing.
    }
}