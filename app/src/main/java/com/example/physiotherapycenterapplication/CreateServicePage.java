package com.example.physiotherapycenterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateServicePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_service_page);
        getSupportActionBar().setTitle("Σελίδα Προσθήκης Νέας Παροχής");
    }

    public void clearForm(){
        EditText nameField = findViewById(R.id.servNameField);
        EditText codeField = findViewById(R.id.servCodeField);
        EditText costField = findViewById(R.id.servCostField);
        EditText desField = findViewById(R.id.servDescriptionField);

        nameField.setText("");
        codeField.setText("");
        costField.setText("");
        desField.setText("");
    }

    public void createServ(View v){
        EditText nameField = findViewById(R.id.servNameField);
        EditText codeField = findViewById(R.id.servCodeField);
        EditText costField = findViewById(R.id.servCostField);
        EditText desField = findViewById(R.id.servDescriptionField);

        String name = nameField.getText().toString();
        String code = codeField.getText().toString();
        String cost = costField.getText().toString();
        String des = desField.getText().toString();

        if(checkData(name,code,cost,des)){
            OkHttpMediator mediator = new OkHttpMediator();
            String url = "http://10.0.2.2/AndroidStudioProviders/createService.php?id="+code+"&name="+name+"&description="+des+"&cost="+cost;
            try {
                String mess = mediator.addService(url);
                if(mess.equals("\"comple\"")){
                    clearForm();
                    Toast.makeText(this, "Επιτυχής Δημιουργία", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Σφάλμα: Ελέξτε τα δεδομένα σας", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Ελέξτε τα δεδομένα σας", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkData(String name, String code, String cost, String des) {

        if(name.length()==0 || name.length()>35){
            return false;
        }

        if(code.length()!=6 && !code.startsWith("srv")){
            return false;
        }

        if(cost.length()==0){
            return false;
        }

        if(des.length()==0 || des.length()>120){
            return false;
        }

        return true;
    }
}