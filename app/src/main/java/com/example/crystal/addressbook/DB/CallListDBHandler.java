package com.example.crystal.addressbook.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by crystal on 2017. 5. 14..
 */

public class CallListDBHandler extends SQLiteOpenHelper{

    public CallListDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CALLLIST (_id INTEGER PRIMARY KEY AUTOINCREMENT, RECEIVE INTEGER, PHONE TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void INSERT(int receive, String phone) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO CALLLIST VALUES (null, " + receive + ", " + phone + ");");
        db.close();
    }

    public void DELETE(String phone) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM CALLLIST WHERE phone= " + phone + ";");
        db.close();
    }

    public String getResult() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM CALLLIST", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(1) + " : " + cursor.getString(2) + ";";
        }
        return result;
    }
}
