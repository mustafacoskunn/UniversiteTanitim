package com.tanitim.universitetanitim;

import android.support.v4.content.ContextCompat;
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
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tanitim.universitetanitim.Adapters.SehirlerAdapter;
import com.tanitim.universitetanitim.Adapters.UniversitelerAdapter;
import com.tanitim.universitetanitim.Models.Universiteler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class sehireGoreUniversite extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Universiteler> universitelerListe;
    private UniversitelerAdapter adapter;
    private Universiteler universite1;
    private Universiteler universite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehire_gore_universite);


        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        universite1 = (Universiteler) getIntent().getSerializableExtra("nesne");
        String kelime=universite1.getIl();


        toolbar.setTitle(kelime+" Üniversiteler");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black));









        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        universitelerListe = new ArrayList<>();

        adapter = new UniversitelerAdapter(this, universitelerListe);

        rv.setAdapter(adapter);


        listele(kelime);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);


        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.hit));
        ImageView searchClose = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);

        searchView.setQueryHint("Süleyman Demirel Üniversitesi");
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
        kelimeAra(newText);
        return false;
    }


    public void arama(final String kelime){

        String url = "https://universitetanitim.tk/sehiregorefiltre.php";

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



                    adapter = new UniversitelerAdapter(getApplicationContext(),universitelerListe);

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

                params.put("il",kelime);

                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


    }

    public void kelimeAra(final String aramaKelime){

        String url = "https://universitetanitim.tk/universite_ara.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("cevap:"+response);

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



                    adapter = new UniversitelerAdapter(getApplicationContext(),universitelerListe);

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

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }




    public void listele(final String kelime){

        String url = "https://universitetanitim.tk/sehiregorefiltre.php";

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



                    adapter = new UniversitelerAdapter(getApplicationContext(),universitelerListe);

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

                params.put("il",kelime);

                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


    }





}