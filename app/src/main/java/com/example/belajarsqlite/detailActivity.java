package com.example.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class detailActivity extends AppCompatActivity {

    TextView nim, nama;
    database database;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        String nimNilai = bundle.getString("nimNilai");
        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);

        database = new database(this);
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa where nim='"+nimNilai+"'", null);
        cursor.moveToFirst();
        nim.setText(cursor.getString(0));
        nama.setText(cursor.getString(1));
    }
}