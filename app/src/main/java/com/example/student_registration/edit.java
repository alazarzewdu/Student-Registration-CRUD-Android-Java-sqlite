package com.example.student_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends AppCompatActivity {

    EditText eid,efname,elname,eage,edepartment,esection;
    Button badd,bdisplay,bedit,bdelete,bmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        eid = findViewById(R.id.st_ide);
        efname = findViewById(R.id.st_fnamee);
        elname = findViewById(R.id.st_lnamee);
        eage = findViewById(R.id.st_agee);
        edepartment = findViewById(R.id.st_departmente);
        esection = findViewById(R.id.st_sectione);


        bedit = findViewById(R.id.btn_edit);
        bdelete = findViewById(R.id.btn_delete);
       // bmain = findViewById(R.id.btn_main);


        Intent it = getIntent();

        String t1 = it.getStringExtra("id").toString();
        String t2 = it.getStringExtra("fname").toString();
        String t3 = it.getStringExtra("lname").toString();
        String t4 = it.getStringExtra("age").toString();
        String t5 = it.getStringExtra("department").toString();
        String t6 = it.getStringExtra("section").toString();


        eid.setText(t1);
        efname.setText(t2);
        elname.setText(t3);
        eage.setText(t4);
        edepartment.setText(t5);
        esection.setText(t6);


//        bmain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),Home.class);
//                startActivity(i);
//            }
//        });


        bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }
        });


        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });



    }


    public void edit () {
        try {

            String fname = efname.getText().toString();
            String lname = elname.getText().toString();
            String age = eage.getText().toString();
            String department = edepartment.getText().toString();
            String section = esection.getText().toString();
            String id = eid.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);

            String sql = "update student set fname =?, lname=?,age=?,department=?,section=? where id =?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,fname);
            statement.bindString(2,lname);
            statement.bindString(3,age);
            statement.bindString(4,department);
            statement.bindString(5,section);
            statement.bindString(6,id);
            statement.execute();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }


    public void delete () {
        try {


            String id = eid.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);

            String sql = "delete from student where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,id);
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
}