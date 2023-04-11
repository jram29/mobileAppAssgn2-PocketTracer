package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onLoginBtnClick (View view) {

        EditText txtUsername = findViewById(R.id.txtUsername);
        txtUsername.getText().toString();

        EditText txtPassword = findViewById(R.id.txtPassword);
        txtPassword.getText().toString();

        if(txtUsername.getText().toString().equals("admin") &&
                txtPassword.getText().toString().equals("pass123")) {
            openHomeScreen();
        }
    }

    public void openHomeScreen(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}



