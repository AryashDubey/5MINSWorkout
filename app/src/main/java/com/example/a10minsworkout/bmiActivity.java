package com.example.a10minsworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class bmiActivity extends AppCompatActivity {
    int a;
    int b;
    AppCompatEditText weight;
    TextView bmicompliment;
    AppCompatEditText height;
    TextView Bmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        bmicompliment= findViewById(R.id.textView12);
        weight = findViewById(R.id.weightedittext);
        height = findViewById(R.id.height);
        Bmi = findViewById(R.id.textView10);
        Button calc= findViewById(R.id.button3);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bminum=0;
                if(weight.getText().toString().equals("")||height.getText().toString().equals("")){
                    Toast.makeText(bmiActivity.this, "Please Enter Valid Information", Toast.LENGTH_SHORT).show();
                }else {
                    a = Integer.parseInt(weight.getText().toString());
                    b = Integer.parseInt(height.getText().toString());
                    try {
                        if ((a / (b * b / 10000)) < 1) {
                            Toast.makeText(bmiActivity.this, "Please Recheck Your Info", Toast.LENGTH_SHORT).show();
                        } else {
                            bminum = a / (b * b / 10000);
                        }
                    }catch (Exception e){
                        if(e!=null){
                            Toast.makeText(bmiActivity.this, "Please Recheck Your Information", Toast.LENGTH_SHORT).show();
                        }
                    }
                    if(bminum!=0) {
                        Bmi.setText("Your BMI is:- " + bminum);
                        if (18 >= bminum) {
                            bmicompliment.setText("You Need To Gain Weight!");
                        } else if (18 < bminum && bminum < 25) {
                            bmicompliment.setText("You Are In Good Shape!");
                        } else {
                            bmicompliment.setText("You Need To Lose Weight!");
                        }
                    }

                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CALCULATE BMI");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}