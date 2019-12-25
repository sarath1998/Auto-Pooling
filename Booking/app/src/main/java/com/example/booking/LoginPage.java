package com.example.booking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    UserRegistartionHelper userRegistartionHelper;
    Button _btnlogin,_btnregister;
    EditText _txtusername, _txtpassword;
    String username ="", password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        _txtusername = (EditText) findViewById(R.id.txtusername);
        _txtpassword = (EditText) findViewById(R.id.txtpassword);
        _btnlogin = (Button) findViewById(R.id.btnlogin);
        _btnregister = (Button)findViewById(R.id.btnregister);
        userRegistartionHelper = new UserRegistartionHelper(this);

        _btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, Register.class);
                startActivity(intent);
            }
        });

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 username = _txtusername.getText().toString();
                 password = _txtpassword.getText().toString();

                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fields are missing", Toast.LENGTH_SHORT).show();
                }

                else{
                    searchforusers(username, password);
                }
            }
        });
    }

    public void searchforusers(String u_name, String pass) {
//        Toast.makeText(getApplicationContext(), "Inside searchforuser()", Toast.LENGTH_SHORT).show();
        Cursor results = userRegistartionHelper.getAllUsers(u_name,pass);
        if (results.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(LoginPage.this, Afterlogin.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Welcome User", Toast.LENGTH_SHORT).show();
        }
        }



    }


