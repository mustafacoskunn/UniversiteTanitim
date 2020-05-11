package com.tanitim.universitetanitim.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.tanitim.universitetanitim.Adapters.DetayViewPagerAdapter;
import com.tanitim.universitetanitim.Fragments.DetayBilgiFragment;
import com.tanitim.universitetanitim.Fragments.MapsActivity;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;

public class DetayActivity extends BaseActivity {

    Universiteler universite;
    private ImageView logo;
    private TabLayout detayTab;
    private ViewPager detayViewPager;
    private View mViewNeedOffset;
    SliderLayout detayImage;


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


        Glide.with(DetayActivity.this).load("https://www.diziblog.net/yekimsdfdf/yedek/logo/" +
                universite.getSlug() + "-logo.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).into(logo);


        detayImage.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using 	 SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        detayImage.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        detayImage.setScrollTimeInSec(2); //set scroll delay in seconds :

        setSliderViews();

    }

    private void setSliderViews() {

        for (int i = 0; i <= 2; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://www.diziblog.net/yekimsdfdf/yedek/resim/" +
                            universite.getSlug() + "-1.jpg");
                    break;
                case 1:
                    sliderView.setImageUrl("https://www.diziblog.net/yekimsdfdf/yedek/resim/" +
                            universite.getSlug() + "-2.jpg");
                    break;
                case 2:
                    sliderView.setImageUrl("https://www.diziblog.net/yekimsdfdf/yedek/resim/" +
                            universite.getSlug() + "-3.jpg");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                }
            });

            //at last add this view in your layout :
            detayImage.addSliderView(sliderView);
        }
    }


    private void setupViewPager(ViewPager viewPager) {

        DetayViewPagerAdapter adapterDetay = new DetayViewPagerAdapter(getSupportFragmentManager());
        adapterDetay.addFragment(new DetayBilgiFragment(), "Bilgi");
        adapterDetay.addFragment(new MapsActivity(), "Konum");
        viewPager.setAdapter(adapterDetay);
    }


}
