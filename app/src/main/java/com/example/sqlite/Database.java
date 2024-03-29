package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private SQLiteDatabase db;

    public Database(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        db = DB;
        DB.execSQL("create Table Userdetails(StudentID TEXT primary key, Fullname TEXT, Address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String StudentID, String Fullname, String Address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StudentID", StudentID);
        contentValues.put("Fullname", Fullname);
        contentValues.put("Address", Address);
        long result=DB.insert("Userdetails", null, contentValues);
        return result != -1;
    }


    public Boolean updateuserdata(String name, String contact, String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from Userdetails where StudentID = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }}


    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            return result != -1;
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        try (Cursor cursor = DB.rawQuery("Select * from Userdetails", null)) {
            return cursor;
        }
    }

}

