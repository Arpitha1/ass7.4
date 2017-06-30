package com.example.abhi.sqlitedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.abhi.sqlitedemo.vo.NameVO;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper implements CRUDOperation{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "demoDb";

    // Table name
    private static final String TABLE_NAME = "names";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST_NAME = "firstName";
    private static final String KEY_LAST_NAME = "lastName";

    /**
     * @param context
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    /**
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }


    @Override
    public void addNameVO(NameVO name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, name.getFirstName()); // First Name
        values.put(KEY_LAST_NAME, name.getLastName()); // Last Name
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        // Closing database connection
        db.close();
    }


    @Override
    public NameVO getNameVO(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_FIRST_NAME, KEY_LAST_NAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        NameVO nameVO = new NameVO(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return nameVO
        return nameVO;
    }


    @Override
    public List<NameVO> getAllNameVO() {
        List<NameVO> nameVoList = new ArrayList<NameVO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NameVO nameVO = new NameVO();
                nameVO.setId(Integer.parseInt(cursor.getString(0)));
                nameVO.setFirstName(cursor.getString(1));
                nameVO.setLastName(cursor.getString(2));
                // Adding NameVO to list
                nameVoList.add(nameVO);
            } while (cursor.moveToNext());
        }
        // return NameVO list
        return nameVoList;
    }


    @Override
    public int getNameVOCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }


    @Override
    public int updateNameVO(NameVO name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, name.getFirstName());
        values.put(KEY_LAST_NAME, name.getLastName());
        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(name.getId()) });
    }


    @Override
    public void deleteNameVO(NameVO name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(name.getId()) });
        db.close();
    }

    @Override
    public void deleteAllNames(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
