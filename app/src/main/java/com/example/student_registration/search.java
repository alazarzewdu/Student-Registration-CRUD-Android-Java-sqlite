package com.example.student_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Paths;
import java.util.ArrayList;

public class search extends AppCompatActivity {

    EditText s_field;
    Button s_btn;
    ListView s_list;

    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        s_field = findViewById(R.id.s_field);
        s_btn = findViewById(R.id.s_btn);



        s_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });








    }

    public void search () {
        String n = s_field.getText().toString();
        SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("select * from student where id='"+n+"'", null);
        if (c.getCount() == 0) {
            Toast.makeText(getApplicationContext(),"NO record",Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()) {
            buffer.append("Id: \t" +c.getString(0)+"\n");
            buffer.append("First Name: \t" +c.getString(1)+"\n");
            buffer.append("Last Name: \t" +c.getString(2)+"\n");
            buffer.append("Age: \t" +c.getString(3)+"\n");
            buffer.append("Department: \t" +c.getString(4)+"\n");
            buffer.append("Section: \t" +c.getString(5)+"\n");

        }
        Toast toast = Toast.makeText(getApplicationContext(), "Found \n" +buffer.toString(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.LEFT, 300, 450);
        toast.show();



    }
}