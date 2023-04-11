package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class InventoryList extends AppCompatActivity {

    RecyclerView viewInventoryList;
    FloatingActionButton addItemButton;
    ImageView iv_storage;
    TextView tv_noItem;

    DataBaseHelper dataBaseHelper;
    CustomAdapter customAdapter;
   // List<ItemDetails> returnList = new ArrayList<>();
    ArrayList<String> item_ID, item_Name, item_Category, item_Price

    , item_Brand, item_PurchasedDate,
                      item_WarrantyDuration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        viewInventoryList = findViewById(R.id.viewInventoryList);
        iv_storage = findViewById(R.id.iv_storage);
        tv_noItem = findViewById(R.id.tv_noItem);
        addItemButton = findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InventoryList.this, AddItemActivity.class);
                startActivity(intent);

            }
        });



        dataBaseHelper = new DataBaseHelper(InventoryList.this);
        item_ID = new ArrayList<>();
        item_Name = new ArrayList<>();
        item_Category = new ArrayList<>();
        item_Price = new ArrayList<>();
        item_Brand = new ArrayList<>();
        item_PurchasedDate = new ArrayList<>();
        item_WarrantyDuration = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(InventoryList.this, this, item_ID, item_Name,
                item_Category, item_Price,
         item_Brand, item_PurchasedDate, item_WarrantyDuration );
        viewInventoryList.setAdapter(customAdapter);
        viewInventoryList.setLayoutManager(new LinearLayoutManager(InventoryList.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            recreate();
        }
    }



    void storeDataInArrays(){
        Cursor cursor = dataBaseHelper.readAllData();

        if(cursor.getCount() == 0){
           // Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            iv_storage.setVisibility(View.VISIBLE);
            tv_noItem.setVisibility(View.VISIBLE);
        }
        else{
            while (cursor.moveToNext()){

                item_ID.add(cursor.getString(0));
                item_Name.add(cursor.getString(1));
                item_Category.add(cursor.getString(2));
                item_Price.add(cursor.getString(4));
                item_Brand.add(cursor.getString(3));
                item_PurchasedDate.add(cursor.getString(5));
                item_WarrantyDuration.add(cursor.getString(6));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.deleteAll){

            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure want to delete all items?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataBaseHelper myDB = new DataBaseHelper(InventoryList.this);
                myDB.deleteAll();
                Toast.makeText(InventoryList.this, "All items has been deleted.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InventoryList.this, InventoryList.class);
                startActivity(intent);
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