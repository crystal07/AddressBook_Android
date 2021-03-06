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
        db.execSQL("INSERT INTO ADDRESSBOOK VALUES (null, 'me', '00000000000', 'HYU', 'ssj977@naver.com', 'null');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void INSERT(String name, String phone, String organization, String email, String memo) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO ADDRESSBOOK VALUES (null, '" + name + "', '" + phone + "', '" + organization + "', '" + email + "', '" + memo + "');");
        Log.e("ADDRESSBDB", "INSERT: ");
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

        db.close();
        return result;
    }

    public String findName(String phone) {
        SQLiteDatabase db = getReadableDatabase();
        String name = "";

        Cursor cursor = db.rawQuery("SELECT * FROM ADDRESSBOOK WHERE PHONE = '" + phone + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount()>0) name = cursor.getString(1);
        else name = null;

        db.close();
        return name;
    }

    public String findPhone(String name) {
        SQLiteDatabase db = getReadableDatabase();
        String phone = "";

        Cursor cursor = db.rawQuery("SELECT * FROM ADDRESSBOOK WHERE NAME = '" + name + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount()>0) phone = cursor.getString(2);
        else phone = null;

        db.close();
        Log.e("MessageDB", "findPhone: "+phone);
        return phone;
    }

    public boolean checkExist(String name) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM ADDRESSBOOK WHERE NAME = '" + name + "'", null);

        boolean check;

        if (cursor.getCount()>0) check = true;
        else check = false;
        db.close();

        return check;
    }

    public int getDataSize () {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id FROM ADDRESSBOOK;", null);

        int size = cursor.getCount();
        db.close();
        return size;
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
