package com.example.bigbusiness.Main.ui.Invoice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bigbusiness.Models.GenerateInvoiceItem;
import com.example.bigbusiness.Models.InvoiceItem;

import java.util.ArrayList;

public class GenerateInvoiceDBHelper extends SQLiteOpenHelper {
        public static final String DB_NAME = "BIGBUSINESSS";
    public static final String TABLE_NAME = "GENERATEINVOICE";

    public GenerateInvoiceDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +" (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, name TEXT, invoicename TEXT, buyername TEXT ,price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public GenerateInvoiceItem getItemFromCursor(Cursor cursor)
    {
        if(cursor == null)
        {
            return null;
        }
        int id = Integer.parseInt(cursor.getString(0));
        String name = cursor.getString(1);
        String invoicename = cursor.getString(2);
        String buyername = cursor.getString(3);
        String price = cursor.getString(5);

        GenerateInvoiceItem item = new GenerateInvoiceItem(id, name , invoicename , buyername, price );
        return item;
    }

    public ArrayList<GenerateInvoiceItem> getAllItems()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<GenerateInvoiceItem> allItems = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do{
                GenerateInvoiceItem item = getItemFromCursor(cursor);
                allItems.add(item);
            }while(cursor.moveToNext());
        }
        return allItems;
    }

    public GenerateInvoiceItem getItem(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("SELECT * FROM %s WHERE id=%d", TABLE_NAME,id);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst() && cursor.getCount() == 1)
        {
            GenerateInvoiceItem item = getItemFromCursor(cursor);
            return item;
        }
        return null;
    }

    public ContentValues getContentValues(GenerateInvoiceItem item)
    {
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        return values;
    }

    public boolean addItem(GenerateInvoiceItem item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = getContentValues(item);
        // Inserting Row
        if(db.insert(TABLE_NAME, null, values) == -1)
        {
            return false;
        }
        return true;
    }

    public boolean deleteItem(GenerateInvoice item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int success = db.delete(TABLE_NAME, "ID = ?", new String[] {String.valueOf(item.getId())});

        if(success > 0)
            return true;
        else
            return false;
    }

    public boolean updateItem(GenerateInvoiceItem item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = getContentValues(item);
        // updating row
        int success = db.update(TABLE_NAME, values,  "ID"+ " = ?",
                new String[] { String.valueOf(item.getId()) });
        if(success > 0)
            return true;
        else
            return false;
    }

}