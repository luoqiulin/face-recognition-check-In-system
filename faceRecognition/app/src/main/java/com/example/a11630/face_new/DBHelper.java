package com.example.a11630.face_new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DBHelper(Context context) {
        super(context, "logindb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String str="create table login_table(username text primary key ,password text,phone text)";
        sqLiteDatabase.execSQL(str);


    }

    public void insertLogin(String username,String password,String phone){
        db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        cv.put("phone",phone);
        db.insert("login_table",null,cv);
    }
    public Cursor selectLogin(){
        db=getReadableDatabase();
        Cursor cursor=db.query("login_table",null,null,
                null,null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
