package com.tanitim.universitetanitim.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.tanitim.universitetanitim.Adapters.DetayViewPagerAdapter;
import com.tanitim.universitetanitim.Fragments.DetayBilgiFragment;
import com.tanitim.universitetanitim.Fragments.MapsActivity;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;

public class DetayActivity extends BaseActivity {

    Universiteler universite;
    private ImageView detayImage, logo;
    private TabLayout detayTab;
    private ViewPager detayViewPager;
    private View mViewNeedOffset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);


        detayViewPager = findViewById(R.id.detayViewPager);
        detayTab = findViewById(R.id.detayTab);
        detayImage = findViewById(R.id.detayImage);
        logo = findViewById(R.id.logo);







        setupViewPager(detayViewPager);
        detayTab.setupWithViewPager(detayViewPager);

        universite = (Universiteler) getIntent().getSerializableExtra("nesne");


        Glide.with(DetayActivity.this).load("https://tohere.net/yedek/resim/" +
                universite.getSlug() + ".jpg").into(detayImage);

        Glide.with(DetayActivity.this).load("https://tohere.net/yedek/logo/" +
                universite.getSlug() + ".png").into(logo);


    }


    private void setupViewPager(ViewPager viewPager) {

        DetayViewPagerAdapter adapterDetay = new DetayViewPagerAdapter(getSupportFragmentManager());
        adapterDetay.addFragment(new DetayBilgiFragment(), "Bilgi");
        adapterDetay.addFragment(new MapsActivity(), "Konum");
        viewPager.setAdapter(adapterDetay);
    }


}
