package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.util.Date;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String ITEM_INVENTORY_TABLE = "ITEM_INVENTORY_TABLE";
    private static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    private static final String COLUMN_ITEM_CATEGORY = "ITEM_CATEGORY";
    private static final String COLUMN_ITEM_BRAND = "ITEM_BRAND";
    private static final String COLUMN_ITEM_PRICE = "ITEM_PRICE";
    private static final String COLUMN_ITEM_PURCHASE_DATE = "ITEM_PURCHASE_DATE";
    private static final String COLUMN_ITEM_DATE_WARRANTY = "ITEM_DATE_WARRANTY";
    private static final String COLUMN_ID = "ID";


    DataBaseHelper(@Nullable Context context) {
        super(context, "inventoryItem.db", null, 1);
        this.context = context;
    }

    //this is called the first time a database is assessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {

    String createTableStatement = "CREATE TABLE " + ITEM_INVENTORY_TABLE +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_ITEM_NAME + " TEXT, " +
            COLUMN_ITEM_CATEGORY + " TEXT, " +
            COLUMN_ITEM_BRAND + " TEXT, " +
            COLUMN_ITEM_PRICE + " DOUBLE, " +
            COLUMN_ITEM_PURCHASE_DATE + " INT, " +
            COLUMN_ITEM_DATE_WARRANTY + " INT)";

    db.execSQL(createTableStatement);

    }

    //this is called if the database version number changes. It prevents previous user apps from breaking when you change the design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ITEM_INVENTORY_TABLE);
        onCreate(sqLiteDatabase);

    }


    public boolean addItem(ItemDetails itemDetails, long dateTimeMillis, long dateTimeMillis2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_ITEM_NAME, itemDetails.getName());
        cv.put(COLUMN_ITEM_CATEGORY, itemDetails.getCategory());
        cv.put(COLUMN_ITEM_BRAND, itemDetails.getBrand());
        cv.put(COLUMN_ITEM_PRICE, itemDetails.getPrice());
        cv.put(COLUMN_ITEM_PURCHASE_DATE, itemDetails.getPurchasedDate());
        cv.put(COLUMN_ITEM_DATE_WARRANTY, itemDetails.getWarrantyDuration());


        long insert = db.insert(ITEM_INVENTORY_TABLE, null, cv);

        if(insert == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(context, "Item was added to Inventory.", Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    void deleteOne(String row_ID){
        SQLiteDatabase db = getWritableDatabase();
        long result = db.delete(ITEM_INVENTORY_TABLE, "ID=?", new String[]{row_ID});

        if(result == -1){
            Toast.makeText(context, "Failed to delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully deleted.", Toast.LENGTH_SHORT).show();
        }


    }

    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + ITEM_INVENTORY_TABLE);
    }


    Cursor readTotalPrice(){
        String queryString = "SELECT * FROM " + COLUMN_ITEM_PRICE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
        }

        return cursor;
    }

    Cursor readAllData(){
        String queryString = "SELECT * FROM " + ITEM_INVENTORY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(queryString, null);
        }

        return cursor;

    }

    void UpdateItem(String row_ID, ItemDetails itemDetails, long dateTimeMillis, long dateTimeMillis2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_ITEM_NAME, itemDetails.getName());
        cv.put(COLUMN_ITEM_CATEGORY, itemDetails.getCategory());
        cv.put(COLUMN_ITEM_BRAND, itemDetails.getBrand());
        cv.put(COLUMN_ITEM_PRICE, itemDetails.getPrice());
        cv.put(COLUMN_ITEM_PURCHASE_DATE, itemDetails.getPurchasedDate());
        cv.put(COLUMN_ITEM_DATE_WARRANTY, itemDetails.getWarrantyDuration());

        long update = db.update(ITEM_INVENTORY_TABLE, cv, "ID=?", new String[]{row_ID});

        if(update == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(context, "Item details was updated.", Toast.LENGTH_SHORT).show();

        }

    }
}
