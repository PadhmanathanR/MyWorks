package com.example.sys.myworks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelperClass extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="work_record.db";
    public static final String TABLE_NAME="my_table";
    public static final String DATE_A="date";
    public static final String TIME_A="time";
    public static final String WORK_A="message";

    public dbHelperClass(Context context)
    {
        super(context,DATABASE_NAME,null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (" + DATE_A + " text," + TIME_A + " text  , "+ WORK_A + " text , ID text primary key )") ;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+TABLE_NAME ;
        return db.rawQuery(query,null);
    }
    public boolean inset_data(String date,String time,String msg,String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DATE_A,date);
        contentValues.put(TIME_A,time);
        contentValues.put(WORK_A,msg);
        contentValues.put("ID",id);

        long res =db.insert(TABLE_NAME,null,contentValues);

        return res != -1;
    }
    public boolean delete_data(String id)
    {
      SQLiteDatabase db=this.getWritableDatabase();
      int ans=db.delete(TABLE_NAME,"ID =?",new String[]{id});

        return ans != -1;
    }
}
