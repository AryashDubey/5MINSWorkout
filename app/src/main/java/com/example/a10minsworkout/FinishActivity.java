package com.example.a10minsworkout;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FinishActivity extends AppCompatActivity {
    static ArrayList<String> exercisename;
    static ArrayList<String> exercisedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);


        exercisename =new ArrayList<>();
        exercisedate =new ArrayList<>();
        Button restart = findViewById(R.id.button2);
        Button finish = findViewById(R.id.button);

        Intent intent = getIntent();
        String a =intent.getStringExtra("WorkoutType");
        SQL sql = new SQL(this);
        SQLiteDatabase database=sql.getWritableDatabase();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        sql.insert(a,date,database);
        MediaPlayer song = MediaPlayer.create(getApplicationContext(),R.raw.congo);
        song.start();
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ExerciseActivity.class);
                startActivity(intent);
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}