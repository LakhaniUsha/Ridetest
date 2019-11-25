package com.example.ridetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentRegistration extends AppCompatActivity {
    TextView out1;
    Button btngo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        out1= findViewById(R.id.txt_msg);
        btngo= findViewById(R.id.btn_sub);
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
            }
        });





    }

    private class MyTask extends AsyncTask<Void,Void,Void> {
        String o1;

        @Override
        protected Void doInBackground(Void... params) {

            URL url = null;
            EditText ed1 = findViewById(R.id.edt_id);
            EditText ed2 = findViewById(R.id.edt_fname);
            EditText ed3 = findViewById(R.id.edt_lname);
            EditText ed4 = findViewById(R.id.editText5);
            EditText ed5 = findViewById(R.id.editText6);
            EditText ed6 = findViewById(R.id.editText7);
            EditText ed7 = findViewById(R.id.editText8);
            EditText ed8 = findViewById(R.id.editText9);

            @SuppressLint("WrongThread") String val1=(ed1.getText().toString());
            @SuppressLint("WrongThread") String val2=(ed2.getText().toString());
            @SuppressLint("WrongThread") String val3=(ed3.getText().toString());
            @SuppressLint("WrongThread") String val4=(ed4.getText().toString());
            @SuppressLint("WrongThread") String val5=(ed5.getText().toString());
            @SuppressLint("WrongThread") String val6=(ed6.getText().toString());
            @SuppressLint("WrongThread") String val7=(ed7.getText().toString());
            @SuppressLint("WrongThread") String val8=(ed8.getText().toString());

//            Intent mynewIntent1 = getIntent();


            try{
                url = new URL("http://172.26.30.16:8080/CAR_sharing/car/mad/studentregistration&"+val1 +"&"+val2+"&"+val3+"&"+val4+"&"+val5+"&"+val6+"&"+val7+"&"+val8);

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

                o1=obj.getString("Message");



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

            super.onPostExecute(result);
        }

    }
}
