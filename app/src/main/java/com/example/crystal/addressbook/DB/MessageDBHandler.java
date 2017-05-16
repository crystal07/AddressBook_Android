package com.example.crystal.addressbook.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by crystal on 2017. 5. 14..
 */

public class MessageDBHandler extends SQLiteOpenHelper {

    private static MessageDBHandler instance;
    private static SQLiteDatabase db;

    private MessageDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static void initialize(Context context) {
        if (instance==null) {
            instance = new MessageDBHandler(context, "MESSAGE.db", null, 1);
            db = instance.getWritableDatabase();
        }
    }

    public static final MessageDBHandler getInstance(Context context) {
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
        db.execSQL("CREATE TABLE MESSAGE (_id INTEGER PRIMARY KEY AUTOINCREMENT, caller TEXT, phone TEXT, message TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void INSERT(String caller, String phone, String message) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO MESSAGE VALUES (null, '" + caller + "', '" + phone + "', '" + message + "')");
        db.close();
    }

    public boolean checkExist(String phone) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM MESSAGE WHERE PHONE = '" + phone + "'", null);

        if (cursor.getCount()>0) return true;
        else return false;
    }

    public void DELETE(String phone) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM MESSAGE WHERE phone= '" + phone + "';");
        db.close();
    }

    public String getPhone() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM MESSAGE order by _id desc", null);

        while (cursor.moveToNext()) {
            result += cursor.getString(2) + ":" + cursor.getString(3) + ":";
        }

        db.close();
        return result;
    }

    public String getLog(String phone) {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM MESSAGE WHERE (PHONE = '" + phone + "' OR CALLER =  + " + "'" + phone + "')", null);

        while (cursor.moveToNext()) {
            result += cursor.getString(1) + ":" + cursor.getString(2) + ":" + cursor.getString(3) + ":";
        }

        db.close();
        return result;
    }
}
