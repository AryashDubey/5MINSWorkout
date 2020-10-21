package com.example.a10minsworkout;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    static String difficulty="Easy";
    Boolean ActivityStarted =false;
    public void start(View view){
        if(!ActivityStarted) {
            ActivityStarted = true;
            Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
            startActivity(intent);
            CountDownTimer countDownTimer = new CountDownTimer(5000,1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    ActivityStarted=false;
                }
            }.start();
        }else{
            Toast.makeText(this, "Slow It Down!!!!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = findViewById(R.id.spinner);
        SharedPreferences sharedPref = getSharedPreferences("FileName", MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);
        if (spinnerValue != -1) {
            spinner.setSelection(spinnerValue);
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences sharedPref = getSharedPreferences("FileName", 0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("userChoiceSpinner", i);
                prefEditor.apply();
                if (i == 0) {
                    difficulty = "Easy";
                } else if (i == 1) {
                    difficulty = "Medium";
                } else if (i == 2) {
                    difficulty = "Hard";
                } else {
                    difficulty = "Crazy";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }
    public void bmi(View view){
        Intent intent = new Intent(this,bmiActivity.class);
        startActivity(intent);
    }
    public void Activity(View view){
        Intent intent = new Intent(this,History.class);
        startActivity(intent);
    }
}