package com.tanitim.universitetanitim.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tanitim.universitetanitim.Activity.DetayActivity;
import com.tanitim.universitetanitim.Activity.MainActivity;
import com.tanitim.universitetanitim.Adapters.UniversitelerAdapter;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.ApiUtils;
import com.tanitim.universitetanitim.retrofit.UniversitelerCevap;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class FavoriFragment extends Fragment {

    public FavoriFragment() {
        // Required empty public constructor
    }

    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Universiteler> universitelerListe;
    private UniversitelerAdapter adapter;
    private UniversitelerDAOinterface universitelerDAOinterface;
    public static Context mContext;
    String android_id;
    TextView textEkle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmeant
        View view = inflater.inflate(R.layout.fragment_favori, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        rv = view.findViewById(R.id.rv);
        textEkle=view.findViewById(R.id.textEkle);
        toolbar.setTitle("Favori Üniversiteler");
        mContext = container.getContext();
        android_id = Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.black));

        universitelerDAOinterface = ApiUtils.getUniversilerDAOinterface();
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        universitelerListe = new ArrayList<>();
        adapter = new UniversitelerAdapter(getContext(), universitelerListe, universitelerDAOinterface);
        rv.setAdapter(adapter);
        setHasOptionsMenu(true);

        favoriUniversiteler();
        return view;

    }


    public void favoriUniversiteler() {
        universitelerDAOinterface.tumFavoriler(android_id).enqueue(new Callback<UniversitelerCevap>() {
            @Override
            public void onResponse(Call<UniversitelerCevap> call, retrofit2.Response<UniversitelerCevap> response) {
                List<Universiteler> liste = response.body().getUniversiteler();

                if (liste != null) {
                    adapter = new UniversitelerAdapter(mContext, liste, universitelerDAOinterface);
                    rv.setAdapter(adapter);
                    textEkle.setVisibility(TextView.INVISIBLE);

                }else {
                    textEkle.setVisibility(TextView.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<UniversitelerCevap> call, Throwable t) {

            }
        });

    }




}
