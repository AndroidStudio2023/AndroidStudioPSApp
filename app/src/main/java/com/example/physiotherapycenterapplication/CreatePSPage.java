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
        getSupportActionBar().setTitle("Σελίδα Προσθήκης Νέου Φυσιοθεραπευτηρίου");


    }

    public void CreateNewPS(View view) {
        //Flag == 3 , create new PS
        int flag = 0;
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
        if (checkForCorrectData(aClinicName,aClinicAddress,aClinicAFM,aPhyName,aPhyEmail,aPhyPass,aPhyID)) {
            flag++;
        }
        if(checkForDataCompleteness(aClinicName,aClinicAddress,aClinicAFM,aPhyName,aPhyEmail,aPhyPass,aPhyID)){
            flag++;
        }
        if(checkForEmptyData(aClinicName,aClinicAddress,aClinicAFM,aPhyName,aPhyEmail,aPhyPass,aPhyID)){
            flag++;
        }

        if(flag==3){

            //Create new ps and doctor
            OkHttpMediator mediator = new OkHttpMediator();
            String url = "http://10.0.2.2/AndroidStudioProviders/createNewClinic.php?docName="+aPhyName+"&address="+aClinicAddress+"&afm="+aClinicAFM+"&docID="+aPhyID+"&name="+aClinicName+"&email="+aPhyEmail+"&password="+aPhyPass;
            try{
                String message = mediator.createNewPhysicotherapist(url);
                if(message.equals("fail")){
                    Toast.makeText(this, "Αποτυχία Συστήματος", Toast.LENGTH_SHORT).show();
                }else{
                    //Clear form
                    clinicName.setText("");
                    clinicAddress.setText("");
                    clinicAfm.setText("");
                    phyName.setText("");
                    phyEmail.setText("");
                    phyPass.setText("");
                    phyID.setText("");
                    Toast.makeText(this, "Επιτυχής Δημιουργία", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    //returns number of empty data
    public boolean checkForEmptyData(String clinicName,String clinicAddress,String clinicAFM,String docName,String docEmail, String docPass,String docID){

        //Error Message TextView
        TextView aError;
        //Empty fields number
        int emptyFileds = 0;
        //Incorrect clinic Name
        aError = findViewById(R.id.nameErrorMessage);
        if (clinicName.equals("")) {
            aError.setText("!Το πεδίο είναι κενό");
            emptyFileds++;
        }

        //Incorrect clinic Address
        aError = findViewById(R.id.addresErrorMessage);
        if (clinicAddress.equals("")) {
            aError.setText("!Το πεδίο είναι κενό");
            emptyFileds++;
        }

        //Incorrect AFM
        aError = findViewById(R.id.afmErrorMessage);
        if (clinicAFM.equals("")) {
            aError.setText("!Το πεδίο είναι κενό");
            emptyFileds++;
        }

        //Incorrect physicotherapist Name
        aError = findViewById(R.id.doctorNameErrorMessage);
        if (docName.equals("")) {
            aError.setText("!Το πεδίο είναι κενό");
            emptyFileds++;
        }

        //Incorrect doctor email
        aError = findViewById(R.id.mailErrorMessage);
        if (docEmail.equals("")) {
            aError.setText("!Το πεδίο είναι κενό");
            emptyFileds++;
        }

        //Incorrect doctor password
        aError = findViewById(R.id.passwordErrorMessage);
        if (docPass.equals("")) {
            aError.setText("!Το πεδίο είναι κενό");
            emptyFileds++;
        }

        //Incorrect doctor id
        aError = findViewById(R.id.idErrorMessage);
        if (docID.equals("")) {
            aError.setText("!Το πεδίο είναι κενό");
            emptyFileds++;
        }

        if(emptyFileds>0){
            return false;
        }
        return true;
    }


    public boolean checkForCorrectData(String clinicName,String clinicAddress,String clinicAFM,String docName,String docEmail, String docPass,String docID){
        OkHttpMediator mediator = new OkHttpMediator();
        String url = "http://10.0.2.2/AndroidStudioProviders//checkDataForNewClinic.php?docName="+docName+"&address="+clinicAddress+"&afm="+clinicAFM+"&docID="+docID+"&name="+clinicName+"&email="+docEmail+"&password="+docPass+"";
        try {
            ArrayList<Boolean> correctValues = mediator.checkDataOfNewClinicAndDoctor(url);
            if(correctValues.size()==7){
                return updateForm(correctValues);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //System fail
        return false;
    }

    public boolean updateForm(ArrayList<Boolean> list){
        //Error Message TextView
        TextView aError;
        Boolean flag=true; //Means all data is correct
        //Incorrect clinic Name
        aError = findViewById(R.id.nameErrorMessage);
        if (!list.get(0)) {
            aError.setText("!Το όνομα χρησιμοποιείται ήδη");
            flag = false;
        }

        //Incorrect clinic Address
        aError = findViewById(R.id.addresErrorMessage);
        if (!list.get(1)) {
            aError.setText("!Η διεύθυνση χρησιμοποιείται ήδη");
            flag = false;
        }

        //Incorrect AFM
        aError = findViewById(R.id.afmErrorMessage);
        if (!list.get(2)) {
            aError.setText("!Το Α.Φ.Μ χρησιμοποιείται ήδη");
            flag = false;
        }

        //Incorrect physicotherapist Name
        aError = findViewById(R.id.doctorNameErrorMessage);
        if (!list.get(3)) {
            aError.setText("!Το όνομα χρησιμοποιείται ήδη");
            flag = false;
        }

        //Incorrect doctor email
        aError = findViewById(R.id.mailErrorMessage);
        if (!list.get(5)) {
            aError.setText("!Το email χρησιμοποιείται ήδη");
            flag = false;
        }

        //Incorrect doctor password
        aError = findViewById(R.id.passwordErrorMessage);
        if (!list.get(6)) {
            aError.setText("!Ο κωδ. πρόσβασης χρησιμοποιείται ήδη");
            flag = false;
        }

        //Incorrect doctor id
        aError = findViewById(R.id.idErrorMessage);
        if (!list.get(4)) {
            aError.setText("!Το id χρησιμοποιείται ήδη");
            flag = false;
        }
        return flag;
    }

    //Elegxos an ta dedomena plhroun tis prodiagrafes
    public boolean checkForDataCompleteness(String clinicName,String clinicAddress,String clinicAFM,String docName,String docEmail, String docPass,String docID ) {
        //Error Message TextView
        TextView aError;
        //Empty fields number
        int correctFields = 0;
        //correct clinic Name
        aError = findViewById(R.id.nameErrorMessage);
        if (clinicName.length()<=45) {
            correctFields++;
        } else{
            aError.setText("!Το όνομα πρέπει να έχει εώς 45 χαρακτήρες");
        }

        //correct clinic Address
        aError = findViewById(R.id.addresErrorMessage);
        if (clinicAddress.length()<=30) {
            correctFields++;
        }else{
            aError.setText("!Η διέυθυνση πρέπει να έχει εώς 30 χαρακτήρες");
        }

        //correct AFM
        aError = findViewById(R.id.afmErrorMessage);
        if (clinicAFM.length()==9) {
            correctFields++;
        }else{
            aError.setText("!Το Α.Φ.Μ πρέπει να είναι 9 ψηφία");
        }

        //Incorrect physicotherapist Name
        aError = findViewById(R.id.doctorNameErrorMessage);
        if (docName.length()<=35) {
            correctFields++;
        }else{
            aError.setText("!Το όνομα πρέπει να είναι εώς 35 χαρακτήρες");
        }

        //Incorrect doctor email
        aError = findViewById(R.id.mailErrorMessage);
        if (docEmail.endsWith("@gmail.com") && docEmail.length()<=50) {
            correctFields++;
        }else{
            aError.setText("!Το email πρέπει να τελειώνει με '@gmail.com' και να έχει εώς 50 χαρακτήρες");
        }

        //Incorrect doctor password
        aError = findViewById(R.id.passwordErrorMessage);
        if (docPass.length()<=20) {
            correctFields++;
        }else{
            aError.setText("!Ο κωδικός πρέπει να έχει εώς 20 χαρακτήρες");
        }

        //Incorrect doctor id
        aError = findViewById(R.id.idErrorMessage);
        if (docID.length()<=6 && docID.startsWith("phy")) {
            correctFields++;
        }else {
            aError.setText("!Το ID πρέπει να ξεκινά με 'phy' ακολουθόμενο από 3 ψηφία");
        }

        if(correctFields<7){
            return false;
        }
        return true;
    }

    public void clearForm(View view){
        //Fields
        TextView clinicName = findViewById(R.id.newPSName);
        TextView clinicAddress = findViewById(R.id.newPSAddress);
        TextView clinicAfm = findViewById(R.id.newPSAFM);
        TextView phyName = findViewById(R.id.newDoctorName);
        TextView phyEmail = findViewById(R.id.newDoctorMail);
        TextView phyPass = findViewById(R.id.doctorPassword);
        TextView phyID = findViewById(R.id.newDoctorID);

        clinicName.setText("");
        clinicAddress.setText("");
        clinicAfm.setText("");
        phyName.setText("");
        phyEmail.setText("");
        phyPass.setText("");
        phyID.setText("");

        //Error messages

        TextView er1 = findViewById(R.id.nameErrorMessage);
        TextView er2 = findViewById(R.id.addresErrorMessage);
        TextView er3 = findViewById(R.id.afmErrorMessage);
        TextView er4 = findViewById(R.id.doctorNameErrorMessage);
        TextView er5 = findViewById(R.id.idErrorMessage);
        TextView er6 = findViewById(R.id.mailErrorMessage);
        TextView er7 = findViewById(R.id.passwordErrorMessage);

        er1.setText("");
        er2.setText("");
        er3.setText("");
        er4.setText("");
        er5.setText("");
        er6.setText("");
        er7.setText("");


    }

}


