package com.example.android.covoiturageiset;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by khalil on 10/01/2018.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>{
    private LayoutInflater mInflater;
    private final LinkedList<LinkedList> List;
     LinkedList<String> List_num_et = new LinkedList<>();
     LinkedList<String> List_depart = new LinkedList<>();
     LinkedList<String> List_destination = new LinkedList<>();
     LinkedList<String> List_date = new LinkedList<>();
     LinkedList<String> List_time = new LinkedList<>();



    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView tv_et,tv_depart,tv_destination,tv_date,tv_time;
        final WordListAdapter mAdapter;
        Button choisir;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);


            tv_et = (TextView) itemView.findViewById(R.id.tv_et);
            tv_depart = (TextView) itemView.findViewById(R.id.tvdep);
            tv_destination = (TextView) itemView.findViewById(R.id.tvdest);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date_cov);
            tv_time = (TextView) itemView.findViewById(R.id.tv_heure);
            choisir=(Button)itemView.findViewById(R.id.button2);
            this.mAdapter = adapter;
            choisir.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            Notification not=new Notification(Chercher.num_et,List_num_et.get(getAdapterPosition()));
            Chercher.db_chercher.addNotification(not,tv_depart.getText().toString(),tv_destination.getText().toString(),
                    tv_date.getText().toString(),tv_time.getText().toString());
            Log.d("TABLE_NOTIFICATIONS",Chercher.db_chercher.NotificationsToString());
            AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
            builder1.setMessage("Vous avez choisi cette covoiturage , soyez à l'heure s'il vous plaît !!");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();

            alert11.show();        }
    }
/****************************************************************************************************/
    public WordListAdapter(Context context, LinkedList<LinkedList> mWordList) {
        mInflater = LayoutInflater.from(context);
        this.List = mWordList;
        List_num_et = List.get(0);
        List_depart = List.get(1);
        List_destination = List.get(2);
        List_date = List.get(3);
        List_time=List.get(4);

    }

    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);

    }

    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder, int position) {
        Log.d("List_date",List_date.toString());
        String num_et = List_num_et.get(position);
        String depart = List_depart.get(position);
        String destination = List_destination.get(position);
        String date = List_date.get(position);
        String time = List_time.get(position);

        Etudiant et=Chercher.db_chercher.chercherdb(num_et);
        if(et!=null)
        holder.tv_et.setText(et.getNom()+" "+et.getPrénom());
        holder.tv_depart.setText(depart);
        holder.tv_destination.setText(destination);
        holder.tv_date.setText("Le "+date);
        holder.tv_time.setText(" à "+time);


    }

    @Override
    public int getItemCount() {
        return List_date.size();
    }
}
