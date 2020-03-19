package com.tanitim.universitetanitim;

import android.graphics.Typeface;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.tanitim.universitetanitim.Adapters.ViewPagerAdapter;
import com.tanitim.universitetanitim.Fragments.AnasayfaFragment;
import com.tanitim.universitetanitim.Fragments.KarsilastirFragment;

import com.tanitim.universitetanitim.Fragments.SehirlerFragment;
import com.tanitim.universitetanitim.Fragments.UniversitelerFragment;




public class MainActivity extends AppCompatActivity {



    private ViewPager viewPager;


    //Fragments

    UniversitelerFragment universitelerFragment;
    AnasayfaFragment anasayfaFragment;
    KarsilastirFragment karsilastirFragment;
    SehirlerFragment sehirlerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);


        final BubbleNavigationLinearView bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear);
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
        anasayfaFragment =new AnasayfaFragment();
        universitelerFragment =new UniversitelerFragment();
        karsilastirFragment =new KarsilastirFragment();
        sehirlerFragment =new SehirlerFragment();

        adapter.addFragment(anasayfaFragment);
        adapter.addFragment(universitelerFragment);
        adapter.addFragment(karsilastirFragment);
        adapter.addFragment(sehirlerFragment);
        viewPager.setAdapter(adapter);
    }
}
