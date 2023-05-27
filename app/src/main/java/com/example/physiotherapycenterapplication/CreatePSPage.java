package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreatePSPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pspage);
        //Change Action Title
        getSupportActionBar().setTitle("Add New Physiotherapy Page");


    }

    public void CreateNewPS(View view) {
        //Find and Save imported data
        TextView clinicName = findViewById(R.id.newPSName);
        TextView clinicAddress = findViewById(R.id.newPSAddress);
        TextView clinicAfm = findViewById(R.id.newPSAFM);
        TextView phyName = findViewById(R.id.newDoctorName);
        TextView phyEmail = findViewById(R.id.newDoctorMail);
        TextView phyPass = findViewById(R.id.doctorPassword);
        TextView phyID = findViewById(R.id.newDoctorID);

        String aClinicName = clinicName.getText().toString();
        String aClinicAddress = clinicAddress.getText().toString();
        String aClinicAFM = clinicAfm.getText().toString();
        String aPhyName = phyName.getText().toString();
        String aPhyEmail =phyEmail.getText().toString();
        String aPhyPass = phyPass.getText().toString();
        String aPhyID = phyID.getText().toString();





        //Correct All Data
        if (checkForEmptyData(aClinicName,aClinicAddress,aClinicAFM,aPhyName,aPhyEmail,aPhyPass,aPhyID)) {
            checkForCorrectData(aClinicName,aClinicAddress,aClinicAFM,aPhyName,aPhyEmail,aPhyPass,aPhyID);
        }

        //Clear form
        clinicName.setText("");
        clinicAddress.setText("");
        clinicAfm.setText("");
        phyName.setText("");
        phyEmail.setText("");
        phyPass.setText("");
        phyID.setText("");

    }

    public boolean checkForEmptyData(String clinicName,String clinicAddress,String clinicAFM,String docName,String docID,String docEmail, String docPass){

        //Error Message TextView
        TextView aError;
        //Empty fields number
        int emptyFileds = 0;
        //Incorrect clinic Name
        aError = findViewById(R.id.nameErrorMessage);
        if (clinicName.equals("")) {
            aError.setText("!Name is Empty");
            emptyFileds++;
        } else {
            aError.setText("");
        }

        //Incorrect clinic Address
        aError = findViewById(R.id.addresErrorMessage);
        if (clinicAddress.equals("")) {
            aError.setText("!Address is empty");
            emptyFileds++;
        } else {
            aError.setText("");
        }

        //Incorrect AFM
        aError = findViewById(R.id.afmErrorMessage);
        if (clinicAFM.equals("")) {
            aError.setText("!AFM is empty");
            emptyFileds++;
        } else {
            aError.setText("");
        }

        //Incorrect physicotherapist Name
        aError = findViewById(R.id.doctorNameErrorMessage);
        if (docName.equals("")) {
            aError.setText("!Name is Empty");
            emptyFileds++;
        } else {
            aError.setText("");
        }

        //Incorrect doctor email
        aError = findViewById(R.id.mailErrorMessage);
        if (docEmail.equals("")) {
            aError.setText("!Email is Empty");
            emptyFileds++;
        } else {
            aError.setText("");
        }

        //Incorrect doctor password
        aError = findViewById(R.id.passwordErrorMessage);
        if (docPass.equals("")) {
            aError.setText("!Password is Empty");
            emptyFileds++;
        } else {
            aError.setText("");
        }

        //Incorrect doctor id
        aError = findViewById(R.id.idErrorMessage);
        if (docID.equals("")) {
            aError.setText("!ID is Empty");
            emptyFileds++;
        } else {
            aError.setText("");
        }

        if(emptyFileds==0){
            return true;
        }else{
            return false;
        }
    }

    public void checkForCorrectData(String clinicName,String clinicAddress,String clinicAFM,String docName,String docID,String docEmail, String docPass){
        OkHttpMediator mediator = new OkHttpMediator();
        String url = "http://10.0.2.2/AndroidStudioProviders//checkDataForNewClinic.php?docName="+docName+"&address="+clinicAddress+"&afm="+clinicAFM+"&docID="+docID+"&name="+clinicName+"&email="+docEmail+"&password="+docPass+"";
        try {
            ArrayList<Boolean> correctValues = mediator.checkDataOfNewClinicAndDoctor(url);
            if(correctValues.size()==7){
                updateForm(correctValues);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateForm(ArrayList<Boolean> list){
        //Error Message TextView
        TextView aError;

        //Incorrect clinic Name
        aError = findViewById(R.id.nameErrorMessage);
        if (!list.get(0)) {
            aError.setText("!Name Already Exist");
        }

        //Incorrect clinic Address
        aError = findViewById(R.id.addresErrorMessage);
        if (!list.get(1)) {
            aError.setText("!Address Already Exist");
        }

        //Incorrect AFM
        aError = findViewById(R.id.afmErrorMessage);
        if (!list.get(2)) {
            aError.setText("!AFM Already Exist");
        }

        //Incorrect physicotherapist Name
        aError = findViewById(R.id.doctorNameErrorMessage);
        if (!list.get(3)) {
            aError.setText("!Name Already Exist");
        }

        //Incorrect doctor email
        aError = findViewById(R.id.mailErrorMessage);
        if (!list.get(5)) {
            aError.setText("!Email Already Exist");
        }

        //Incorrect doctor password
        aError = findViewById(R.id.passwordErrorMessage);
        if (!list.get(6)) {
            aError.setText("!Password Already Exist");
        }

        //Incorrect doctor id
        aError = findViewById(R.id.idErrorMessage);
        if (!list.get(4)) {
            aError.setText("!ID Already Exist");
        }

    }


}


