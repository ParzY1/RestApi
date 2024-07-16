package com.example.restapi;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RandomPersonTask extends AsyncTask {
    InputStream inputStream;
    URL url = null;
    TextView name;
    TextView email;
    TextView gender;
    ImageView photo;
    TextView birthdate;
    TextView age;
    TextView phone;
    TextView country;
    TextView city;
    TextView street;

    public RandomPersonTask(TextView name, TextView email, TextView gender, ImageView photo, TextView birthdate, TextView age, TextView phone, TextView country, TextView city, TextView street) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.photo = photo;
        this.birthdate = birthdate;
        this.age = age;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Override
    protected void onPreExecute() {
        Log.v("atask", "--------------------------> Start onPreExecute()");
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.v("atask", "--------------------------> Start doInBackground()");
        try{
            url = new URL("https://randomuser.me/api");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try{
            inputStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stringBuilder.append(line);
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            JSONObject person = jsonArray.getJSONObject(0);
            JSONObject name = person.getJSONObject("name");
            String strname = name.getString("title")
                    +". "+name.getString("first")
                    +". "+name.getString("last");
            String email = person.getString("email");
            String gender = person.getString("gender");
            JSONObject picture = person.getJSONObject("picture");
            String portrait = picture.getString("large");
            JSONObject dob = person.getJSONObject("dob");
            String birthdate = dob.getString("date");
            String age = dob.getString("age");
            String phone = person.getString("phone");
            JSONObject location = person.getJSONObject("location");
            String country = location.getString("country");
            String city = location.getString("city");
            JSONObject street = location.getJSONObject("street");
            String streetNumber = street.getString("number");
            String streetName = street.getString("name");
            String fullStreet = streetName + " " + streetNumber;

            publishProgress(strname, 0);
            publishProgress(email, 1);
            publishProgress(gender, 2);
            publishProgress(portrait, 3);
            publishProgress(birthdate, 4);
            publishProgress(age, 5);
            publishProgress(phone, 6);
            publishProgress(country, 7);
            publishProgress(city, 8);
            publishProgress(fullStreet, 9);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        Log.v("atask", "--------------------------> Start onProgressUpdate()");
        Log.v("atask", values[0].toString());
        switch (Integer.parseInt(values[1] + "")) {
            case 0:
                name.setText("Name: " + values[0].toString());
                break;
            case 1:
                email.setText("E-mail: " + values[0].toString());
                break;
            case 2:
                gender.setText("Gender: " + values[0].toString());
                break;
            case 3:
                Glide.with(photo.getContext()).load(values[0].toString()).override(124, 124).into(photo);
                break;
            case 4:
                birthdate.setText("Birthdate: " + values[0].toString());
                break;
            case 5:
                age.setText("Age: " + values[0].toString());
                break;
            case 6:
                phone.setText("Phone number: " + values[0].toString());
                break;
            case 7:
                country.setText("Country: " + values[0].toString());
                break;
            case 8:
                city.setText("City: " + values[0].toString());
                break;
            case 9:
                street.setText("Street: " + values[0].toString());
            default:
                return;
        }

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
        Log.v("atask", "--------------------------> Start onPostExecute()");
        super.onPostExecute(o);
    }
}
//Marcel Parzyszek 4P