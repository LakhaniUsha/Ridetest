package com.example.ridetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CarOwnerProfile extends AppCompatActivity {

    TextView out1, out2,out3,out4,out5,out6,out7,out8,out9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        out1= findViewById(R.id.txt1);
        out2= findViewById(R.id.txt2);
        out3= findViewById(R.id.txt3);
        out4= findViewById(R.id.txt4);
        out5= findViewById(R.id.txt5);
        out6= findViewById(R.id.txt6);
        out7= findViewById(R.id.txt7);
        out8= findViewById(R.id.txt8);
        out9= findViewById(R.id.txt_msg1);


        new MyTask().execute();
    }


    private class MyTask extends AsyncTask<Void,Void,Void>
    {
        String o1,o2,o3,o4,o5,o6,o7,o8,o9;

        @Override
        protected Void doInBackground(Void... params) {

            URL url = null;
            Intent mynewIntent = getIntent();

            int personid = mynewIntent.getIntExtra("personid",100);
            try{
                url = new URL("http://172.26.30.18:8080/CAR_sharing/car/mad/carownerprofile&" + personid);

                HttpURLConnection client = null;
                client= (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                int responseCode = client.getResponseCode();
                System.out.println("\n Sending 'GET' requestto URL :" +url);
                System.out.println(" response code :" +responseCode);

                InputStreamReader myInput = new InputStreamReader(client.getInputStream());
                BufferedReader in = new BufferedReader(myInput);
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) !=null)
                {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());

                JSONObject obj =new JSONObject(response.toString());


                    o1 = obj.getString("personid :");
                    o2 = obj.getString("f_name :");
                    o3 = obj.getString("l_name :");
                    o4 = obj.getString("contact :");
                    o5 = obj.getString("email :");
                    o6 = obj.getString("address :");
                    o7 = obj.getString("gender :");
                    o8 = obj.getString("experience :");


                    o9 = obj.getString("message :");



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  null;
        }


        @Override
        protected void onPostExecute(Void result)
        {
            out1.setText(o1);
            out2.setText(o2);
            out3.setText(o3);
            out4.setText(o4);
            out5.setText(o5);
            out6.setText(o6);
            out7.setText(o7);
            out8.setText(o8);
            out9.setText(o9);

            super.onPostExecute(result);
        }

    }
}

