package com.example.android.covoiturageiset;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Proposer extends AppCompatActivity {
    Spinner sp_villes1,sp_villes2;
    private TextView tv_date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    MyDBHandler db_prop;
    String date_prop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposer);
        sp_villes1=(Spinner)findViewById(R.id.spinner_villes1);
        sp_villes2=(Spinner)findViewById(R.id.spinner_villes2);
        tv_date=(TextView)findViewById(R.id.tv_date);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          sp_villes1.setAdapter(adapter);
          sp_villes2.setAdapter(adapter);

        db_prop=new MyDBHandler(this);


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet( DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                date_prop = day + "/" + month + "/" + year;
                tv_date.setText(date_prop);
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

    public void Ajouter_proposition(View view) {
        Proposition prop=new Proposition(sp_villes1.getSelectedItem().toString(),sp_villes1.getSelectedItem().toString(),date_prop);
        Long add=db_prop.addProposition(prop,"num_et");
        if (add==-1)
            Toast.makeText(this,"Proposition non enregistrée  erreur",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Proposition enregistrée avec succés, nous allons vous notifier si quelq'un demande votre covoiturage",Toast.LENGTH_LONG).show();

    }
}
