package com.example.bigbusiness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class sqldbhelper extends SQLiteOpenHelper {

    public static  final  String DB_NAME = "INVOICE.db";
    public static  final  String TABLE_NAME = "invoice";
    public static  final  String TABLE_ID = "ID";
    public static  final  String BIZ_NAME_COL = "biz_name";
    public static  final  String DATE_DAY_TIME = "DATE_DAY_TIME";
    public static  final  String PRODUCT_NAME = "PRODUCT_NAME";
    public static  final  String QUANTITY = "QUANTITY";
    public static  final  String AMOUNT = "AMOUNT";
    public static  final  String TOTAL = "TOTAL";
    public static  final  String PDF = "PDF";



    public sqldbhelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +" (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, biz_name TEXT , DATE_DAY_TIME TEXT , PRODUCT_NAME TEXT , QUANTITY INTEGER , AMOUNT TEXT , TOTAL TEXT , PDF TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }



    public boolean insertdata(String biz_name,String datedaytime,String product,Integer qty,String amt,String total,String pdf){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
                        contentValues.put(BIZ_NAME_COL,biz_name);
                        contentValues.put(DATE_DAY_TIME,datedaytime);
                        contentValues.put(PRODUCT_NAME,product);
                        contentValues.put(QUANTITY,qty);
                        contentValues.put(AMOUNT,amt);
                        contentValues.put(TOTAL,total);
                        contentValues.put(PDF,pdf);


        long r= db.insert(TABLE_NAME,null,contentValues);

       if (r == -1) {
           return false;
       }else{
           return true;
       }

    }

    public  Cursor  getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }
}
