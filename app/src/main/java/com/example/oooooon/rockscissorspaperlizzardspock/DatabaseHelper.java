package com.example.oooooon.rockscissorspaperlizzardspock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OooOoOn on 27/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "players.db";
    public static final String TABLE_NAME = "players_table";
    public static final String COLUMN1 = "Name";
    public static final String COLUMN2 = "Score";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Score INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN1, name);
        contentValues.put(COLUMN2, score);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        /*Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;*/

        //refactorized to remove unneccessary code above. tested and works.
        return db.rawQuery("select * from " + TABLE_NAME, null);

    }
}
