package com.example.restapi;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class JokesTask extends AsyncTask<Void, Void, String> {

    private TextView quote;

    public JokesTask(TextView quote){
        this.quote = quote;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            try {
                URL url = new URL("https://api.chucknorris.io/jokes/random");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder jokeBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jokeBuilder.append(line);
                }
                reader.close();
                inputStream.close();
                connection.disconnect();

                JSONObject jsonObject = new JSONObject(jokeBuilder.toString());
                String joke = jsonObject.getString("value");
                stringBuilder.append(joke).append("\n\n");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String jokes) {
        super.onPostExecute(jokes);
        quote.setText(jokes);
    }
}
