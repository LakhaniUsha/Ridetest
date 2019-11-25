package com.example.ridetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText personid;
        Button b1 = (Button) findViewById(R.id.button_carpro);
        personid = (EditText) findViewById(R.id.editText);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), CarOwnerProfile.class);
                myIntent.putExtra("personid", Integer.parseInt(personid.getText().toString()));
                startActivity(myIntent);
            }
        });


        Button b2 = findViewById(R.id.button_reg);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {


                Intent myIntent1 =new Intent (getApplicationContext(),StudentRegistration.class);



                startActivity(myIntent1);
            }
        });

    }
}
