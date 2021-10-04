package com.example.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tambahActivity extends AppCompatActivity {

    Button tambah, reset, batal;
    EditText nim, nama;
    database database;
    Cursor cursor;
    String nimNilai = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        database = new database(this);
        nim = findViewById(R.id.nim);
        nama = findViewById(R.id.nama);
        tambah = findViewById(R.id.tambah);
        reset = findViewById(R.id.reset);
        batal = findViewById(R.id.batal);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            nimNilai = bundle.getString("nimNilai");
        }

        if(nimNilai != null){
            SQLiteDatabase db = database.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM mahasiswa where nim='"+nimNilai+"'",null);
            cursor.moveToFirst();
            nim.setText(cursor.getString(0));
            nama.setText(cursor.getString(1));
        }

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nim.getText() == null){
                    nim.setError("Harap Isi bidang ini");
                }
                else if(nama.getText().toString() == null){
                    nim.setError("Harap Isi bidang ini");
                }
                else{
                    SQLiteDatabase db = database.getReadableDatabase();
                    if (nimNilai != null){
                        db.execSQL("UPDATE mahasiswa set nim='" +
                                nim.getText().toString()+"', nama='" +
                                nama.getText().toString()+"' WHERE nim = '" +
                                nimNilai+"'");
                        MainActivity.ma.merefresh();
                        finish();
                    }
                    else{
                        db.execSQL("INSERT INTO mahasiswa(nim,nama) VALUES('" +
                                nim.getText().toString()+"', '" +
                                nama.getText().toString()+"')");
                        MainActivity.ma.merefresh();
                        finish();
                    }
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim.setText("");
                nama.setText("");
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}