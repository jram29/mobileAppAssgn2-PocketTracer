package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class AddItemActivity extends AppCompatActivity {

    EditText et_Name, et_Category, et_Brand, et_Price, et_PurchasedDate, et_WarrantyDuration;
    Button btnAdd, btnPurchasedDate, btnWarrantyDurationDate;

    private DatePickerDialog.OnDateSetListener btnPurchasedDateSetListener;
    private DatePickerDialog.OnDateSetListener btnWarrantyDurationDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        et_Name = findViewById(R.id.et_Name);
        et_Category = findViewById(R.id.et_Category);
        et_Brand = findViewById(R.id.et_Brand);
        et_Price = findViewById(R.id.et_Price);
        et_PurchasedDate = findViewById(R.id.et_PurchasedDate);
        et_WarrantyDuration = findViewById(R.id.et_WarrantyDuration);

        btnPurchasedDate = findViewById(R.id.btnPurchasedDate);
        btnWarrantyDurationDate = findViewById(R.id.btnWarrantyDurationDate);


        btnPurchasedDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddItemActivity.this,
                        android.R.style.Theme_Light_WallpaperSettings, btnPurchasedDateSetListener, year, month, day);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnPurchasedDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                et_PurchasedDate.setText(date);

            }
        };


        btnWarrantyDurationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddItemActivity.this,
                        android.R.style.Theme_Light_WallpaperSettings, btnWarrantyDurationDateSetListener, year, month, day);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnWarrantyDurationDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                et_WarrantyDuration.setText(date);

            }
        };




        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {


                ItemDetails itemDetails = new ItemDetails
                        (-1, et_Name.getText().toString(), et_Category.getText().toString(),
                                et_Brand.getText().toString(), Double.valueOf(et_Price.getText().toString()),
                                et_PurchasedDate.getText().toString(),
                                et_WarrantyDuration.getText().toString());

                DataBaseHelper myDB = new DataBaseHelper(AddItemActivity.this);
                try {
                    myDB.addItem(itemDetails, getTimeMillis(et_PurchasedDate.getText().toString()),
                            getTimeMillis(et_WarrantyDuration.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static long getTimeMillis(String dateString) throws ParseException{
        String myDate = dateString;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = sdf.parse(myDate);
        long millis = date.getTime();

        return millis;
    }

 }
