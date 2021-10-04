package com.example.belajarsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class database extends SQLiteOpenHelper {
    private static String database = "umpar";
    private static int versi_database = 1;
    public database(Context context){
        super(context, database, null, versi_database);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE mahasiswa(nim INTEGER(9) PRIMARY KEY, nama varchar(30))";
        Log.d(TAG, "onCreate: "+sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
