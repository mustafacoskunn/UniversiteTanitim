package com.tanitim.universitetanitim.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.jaeger.library.StatusBarUtil;
import com.squareup.picasso.Picasso;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;

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
        textViewID.setText(universite.getBolge() + " BÖLGESİ");
        Picasso.with(DetayActivity.this)
                .load("https://tohere.net/yedek/resim/" + universite.getSlug() + ".webp")
                .into(detayImage);


    }

    @Override
    protected void setStatusBar() {
        mViewNeedOffset = findViewById(R.id.view_need_offset);
        StatusBarUtil.setTranslucentForImageView(this, mViewNeedOffset);
    }


}
