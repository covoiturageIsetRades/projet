package com.example.android.covoiturageiset;

/**
 * Created by khalil on 09/12/2017.
 */

public class Etudiant {
    private String num_et;
    private String nom;
    private String prénom;
    private String CIN;
    private String mail;
    private String password;


    //Added this empty constructor in lesson 50 in case we ever want to create the object and assign it later.
    public Etudiant(){

    }

    public Etudiant(String num_et, String nom, String prénom,String CIN,String mail,String password) {
        this.num_et = num_et;
        this.nom = nom;
        this.prénom = prénom;
        this.CIN=CIN;
        this.mail=mail;
        this.password=password;
    }


    public String getNum_et() {
        return num_et;
    }

    public void setNum_et(String num_et) {
        this.num_et = num_et;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Etudiant n° " + num_et +
                " " + nom  + " " + prénom ;
    }
}
