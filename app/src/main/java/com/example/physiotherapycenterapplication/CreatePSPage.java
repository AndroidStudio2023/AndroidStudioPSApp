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
    }

    public void CreateNewPS(View view){
        TextView name = findViewById(R.id.newPSName);
        TextView address = findViewById(R.id.newPSAddress);
        TextView afm = findViewById(R.id.newPSAFM);

        String aName = name.getText().toString();
        String aAddress = address.getText().toString();
        String aAFM = afm.getText().toString();

        Toast.makeText(this, "Name: "+aName+"\nAddress: "+aAddress+"\nAFM: "+aAFM, Toast.LENGTH_SHORT).show();
    }
}