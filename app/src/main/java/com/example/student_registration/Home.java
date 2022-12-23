package com.example.student_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    EditText eid,efname,elname,eage,edepartment,esection;
    Button badd,bdisplay,bedit,bdelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        eid = findViewById(R.id.st_ide);
        efname = findViewById(R.id.st_fnamee);
        elname = findViewById(R.id.st_lnamee);
        eage = findViewById(R.id.st_agee);
        edepartment = findViewById(R.id.st_departmente);
        esection = findViewById(R.id.st_sectione);

        badd = findViewById(R.id.btn_add);
        bdisplay = findViewById(R.id.btn_display);
        bedit = findViewById(R.id.btn_edit);
        bdelete = findViewById(R.id.btn_delete);


        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    insert();
            }
        });


        bdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Display.class);
                startActivity(i);
            }
        });


        bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    public void insert() {
        try {
            String id = eid.getText().toString();
            String fname = efname.getText().toString();
            String lname = elname.getText().toString();
            String age = eage.getText().toString();
            String department = edepartment.getText().toString();
            String section = esection.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS student(id VARCHAR PRIMARY KEY, fname VARCHAR, lname VARCHAR, age INTEGER, department VARCHAR, section VARCHAR)");

            String sql = "INSERT INTO student(id,fname,lname,age,department,section)values(?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,id);
            statement.bindString(2,fname);
            statement.bindString(3,lname);
            statement.bindString(4,age);
            statement.bindString(5,department);
            statement.bindString(6,section);
            statement.execute();
            Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();

            eid.setText("");
            efname.setText("");
            elname.setText("");
            eage.setText("");
            edepartment.setText("");
            esection.setText("");
            eid.requestFocus();


        }
        catch (Exception ex) {
            Toast.makeText(this, "Failed Registration", Toast.LENGTH_SHORT).show();

        }
    }



    //edit


    public void edit() {
        try {
            String id = eid.getText().toString();
            String fname = efname.getText().toString();
            String lname = elname.getText().toString();
            String age = eage.getText().toString();
            String department = edepartment.getText().toString();
            String section = esection.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);

            String sql = "UPDATE student set fname=?, lname=?, age=?, department=?, section=? WHERE id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,id);
            statement.bindString(2,fname);
            statement.bindString(3,lname);
            statement.bindString(4,age);
            statement.bindString(5,department);
            statement.bindString(6,section);
            statement.execute();
            Toast.makeText(this, "Record Edited", Toast.LENGTH_SHORT).show();

            eid.setText("");
            efname.setText(""); 
            elname.setText("");
            eage.setText("");
            edepartment.setText("");
            esection.setText("");
            eid.requestFocus();


        }
        catch (Exception ex) {
            Toast.makeText(this, "Failed Registration", Toast.LENGTH_SHORT).show();

        }
    }


}