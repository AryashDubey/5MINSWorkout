package com.example.a10minsworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class History extends AppCompatActivity {
    Array adapter;
    RecyclerView recyclerView;
    TextView text;
    ArrayList exercise = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        text = findViewById(R.id.textView11);
        SQL sql = new SQL(this);
        SQLiteDatabase database=sql.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM WORKOUT",null);
        int nameindex=cursor.getColumnIndex("NAME");
        int dateindex = cursor.getColumnIndex("DATETIME");
        cursor.moveToFirst();
        try {
            do {
                String Exercisename = cursor.getString(nameindex);
                String Date = cursor.getString(dateindex);
                exercise.add("Workout Type :- "+Exercisename+" Workout     Date:- "+Date);

            } while (cursor.moveToNext());
        }catch(Exception e){
            e.printStackTrace();
        }
        if(exercise.size()!=0) {
            text.setVisibility(View.GONE);
            Recycle();
        }
        else {
            text.setVisibility(View.VISIBLE);
        }
    }

    public void Recycle(){
        recyclerView = findViewById(R.id.datarecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new Array(exercise);
        recyclerView.setAdapter(adapter);
    }
}