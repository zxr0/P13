package sg.edu.rp.c346.id20000892.p13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "schedule.db";
    private static final int DATABASE_VERSION = 2;
    private static final String table_sch = "schedule";
    private static final String id = "id";
    private static final String name = "name";
    private static final String time = "time";
    private static final String date = "date";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + table_sch + "("
                + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + name + " TEXT,"
                + time + " TEXT,"
                + date + " TEXT )";

        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        //dummy
            ContentValues values = new ContentValues();
            values.put(name, "test");
            values.put(time, "10:00:00");
            values.put(date, "1/1/2000");
            db.insert(table_sch, null, values);
        Log.i("info", "dummy records inserted");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("ALTER TABLE " + table_sch + " ADD COLUMN  module_name TEXT ");
        //onCreate(db);
    }

    public void insertSch(String name1, String time1, String date1){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name, name1);
        values.put(time, time1);
        values.put(date, date1);
        db.insert(table_sch, null, values);
        db.close();
    }

    public ArrayList<sch> getSch() {
        ArrayList<sch> schs = new ArrayList<sch>();

        String selectQuery = "SELECT " + id + "," + name + "," + time + "," +
                 date + " FROM " + table_sch;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String namee = cursor.getString(1);
                String timee = cursor.getString(2);
                String datee = cursor.getString(3);
                sch sch1 = new sch(id, namee, timee, datee);
                schs.add(sch1);
            } while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return schs;
    }

    public int updateSch(sch data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name, data.getName());
        values.put(time, data.getTime());
        values.put(date, data.getDate());
        String condition = id + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(table_sch, values, condition, args);
        db.close();
        return result;
    }

    public int del(int id1){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = id + "= ?";
        String[] args = {String.valueOf(id1)};
        int result = db.delete(table_sch, condition, args);
        db.close();
        return result;
    }

    public void delall(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+table_sch);
        db.close();
    }
}
