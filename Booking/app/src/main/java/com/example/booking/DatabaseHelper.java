package com.example.booking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME="Bookings";
    public static final String COL_1="ID";
    public static final String COL_2="Name";
    public static final String COL_3="Source";
    public static final String COL_4="Destination";
    public static final String COL_5="Time";
    public static final String COL_6="Date";
    public static final String COL_7="Phone";

    public DatabaseHelper(Context context)

    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+
                " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT ,"+ COL_3+" TEXT ,"+COL_4+" TEXT ,"+COL_5+" TEXT ,"+COL_6+" TEXT,"+COL_7+" TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }
                            //name,source,destination,time,date,mobile
    public boolean addData(String item1, String item2, String item3, String item4, String item5, String item6){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, item1);
            contentValues.put(COL_3, item2);
            contentValues.put(COL_4, item3);
            contentValues.put(COL_5, item4);
            contentValues.put(COL_6, item5);
            contentValues.put(COL_7, item6);


        Log.d("Database Journey", "Posting details");

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        return true;
    }

    public Cursor allData(String edittextsrc, String edittextdest, String edittextdate){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Bookings where lower(Source) = '"+(edittextsrc.trim()).toLowerCase()+"' AND lower(Destination) = '"+(edittextdest.trim()).toLowerCase()+"' " , null );
            return  cursor;
    }
}
