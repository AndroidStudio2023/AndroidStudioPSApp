package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.view.*;

import com.example.physiotherapycenterapplication.R.id;

public class LoginPageActivity extends AppCompatActivity {

    TextView user;
    Bundle extras;
    String typeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        extras = getIntent().getExtras();
        user = (TextView)findViewById(R.id.userText);
        typeUser = extras.getString("TypeUser");
        user.setText(typeUser);
    }
}