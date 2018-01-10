package com.example.android.covoiturageiset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Authentification extends AppCompatActivity {
     EditText ET_matricule,ET_password;
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        ET_matricule=(EditText)findViewById(R.id.ET_matricule);
        ET_password=(EditText)findViewById(R.id.ET_mdp);
        dbHandler = new MyDBHandler(this);

    }

    public void Se_connecter(View view) {
        Etudiant et_cherché = dbHandler.chercherdb(ET_matricule.getText().toString());

        if(et_cherché!=null)
            if(et_cherché.getPassword().equals(ET_password.getText().toString())) {
                Toast.makeText(this, "connexion réussite", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Authentification.this,Menu_covoiturage.class);
                i.putExtra("num_et",ET_matricule.getText().toString());
                startActivity(i);
              }
            else
                Toast.makeText(this,"mot de passe incorrecte",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"matricule incorrecte",Toast.LENGTH_SHORT).show();

    }
}
