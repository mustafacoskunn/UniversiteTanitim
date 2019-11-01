package com.tanitim.universitetanitim.Fragments;



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


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tanitim.universitetanitim.Adapters.UniversitelerAdapter;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UniversitelerFragment extends Fragment implements SearchView.OnQueryTextListener {


    public UniversitelerFragment() {
    }

    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Universiteler> universitelerListe;
    private UniversitelerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_universiteler, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        rv = view.findViewById(R.id.rv);

        toolbar.setTitle("Üniversiteler");
        toolbar.setTitleTextColor(ContextCompat.getColor(getContext(), R.color.black));
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);




        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        universitelerListe = new ArrayList<>();
        universiteBilgi();
        adapter = new UniversitelerAdapter(getContext(), universitelerListe);

        rv.setAdapter(adapter);
        setHasOptionsMenu(true);


     //   progressBar.getIndeterminateDrawable().setColorFilter(getResources()
       //         .getColor(R.color.purple_inactive), PorterDuff.Mode.SRC_IN);


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

        searchView.setQueryHint("Süleyman Demirel Üniversitesi...");
        searchView.setOnQueryTextListener(this);



        super.onCreateOptionsMenu(menu, inflater);
    }

    public void universiteBilgi(){

        String url = "https://universitetanitim.tk/tum_universiteler.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                universitelerListe = new ArrayList<>();

                try {
                    JSONObject jsonObj = new JSONObject(response);


                    JSONArray universiteler = jsonObj.getJSONArray("universiteler");

                    // looping through All Contacts
                    for (int i = 0; i < universiteler.length(); i++) {
                        JSONObject u = universiteler.getJSONObject(i);

                         String adres=u.getString("adres");
                         int arastirma=u.getInt("arastirma");
                         int docent=u.getInt("docent");
                         int doktor=u.getInt("doktor");
                         int doktoraerkek=u.getInt("doktoraerkek");
                         int doktorakadin=u.getInt("doktorakadin");
                         int doktoratoplam=u.getInt("doktoratoplam");
                         String eposta=u.getString("eposta");
                         String il=u.getString("il");
                         String isim=u.getString("isim");
                         String kurulus=u.getString("kurulus");
                         int lisanserkek=u.getInt("lisanserkek");
                         int lisanskadin=u.getInt("lisanskadin");
                         int lisanstoplam=u.getInt("lisanstoplam");
                         int ogretim=u.getInt("ogretim");
                         int onlisanserkek=u.getInt("onlisanserkek");
                         int onlisanskadin=u.getInt("onlisanskadin");
                         int onlisanstoplam=u.getInt("onlisanstoplam");
                         int profesor=u.getInt("profesor");
                         String rektor=u.getString("rektor");
                         String slug=u.getString("slug");
                         int toplam=u.getInt("toplam");
                         int toplamerkek=u.getInt("toplamerkek");
                         int toplamkadin=u.getInt("toplamkadin");
                         String tur=u.getString("tur");
                         String website=u.getString("website");
                         int yukseklisanserkek=u.getInt("yukseklisanserkek");
                         int yukseklisanskadin=u.getInt("yukseklisanskadin");
                         int yukseklisanstoplam=u.getInt("yukseklisanstoplam");
                         String fax=u.getString("fax");
                         String bolge=u.getString("bolge");
                         String telefon=u.getString("telefon");





                        Universiteler universite = new Universiteler( adres,  arastirma,  docent,  doktor,  doktoraerkek,  doktorakadin,  doktoratoplam,  eposta,  il,  isim,  kurulus,  lisanserkek,  lisanskadin,  lisanstoplam,  ogretim,  onlisanserkek,  onlisanskadin,  onlisanstoplam,  profesor,  rektor,  slug,  toplam,  toplamerkek,  toplamkadin,  tur,  website,  yukseklisanserkek,  yukseklisanskadin,  yukseklisanstoplam,  fax,  bolge,  telefon);

                        universitelerListe.add(universite);

                    }

                    adapter = new UniversitelerAdapter(getActivity().getApplicationContext(),universitelerListe);

                    rv.setAdapter(adapter);


                } catch (JSONException e) {
                    System.out.println("hata:"+e);


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("hata");

            }
        });

        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);

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


    public void kelimeAra(final String aramaKelime){

        String url = "https://universitetanitim.tk/universite_ara.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                universitelerListe = new ArrayList<>();

                try {
                    JSONObject jsonObj = new JSONObject(response);


                    JSONArray universiteler = jsonObj.getJSONArray("universiteler");

                        for (int i = 0; i < universiteler.length(); i++) {
                            JSONObject u = universiteler.getJSONObject(i);

                            String adres=u.getString("adres");
                            int arastirma=u.getInt("arastirma");
                            int docent=u.getInt("docent");
                            int doktor=u.getInt("doktor");
                            int doktoraerkek=u.getInt("doktoraerkek");
                            int doktorakadin=u.getInt("doktorakadin");
                            int doktoratoplam=u.getInt("doktoratoplam");
                            String eposta=u.getString("eposta");
                            String il=u.getString("il");
                            String isim=u.getString("isim");
                            String kurulus=u.getString("kurulus");
                            int lisanserkek=u.getInt("lisanserkek");
                            int lisanskadin=u.getInt("lisanskadin");
                            int lisanstoplam=u.getInt("lisanstoplam");
                            int ogretim=u.getInt("ogretim");
                            int onlisanserkek=u.getInt("onlisanserkek");
                            int onlisanskadin=u.getInt("onlisanskadin");
                            int onlisanstoplam=u.getInt("onlisanstoplam");
                            int profesor=u.getInt("profesor");
                            String rektor=u.getString("rektor");
                            String slug=u.getString("slug");
                            int toplam=u.getInt("toplam");
                            int toplamerkek=u.getInt("toplamerkek");
                            int toplamkadin=u.getInt("toplamkadin");
                            String tur=u.getString("tur");
                            String website=u.getString("website");
                            int yukseklisanserkek=u.getInt("yukseklisanserkek");
                            int yukseklisanskadin=u.getInt("yukseklisanskadin");
                            int yukseklisanstoplam=u.getInt("yukseklisanstoplam");
                            String fax=u.getString("fax");
                            String bolge=u.getString("bolge");
                            String telefon=u.getString("telefon");





                            Universiteler universite = new Universiteler( adres,  arastirma,  docent,  doktor,  doktoraerkek,  doktorakadin,  doktoratoplam,  eposta,  il,  isim,  kurulus,  lisanserkek,  lisanskadin,  lisanstoplam,  ogretim,  onlisanserkek,  onlisanskadin,  onlisanstoplam,  profesor,  rektor,  slug,  toplam,  toplamerkek,  toplamkadin,  tur,  website,  yukseklisanserkek,  yukseklisanskadin,  yukseklisanstoplam,  fax,  bolge,  telefon);


                            universitelerListe.add(universite);





                    }



                    adapter = new UniversitelerAdapter(getActivity().getApplicationContext(),universitelerListe);

                    rv.setAdapter(adapter);


                } catch (JSONException e) {
                    System.out.println("hata:"+e);


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("hata:"+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("isim",aramaKelime);

                return params;
            }
        };

        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);

    }





}
