package com.tanitim.universitetanitim.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.tanitim.universitetanitim.Activity.MainActivity;
import com.tanitim.universitetanitim.Adapters.SehirlerAdapter;
import com.tanitim.universitetanitim.Adapters.UniversitelerAdapter;
import com.tanitim.universitetanitim.Adapters.ViewPagerAdapter;
import com.tanitim.universitetanitim.Adapters.populerSehilerAdapter;
import com.tanitim.universitetanitim.Adapters.populerUniversitelerAdapter;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.ApiUtils;
import com.tanitim.universitetanitim.retrofit.UniversitelerCevap;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class AnasayfaFragment extends Fragment {
    public AnasayfaFragment() {
    }
    private RecyclerView rv,rv2;
    private ArrayList<Universiteler> universitelerListe,sehirlerListe;
    private populerUniversitelerAdapter adapter;
    private populerSehilerAdapter sehirAdapter;
    private UniversitelerDAOinterface universitelerDAOinterface;
    Button karsilastirButton;

    static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public static Context mContext;
    public static int tempSize;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anasayfa, container, false);
        rv = view.findViewById(R.id.populerRecylerView);
        rv2 = view.findViewById(R.id.populerSehirRecylerView);
        mContext = container.getContext();
        universitelerDAOinterface = ApiUtils.getUniversilerDAOinterface();
        rv.setHasFixedSize(true);
        rv2.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(gridLayoutManager);

        karsilastirButton=view.findViewById(R.id.karsilastirButton);

        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(mContext, 1);
        gridLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv2.setLayoutManager(gridLayoutManager2);


        universitelerListe = new ArrayList<>();

        tumUniversiteler();
        adapter = new populerUniversitelerAdapter(getContext(), universitelerListe, universitelerDAOinterface);
        rv.setAdapter(adapter);

        sehirlerListe = new ArrayList<>();
        sehirAdapter=new populerSehilerAdapter(getContext(),sehirlerListe,universitelerDAOinterface);
        tumSehirler();
        rv2.setAdapter(sehirAdapter);
        setHasOptionsMenu(true);




        karsilastirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.viewPager.setCurrentItem(2);



            }
        });








        sharedPreferences = getActivity().getSharedPreferences("favUni", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();



        Map<String, ?> allEntries = sharedPreferences.getAll();
        int size=allEntries.size();
        tempSize=allEntries.size();
        int favEkliMi[] = new int[size];
        String uniDizi[]=new String[size];
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            System.out.println(("map values"+ entry.getKey() + ": " + entry.getValue().toString()));
            favEkliMi[size-1]=Integer.parseInt(entry.getValue().toString());
            uniDizi[size-1]=entry.getKey();
            size--;
        }









        return view;
    }





    public void tumUniversiteler() {
        universitelerDAOinterface.pupulerUni().enqueue(new Callback<UniversitelerCevap>() {
            @Override
            public void onResponse(Call<UniversitelerCevap> call, retrofit2.Response<UniversitelerCevap> response) {
                List<Universiteler> sehirlerListe = response.body().getUniversiteler();

                List<Universiteler> liste = response.body().getUniversiteler();
                adapter = new populerUniversitelerAdapter(mContext, liste, universitelerDAOinterface);


                rv.setAdapter(adapter);




            }

            @Override
            public void onFailure(Call<UniversitelerCevap> call, Throwable t) {

            }
        });

    }

    public void tumSehirler() {

        universitelerDAOinterface.populersehir().enqueue(new Callback<UniversitelerCevap>() {
            @Override
            public void onResponse(Call<UniversitelerCevap> call, retrofit2.Response<UniversitelerCevap> response) {




                List<Universiteler> sehirlerListe = response.body().getUniversiteler();



                sehirAdapter = new populerSehilerAdapter(mContext, sehirlerListe, universitelerDAOinterface);


                rv2.setAdapter(sehirAdapter);



            }

            @Override
            public void onFailure(Call<UniversitelerCevap> call, Throwable t) {

            }
        });
    }








}
