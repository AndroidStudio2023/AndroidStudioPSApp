package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;


import com.example.physiotherapycenterapplication.R.id;

import java.util.Objects;


public class LoginPageActivity extends AppCompatActivity {

    TextView user;
    Bundle extras;//Gia na paroume ta data apo to proigoumeno Activity
    String typeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //Change Action Title
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login Page");

        extras = getIntent().getExtras();//Bundle extras
        user = findViewById(R.id.userText);
        typeUser = extras.getString("TypeUser");//Save extra information
        //user.setText(typeUser);


    }

    public void goToUserCentralPage(View view){

        EditText someField;
        //"Keys" variables
        boolean correctEmail,correctPassword;
        //Find and Save ErrorMessages
        TextView wrongEmailMessage = findViewById(R.id.emailErrorMessage);
        TextView wrongPassMessage = findViewById(R.id.passwordErrorMessage);
        //Find and save email
        EditText Email = findViewById(R.id.userEmail);
        String userEmail = Email.getText().toString();
        //Find and save password
        EditText Password = findViewById(id.userPassword);
        String userPassword = Password.getText().toString();

        //Control user's import data
        //!Pros to paron "uparxei" 1 xristis, o PSF

        correctEmail = checkEmail(typeUser,userEmail);
        correctPassword = checkPassword(typeUser,userPassword);
        //1o senario: Lathos email
        someField = findViewById(id.userEmail);
        if(!correctEmail){
            someField.setBackground(getResources().getDrawable(R.drawable.error_textfileds_border));
            wrongEmailMessage.setText("! Email is incorrect or empty");
        }else{
            someField.setBackground(getResources().getDrawable(R.drawable.normal_textfileds_border));
            wrongEmailMessage.setText("");
        }

        //2o senario: Lathos paswword
        someField = findViewById(id.userPassword);
        if(!correctPassword){
            someField.setBackground(getResources().getDrawable(R.drawable.error_textfileds_border));
            wrongPassMessage.setText("! Password is incorrect or empty");
        }else{
            someField.setBackground(getResources().getDrawable(R.drawable.normal_textfileds_border));
            wrongPassMessage.setText("");
        }

        //Clear form
        Email.setText("");
        Password.setText("");

        //3o senario: Swsta stoixeia
        if(correctEmail && correctPassword){
            //Clear form
            Email.setText("");
            Password.setText("");
            //Sundesi stin kentrikh selida PSF
            Intent intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
            startActivity(intent);
        }



    }

    public boolean checkEmail(String type,String email){

        if(type.equals("ΠΣΦ") && email.equals("psf@gmail.com")){
            return true;
        }else{
            return false;
        }

    }

    public boolean checkPassword(String type, String password){
        if(type.equals("ΠΣΦ") && password.equals("1234")){
            return true;
        }else{
            return false;
        }
    }
}