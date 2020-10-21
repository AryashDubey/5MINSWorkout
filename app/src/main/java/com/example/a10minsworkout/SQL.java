package com.example.a10minsworkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQL extends SQLiteOpenHelper {
    public static final String database="fiveminworkout";
    public static final int version =1;
    public SQL(Context context){
        super(context,database,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS WORKOUT(_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DATETIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insert(String Name,String date,SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put("NAME",Name);
        values.put("DATETIME",date);
        database =this.getWritableDatabase();
        database.insert("WORKOUT",null,values);
    }
}
