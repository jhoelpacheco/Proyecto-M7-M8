package com.example.login.DB;

import static com.example.login.DB.HeroesContract.HeroesEntry.ID;
import static com.example.login.DB.HeroesContract.HeroesEntry.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.login.DB.HeroesContract.*;
import com.example.login.Model.Hero;

import java.util.ArrayList;


public class HeroesDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Overwatch.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + "(" + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    HeroesEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
                    HeroesEntry.COLUMN_ROL_TITLE + " TEXT NOT NULL, " +
                    HeroesEntry.COLUMN_SUB_ROL_TITLE + " TEXT NOT NULL); ";


    public HeroesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void delete() {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public void insertHero(SQLiteDatabase db, Hero h){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Insert the incidence getting all values
            values.put(HeroesEntry.COLUMN_NAME_TITLE, h.getNom());
            db.insert(TABLE_NAME, null, values);

            values.put(HeroesEntry.COLUMN_ROL_TITLE, h.getRol());
            db.insert(TABLE_NAME, null, values);

            values.put(HeroesEntry.COLUMN_SUB_ROL_TITLE, h.getSub_rol());
            db.insert(TABLE_NAME, null, values);
        }else{
            Log.i("sql","Database is closed");
        }
    }
    /*public void deleteHero(SQLiteDatabase db, Hero h){
        if (db.isOpen()){
            db.delete(ContactsEntry.TABLE_NAME, h.getNom() + "=" + h.getNom(), null);
        }
    }*/

    public ArrayList<Hero> getAllData(SQLiteDatabase db){
        ArrayList<Hero> arrayHeroes = new ArrayList<>();

        String GET_ALL_HEROES = "SELECT * FROM " + TABLE_NAME;

        db = getReadableDatabase();
        if(db!=null)
        {
            Cursor cursor = db.rawQuery(GET_ALL_HEROES, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                String nombre = cursor.getString(cursor.getColumnIndex(HeroesEntry.COLUMN_NAME_TITLE));
                String rol = cursor.getString(cursor.getColumnIndex(HeroesEntry.COLUMN_ROL_TITLE));
                String subrol = cursor.getString(cursor.getColumnIndex(HeroesEntry.COLUMN_SUB_ROL_TITLE));

                arrayHeroes.add(new Hero(nombre, rol, subrol));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return arrayHeroes;
    }
}
