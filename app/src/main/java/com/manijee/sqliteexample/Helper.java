package com.manijee.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    public static final String tableName="EmployeeRecord";
    public static final String name="emp_name";
    public static final String contact="emp_contact";

    SQLiteDatabase database;
    public Helper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tableName+"("+name+" text,"+contact+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("drop table "+tableName);
      onCreate(db);
    }
    public String onSave(String username,String usercontact){
        try {
            database = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(name, username);
            cv.put(contact, usercontact);
            long result = database.insert(tableName, null, cv);
            if (result > 0) {
                return "Data saved successfully";
            } else {
                return "Error to save data";
            }
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public Cursor fetchAllData() {
        Cursor cursor=null;
        try{
         database=getReadableDatabase();
         String[] columns=new String[]{name,contact};
         cursor=database.query(tableName,columns,null,null,null,null,null);
        }catch (Exception ex){
            Log.i("sqlite_error",ex.getMessage());
        }
        return cursor;
    }

    public Cursor searchData(String param) {
        Cursor cursor=null;
        try{
            database=getReadableDatabase();
            String[] columns=new String[]{name,contact};
            cursor=database.query(tableName,columns,contact+"=?",new String[]{param},null,null,null);
        }catch (Exception ex){
            Log.i("sqlite_error",ex.getMessage());
        }
        return cursor;
    }

    public String deleleteData(String userNumber) {
        try{
          database=getWritableDatabase();
         long result=database.delete(tableName,contact+"=?",new String[]{userNumber});
         if (result>0){
             return "data deleted";
         }else{
             return "Failed to delete item,please try later";
         }
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    public String onUpdate(String username, String usercontact) {
        try {
            database = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(name, username);
            cv.put(contact, usercontact);
            long result = database.update(tableName, cv,contact+"=?",new String[]{usercontact});
            if (result > 0) {
                return "Data updated successfully";
            } else {
                return "Error to update data";
            }
        }catch (Exception ex){
            return ex.getMessage();
        }
    }
}
