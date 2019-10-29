package com.tanitim.universitetanitim.Fragments;


import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tanitim.universitetanitim.Adapters.UniversitelerAdapter;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;

import java.util.ArrayList;


public class UniversitelerFragment extends Fragment implements SearchView.OnQueryTextListener {


    public UniversitelerFragment() {
    }

    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Universiteler> universitelerListe;
    private UniversitelerAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_universiteler, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        rv = view.findViewById(R.id.rv);
        progressBar=view.findViewById(R.id.progressBar);

        toolbar.setTitle("Üniversiteler");
        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.black));
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("universiteler");

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        universitelerListe = new ArrayList<>();

        adapter = new UniversitelerAdapter(getContext(), universitelerListe);

        rv.setAdapter(adapter);
        setHasOptionsMenu(true);

        universiteBilgi();
        progressBar.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.purple_inactive), PorterDuff.Mode.SRC_IN);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.toolbar_menu,menu);

        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);


        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.hit));
        ImageView searchClose = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);

        searchView.setQueryHint("Süleyman Demirel Üniversitesi");
        searchView.setOnQueryTextListener(this);



        super.onCreateOptionsMenu(menu, inflater);
    }

    public void universiteBilgi(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);

                universitelerListe.clear();

                for(DataSnapshot d : dataSnapshot.getChildren()){
                    Universiteler universite = d.getValue(Universiteler.class);
                    universite.setUniversite_id(d.getKey());

                    universitelerListe.add(universite);

                }

                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        arama(newText);
        return false;
    }
    public static String karakterCevir(String kelime)
    {
        String mesaj = kelime;
        char[] oldValue = new char[] { 'ö', 'Ö', 'ü', 'Ü', 'ç', 'Ç', 'İ', 'ı', 'Ğ', 'ğ', 'Ş', 'ş' };
        char[] newValue = new char[] { 'o', 'O', 'u', 'U', 'c', 'C', 'I', 'i', 'G', 'g', 'S', 's' };
        for (int sayac = 0; sayac < oldValue.length; sayac++)
        {
            mesaj = mesaj.replace(oldValue[sayac], newValue[sayac]);
        }
        return mesaj;
    }

    public void arama(final String universiteAra){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                universitelerListe.clear();

                String kucuk=universiteAra.toLowerCase();

                String cevir=karakterCevir(kucuk);
                String sehir=universiteAra.toUpperCase();

                for(DataSnapshot d : dataSnapshot.getChildren()){
                    Universiteler universite = d.getValue(Universiteler.class);

                    if(universite.getSlug().contains(cevir)){
                        universite.setUniversite_id(d.getKey());
                        universitelerListe.add(universite);


                    }
                    else if(universite.getIl().contains(sehir)){
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
