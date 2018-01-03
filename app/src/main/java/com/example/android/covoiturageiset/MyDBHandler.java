package com.example.android.covoiturageiset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
// This class handles all the database activities
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "etudiantDB.db";
    public static final String TABLE_PRODUCTS = "etudiants";
    public static final String COL_NUM_ET = "NUM_ET";
    public static final String COL_NOM = "NOM_ET";
    public static final String COL_PRENOM = "PRENOM_ET";
    public static final String COL_CIN = "CIN_ET";
    public static final String COL_MAIL = "MAIL_ET";
    public static final String COL_PASSWORD = "PASSWORD_ET";

    public MyDBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
//opkkk
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COL_NUM_ET  + " TEXT PRIMARY KEY , " +
                COL_NOM + " TEXT ," +
                COL_PRENOM + " TEXT ," +
                COL_CIN + " TEXT ," +
                COL_MAIL + " TEXT ,                                                                                                                                                                                                                                                                                                     " +
                COL_PASSWORD + " TEXT " +
                ");";
        db.execSQL(query);

    }
    //Lesson 51
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public long addEtudiant(Etudiant etudiant){
        ContentValues values = new ContentValues();
        values.put(COL_NUM_ET, etudiant.getNum_et());
        values.put(COL_NOM, etudiant.getNom());
        values.put(COL_PRENOM, etudiant.getPrénom());
        values.put(COL_CIN, etudiant.getCIN());
        values.put(COL_MAIL, etudiant.getMail());
        values.put(COL_PASSWORD, etudiant.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        Long add=db.insert(TABLE_PRODUCTS, null, values);
        db.close();
        Log.d("addProduct","add="+Long.toString(add));
        return add;
    }

    public void deleteEtudiant(String num_etudiant){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COL_NUM_ET + "=\"" + num_etudiant + "\";");
    }


    public Etudiant chercherdb(String num_etudiant){
            Etudiant et=null;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_PRODUCTS, new String[] { COL_NUM_ET,
                            COL_NOM, COL_PRENOM ,COL_CIN, COL_MAIL, COL_PASSWORD}, COL_NUM_ET + "=?", new String[] { num_etudiant }, null, null, null,null);


        if(cursor.getCount()!=0)
        {
            if (cursor!=null)
            {
                cursor.moveToFirst();

                et=new Etudiant(cursor.getString(0),cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));}
        }


        return  et;



    }
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();

        dbString="num       nom         prenom         CIN         Email           password\n";
        while (!recordSet.isAfterLast()) {
            String ch_num=recordSet.getString(recordSet.getColumnIndex(COL_NUM_ET));
            String ch_nom=recordSet.getString(recordSet.getColumnIndex(COL_NOM));
            String ch_prenom=recordSet.getString(recordSet.getColumnIndex(COL_PRENOM));
            String ch_cin=recordSet.getString(recordSet.getColumnIndex(COL_CIN));
            String ch_mail=recordSet.getString(recordSet.getColumnIndex(COL_MAIL));
            String ch_password=recordSet.getString(recordSet.getColumnIndex(COL_PASSWORD));

            if (recordSet.getString(recordSet.getColumnIndex(COL_NUM_ET)) != null) {
                dbString +=ch_num+"          "+ch_nom+"        "+ch_prenom+"        "+ch_cin+"        "+ch_mail+"        "+ch_password ;
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }



}