package com.example.android.covoiturageiset;

/**
 * Created by khalil on 11/01/2018.
 */

public class Notification {
    private String num_sender,num_receiver;


    public Notification(String num_sender, String num_receiver) {
        this.num_sender = num_sender;
        this.num_receiver = num_receiver;

    }

    public String getNum_sender() {
        return num_sender;
    }

    public String getNum_receiver() {
        return num_receiver;
    }

}
