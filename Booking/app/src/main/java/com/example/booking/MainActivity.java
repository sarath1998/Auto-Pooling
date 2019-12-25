package com.example.booking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    DatabaseHelper databaseHelper;
    Button _btnsubmit,_btngotosearchpassengers;
    EditText _txtname, _txtsource, _txtdestination, _txtmobile, _txttime, _txtslot;
    int day, month, year, hour, minute, AM_PM;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _txtname = (EditText) findViewById(R.id.txtname);
        _txtsource = (EditText) findViewById(R.id.txtsource);
        _txtdestination = (EditText) findViewById(R.id.txtdestination);
        _txtmobile = (EditText) findViewById(R.id.txtmobile);
        _txttime = (EditText) findViewById(R.id.txttime);
        _txtslot = (EditText) findViewById(R.id.txtslot);
        _btnsubmit = (Button) findViewById(R.id.btnsubmit);
        _btngotosearchpassengers = (Button)findViewById(R.id.btngotosearch);
        databaseHelper = new DatabaseHelper(this);

        _btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = _txtname.getText().toString();
                String source = _txtsource.getText().toString();
                String destination = _txtdestination.getText().toString();
                String mobile = _txtmobile.getText().toString();
                String date = _txttime.getText().toString();
                String time = _txtslot.getText().toString();
                if(name.isEmpty() || source.isEmpty() || destination.isEmpty() || mobile.isEmpty() || time.isEmpty() || date.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fields are missing", Toast.LENGTH_SHORT).show();
                }
                else if(source.equalsIgnoreCase(destination)){
                    Toast.makeText(getApplicationContext(), "Same src and destintion", Toast.LENGTH_SHORT).show();
                }
                    else {
                    addData(name, source, destination, time, date, mobile);
                }
            }
        });

        _btngotosearchpassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });

        _txttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, MainActivity.this, year,month,day);
                datePickerDialog.show();
            }
        });

    }

   public void addData(String name, String source, String destination, String time, String date, String mobile){
        boolean inserData = databaseHelper.addData(name,source,destination,time,date,mobile);
        if(inserData)
        {
            Toast.makeText(getApplicationContext(), " Details Successfully Posted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Afterlogin.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Failed posting", Toast.LENGTH_SHORT).show();
        }
   }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month+1;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, MainActivity.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String AM_PM ="AM";
        hourFinal = hourOfDay;
        minuteFinal = minute;
        if(hourFinal > 12) {
            hourFinal -= 12;
            AM_PM = "PM";
        }
        _txttime.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
        _txtslot.setText(String.format("%02d:%02d %s", hourFinal, minuteFinal,AM_PM));
//        Toast.makeText(getApplicationContext(), "Time Choosen : "+hourFinal+" "+minuteFinal, Toast.LENGTH_SHORT).show();


    }
}


