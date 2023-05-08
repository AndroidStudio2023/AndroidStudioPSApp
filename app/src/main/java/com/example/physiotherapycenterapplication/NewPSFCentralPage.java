package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * NewPSFCentralPage
 * This page is the central page for PSF user.
 * This page will has 2 choices...
 * visit to physiotherapy clinics page and visit to physiotherapy services page
 */

public class NewPSFCentralPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_psfcentral_page);
        getSupportActionBar().setTitle("Central Page");
    }

    //Log Out function
    public void LogOut(View view){
        Intent intent = new Intent(getApplicationContext(),LoginPageActivity.class);
        intent.putExtra("TypeUser","ΠΣΦ");
        startActivity(intent);
    }

    //Next Page Function
    public void NextPage(View view){
        int buttonID;
        //Get ID button
        buttonID = view.getId();

        Intent intent;
        //2131296468 -> id from the button for move to Physiotherapy Clinics Page
        if(buttonID==2131296468){
            intent = new Intent(getApplicationContext(),PSFCentralPage.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
        }
    }

    //Print column info function
    public void printInfo(View view){
        int infoButtonID = view.getId();
        String informationLeft = "Η αριστερή στύλη εμφανίζει τον \nαριθμό των φυσικοθεραπευτηρίων που\nσυντελούν τον ΠΣΦ. Το κουμπί\nΕΠΙΛΟΓΗ οδηγεί στην κεντρική\nσελίδα φυσικοθεραπευτηρίων.";
        String informationRight = "Η δεξιά στύλη εμφανίζει τον\nαριθμό των παροχών που\nμπορεί να προσφέρουν τα \nφυσικοθεραπευτήρια. Το κουμπί\nΕΠΙΛΟΓΗ οδηγεί στην κεντρική\nσελίδα παροχών.";

        TextView infoPanel = findViewById(R.id.informationText);

        //Left column
        //2131296825->id from left imagebutton
        if(infoButtonID==2131296825){
            //If info TextView hasn't text or has right col info text
            if(infoPanel.getText().equals("") || infoPanel.getText().equals(informationRight)){
                infoPanel.setText(informationLeft);
            } else if (infoPanel.getText().equals("") || infoPanel.getText().equals(informationLeft)) {
                //2sd click removes text
                infoPanel.setText("");
            }
        }else if(infoButtonID==2131296826){
            //Right column
            //2131296826->id from right imagebutton
            //If info TextView hasn't text or has left col info text
            if(infoPanel.getText().equals("") || infoPanel.getText().equals(informationLeft)){
                infoPanel.setText(informationRight);
            } else if (infoPanel.getText().equals("") || infoPanel.getText().equals(informationRight)) {
                //2sd click removes text
                infoPanel.setText("");
            }
        }


    }
}