package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PropertyTab extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_tab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


    }


    public void onPropertyTabClick (View view) {
        openPropertyClickedTab();
        Toast.makeText(this, "Welcome to Property 1.", Toast.LENGTH_SHORT).show();
    }

    public void openPropertyClickedTab(){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

}