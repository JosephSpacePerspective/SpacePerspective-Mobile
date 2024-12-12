package com.SpacePerspective.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DataActivity extends Activity {

    String pid = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            pid = extras.getString("pid");
            String response = getData(pid);

            TextView dataText = findViewById(R.id.dataText);
            dataText.setText(response);

            try {
                JSONArray jArray = new JSONArray(response);
                int total = jArray.length();
                for (int i = 0; i < total; i++) {
                    JSONObject json = jArray.getJSONObject(i);
                    String deviceId = json.getString("DeviceID");

                }
            } catch(Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Failed to read" + pid+ " data",Toast.LENGTH_LONG).show();
            }


        }

    }

    public String getData(String PID) {

        URL url;
        String response = "";
        try {
            url = new URL("http://192.168.10.89:5000/readHmiInd");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("ProcessID", PID);
            String jsonString = jsonParam.toString();
            //send the POST out
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonParam.toString());
            os.flush();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            }

            } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


}
