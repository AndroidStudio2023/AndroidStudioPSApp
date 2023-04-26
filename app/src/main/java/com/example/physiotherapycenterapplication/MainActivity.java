package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;//Gia tin apothikeusi tou koumpiou pou epilegei o xristis
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Change Action Title
        getSupportActionBar().setTitle("Home Page");

    }


    public void moveToLoginPage(View view){
        String userType;//Gia apotikeusi tou onomatos tou epilegmenou koumpiou

        //Pairnoume to typo xristi
        login = (Button)view;
        userType = login.getText().toString();

        //Metafora stin epomeni selida (Sto basicActivity uparxei by default)

        Intent intent = new Intent(getApplicationContext(),LoginPageActivity.class);
        //intent.putExtra(key,value):pairnei extra dedomena sto epomeno activity (edw ton typo xristi)
        intent.putExtra("TypeUser",userType);
        startActivity(intent);
    }


}