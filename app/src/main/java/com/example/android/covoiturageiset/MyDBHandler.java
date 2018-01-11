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

import java.util.LinkedList;
import java.util.List;

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
    public static final String COL_TIME_DEPART = "TIME_DEPART";


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
                COL_NUM_NOTIFICATION  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NUM_SENDER + " TEXT ," +
                COL_NUM_RECEIVER + " TEXT ," +
                COL_DEPART +" TEXT ,"+
                COL_DESTINATION +" TEXT ,"+
                COL_DATE_DEPART +" TEXT ,"+
                COL_TIME_DEPART +" TEXT  "+
                ");";
        db.execSQL(table_notifications);

        String table_propositions = "CREATE TABLE " + TABLE_PROPOSITIONS + "(" +
                COL_NUM_PROPOSITION+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_NUM_ET +" TEXT ,"+
                COL_DEPART +" TEXT ,"+
                COL_DESTINATION +" TEXT ,"+
                COL_DATE_DEPART +" TEXT ,"+
                COL_TIME_DEPART +" TEXT  "+
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
        values.put(COL_TIME_DEPART, proposition.getTime_proposition());
        SQLiteDatabase db = getWritableDatabase();
        Long add=db.insert( TABLE_PROPOSITIONS, null, values);
        db.close();
        Log.d("addProposition","add="+Long.toString(add));
        return add;
    }
    public long addNotification(Notification not,String dep,String dest,String date,String time){
        ContentValues values = new ContentValues();
        values.put(COL_NUM_SENDER, not.getNum_sender());
        values.put(COL_NUM_RECEIVER, not.getNum_receiver());
        values.put(COL_DEPART, dep);
        values.put(COL_DESTINATION, dest);
        values.put(COL_DATE_DEPART, date);
        values.put(COL_TIME_DEPART, time);
        SQLiteDatabase db = getWritableDatabase();
        Long add=db.insert( TABLE_NOTIFICATIONS, null, values);
        db.close();
        Log.d("addNotification","add="+Long.toString(add));
        return add;
    }

    public void deleteEtudiant(String num_etudiant){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " +  TABLE_ETUDIANTS + " WHERE " + COL_NUM_ET + "=\"" + num_etudiant + "\";");
    }
    public void deleteProposition(String num_proposition){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " +  TABLE_ETUDIANTS + " WHERE " + COL_NUM_PROPOSITION + "=\"" + num_proposition + "\";");
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
    public String EtudiantsToString(){
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
    public String PropositionsToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " +  TABLE_PROPOSITIONS + " WHERE 1";

        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();

        dbString="num_et       Proposition         depart         destination         date_depart       time_depart\n";
        while (!recordSet.isAfterLast()) {
            String ch_num_prop=recordSet.getString(recordSet.getColumnIndex(COL_NUM_PROPOSITION));
            String ch_num_et=recordSet.getString(recordSet.getColumnIndex(COL_NUM_ET));
            String ch_depart=recordSet.getString(recordSet.getColumnIndex(COL_DEPART));
            String ch_destination=recordSet.getString(recordSet.getColumnIndex(COL_DESTINATION));
            String ch_date_depart=recordSet.getString(recordSet.getColumnIndex(COL_DATE_DEPART));
            String ch_time_depart=recordSet.getString(recordSet.getColumnIndex(COL_TIME_DEPART));

            if (recordSet.getString(recordSet.getColumnIndex(COL_NUM_PROPOSITION)) != null) {
                dbString +=ch_num_et+"          "+ch_num_prop+"          "+ch_depart+"          "+ch_destination+"           "+ch_date_depart+"            "+ch_time_depart;
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }
    public String NotificationsToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " +  TABLE_NOTIFICATIONS + " WHERE 1";

        Cursor recordSet = db.rawQuery(query, null);
        recordSet.moveToFirst();

        dbString="num_not       num_sender         num_receiver  \n";
        while (!recordSet.isAfterLast()) {
            String ch_num_not=recordSet.getString(recordSet.getColumnIndex(COL_NUM_NOTIFICATION));
            String ch_num_sender=recordSet.getString(recordSet.getColumnIndex(COL_NUM_SENDER));
            String ch_num_receiver=recordSet.getString(recordSet.getColumnIndex(COL_NUM_RECEIVER));
            String ch_depart=recordSet.getString(recordSet.getColumnIndex(COL_DEPART));
            String ch_destination=recordSet.getString(recordSet.getColumnIndex(COL_DESTINATION));
            String ch_date_depart=recordSet.getString(recordSet.getColumnIndex(COL_DATE_DEPART));
            String ch_time_depart=recordSet.getString(recordSet.getColumnIndex(COL_TIME_DEPART));

            if (recordSet.getString(recordSet.getColumnIndex(COL_NUM_NOTIFICATION)) != null) {
                dbString +=ch_num_not+"          "+ch_num_sender+"          "+ch_num_receiver+"          "+ch_depart+"          "+ch_destination+"           "+ch_date_depart+"            "+ch_time_depart;
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }
    public LinkedList<LinkedList> chercher_proposition(String destination,String date_dep){
        String ch_prop;
        String requete;
        SQLiteDatabase db=getWritableDatabase();
        final LinkedList<String> List_num_et = new LinkedList<>();
        final LinkedList<String> List_depart = new LinkedList<>();
        final LinkedList<String> List_destination = new LinkedList<>();
        final LinkedList<String> List_date = new LinkedList<>();
        final LinkedList<String> List_time = new LinkedList<>();
        final LinkedList<LinkedList> List = new LinkedList<>();



        if(destination.equals("-Sélectionner-") && date_dep==null)
            requete="SELECT * FROM " +  TABLE_PROPOSITIONS + " WHERE 1";
        else
            if(!destination.equals("-Sélectionner-") && date_dep==null)
                requete="SELECT * FROM " +  TABLE_PROPOSITIONS + " WHERE "+COL_DESTINATION+"=\""+destination+"\"";
            else
                if(date_dep!=null && destination.equals("-Sélectionner-"))
                    requete="SELECT * FROM " +  TABLE_PROPOSITIONS + " WHERE "+COL_DATE_DEPART+"=\""+date_dep+"\"";
            else
               requete="SELECT * FROM " +  TABLE_PROPOSITIONS + " WHERE "+COL_DESTINATION+"=\""+destination+"\" AND "+
                                          COL_DATE_DEPART+"=\""+date_dep+"\"";


        Cursor recordSet = db.rawQuery(requete, null);
        recordSet.moveToFirst();
        ch_prop="num_et       Proposition         depart         destination         date_depart\n";
        while (!recordSet.isAfterLast()) {
            String ch_num_prop=recordSet.getString(recordSet.getColumnIndex(COL_NUM_PROPOSITION));
            String ch_num_et=recordSet.getString(recordSet.getColumnIndex(COL_NUM_ET));
            String ch_depart=recordSet.getString(recordSet.getColumnIndex(COL_DEPART));
            String ch_destination=recordSet.getString(recordSet.getColumnIndex(COL_DESTINATION));
            String ch_date_depart=recordSet.getString(recordSet.getColumnIndex(COL_DATE_DEPART));;
            String ch_time_depart=recordSet.getString(recordSet.getColumnIndex(COL_TIME_DEPART));;

            if (recordSet.getString(recordSet.getColumnIndex(COL_NUM_PROPOSITION)) != null) {
                ch_prop +=ch_num_et+"          "+ch_num_prop+"            "+ch_depart+"            "+ch_destination+"            "+ch_date_depart+"             "+ch_time_depart;
                ch_prop += "\n";
            }
            List_num_et.add(ch_num_et);
            List_depart.add(ch_depart);
            List_destination.add(ch_destination);
            List_date.add(ch_date_depart);
            List_time.add(ch_time_depart);

            recordSet.moveToNext();
        }
        List.add(List_num_et);
        List.add(List_depart);
        List.add(List_destination);
        List.add(List_date);
        List.add(List_time);
        db.close();
        return List;

    }

    public LinkedList<LinkedList> chercher_notification(String num_receiver){
        String requete;
        SQLiteDatabase db=getWritableDatabase();
        final LinkedList<String> List_num_sender = new LinkedList<>();
        final LinkedList<String> List_depart = new LinkedList<>();
        final LinkedList<String> List_destination = new LinkedList<>();
        final LinkedList<String> List_date = new LinkedList<>();
        final LinkedList<String> List_time = new LinkedList<>();
        final LinkedList<LinkedList> List = new LinkedList<>();




            requete="SELECT * FROM " +  TABLE_NOTIFICATIONS + " WHERE "+COL_NUM_RECEIVER+"=\""+num_receiver+"\"";



        Cursor recordSet = db.rawQuery(requete, null);
        recordSet.moveToFirst();
        String ch_prop="num_et       Proposition         depart         destination         date_depart\n";

        while (!recordSet.isAfterLast()) {
            String ch_num_sender=recordSet.getString(recordSet.getColumnIndex(COL_NUM_SENDER));
            String ch_depart=recordSet.getString(recordSet.getColumnIndex(COL_DEPART));
            String ch_destination=recordSet.getString(recordSet.getColumnIndex(COL_DESTINATION));
            String ch_date_depart=recordSet.getString(recordSet.getColumnIndex(COL_DATE_DEPART));;
            String ch_time_depart=recordSet.getString(recordSet.getColumnIndex(COL_TIME_DEPART));;


            if (recordSet.getString(recordSet.getColumnIndex(COL_NUM_NOTIFICATION)) != null) {
                ch_prop +=ch_num_sender+"          "+ch_depart+"            "+ch_destination+"            "+ch_date_depart+"             "+ch_time_depart;
                ch_prop += "\n";
            }
            List_num_sender.add(ch_num_sender);
            List_depart.add(ch_depart);
            List_destination.add(ch_destination);
            List_date.add(ch_date_depart);
            List_time.add(ch_time_depart);
            recordSet.moveToNext();

        }
        Log.d("NOTIFICATIONS_RESULTATS",ch_prop);

        List.add(List_num_sender);
        List.add(List_depart);
        List.add(List_destination);
        List.add(List_date);
        List.add(List_time);
        db.close();
        return List;

    }





}