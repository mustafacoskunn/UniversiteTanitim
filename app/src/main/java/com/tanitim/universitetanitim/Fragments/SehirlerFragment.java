package com.tanitim.universitetanitim.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tanitim.universitetanitim.Adapters.SehirlerAdapter;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.ApiUtils;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.retrofit.UniversitelerCevap;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class SehirlerFragment extends Fragment implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Universiteler> universitelerListe;
    private SehirlerAdapter adapter;
    private UniversitelerDAOinterface universitelerDAOinterface;
    private Context mContext;

    public SehirlerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sehirler, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        rv = view.findViewById(R.id.rv1);
        mContext = container.getContext();
        toolbar.setTitle("Şehirler");
        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.black));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        universitelerDAOinterface = ApiUtils.getUniversilerDAOinterface();
        rv.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(gridLayoutManager);
        universitelerListe = new ArrayList<>();
        tumSehirler();
        adapter = new SehirlerAdapter(getContext(), universitelerListe, universitelerDAOinterface);
        rv.setAdapter(adapter);
        setHasOptionsMenu(true);


        //   progressBar.getIndeterminateDrawable().setColorFilter(getResources()
        //         .getColor(R.color.purple_inactive), PorterDuff.Mode.SRC_IN);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // toolbar setup
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        ImageView searchClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        searchView.setQueryHint("Süleyman Demirel Üniversitesi...");
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    public void tumSehirler() {

        universitelerDAOinterface.tumSehirler().enqueue(new Callback<UniversitelerCevap>() {
            @Override
            public void onResponse(Call<UniversitelerCevap> call, retrofit2.Response<UniversitelerCevap> response) {
                List<Universiteler> liste = response.body().getUniversiteler();
                adapter = new SehirlerAdapter(mContext, liste, universitelerDAOinterface);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UniversitelerCevap> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        kelimeAra(newText);
        return false;
    }


    public void kelimeAra(final String aramaKelime) {
        universitelerDAOinterface.ilAra(aramaKelime).enqueue(new Callback<UniversitelerCevap>() {
            @Override
            public void onResponse(Call<UniversitelerCevap> call, retrofit2.Response<UniversitelerCevap> response) {
                List<Universiteler> liste = response.body().getUniversiteler();
                if (liste != null) {

                    adapter = new SehirlerAdapter(mContext, liste, universitelerDAOinterface);
                }
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UniversitelerCevap> call, Throwable t) {
            }
        });
    }
}