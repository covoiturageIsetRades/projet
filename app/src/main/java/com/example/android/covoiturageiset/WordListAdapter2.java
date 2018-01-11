package com.example.android.covoiturageiset;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

/**
 * Created by khalil on 11/01/2018.
 */

public class WordListAdapter2 extends RecyclerView.Adapter<WordListAdapter2.WordViewHolder2> {
    private LayoutInflater mInflater;
    private final LinkedList<LinkedList> List1;
    LinkedList<String> List_num_sender = new LinkedList<>();
    LinkedList<String> List_depart = new LinkedList<>();
    LinkedList<String> List_destination = new LinkedList<>();
    LinkedList<String> List_date = new LinkedList<>();
    LinkedList<String> List_time = new LinkedList<>();




    public class WordViewHolder2 extends RecyclerView.ViewHolder {

        public final TextView tv_et_sender,tv_depart,tv_destination,tv_date,tv_time;
        final WordListAdapter2 mAdapter;


        public WordViewHolder2(View itemView, WordListAdapter2 mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            tv_et_sender = (TextView) itemView.findViewById(R.id.tv_etu);
            tv_depart = (TextView) itemView.findViewById(R.id.tvdep);
            tv_destination = (TextView) itemView.findViewById(R.id.tvdest);
            tv_date = (TextView) itemView.findViewById(R.id.tvdate);
            tv_time = (TextView) itemView.findViewById(R.id.tvtime);
        }
    }
   /**********************************************************************************/
    public WordListAdapter2(Context c, LinkedList<LinkedList> list) {
        mInflater = LayoutInflater.from(c);
        List1 = list;
        List_num_sender = List1.get(0);
        List_depart = List1.get(1);
        List_destination = List1.get(2);
        List_date = List1.get(3);
        List_time=List1.get(4);

    }

    @Override
    public WordViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item2, parent, false);
        return new WordListAdapter2.WordViewHolder2(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder2 holder, int position) {
        String num_et = List_num_sender.get(position);
        String depart = List_depart.get(position);
        String destination = List_destination.get(position);
        String date = List_date.get(position);
        String time = List_time.get(position);

        Etudiant et=Chercher.db_chercher.chercherdb(num_et);
        if(et!=null)
         holder.tv_et_sender.setText(et.getNom()+" "+et.getPrénom());
        holder.tv_depart.setText(depart);
        holder.tv_destination.setText(destination);
        holder.tv_date.setText(date);
        holder.tv_time.setText(time);



    }

    @Override
    public int getItemCount() {
        return List_date.size();
    }


}
