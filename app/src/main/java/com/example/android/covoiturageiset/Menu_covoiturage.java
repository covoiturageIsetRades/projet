package com.example.android.covoiturageiset;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Menu_covoiturage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String num_et;
    TextView tv_num_et,tv_nom_prenom;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_covoiturage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        num_et=intent.getExtras().getString("num_et");

        dbHandler=new MyDBHandler(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);
/*View        view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        tv_nom_prenom = (TextView)header.findViewById(R.id.textView_nom_prenom);
        tv_num_et = (TextView)header.findViewById(R.id.textView_num_et);
        Etudiant et=dbHandler.chercherdb(num_et);
        if(et!=null)
        tv_nom_prenom.setText(et.getNom()+" "+et.getPrénom());
        tv_num_et.setText(num_et);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_covoiturage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_proposer) {
            Intent i=new Intent(this,Proposer.class);
            i.putExtra("num_et",num_et);
            startActivity(i);
        } else if (id == R.id.nav_chercher) {
            Intent i=new Intent(this,Chercher.class);
            i.putExtra("num_et",num_et);
            startActivity(i);
        } else if (id == R.id.nav_notifications) {
            Intent i=new Intent(this,NotificationsActivity.class);
            i.putExtra("num_et",num_et);
            startActivity(i);
        }
        else if (id == R.id.nav_deconnexion) {
            Intent i=new Intent(this,Authentification.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
