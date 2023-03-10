package com.example.student_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    EditText eid,efname,elname,eage,edepartment,esection;
    Button badd,bdisplay,bedit,bdelete,bdeleteall,btnsearch;
    TextView dev;


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
        bdeleteall = findViewById(R.id.btn_deleteall);
        btnsearch = findViewById(R.id.btn_search);

        dev = findViewById(R.id.dev);



        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dev = new Intent(Intent.ACTION_VIEW);
                dev.setData(Uri.parse("https://github.com/alazarzewdu"));
                startActivity(dev);

            }
        });


        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        bdisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),view.class);
                startActivity(i);

            }
        });

        bdeleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteall();
            }
        });


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),search.class);
                startActivity(i);
            }
        });






    }



    public void insert () {
        try {
            String id = eid.getText().toString();
            String fname = efname.getText().toString();
            String lname = elname.getText().toString();
            String age = eage.getText().toString();
            String department = edepartment.getText().toString();
            String section = esection.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);
            db.execSQL("Create Table If Not Exists student(id Text PRIMARY KEY, fname Text, lname Text, age Text, department Text, section Text)");

            String sql = "Insert Into student(id,fname,lname,age,department,section)values(?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,id);
            statement.bindString(2,fname);
            statement.bindString(3,lname);
            statement.bindString(4,age);
            statement.bindString(5,department);
            statement.bindString(6,section);
            statement.execute();
            Toast.makeText(this, "Record Added", Toast.LENGTH_SHORT).show();

            eid.setText("");
            efname.setText("");
            elname.setText("");
            eage.setText("");
            edepartment.setText("");
            esection.setText("");
            eid.requestFocus();


        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Record Failed", Toast.LENGTH_SHORT).show();


        }


    }





    public void deleteall () {
        try {


            String id = eid.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);

            String sql = "delete from student";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.execute();
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();

            eid.setText("");
            efname.setText("");
            elname.setText("");
            eage.setText("");
            edepartment.setText("");
            esection.setText("");
            eid.requestFocus();
            Intent i = new Intent(getApplicationContext(),Home.class);
            startActivity(i);


        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }
    }










    //edit



    //delete





}