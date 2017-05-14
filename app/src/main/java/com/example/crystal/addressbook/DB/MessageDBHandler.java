package com.example.crystal.addressbook.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL("CREATE TABLE MESSAGE (caller TEXT, phone TEXT, message TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void INSERT(String caller, String phone, String message) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO MESSAGE VALUES (" + caller + ", " + phone + ", " + message + ")");
        db.close();
    }

    public void DELETE(String phone) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM MESSAGE WHERE phone= " + phone + ";");
        db.close();
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
