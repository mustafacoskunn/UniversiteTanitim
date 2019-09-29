package com.tanitim.universitetanitim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {
    private TextView textViewIsim;
    private TextView textViewID;
    private Universiteler universite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        textViewIsim = findViewById(R.id.textViewIsim);
        textViewID = findViewById(R.id.textViewID);

        universite = (Universiteler) getIntent().getSerializableExtra("nesne");
        textViewIsim.setText(universite.getIsim());
        textViewID.setText(universite.getBolge()+" BÖLGESİ");

        Log.e("getAdres:",universite.getAdres());
        Log.e("getEposta:",universite.getEposta());
        Log.e("getIl:",universite.getIl());
        Log.e("getIsim:",universite.getIsim());
        Log.e("getKurulus:",universite.getKurulus());
        Log.e("getRektor:",universite.getRektor());
        Log.e("getSlug:",universite.getSlug());
        Log.e("getTur:",universite.getTur());
        Log.e("getWebsite:",universite.getWebsite());
        Log.e("getToplam:",String.valueOf(universite.getToplam()));







    }
}
