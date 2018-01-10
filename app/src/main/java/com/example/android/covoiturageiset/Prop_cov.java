package com.example.android.covoiturageiset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.LinkedList;

public class Prop_cov extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private  LinkedList<LinkedList> mWordList = new LinkedList<>();

    MyDBHandler db_chercher;
    String ville,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop_cov);

        db_chercher=new MyDBHandler(this);



        Intent i=getIntent();
        ville=i.getExtras().getString("spville");
        date=i.getExtras().getString("date");

        mWordList=db_chercher.chercher_proposition(ville,date);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
