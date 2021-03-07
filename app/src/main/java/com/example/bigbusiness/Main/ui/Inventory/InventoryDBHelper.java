package com.example.bigbusiness.Main.ui.Inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.bigbusiness.Models.InventoryItem;

import java.util.ArrayList;

public class InventoryDBHelper extends SQLiteOpenHelper {
    public static  final  String DB_NAME = "BIGBUSINESSSS";
    public static  final  String TABLE_NAME = "INVENTORY";

    public InventoryDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE "+ TABLE_NAME +" (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,invoiceId TEXT , name TEXT , quantity INTEGER , price INTEGER , units TEXT,image BLOB)");
       db.execSQL("CREATE TABLE "+ "INVOICE" +" (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, name TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
       onCreate(db);
    }

    public InventoryItem getItemFromCursor(Cursor cursor)
    {
        if(cursor == null)
        {
            return null;
        }
        int id = Integer.parseInt(cursor.getString(0));
        String invoiceId = cursor.getString(1);
        String name = cursor.getString(2);
        int quantity = Integer.parseInt(cursor.getString(3));
        int price = Integer.parseInt(cursor.getString(4));
        String units = cursor.getString(5);
        byte[] image = cursor.getBlob(6);
        InventoryItem item = new InventoryItem(id, name, quantity, units, price, invoiceId , image);
        return item;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public ArrayList<InventoryItem> getAllItems()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<InventoryItem> allItems = new ArrayList<>();
        CursorWindow cw = new CursorWindow("test", 5000);
        AbstractWindowedCursor ac = (AbstractWindowedCursor) cursor;
        ac.setWindow(cw);
        try {
            ac.moveToNext();
            fail("Exception is expected when row exceeds CursorWindow size");
        } catch (SQLiteBlobTooBigException expected) {
        }

        if(cursor.moveToFirst())
        {
            do{
                InventoryItem item = getItemFromCursor(cursor);
                allItems.add(item);
            }while(cursor.moveToNext());
        }
        return allItems;
    }
    private void fail(String s) {
        System.out.println(s);
    }

    public InventoryItem getItem(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("SELECT * FROM %s WHERE id=%d", TABLE_NAME,id);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst() && cursor.getCount() == 1)
        {
            InventoryItem item = getItemFromCursor(cursor);
            return item;
        }
        return null;
    }

    public ContentValues getContentValues(InventoryItem item)
    {
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("quantity", item.getQuantity());
        values.put("price", item.getPrice());
        values.put("units", item.getUnit());
        values.put("invoiceId", item.getInvoiceId());
        values.put("image",item.getImage());
        return values;
    }

    public boolean addItem(InventoryItem item)
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

    public boolean deleteItem(InventoryItem item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int success = db.delete(TABLE_NAME, "ID = ?", new String[] {String.valueOf(item.getItemID())});

        if(success > 0)
            return true;
        else
            return false;
    }

    public boolean updateItem(InventoryItem item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = getContentValues(item);
        // updating row
        int success = db.update(TABLE_NAME, values,  "ID"+ " = ?",
                new String[] { String.valueOf(item.getItemID()) });
        if(success > 0)
            return true;
        else
            return false;
    }

    public void increaseQuantity(InventoryItem item)
    {
        item.setQuantity(item.getQuantity()+1);
        updateItem(item);
    }

    public void decreaseQuantity(InventoryItem item)
    {
        item.setQuantity(item.getQuantity()-1);
        updateItem(item);
    }
}
