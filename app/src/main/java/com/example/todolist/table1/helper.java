package com.example.todolist.table1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class helper extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String DATABASE_NAME="todolist.db";
    public helper(Context context)
    {
        super(context,DATABASE_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       String TABLE_QUERY="CREATE TABLE "+contractclass.ListEntry.TABLE_NAME+"("+contractclass.ListEntry.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+contractclass.ListEntry.COLUMN_TASK+" TEXT NOT NULL,"+contractclass.ListEntry.COLUMN_TIME+" TEXT NOT NULL);";
   sqLiteDatabase.execSQL(TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
