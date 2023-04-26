package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CreatePSPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pspage);
        //Change Action Title
        getSupportActionBar().setTitle("Add Physiotherapy Center Page");
    }

    public void CreateNewPS(View view){
        //Find and Save imported data
        TextView name = findViewById(R.id.newPSName);
        TextView address = findViewById(R.id.newPSAddress);
        TextView afm = findViewById(R.id.newPSAFM);

        String aName = name.getText().toString();
        String aAddress = address.getText().toString();
        String aAFM = afm.getText().toString();

        //"Keys" variables, Arxikopoiisi me tis sunartiseis elegxou dedomenwn
        Boolean correctName = checkPSFName(aName);
        Boolean correctAddress = checkPSFAddress(aAddress);
        Boolean correctAFM = checkPSFAFM(aAFM);

        //Error Message TextView
        TextView aError;

        //Incorrect Name
        aError = findViewById(R.id.nameErrorMessage);
        if(!correctName){
            aError.setText("!Name is Empty");
        }else{
            aError.setText("");
        }

        //Incorrect Address
        aError = findViewById(R.id.addresErrorMessage);
        if(!correctAddress){
            aError.setText("!Address is empty");
        }else{
            aError.setText("");
        }

        //Incorrect AFM
        aError = findViewById(R.id.afmErrorMessage);
        if(!correctAFM){
            aError.setText("!AFM is empty");
        }else{
            aError.setText("");
        }

        //Correct All Data
        if(correctAddress && correctName && correctAFM){
            Toast.makeText(this, "Name: "+aName+"\nAddress: "+aAddress+"\nAFM: "+aAFM, Toast.LENGTH_SHORT).show();
        }

        //Clear form
        name.setText("");
        address.setText("");
        afm.setText("");

    }

    public boolean checkPSFName(String aName){
        if(aName.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkPSFAddress(String aAddress){
        if(aAddress.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkPSFAFM(String aAFM){
        if(aAFM.equals("")){
            return false;
        }else{
            return true;
        }
    }
}