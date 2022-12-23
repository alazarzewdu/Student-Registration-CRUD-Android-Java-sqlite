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

public class view extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("unity", Context.MODE_PRIVATE, null);

        lst1 = findViewById(R.id.lst1);
        final Cursor c = db.rawQuery("select * from student", null);
        int id = c.getColumnIndex("id");
        int fname = c.getColumnIndex("fname");
        int lname = c.getColumnIndex("lname");
        int age = c.getColumnIndex("age");
        int department = c.getColumnIndex("department");
        int section = c.getColumnIndex("section");
        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<student> stud = new ArrayList<student>();

        if(c.moveToFirst()) {
            do{
                student stu = new student();
                stu.id = c.getString(id);
                stu.fname = c.getString(fname);
                stu.lname = c.getString(lname);
                stu.age = c.getString(age);
                stu.department = c.getString(department);
                stu.section = c.getString(section);

                stud.add(stu);


                titles.add(c.getString(id) + " \t " + c.getString(fname) + " \t " + c.getString(lname) + " \t " + c.getString(age) + " \t " + c.getString(department) + " \t " + c.getString(section));



            }while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String aa = titles.get(i).toString();

                student stu = stud.get(i);
                Intent it = new Intent(getApplicationContext(),edit.class);

                it.putExtra("id",stu.id);
                it.putExtra("fname",stu.fname);
                it.putExtra("lname",stu.lname);
                it.putExtra("age",stu.age);
                it.putExtra("department",stu.department);
                it.putExtra("section",stu.section);
                startActivity(it);


            }
        });









    }
}