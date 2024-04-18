package com.example.indisky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PassengerDetails extends AppCompatActivity {

    EditText nameEditText, ageEditText;

    RadioGroup radioGroup;

    RadioButton maleRadioButton, femaleRadioButton;

    Button submitButton;

    String name, gender, date, dateCheck;
    int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);

        nameEditText=findViewById(R.id.nameEditText);
        ageEditText=findViewById(R.id.ageEditText);
        radioGroup=findViewById(R.id.radioGroup);
        maleRadioButton=findViewById(R.id.maleRadioButton);
        femaleRadioButton=findViewById(R.id.femaleRadioButton);
        submitButton=findViewById(R.id.submitButton);

        date = getIntent().getStringExtra("date");
        dateCheck=getIntent().getStringExtra("dateCheck");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.maleRadioButton)
                {
                    gender="Male";
                }
                else if(i==R.id.femaleRadioButton)
                {
                    gender="Female";
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEditText.getText().toString();
                String ageString = ageEditText.getText().toString();
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if(name.isEmpty()||ageString.isEmpty()||checkedRadioButtonId==-1)
                    Toast.makeText(PassengerDetails.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                else
                {
                    age = Integer.parseInt(ageString);
                    Intent intent = new Intent(PassengerDetails.this, FlightSummary.class);
                    intent.putExtra("name", name);
                    intent.putExtra("gender", gender);
                    intent.putExtra("age", age);
                    intent.putExtra("date", date);
                    intent.putExtra("dateCheck", dateCheck);
                    startActivity(intent);
                }
            }
        });

    }
}