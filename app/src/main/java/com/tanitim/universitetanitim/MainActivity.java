package com.tanitim.universitetanitim;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Universiteler> universitelerListe;
    private UniversitelerAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        toolbar.setTitle("Üniversite Tanıtım");
        setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("universiteler");

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        universitelerListe = new ArrayList<>();

        adapter = new UniversitelerAdapter(this, universitelerListe);

        rv.setAdapter(adapter);


        universiteBilgi();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("Gönderilen arama",query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("Harf girdikçe",newText);
        arama(newText);
        return false;
    }


    public void universiteBilgi(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                universitelerListe.clear();

                for(DataSnapshot d : dataSnapshot.getChildren()){
                    Universiteler universite = d.getValue(Universiteler.class);
                    universite.setUniversite_id(d.getKey());

                    universitelerListe.add(universite);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    public void arama(final String universiteAra){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                universitelerListe.clear();

                for(DataSnapshot d : dataSnapshot.getChildren()){
                    Universiteler universite = d.getValue(Universiteler.class);

                    if(universite.getIsim().contains(universiteAra)){
                        universite.setUniversite_id(d.getKey());
                        universitelerListe.add(universite);
                    }
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }





}
