package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;

import com.example.physiotherapycenterapplication.R.id;

public class LoginPageActivity extends AppCompatActivity {

    TextView user;
    Bundle extras;//Gia na paroume ta data apo to proigoumeno Activity
    String typeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        extras = getIntent().getExtras();//Arxikopoiisi Bundle extras
        user = findViewById(R.id.userText);
        typeUser = extras.getString("TypeUser");//Apothikeusi tis extra pliroforias
        //user.setText(typeUser);


    }

    public void goToUserCentralPage(View view){


        //Find and save email
        EditText Email = findViewById(id.userEmail);
        String userEmail = Email.getText().toString();
        //Find and save password
        EditText Password = findViewById(id.userPassword);
        String userPassword = Password.getText().toString();

        //Elegxos dedomenwn xristi
        //!Pros to paron "uparxei" 1 xristis, o PSF
        if(typeUser.equals("ΠΣΦ") && userEmail.equals("psf@gmail.com") && userPassword.equals("1234")){
            //Katharisma formas
            Email.setText("");
            Password.setText("");
            //Sundesi stin kentrikh selida PSF
            Intent intent = new Intent(getApplicationContext(),PSFCentralPage.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }


    }
}