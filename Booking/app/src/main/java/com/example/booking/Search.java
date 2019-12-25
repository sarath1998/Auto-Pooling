package com.example.booking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Search extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DatabaseHelper databaseHelper;
    Button _btnsearch,_btnpost;
    EditText _edittextsrc, _edittextdest, _edittextdate ;
    int day, month, year;
    int dayFinal, monthFinal, yearFinal;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        openHelper = new DatabaseHelper(this);
        databaseHelper = new DatabaseHelper(this);
        _edittextsrc = (EditText) findViewById(R.id.edittextsrc);
        _edittextdest = (EditText) findViewById(R.id.edittextdest);
        _edittextdate = (EditText) findViewById(R.id.edittextdate);
        _btnsearch = (Button) findViewById(R.id.btnsearch);
        _btnpost = (Button)findViewById(R.id.btnpost);

        _edittextdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Search.this, Search.this, year,month,day);
                datePickerDialog.show();

            }
        });

        _btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                db = openHelper.getWritableDatabase();
                String edittextsrc = _edittextsrc.getText().toString();
                String edittextdest = _edittextdest.getText().toString();
                String edittextdate = _edittextdate.getText().toString();
                if(edittextsrc.isEmpty() || edittextdest.isEmpty() || edittextdate.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Field/s is empty", Toast.LENGTH_LONG).show();
                }
                else if(edittextsrc.equalsIgnoreCase(edittextdest)){
                    Toast.makeText(getApplicationContext(), "Source and Destination are same", Toast.LENGTH_LONG).show();
                }
                else {
                    searchdata(edittextsrc,edittextdest,edittextsrc);
                }
            }
        });

        _btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void displayPassengers(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(content);
        builder.show();
    }

    public void searchdata(String edittextsrc, String edittextdest, String edittextdate) {
        Toast.makeText(getApplicationContext(), "Inside searchdata()", Toast.LENGTH_LONG).show();
        Cursor results = databaseHelper.allData(edittextsrc, edittextdest, edittextdate);
        if (results.getCount() == 0){
//            Toast.makeText(getApplicationContext(), "No Passengers from this source", Toast.LENGTH_LONG).show();
            displayPassengers("Error","No Passengers to display");
        }
        else
        {
            StringBuffer buffer = new StringBuffer();
            while(results.moveToNext()) {
                buffer.append(System.getProperty("line.separator"));//new line
                buffer.append("Name :" +results.getString(1));
                buffer.append(System.getProperty("line.separator")); //new line
                buffer.append("Time :" +results.getString(4));
                buffer.append(System.getProperty("line.separator"));//new line
                buffer.append("Date :" +results.getString(5));
                buffer.append(System.getProperty("line.separator"));//new line
                buffer.append("Mobile : "+results.getString(6));
                buffer.append(System.getProperty("line.separator"));//new line
                buffer.append("---------");
            }
            displayPassengers("Passengers List",buffer.toString());
            }
        }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month+1;
        dayFinal = dayOfMonth;

        _edittextdate.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
    }
}



