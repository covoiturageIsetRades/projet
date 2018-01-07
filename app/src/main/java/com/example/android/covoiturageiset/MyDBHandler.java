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
    public static final String TABLE_ETUDIANTS = "etudiants";
    public static final String COL_NUM_ET = "NUM_ET";
    public static final String COL_NOM = "NOM_ET";
    public static final String COL_PRENOM = "PRENOM_ET";
    public static final String COL_CIN = "CIN_ET";
    public static final String COL_MAIL = "MAIL_ET";
    public static final String COL_PASSWORD = "PASSWORD_ET";
    public static final String COL_SOLDE = "SOLDE_ET";
    public static final String COL_NOTIFICATIONS = "NOTIFICATIONS_ET";


    public static final String TABLE_NOTIFICATIONS = "notifications";
    public static final String COL_NUM_NOTIFICATION = "NUM_NOTIFICATION";
    public static final String COL_NUM_SENDER = "NUM_SENDER";
    public static final String COL_NUM_RECEIVER = "NUM_RECEIVER";

    public static final String TABLE_PROPOSITIONS = "propositions";
    public static final String COL_NUM_PROPOSITION = "NUM_PROPOSITION";
    public static final String COL_DEPART = "DEPART";
    public static final String COL_DESTINATION = "DESTINATION";
    public static final String COL_DATE_DEPART = "DATE_DEPART";



    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_etudiants = "CREATE TABLE " + TABLE_ETUDIANTS + "(" +
                COL_NUM_ET  + " TEXT PRIMARY KEY , " +
                COL_NOM + " TEXT ," +
                COL_PRENOM + " TEXT ," +
                COL_CIN + " TEXT ," +
                COL_MAIL + " TEXT ,                                                                                                                                                                                                                                                                                                     " +
                COL_PASSWORD + " TEXT ," +
                COL_SOLDE + " TEXT ," +
                COL_NOTIFICATIONS + " TEXT " +
                ");";
        db.execSQL(table_etudiants);

        String table_notifications = "CREATE TABLE " + TABLE_NOTIFICATIONS + "(" +
                COL_NUM_NOTIFICATION  + " TEXT PRIMARY KEY , " +
                COL_NUM_SENDER + " TEXT ," +
                COL_NUM_RECEIVER + " TEXT " +
                ");";
        db.execSQL(table_notifications);

        String table_propositions = "CREATE TABLE " + TABLE_PROPOSITIONS + "(" +
                COL_NUM_PROPOSITION  + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                COL_NUM_ET + " TEXT ," +
                COL_DEPART + " TEXT ," +
                COL_DESTINATION + " TEXT ," +
                COL_DATE_DEPART + " TEXT " +
                ");";
        db.execSQL(table_propositions);

    }
    //Lesson 51
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_ETUDIANTS);
        onCreate(db);
    }

    public long addEtudiant(Etudiant etudiant){
        ContentValues values = new ContentValues();
        values.put(COL_NUM_ET, etudiant.getNum_et());
        values.put(COL_NOM, etudiant.getNom());
        values.put(COL_PRENOM, etudiant.getPrénom());
        values.put(COL_CIN, etudiant.getCIN());
        values.put(COL_MAIL, etudiant.getMail());
        values.put(COL_PASSWORD, etudiant.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        Long add=db.insert( TABLE_ETUDIANTS, null, values);
        db.close();
        Log.d("addProduct","add="+Long.toString(add));
        return add;
    }
    public long addProposition(Proposition proposition,String numero_etudiant){
        ContentValues values = new ContentValues();
        values.put(COL_NUM_ET,numero_etudiant);
        values.put(COL_DEPART, proposition.getDepart());
        values.put(COL_DESTINATION, proposition.getDestination());
        values.put(COL_DATE_DEPART, proposition.getDate_proposition());
        SQLiteDatabase db = getWritableDatabase();
        Long add=db.insert( TABLE_ETUDIANTS, null, values);
        db.close();
        Log.d("addProposition","add="+Long.toString(add));
        return add;
    }

    public void deleteEtudiant(String num_etudiant){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " +  TABLE_ETUDIANTS + " WHERE " + COL_NUM_ET + "=\"" + num_etudiant + "\";");
    }


    public Etudiant chercherdb(String num_etudiant){
            Etudiant et=null;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query( TABLE_ETUDIANTS, new String[] { COL_NUM_ET,
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
        String query = "SELECT * FROM " +  TABLE_ETUDIANTS + " WHERE 1";

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