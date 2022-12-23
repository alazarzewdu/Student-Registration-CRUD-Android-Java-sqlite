package com.example.student_registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Display extends AppCompatActivity {

    ListView list_view;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);

        list_view = findViewById(R.id.list_view);
        final Cursor c = db.rawQuery("select * from student", null);
        int id = c.getColumnIndex("id");
        int fname = c.getColumnIndex("fname");
        int lname = c.getColumnIndex("lname");
        int age = c.getColumnIndex("age");
        int department = c.getColumnIndex("department");
        int section = c.getColumnIndex("section");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        list_view.setAdapter(arrayAdapter);


        final ArrayList<student> stud = new ArrayList<student>();


        if(c.moveToFirst()) {
            do {
                student stu = new student();
                stu.id=c.getString(id);
                stu.fname=c.getString(fname);
                stu.lname=c.getString(lname);
                stu.age=c.getString(age);
                stu.department=c.getString(department);
                stu.section=c.getString(section);

                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(fname) + " \t " + c.getString(lname) + " \t " + c.getInt(age) + " \t " + c.getString(department) + " \t " + c.getString(section));


            }while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            list_view.invalidateViews();
        }

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();

                student stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(), Home.class);

                i.putExtra("id",stu.id);
                i.putExtra("fname",stu.fname);
                i.putExtra("lname",stu.lname);
                i.putExtra("age",stu.age);
                i.putExtra("department",stu.department);
                i.putExtra("section",stu.section);
                startActivity(i);



            }
        });






    }
}