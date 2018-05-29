package com.example.dhiraj.ad.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "easyad.db";
    private static final int database_version = 1;

    private static final String table_name = "ads";
    private static final String column_1 = "phoneNumber";
    private static final String column_2 = "adTitle";
    private static final String column_3 = "adDescription";

    public DatabaseHelper(Context context) {
        super(context, database_name, null, database_version );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+table_name+" (phoneNumber text primary key,adTitle text,adDescription varchar(250))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+table_name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String phoneNumber,String adTitle,String adDescription){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_1,phoneNumber);
        contentValues.put(column_2,adTitle);
        contentValues.put(column_3,adDescription);

        long result = sqLiteDatabase.insert(table_name,null,contentValues);

        if(result==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllAds()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+table_name,null);
        return res;
    }
}
