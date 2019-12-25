package com.example.booking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    UserRegistartionHelper userRegistartionHelper;
    Button _btnregister, _btnlogin;
    EditText _txtregusername, _txtregpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRegistartionHelper = new UserRegistartionHelper(this);
        _txtregusername = (EditText) findViewById(R.id.txtregusername);
        _txtregpassword = (EditText) findViewById(R.id.txtregpassword);
        _btnregister = (Button) findViewById(R.id.btnregister);
        _btnlogin = (Button) findViewById(R.id.btnlogin);


        _btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _txtregusername.getText().toString();
                String password = _txtregpassword.getText().toString();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fields are missing", Toast.LENGTH_SHORT).show();
                }
                else if(username.equals("Sarath") || password.equals("Sarath123")) {
                    Toast.makeText(getApplicationContext(), "Already User exists, Choose some other", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean state = searchforexistingusers(username, password); //Checking if user already registred
                    if (state) {
                        addUser(username, password);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Already User exists, Choose some other", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LoginPage.class);
                startActivity(intent);
            }
        });

    }

    public void addUser(String name, String password){
        boolean inserData = userRegistartionHelper.insertUser(name,password);
        if(inserData)
        {
            Toast.makeText(getApplicationContext(), " Succesfully Registered", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Register.this, LoginPage.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Failed Registration", Toast.LENGTH_SHORT).show();

        }
    }

    public boolean searchforexistingusers(String u_name, String pass) {
//        Toast.makeText(getApplicationContext(), "Inside searchforexistingusers()", Toast.LENGTH_SHORT).show();
        Cursor results = userRegistartionHelper.getAllUsers(u_name,pass);
        if (results.getCount() > 0){
            return false; //User already registred
        }
        else
        {
            return true; //New User registration
        }
    }
}
