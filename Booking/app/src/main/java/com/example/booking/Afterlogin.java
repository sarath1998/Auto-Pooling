package com.example.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Afterlogin extends AppCompatActivity {

    ImageButton _btnpostjourneys,_btnchangepass, _btnsearchpassengers, _btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        _btnpostjourneys = (ImageButton)findViewById(R.id.btnpostjourneys);
        _btnchangepass = (ImageButton)findViewById(R.id.btnchangepass);
        _btnsearchpassengers = (ImageButton)findViewById(R.id.btnsearchpassengers);
        _btnlogout = (ImageButton)findViewById(R.id.btnlogout);

        _btnpostjourneys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Afterlogin.this, MainActivity.class );
                startActivity(intent);
            }
        });

        _btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Afterlogin.this, UpdateProfile.class );
                startActivity(intent);
            }
        });

        _btnsearchpassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Afterlogin.this, Search.class );
                startActivity(intent);
            }
        });

        _btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Afterlogin.this, LoginPage.class );
                startActivity(intent);
                finish();
            }
        });

    }



}
