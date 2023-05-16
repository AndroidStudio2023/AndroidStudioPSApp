package com.example.physiotherapycenterapplication;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
}
