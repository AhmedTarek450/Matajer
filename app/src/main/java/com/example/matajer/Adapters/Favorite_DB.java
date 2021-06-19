package com.example.matajer.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Favorite_DB extends SQLiteOpenHelper {

    static final String DB_NAME = "favoritedb";
    static final String DATABASE_NAME = "my_notes";
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final int DB_VERSION = 1;

    public Favorite_DB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String createtable ="create table" +DATABASE_NAME +"(" + KEY_ID+"INT PRIMRY KEY, "+KEY_TITLE + "TEXT, "+ KEY_DESCRIPTION+ "TEXT"+")";
    sqLiteDatabase.execSQL(createtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DeleteQuery ="DROP TABLE IF EXISTS "+ DATABASE_NAME;
        sqLiteDatabase.execSQL(DeleteQuery);
        onCreate(sqLiteDatabase);
    }

    public long addtofavorite(HomeModel homeModel){
    SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE,homeModel.name);
        contentValues.put(KEY_DESCRIPTION,homeModel.Image);
        long ID =sqLiteDatabase.insert(DATABASE_NAME,null,contentValues);
        return ID;
    };

    public boolean deleteitem(HomeModel homeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ DATABASE_NAME +" WHERE "+ KEY_ID +homeModel.id;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }
}
