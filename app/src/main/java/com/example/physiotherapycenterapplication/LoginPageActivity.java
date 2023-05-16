package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;
import android.widget.Toast;


import com.example.physiotherapycenterapplication.R.id;

import java.io.IOException;
import java.util.ArrayList;
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

    public void onClickLoginButton(View view){

        //Get imported data
        EditText userEmail = findViewById(id.userEmail);
        EditText userPassword = findViewById(id.userPassword);
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        //Clear form
        closeErrors();
        //call goToUserCentralPage
        try{
            goToUserCentralPage(email,password);

        }catch (Exception e){
            System.out.println("========================ERROR call gotToUserCentralPage==============================\n");
            e.printStackTrace();
        }

    }
    public void goToUserCentralPage(String email,String password) throws IOException {
        //Create url (parse the email,password and typeUser with GET method)
        String url = "http://10.0.2.2/AndroidStudioProviders/findUser.php?email="+email+"&password="+password+"&type="+typeUser;
        //Search for user and save user data to userData ArrayList<String>
        OkHttpMediator mediator = new OkHttpMediator();

        ArrayList<String> userData = mediator.findUser(url);



        //If not found user
        if(userData.size()==0){
            printErrors();
            Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
        }else{
            //If found user (userData.length>0)
            Intent intent;
            if(typeUser.equals("PSF")){
                intent = new Intent(getApplicationContext(),NewPSFCentralPage.class);
                intent.putExtra("userDataArrayList",userData);
                startActivity(intent);
            } else if (typeUser.equals("PHY")) {
                Toast.makeText(this, "Coming Soon!!!", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Coming Soon!!!", Toast.LENGTH_SHORT).show();
            }

        }

    }
    //Close "errors"
    public void closeErrors(){
        EditText emailField = findViewById(R.id.userEmail);
        EditText passField = findViewById(R.id.userPassword);
        TextView emailMessage = findViewById(id.emailErrorMessage);
        TextView passMessage = findViewById(id.passwordErrorMessage);

        //Make borders black
        emailField.setBackground(getResources().getDrawable(R.drawable.normal_textfileds_border));
        passField.setBackground(getResources().getDrawable(R.drawable.normal_textfileds_border));
        //Clear entry data
        emailField.setText("");
        passField.setText("");
        //Close error messages
        emailMessage.setText("");
        passMessage.setText("");
    }
    //Print "errors"
    public void printErrors(){
        EditText emailField = findViewById(R.id.userEmail);
        EditText passField = findViewById(R.id.userPassword);
        TextView emailMessage = findViewById(id.emailErrorMessage);
        TextView passMessage = findViewById(id.passwordErrorMessage);

        //Make borders red
        emailField.setBackground(getResources().getDrawable(R.drawable.error_textfileds_border));
        passField.setBackground(getResources().getDrawable(R.drawable.error_textfileds_border));
        //Clear entry data
        emailField.setText("");
        passField.setText("");
        //Print error messages
        emailMessage.setText("Email maybe is Wrong or Empty!");
        passMessage.setText("Password maybe is Wrong or Empty!");
    }

    //Go FirstPage Function

    public void goFirstPage(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    //Block Back Button
    public void onBackPressed() {
        // do nothing.
    }
}