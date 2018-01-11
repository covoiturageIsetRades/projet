package com.example.android.covoiturageiset;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.LinkedList;

public class Chercher extends AppCompatActivity {
    Spinner sp_villes3;
    private TextView tv_date;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    static MyDBHandler db_chercher;
   static String num_et;
    String date_cherher;


    private RecyclerView mRecyclerView;
    WordListAdapter mAdapter;
    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher);

        sp_villes3=(Spinner)findViewById(R.id.spinner_villes3);
        tv_date=(TextView)findViewById(R.id.tv_date);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_villes3.setAdapter(adapter);
        db_chercher=new MyDBHandler(this);

        Intent intent=getIntent();
        num_et=intent.getExtras().getString("num_et");

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                date_cherher = day + "/" + month + "/" + year;
                tv_date.setText("Date de covoiturage : "+date_cherher);
            }
        };



    }

    public void showDatePickerDialog(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(Chercher.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    public void Chercher_proposition(View view) {

        Intent i=new Intent(Chercher.this,Prop_cov.class);
        i.putExtra("spville",sp_villes3.getSelectedItem().toString());
        i.putExtra("date",date_cherher);

        startActivity(i);


    }
}
