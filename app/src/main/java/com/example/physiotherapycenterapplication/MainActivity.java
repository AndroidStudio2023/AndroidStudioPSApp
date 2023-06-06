package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button login;//For saving clicked button by user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Change Action Title
        Objects.requireNonNull(getSupportActionBar()).setTitle("Αρχική Σελίδα");

    }


    public void moveToLoginPage(View view){
        String userType;//Save selected button's name

        //Get user type (PSF, Physiotherapist, Patient)
        login = (Button)view;
        userType = getUserTypeCode(login.getText().toString());

        //Move to Login Page Intent code

        Intent intent = new Intent(getApplicationContext(),LoginPageActivity.class);
        //intent.putExtra(key,value):To move the userType to Login Page
        intent.putExtra("TypeUser",userType);
        startActivity(intent);
    }

    public String getUserTypeCode(String text){
        String code = "PSF";

        if(text.equals("ΦΥΣΙΟΘΕΡΑΠΕΥΤΗΣ")){
            code = "PHY";
        } else if (text.equals("ΑΣΘΕΝΗΣ")) {
            code = "PAT";
        }

        return code;
    }


}