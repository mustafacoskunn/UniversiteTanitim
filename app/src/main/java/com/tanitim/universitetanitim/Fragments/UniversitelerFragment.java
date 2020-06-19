package com.tanitim.universitetanitim.Fragments;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.tanitim.universitetanitim.Adapters.UniversitelerAdapter;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.ApiUtils;
import com.tanitim.universitetanitim.retrofit.UniversitelerCevap;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UniversitelerFragment extends Fragment implements SearchView.OnQueryTextListener {
    public UniversitelerFragment() {
    }


    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Universiteler> universitelerListe;
    private UniversitelerAdapter adapter;
    private UniversitelerDAOinterface universitelerDAOinterface;

    public static Context mContext;
    public OkHttpClient okHttpClient;
    private static int cacheSize = 10 * 1024 * 1024; // 10 MiB

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_universiteler, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        rv = view.findViewById(R.id.rv);
        mContext = container.getContext();
        toolbar.setTitle("Üniversiteler");
        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.black));
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        universitelerDAOinterface = ApiUtils.getUniversilerDAOinterface();
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        universitelerListe = new ArrayList<>();
        tumUniversiteler();
        adapter = new UniversitelerAdapter(getContext(), universitelerListe, universitelerDAOinterface);
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

        searchView.setQueryHint("Süleyman Demirel Üniversitesi...");
        searchView.setOnQueryTextListener(this);


        super.onCreateOptionsMenu(menu, inflater);
    }


    private static boolean isNetworkAvailable() {


        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public void tumUniversiteler() {
        universitelerDAOinterface.tumUniversiteler().enqueue(new Callback<UniversitelerCevap>() {
            @Override
            public void onResponse(Call<UniversitelerCevap> call, retrofit2.Response<UniversitelerCevap> response) {
                List<Universiteler> liste = response.body().getUniversiteler();
                adapter = new UniversitelerAdapter(mContext, liste, universitelerDAOinterface);
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
        universitelerDAOinterface.universiteAra(aramaKelime).enqueue(new Callback<UniversitelerCevap>() {
            @Override
            public void onResponse(Call<UniversitelerCevap> call, retrofit2.Response<UniversitelerCevap> response) {
                List<Universiteler> liste = response.body().getUniversiteler();
                if (liste != null) {
                    adapter = new UniversitelerAdapter(mContext, liste, universitelerDAOinterface);
                }
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UniversitelerCevap> call, Throwable t) {
            }
        });
    }
}