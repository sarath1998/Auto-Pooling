package com.example.booking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserRegistartionHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="Users";
    public static final String COL_1="ID";
    public static final String COL_2="Name";
    public static final String COL_3="Password";


    public UserRegistartionHelper(Context context)
    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+
                " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT ,"+ COL_3+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }

    public boolean insertUser(String item1, String item2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, item1);
        contentValues.put(COL_3, item2);

        Log.d("Database Journey", "Posting User Registration details");
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        return true;
    }

    //For Login Validation
    public Cursor getAllUsers(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Users where Name = '"+username.trim()+"' AND Password = '"+password.trim()+"'", null );
        return  cursor;
    }

    public boolean updateUser(String username,String newpass){
        String where = "Name=?";
        String[] whereArgs = new String[] {username};
        SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_3, newpass);
        db.update(TABLE_NAME, contentValues, where, whereArgs);
        return true;
    }



}
