package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


/**
 * NewPSFCentralPage
 * This page is the central page for PSF user.
 * This page will has 2 choices...
 * visit to physiotherapy clinics page and visit to physiotherapy services page
 */

public class NewPSFCentralPage extends AppCompatActivity {

    ArrayList<String> userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_psfcentral_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Κεντρική Σελίδα ΠΣΦ");
        //Print Welcome toast
        Bundle extras = getIntent().getExtras();
        userData = extras.getStringArrayList("userDataArrayList");
        String userName = userData.get(1);
        Toast.makeText(this, "Καλωσήρθατε κ."+userName, Toast.LENGTH_SHORT).show();

        //Show services and clinics/doctors numbers
        showNumbers();

    }

    public void showNumbers() {
        OkHttpMediator mediator = new OkHttpMediator();
        String url;
        String cNumber= "0";
        String servNumber = "0";

        //Get data from server
        url = "http://10.0.2.2/AndroidStudioProviders/getPhysicotherapistsNumber.php";
        try{
            cNumber = mediator.getClinicsNumber(url);
            //Toast.makeText(this, cNumber, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
        url = "http://10.0.2.2/AndroidStudioProviders/getServicesNumber.php";
        try{
            servNumber = mediator.getClinicsNumber(url);
        }catch (Exception e){
            e.printStackTrace();
        }

        //Clear text number for ""
        cNumber = clearTextNumber(cNumber);
        servNumber = clearTextNumber(servNumber);

        //Show data
        TextView clinics = findViewById(R.id.physNumber);
        clinics.setText(cNumber);
        TextView services = findViewById(R.id.servNumber);
        services.setText(servNumber);
    }

    public String clearTextNumber(String cNumber) {
        String characthers[] = cNumber.split("\"");
        //Toast.makeText(this, characthers[0]+" "+characthers[1], Toast.LENGTH_SHORT).show();
        return  characthers[1];
    }

    //Log Out function
    public void LogOut(View view){
        Intent intent = new Intent(getApplicationContext(),LoginPageActivity.class);
        intent.putExtra("TypeUser","PSF");
        startActivity(intent);
    }

    //Next Page Function
    public void NextPage(View view){
        Button leftColumnButton;
        //Get left column button/ left button "ΕΠΙΛΟΓΗ"
        leftColumnButton = findViewById(R.id.goPhysPage);

        Intent intent;
        //If view (selected button) is button from left column
        if(view.equals(leftColumnButton)){
            intent = new Intent(getApplicationContext(),PSFCentralPage.class);
            intent.putExtra("userDataArrayList",userData);
            startActivity(intent);
        }else{
            intent = new Intent(getApplicationContext(),ServicesCentralPage.class);
            intent.putExtra("userDataArrayList",userData);
            startActivity(intent);
        }
    }

    //Print column info function
    public void printInfo(View view){
        ImageButton leftColumnInfoButton;
        String informationLeft = "Η αριστερή στήλη εμφανίζει τον\nαριθμό των φυσικοθεραπευτηρίων που\nσυντελούν τον ΠΣΦ. Το κουμπί\nΕΠΙΛΟΓΗ οδηγεί στην κεντρική\nσελίδα φυσικοθεραπευτηρίων.";
        String informationRight = "Η δεξιά στήλη εμφανίζει τον\nαριθμό των παροχών που\nμπορεί να προσφέρουν τα \nφυσικοθεραπευτήρια. Το κουμπί\nΕΠΙΛΟΓΗ οδηγεί στην κεντρική\nσελίδα παροχών.";

        TextView infoPanel = findViewById(R.id.informationText);
        leftColumnInfoButton = findViewById(R.id.leftColInfo);
        //Left column
        //If selected infoButton is imagebutton from left column
        if(view.equals(leftColumnInfoButton)){
            //If info TextView hasn't text or has right col info text
            if(infoPanel.getText().equals("") || infoPanel.getText().equals(informationRight)){
                infoPanel.setText(informationLeft);
            } else if (infoPanel.getText().equals("") || infoPanel.getText().equals(informationLeft)) {
                //2nd click removes text
                infoPanel.setText("");
            }
        }else{
            //Right column
            //If info TextView hasn't text or has left col info text
            if(infoPanel.getText().equals("") || infoPanel.getText().equals(informationLeft)){
                infoPanel.setText(informationRight);
            } else if (infoPanel.getText().equals("") || infoPanel.getText().equals(informationRight)) {
                //2nd click removes text
                infoPanel.setText("");
            }
        }


    }


    //Block back button
    public void onBackPressed() {
        // do nothing.
    }
}