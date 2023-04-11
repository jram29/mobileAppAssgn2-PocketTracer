package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PropertyDetailsTab extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                Toast.makeText(this, "Saved changes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnCancel:
                Toast.makeText(this, "No changes has been made", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        Button btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        Property firstProperty = new Property("13 Jackson St Petone", "2",
                "2000", "1500", "5", "3",
                "Admin Property");


        EditText txtEditAddress = findViewById(R.id.txtEditAddress);
        txtEditAddress.setText(firstProperty.getAddress());

        EditText txtEditGarage = findViewById(R.id.txtEditGarage);
        txtEditGarage.setText(firstProperty.getGarage());

        EditText txtEditBackYardSize = findViewById(R.id.txtEditB_YardSize);
        txtEditBackYardSize.setText(firstProperty.getBackYardSize());

        EditText txtEditFrontYardSize = findViewById(R.id.txtEditF_YardSize);
        txtEditFrontYardSize.setText(firstProperty.getFrontYardSize());

        EditText txtEditBedroom = findViewById(R.id.txtEditBedroom);
        txtEditBedroom.setText(firstProperty.getBedroom());

        EditText txtEditBathroom = findViewById(R.id.txtEditBathroom);
        txtEditBathroom.setText(firstProperty.getBathroom());

        EditText txtEditPropertyName = findViewById(R.id.txtEditPropertyName);
        txtEditPropertyName.setText(firstProperty.getPropertyName());


    }

}