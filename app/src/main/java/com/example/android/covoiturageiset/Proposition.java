package com.example.android.covoiturageiset;

/**
 * Created by khalil on 07/01/2018.
 */

public class Proposition {
    private String depart;
    private String destination;
    private String date_proposition;

    public Proposition(String depart, String destination, String date_proposition) {
        this.depart = depart;
        this.destination = destination;
        this.date_proposition = date_proposition;
    }


    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate_proposition() {
        return date_proposition;
    }
}
