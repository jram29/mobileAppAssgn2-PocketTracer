package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {

    TextView tv_total_price;
    ArrayList<String> item_Price, item_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        tv_total_price = findViewById(R.id.tv_price_number);

    }


    void storedTotalPrice(int sum) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(HomeScreen.this);
        Cursor cursor = dataBaseHelper.readTotalPrice();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Total price.", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()) {

                item_ID.add(cursor.getString(0));
                item_Price.add(cursor.getString(1));

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){

            case R.id.action_home:
                Intent intent = new Intent(this, HomeScreen.class);
                startActivity(intent);
                break;

            case R.id.action_property:
                Intent intent2 = new Intent(this, PropertyTab.class);
                startActivity(intent2);
                break;

            case R.id.action__settings:
                Intent intent3 = new Intent(this, AccountSettings.class);
                startActivity(intent3);
                break;

            case  R.id.action_logout:
                Intent intent4 = new Intent(this, MainActivity.class);
                startActivity(intent4);
                break;

            default:


        }
        return super.onOptionsItemSelected(item);
    }


    public void onCalendarClick (View view) {
        openMyCalendar();
    }

    public void openMyCalendar(){
        Intent intent = new Intent(this, CalendarMenu.class);
        startActivity(intent);
    }


    public void onPropertyDetailsTabClick (View view) {
        openMyPropertyDetailsTab();
    }

    public void openMyPropertyDetailsTab(){
        Intent intent = new Intent(this, PropertyDetailsTab.class);
        startActivity(intent);
    }

    public void onInventoryClick (View view) {
        openMyInventory();
    }

    public void openMyInventory(){
        Intent intent = new Intent(this, InventoryList.class);
        startActivity(intent);
    }
}