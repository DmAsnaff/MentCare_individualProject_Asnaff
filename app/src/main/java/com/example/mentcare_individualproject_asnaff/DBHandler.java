package com.example.mentcare_individualproject_asnaff;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import kotlin.text.UStringsKt;

public class DBHandler extends SQLiteOpenHelper {
    public Context context;

    public static final String databaseName = "mentcare.db";

//    DBHandler(@Nullable Context context) {
//        super(context, "mentcare.db", null, 1);
//    }
public static final int DATABASE_VERSION = 2;

    public DBHandler(@Nullable Context context) {
        super(context, "mentcare.db", null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(name TEXT,email TEXT primary key, password TEXT)");

        MyDatabase.execSQL("create Table user_mood (id INTEGER PRIMARY KEY AUTOINCREMENT, user_email TEXT, mood TEXT, date_selected DATE)");
    }


//    @Override
//    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
//        MyDatabase.execSQL("drop Table if exists users");
//
//        MyDatabase.execSQL("drop Table if exists user_mood");
//
//    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("DROP TABLE IF EXISTS users");
        MyDatabase.execSQL("DROP TABLE IF EXISTS user_mood");
//        onCreate(MyDatabase);
    }

    public Boolean insertData(String name,String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean insertMood(String userEmail, String mood, String dateSelected) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_email", userEmail);
        contentValues.put("mood", mood);
        contentValues.put("date_selected", dateSelected);

        try {
            long result = MyDatabase.insert("user_mood", null, contentValues);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }



    public String getUserNameByEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select name from users where email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            cursor.close();
            return name;
        } else {
            cursor.close();
            return "User";
        }
    }

    public boolean updateUser(String newName, String newEmail, String oldEmail) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", newName);
        contentValues.put("email", newEmail); // Update email

        int result = MyDatabase.update("users", contentValues, "email = ?", new String[]{oldEmail});
        return result > 0;
    }


    public User getUserByEmail(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            cursor.close();
            return new User(name, email);
        } else {
            cursor.close();
            return null;
        }
    }

}