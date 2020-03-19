package com.tanitim.universitetanitim.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanitim.universitetanitim.Adapters.UniversitelerAdapter;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.ApiUtils;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.retrofit.UniversitelerCevap;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class UniversiteSehir extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Universiteler> universitelerListe;
    private UniversitelerAdapter adapter;
    private Universiteler universite1;
    private Universiteler universite;
    private UniversitelerDAOinterface universitelerDAOinterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_universiteler);
        universitelerDAOinterface = ApiUtils.getUniversilerDAOinterface();
        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        universite1 = (Universiteler) getIntent().getSerializableExtra("nesne");
        String kelime = universite1.getIl();
        toolbar.setTitle(kelime + " Üniversiteler");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black));
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        universitelerListe = new ArrayList<>();
        adapter = new UniversitelerAdapter(this, universitelerListe, universitelerDAOinterface);
        rv.setAdapter(adapter);
        listele(kelime);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.hit));
        ImageView searchClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        searchView.setQueryHint("Süleyman Demirel Üniversitesi");
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("Gönderilen arama", query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("Harf girdikçe", newText);
        kelimeAra(newText);
        return false;
    }


    public void kelimeAra(final String aramaKelime) {
        //suan yok
        //eklenecek
        //şehirden üniversiteye geçerken bug var düzelt
    }


    public void listele(final String kelime) {
        universitelerDAOinterface.sehirilAra(kelime).enqueue(new Callback<UniversitelerCevap>() {
            @Override
            public void onResponse(Call<UniversitelerCevap> call, retrofit2.Response<UniversitelerCevap> response) {
                List<Universiteler> liste = response.body().getUniversiteler();
                adapter = new UniversitelerAdapter(getApplicationContext(), liste, universitelerDAOinterface);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UniversitelerCevap> call, Throwable t) {

            }
        });
    }
}


