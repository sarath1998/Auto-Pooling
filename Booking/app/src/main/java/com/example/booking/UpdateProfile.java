package com.example.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProfile extends AppCompatActivity {

    LoginPage loginPage;
    UserRegistartionHelper userRegistartionHelper;
    Button _btnupdatepassword;
    EditText _edittextpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);

        _edittextpassword = (EditText) findViewById(R.id.txtupdateassword);
        _btnupdatepassword = (Button) findViewById(R.id.btnupdatepassword);
        loginPage = new LoginPage();
        userRegistartionHelper = new UserRegistartionHelper(this);

        _btnupdatepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginPage.username;
                String newpassword = _edittextpassword.getText().toString();
                if(newpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    updatepassword(username,newpassword);
                }
            }

        });

    }

    public void updatepassword(String username,String newpass){

        boolean updateData = userRegistartionHelper.updateUser(username, newpass);
        if(updateData){
            Toast.makeText(getApplicationContext(), "Update Successfull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UpdateProfile.this,LoginPage.class);
            startActivity(intent);
        }
        else
            Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
    }
}
