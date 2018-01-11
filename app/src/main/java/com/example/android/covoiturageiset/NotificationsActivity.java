package com.example.android.covoiturageiset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedList;

public class NotificationsActivity extends AppCompatActivity {
    String num_et;
    private RecyclerView mRecyclerView;
    private WordListAdapter2 mAdapter;
    private  LinkedList<LinkedList> List = new LinkedList<>();

    MyDBHandler db_chercher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        db_chercher=new MyDBHandler(this);
        Intent i=getIntent();
        num_et=i.getStringExtra("num_et");
        List=db_chercher.chercher_notification(num_et);

        if(List.size()==0)
        {
            Toast.makeText(this,"Aucune notification trouvée",Toast.LENGTH_LONG).show();
        }
        else
        {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        mAdapter = new WordListAdapter2(this, List);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));}
    }
}
