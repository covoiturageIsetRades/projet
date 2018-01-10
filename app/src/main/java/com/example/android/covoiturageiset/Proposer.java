package com.example.android.covoiturageiset;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Proposer extends AppCompatActivity {
    Spinner sp_villes1,sp_villes2;
    private TextView tv_date,tv_time;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    MyDBHandler db_prop;
    String date_prop,time_prop,num_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposer);
        sp_villes1=(Spinner)findViewById(R.id.spinner_villes1);
        sp_villes2=(Spinner)findViewById(R.id.spinner_villes2);
        tv_date=(TextView)findViewById(R.id.tv_date);
        tv_time=(TextView)findViewById(R.id.tv_time);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          sp_villes1.setAdapter(adapter);
          sp_villes2.setAdapter(adapter);

        db_prop=new MyDBHandler(this);

        Intent intent=getIntent();
        num_et=intent.getExtras().getString("num_et");

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet( DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                date_prop = day + "/" + month + "/" + year;
                tv_date.setText("Date de covoiturage : "+date_prop);
            }
        };
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time_prop = hourOfDay+":"+minute;
                tv_time.setText("Heure de covoiturage : "+time_prop);
            }
        };

    }
    public void showDatePickerDialog(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(Proposer.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void showTimePickerDialog(View v) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(Proposer.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mTimeSetListener, hour,minute,true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void Ajouter_proposition(View view) {
        if(date_prop==null)
            Toast.makeText(this,"Veuillez indiquer la date",Toast.LENGTH_LONG).show();
        else
            if(sp_villes1.getSelectedItem().toString().equals("-Sélectionner-"))
                Toast.makeText(this,"Veuillez indiquer le départ",Toast.LENGTH_LONG).show();
        else
            if(sp_villes2.getSelectedItem().toString().equals("-Sélectionner-"))
                Toast.makeText(this,"Veuillez indiquer la destination",Toast.LENGTH_LONG).show();
        else
        {
        Proposition prop=new Proposition(sp_villes1.getSelectedItem().toString(),sp_villes2.getSelectedItem().toString(),date_prop,time_prop);
            Log.d("hhhhhhhhhhhhhhhh",time_prop);

            Long add=db_prop.addProposition(prop,num_et);
        if (add==-1)
            Toast.makeText(this,"Proposition non enregistrée  erreur",Toast.LENGTH_LONG).show();
        else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Proposition enregistrée avec succés, nous allons vous notifier si quelq'un demande votre covoiturage");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();

            alert11.show();
        }
        Log.d("TABLE_PROPOSITIONS",db_prop.PropositionsToString());
        }
    }
}
