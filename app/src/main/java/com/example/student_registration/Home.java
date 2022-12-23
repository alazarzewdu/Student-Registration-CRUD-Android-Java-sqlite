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
                
            }
        });



    }




    //edit



    //delete





}