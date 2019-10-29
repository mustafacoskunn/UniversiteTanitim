package com.tanitim.universitetanitim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;


import com.squareup.picasso.Picasso;
import com.tanitim.universitetanitim.Models.Universiteler;

public class DetayActivity extends BaseActivity {
    private TextView textViewIsim;
    private TextView textViewID;
    private Universiteler universite;
    private Toolbar mToolbar;
    private View mViewNeedOffset;
    private ImageView detayImage;



    private int mAlpha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

      //  textViewIsim = findViewById(R.id.textViewIsim);
      //  textViewID = findViewById(R.id.textViewID);


        detayImage = findViewById(R.id.detayImage);
        mToolbar = findViewById(R.id.toolbar);
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        StatusBarUtil.setTranslucentForImageView(DetayActivity.this, 100, mViewNeedOffset);


        setSupportActionBar(mToolbar);
        textViewIsim = findViewById(R.id.textViewIsim);
        textViewID = findViewById(R.id.textViewID);

        universite = (Universiteler) getIntent().getSerializableExtra("nesne");
        textViewIsim.setText(universite.getIsim());
        textViewID.setText(universite.getBolge()+" BÖLGESİ");


        Picasso.with(DetayActivity.this)
                .load("https://takipgym.com/resim/"+universite.getSlug()+".webp")
                .into(detayImage);


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

    @Override
    protected void setStatusBar() {
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        StatusBarUtil.setTranslucentForImageView(this, mViewNeedOffset);
    }


}
