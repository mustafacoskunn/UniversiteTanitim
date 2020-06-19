package com.tanitim.universitetanitim.Activity;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.tanitim.universitetanitim.Adapters.SehirlerAdapter;
import com.tanitim.universitetanitim.Adapters.ViewPagerAdapter;
import com.tanitim.universitetanitim.Adapters.populerUniversitelerAdapter;
import com.tanitim.universitetanitim.Fragments.AnasayfaFragment;
import com.tanitim.universitetanitim.Fragments.FavoriFragment;
import com.tanitim.universitetanitim.Fragments.KarsilastirFragment;

import com.tanitim.universitetanitim.Fragments.SehirlerFragment;
import com.tanitim.universitetanitim.Fragments.UniversitelerFragment;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.UniversitelerCevap;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    public static ViewPager viewPager;
    //Fragments
    UniversitelerFragment universitelerFragment;
    AnasayfaFragment anasayfaFragment;
    KarsilastirFragment karsilastirFragment;
    SehirlerFragment sehirlerFragment;
    FavoriFragment favoriFragment;
    public static BubbleNavigationLinearView bubbleNavigationLinearView;
    public static String android_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);

        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);


        bubbleNavigationLinearView= findViewById(R.id.bottom_navigation_view_linear);
        //     bubbleNavigationLinearView.setTypeface(Typeface.createFromAsset(getAssets(), "rubok.ttc"));
        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {

                switch (view.getId()) {
                    case R.id.l_item_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.l_item_search:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.l_item_profile_list:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.l_item_profilee_list:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.favori:
                        viewPager.setCurrentItem(4);
                        break;

                }
            }
        });



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bubbleNavigationLinearView.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        setupViewPager(viewPager);
    }




    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        anasayfaFragment = new AnasayfaFragment();
        universitelerFragment = new UniversitelerFragment();
        karsilastirFragment = new KarsilastirFragment();
        sehirlerFragment = new SehirlerFragment();
        favoriFragment=new FavoriFragment();
        adapter.addFragment(anasayfaFragment);
        adapter.addFragment(universitelerFragment);
        adapter.addFragment(karsilastirFragment);
        adapter.addFragment(sehirlerFragment);
        adapter.addFragment(favoriFragment);
        viewPager.setAdapter(adapter);
    }
}
