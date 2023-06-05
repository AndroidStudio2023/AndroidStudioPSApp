package com.example.physiotherapycenterapplication;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpMediator {

    public OkHttpMediator(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    //Check login data and find user on system (if imported data are correct)
    public ArrayList<String> findUser(String url) throws IOException {
        //Array List with user data (userID,userName and clinicsData for physiotherapistUser)
        ArrayList<String> userData = new ArrayList<>();
        System.out.println("\n================URL===================\n");
        System.out.println(url);
        System.out.println("\n===================================\n");

        //Request
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        System.out.println("\n================URL===================\n");
        System.out.println(data);
        System.out.println("\n===================================\n");
        //Import data to ArrayList
        try {
            JSONArray json = new JSONArray(data);
//            JSONArray json_services = new JSONArray(data);
//            JSONArray json_dates = new JSONArray(data);
            userData.add(json.getString(0));
            userData.add(json.getString(1));
            if(json.length()>2){
                //if length>2 -> physiotherapist
                //Add clinic's data
                userData.add(json.getString(2));
                userData.add(json.getString(3));
                userData.add(json.getString(4));
            }
        } catch (JSONException e) {
            System.out.println("======================JSONError================================\n");
            e.printStackTrace();
        }

        return userData;
    }

    //Returns all clinics OR clinics from search
    //On view: Key(String)<->ClinicData(ArrayList<String>)
    public HashMap<Integer,ArrayList<String>> getClinicsData(String url) throws Exception{
        HashMap<Integer,ArrayList<String>> clinics = new HashMap<>();

        //Request
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try {

            JSONArray clinicsObject = new JSONArray(data);
           for(int i=0; i<clinicsObject.length(); i++){
               ArrayList<String> cData = new ArrayList<>();//Returned HashMap value/data
               //Get current clinic's data as JSONObject
               JSONArray aClinic = (JSONArray) clinicsObject.get(i);
               cData.add(aClinic.getString(0));
               cData.add(aClinic.getString(1));
               cData.add(aClinic.getString(2));
                //Put clinic to HashMap, i=key
                clinics.put(i,cData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return clinics;
    }

    //Get physicotherapists/clinics OR Services number
    public String getClinicsNumber(String url) throws Exception{
        String clinicsORservices= "0" ;

        //Request
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        clinicsORservices = data;
        return  clinicsORservices;
    }

    //Check data for new Clinic and Physicotherapist
    //return ArrayList<boolean>
    public ArrayList<Boolean> checkDataOfNewClinicAndDoctor(String url) throws Exception{
        ArrayList<Boolean> corrects = new ArrayList<>();

        //Request
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        try{
            JSONArray returnData = new JSONArray(data);
            for(int i=0; i<returnData.length(); i++){
                corrects.add(returnData.getBoolean(i));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return corrects;
    }

    //Create new clinic and physicotherapist
    //records in two tables
    public String createNewPhysicotherapist(String url) throws Exception{
        String data="fail";
        //Request
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        data = response.body().string();
        System.out.println("\n==========Create new dco============\n");
        System.out.println(data);
        System.out.println("\n======================\n");
        return data;
    }



    //Return history data for selected patient
    public ArrayList <String> patientHistory(String url) throws IOException {

        ArrayList<String> history = new ArrayList<>();

        //Request
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        //Import data from JSON array to history ArrayList
        try {
            JSONArray json = new JSONArray(data);

            for (int i=0; i<json.length(); i++) {
                history.add(json.getString(i));
                System.out.println(json.getString(i));
            }
        }
        catch (JSONException e) {
            System.out.println("======================JSONError================================\n");
            e.printStackTrace();
        }

        return history;
    }


}
