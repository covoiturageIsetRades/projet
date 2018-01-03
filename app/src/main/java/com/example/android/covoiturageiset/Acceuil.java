package com.example.android.covoiturageiset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Acceuil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
    }

    public void creer_compte(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void authentification(View view) {
        Intent i=new Intent(this,Authentification.class);
        startActivity(i);
    }
}
