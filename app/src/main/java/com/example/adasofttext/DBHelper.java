package com.example.adasofttext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "persondb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "person";
    private static final String COL_IDNUMBER = "idnumber";
    private static final String COL_NAME = "name";
    private static final String COL_SURNAME = "surname";
    private static final String COL_AGE = "age";
    private static final String TAG = "check";


    // creating a constructor for our database handler.
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_IDNUMBER + " VARCHAR(14) PRIMARY KEY , "
                + COL_NAME + " VARCHAR(50),"
                + COL_SURNAME + " VARCHAR(50),"
                + COL_AGE + " INTEGER(3))";
        db.execSQL(query);
    }

    public void addNewCourse(String txtIDNumber, String txtName, String txtSurname, int txtAge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_IDNUMBER, txtIDNumber);
        values.put(COL_NAME, txtName);
        values.put(COL_SURNAME, txtSurname);
        values.put(COL_AGE, txtAge);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the courses.
    public ArrayList<PersonModel> readPerson() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorPerson = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<PersonModel> personModelArrayList = new ArrayList<>();


        if (cursorPerson.moveToFirst()) {
            do {
                personModelArrayList.add(new PersonModel(cursorPerson.getString(0),
                        cursorPerson.getString(1),
                        cursorPerson.getString(2),
                        cursorPerson.getString(3)));
                Log.v(TAG,"Read1");
                Log.v(TAG, personModelArrayList.toString());
            } while (cursorPerson.moveToNext());
        }
        cursorPerson.close();
        return personModelArrayList;
    }

    public void updatePerson(String txtIDNumber, String txtName, String txtSurname, int txtAge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_IDNUMBER, txtIDNumber);
        values.put(COL_NAME, txtName);
        values.put(COL_SURNAME, txtSurname);
        values.put(COL_AGE, txtAge);

        db.update(TABLE_NAME, values, "idnumber = ?", new String[] {txtIDNumber});
        db.close();
    }

    public void deletePerson(String txtIDNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "idnumber = ?", new String[] {txtIDNumber});
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<PersonModel> searchPerson(String idn) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorPerson = db.query(TABLE_NAME ,null,COL_IDNUMBER+" LIKE ?", new String[] {"%"+idn+"%"},null,null,null,null);

        ArrayList<PersonModel> personModelArrayList = new ArrayList<>();


        if (cursorPerson.moveToFirst()) {
            do {
                personModelArrayList.add(new PersonModel(cursorPerson.getString(0),
                        cursorPerson.getString(1),
                        cursorPerson.getString(2),
                        cursorPerson.getString(3)));
                Log.v(TAG,"Read1");
                Log.v(TAG, personModelArrayList.toString());
            } while (cursorPerson.moveToNext());
        }
        cursorPerson.close();
        return personModelArrayList;
    }
}


