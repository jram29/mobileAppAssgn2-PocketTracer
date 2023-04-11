package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class UpdateItemDetails extends AppCompatActivity {

    EditText et_Name2, et_Category2, et_Brand2, et_Price2, et_PurchasedDate2, et_WarrantyDuration2;
    Button btnUpdate, btnDelete, btnPurchasedDate2, btnWarrantyDurationDate2;

    private DatePickerDialog.OnDateSetListener btnPurchasedDateSetListener2;
    private DatePickerDialog.OnDateSetListener btnWarrantyDurationDateSetListener2;

    String id, name, category, brand, price, purchasedDate, warrantyDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_details);


        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);


        et_Name2 = findViewById(R.id.et_Name2);
        et_Category2 = findViewById(R.id.et_Category2);
        et_Brand2 = findViewById(R.id.et_Brand2);
        et_Price2 = findViewById(R.id.et_Price2);
        et_PurchasedDate2 = findViewById(R.id.et_PurchasedDate2);
        et_WarrantyDuration2 = findViewById(R.id.et_WarrantyDuration2);

        btnPurchasedDate2 = findViewById(R.id.btnPurchasedDate2);
        btnWarrantyDurationDate2 = findViewById(R.id.btnWarrantyDurationDate2);


        btnPurchasedDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(UpdateItemDetails.this,
                        android.R.style.Theme_Light_WallpaperSettings, btnPurchasedDateSetListener2, year, month, day);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnPurchasedDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                et_PurchasedDate2.setText(date);

            }
        };


        btnWarrantyDurationDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(UpdateItemDetails.this,
                        android.R.style.Theme_Light_WallpaperSettings, btnWarrantyDurationDateSetListener2, year, month, day);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        btnWarrantyDurationDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                et_WarrantyDuration2.setText(date);

            }
        };





        //Initiate get and set data
        getAndSetIntentData();

        ab.setTitle(name);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                ItemDetails itemDetails = new ItemDetails
                        (-1, et_Name2.getText().toString(), et_Category2.getText().toString(),
                                et_Brand2.getText().toString(), Double.valueOf(et_Price2.getText().toString()),
                                et_PurchasedDate2.getText().toString(),
                                et_WarrantyDuration2.getText().toString());

                DataBaseHelper myDB = new DataBaseHelper(UpdateItemDetails.this);
                try {
                    myDB.UpdateItem(id, itemDetails, getTimeMillis(et_PurchasedDate2.getText().toString()),
                            getTimeMillis(et_WarrantyDuration2.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(UpdateItemDetails.this, "Please choose a date.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();

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

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("category") && getIntent().hasExtra("price")

            &&
               getIntent().hasExtra("brand") && getIntent().hasExtra("purchasedDate") &&
                getIntent().hasExtra("warrantyDuration") ){


            //getting data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            category = getIntent().getStringExtra("category");
            price = getIntent().getStringExtra("price");
            brand = getIntent().getStringExtra("brand");
            purchasedDate = getIntent().getStringExtra("purchasedDate");
            warrantyDuration = getIntent().getStringExtra("warrantyDuration");

            //setting data from intent
            et_Name2.setText(name);
            et_Category2.setText(category);
            et_Price2.setText(price);
            et_Brand2.setText(brand);
            et_PurchasedDate2.setText(purchasedDate);
            et_WarrantyDuration2.setText(warrantyDuration);


        }
        else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBaseHelper myDB = new DataBaseHelper(UpdateItemDetails.this);
                myDB.deleteOne(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

}
