package com.example.crystal.addressbook.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by crystal on 2017. 5. 14..
 */

public class AddressDBHandler extends SQLiteOpenHelper {

    private static AddressDBHandler instance;
    private static SQLiteDatabase db;

    private AddressDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static void initialize(Context context) {
        if (instance==null) {
            instance = new AddressDBHandler(context, "ADDRESSBOOK.db", null, 1);
            db = instance.getWritableDatabase();
        }
    }

    public static final AddressDBHandler getInstance(Context context) {
        initialize(context);
        return instance;
    }

    public void close() {
        if (instance != null) {
            db.close();
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ADDRESSBOOK (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT, organization TEXT, email TEXT, memo TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void INSERT(String name, String phone, String organization, String email, String memo) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO ADDRESSBOOK VALUES (null, '" + name + "', '" + phone + "', '" + organization + "', '" + email + "', '" + memo + "');");
        db.close();
    }

    public void DELETE(String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM ADDRESSBOOK WHERE name= '" + name + "';");
        db.close();
    }

    public String getName() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * from ADDRESSBOOK ORDER BY NAME ASC", null);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            result+=cursor.getString(1)+":";
        }

        db.close();
        return result;
    }

    public String getInfo(String name) {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM ADDRESSBOOK WHERE NAME = '" + name + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) result += cursor.getString(0) + ":" + cursor.getString(1) + ":" + cursor.getString(2) + ":" + cursor.getString(3) + ":" + cursor.getString(4) + ":" + cursor.getString(5);
        return result;
    }
/*
    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM ADDRESSBOOK", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3) + "\n";
        }

        db.close();
        return result;
    }*/
}
