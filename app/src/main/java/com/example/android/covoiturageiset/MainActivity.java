package com.example.android.covoiturageiset;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText ET_nom,ET_prenom,ET_num_et,ET_CIN,ET_mail,ET_Password,ET_confirmer_Password;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ET_nom = (EditText) findViewById(R.id.ET_nom);
        ET_prenom = (EditText) findViewById(R.id.ET_prenom);
        ET_num_et = (EditText) findViewById(R.id.ET_num_et);
        ET_CIN = (EditText) findViewById(R.id.ET_CIN);
        ET_mail = (EditText) findViewById(R.id.ET_mail);
        ET_Password = (EditText) findViewById(R.id.ET_password);
        ET_confirmer_Password = (EditText) findViewById(R.id.ET_confirmer_password);



        dbHandler = new MyDBHandler(this);

    }



    public void addButtonClicked(View view){

        if(champs_vide())
            Toast.makeText(this, "Veuillez saisir tous les champs !!", Toast.LENGTH_SHORT).show();
        else
        if(ET_Password.getText().toString().equals(ET_confirmer_Password.getText().toString())) {
            Etudiant et = new Etudiant(ET_num_et.getText().toString(), ET_nom.getText().toString(), ET_prenom.getText().toString(),
                                       ET_CIN.getText().toString(), ET_mail.getText().toString(), ET_Password.getText().toString());
            Long add = dbHandler.addEtudiant(et);
            if (add == -1)
                Toast.makeText(this, "Matricule Etudiant existe déja", Toast.LENGTH_SHORT).show();
            else
            {Toast.makeText(this, "Iscription réussite", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(this,Authentification.class));
                String dbString = dbHandler.databaseToString();
                Log.d("Database=",dbString);
            }

        }
        else
            Toast.makeText(this, "Mot de passe n'est pas identique ", Toast.LENGTH_SHORT).show();

    }

    private boolean champs_vide() {
        if(ET_nom.getText().toString().equals("")||ET_prenom.getText().toString().equals("")||ET_num_et.getText().toString().equals("")
                ||ET_CIN.getText().toString().equals("")||ET_mail.getText().toString().equals("")||ET_Password.getText().toString().equals(""))
         return true;
        else
            return false;
    }


    public void  msg(String ch)
    {
        Toast.makeText(this,ch,Toast.LENGTH_SHORT).show();

}

    public void deleteButtonClicked(View view){
        String inputText = ET_num_et.getText().toString();
        dbHandler.deleteEtudiant(inputText);

    }
    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        Log.d("Database=",dbString);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);

        builder.setTitle("database");
        builder.setMessage(dbString);
        builder.show();
    }
    public void afficherDB(View view) {
        printDatabase();
    }


}
