package com.example.belajarsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fl;
    ListView list;
    database database;
    Cursor cursor;
    public static MainActivity ma;
    String[] daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        fl = findViewById(R.id.fl);
        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, tambahActivity.class));
            }
        });
        database = new database(this);
        ma = this;
        merefresh();
    }
    public void merefresh(){
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa",null);
        cursor.moveToFirst();
        daftar = new String[cursor.getCount()];
        for (int a=0; a<cursor.getCount(); a++){
            cursor.moveToPosition(a);
            daftar[a] = cursor.getString(0);
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, daftar);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nimNilai = daftar[position];
                Intent in = new Intent(MainActivity.this, detailActivity.class);
                in.putExtra("nimNilai", nimNilai);
                startActivity(in);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String nimNilai = daftar[position];
                String[] item = {"Cek Detail", "Ubah Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Data NIM : "+nimNilai);
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                                Intent in = new Intent(MainActivity.this, detailActivity.class);
                                in.putExtra("nimNilai", nimNilai);
                                startActivity(in);
                                break;
                            case 1:
                                Intent i = new Intent(MainActivity.this, tambahActivity.class);
                                i.putExtra("nimNilai", nimNilai);
                                startActivity(i);
                                break;
                        }
                    }
                });
                builder.show();
                return true;
            }
        });
    }
}