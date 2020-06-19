package com.tanitim.universitetanitim.Activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

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
import com.tanitim.universitetanitim.Adapters.UniversitelerAdapter;
import com.tanitim.universitetanitim.Fragments.DetayBilgiFragment;
import com.tanitim.universitetanitim.Fragments.MapsActivity;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;
import com.tanitim.universitetanitim.retrofit.ApiUtils;
import com.tanitim.universitetanitim.retrofit.CRUDCevap;
import com.tanitim.universitetanitim.retrofit.UniversitelerDAOinterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetayActivity extends BaseActivity {

    Universiteler universite;
    private ImageView logo,favori;
    private TabLayout detayTab;
    private ViewPager detayViewPager;
    private View mViewNeedOffset;
    static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    SliderLayout detayImage;
    String favoriEklendiMi;
    static String favUni;
    int favKontrol;
    int tikKontrol;
    String android_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        universite = (Universiteler) getIntent().getSerializableExtra("nesne");
        detayViewPager = findViewById(R.id.detayViewPager);
        detayTab = findViewById(R.id.detayTab);
        detayImage = findViewById(R.id.detayImage);
        logo = findViewById(R.id.logo);
        favori=findViewById(R.id.favori);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        universitelerDAOinterface = ApiUtils.getUniversilerDAOinterface();
        favKontrol=favKontrol();
        favori.setTag(R.drawable.nofavori);

          favUni=universite.getSlug();

        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);


        sharedPreferences=getSharedPreferences("favUni",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        favoriEklendiMi=sharedPreferences.getString(favUni,"0");
        tikKontrol=Integer.parseInt(favoriEklendiMi);
        System.out.println("deneme:"+favoriEklendiMi+" : "+sharedPreferences.getAll());
        if (favoriEklendiMi.equals("1")){
            favori.setBackgroundResource(R.drawable.favori);


        }else {
            favori.setBackgroundResource(R.drawable.nofavori);
        }


        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                favKontrol=favKontrol();

                if (tikKontrol ==1){

                    tikKontrol=0;
                    favKontrol=favKontrol();
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("FavoriKontrol:"+favKontrol);
                            if (favKontrol==0){
                                favoriEkle();
                            }else favorGuncelle();
                        }
                    },200);

                    favori.setBackgroundResource(R.drawable.nofavori);
                    editor.putString(favUni,"0");
                    editor.apply();


                }
               else if(tikKontrol ==0){

                    tikKontrol=1;
                    favKontrol=favKontrol();
                   Handler handler=new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           System.out.println("FavoriKontrol:"+favKontrol);
                           if (favKontrol==0){
                               favoriEkle();
                           }else favorGuncelle();
                       }
                   },200);



                    favori.setBackgroundResource(R.drawable.favori);
                    editor.putString(favUni,"1");
                    editor.apply();
                }




            }
        });

        setupViewPager(detayViewPager);
        detayTab.setupWithViewPager(detayViewPager);




        Glide.with(DetayActivity.this).load("https://www.tohere.net/yekimsdfdf/yedek/logo/" +
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
                    sliderView.setImageUrl("https://www.tohere.net/yekimsdfdf/yedek/resim/" +
                            universite.getSlug() + "-1.jpg");
                    break;
                case 1:
                    sliderView.setImageUrl("https://www.tohere.net/yekimsdfdf/yedek/resim/" +
                            universite.getSlug() + "-2.jpg");
                    break;
                case 2:
                    sliderView.setImageUrl("https://www.tohere.net/yekimsdfdf/yedek/resim/" +
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
    private UniversitelerAdapter adapter;
    private UniversitelerDAOinterface universitelerDAOinterface;



    public void favoriEkle(){
        try {
            universitelerDAOinterface.favoriEkle(android_id,tikKontrol,universite.getSlug()).enqueue(new Callback<CRUDCevap>() {
                @Override
                public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                    Log.e("Ekleme:",response.body().getSuccess().toString());
                    Log.e("Eklememesaj:",response.body().getMessage().toString());


        }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

                System.out.println("hata");
            }
        });
    }catch (Exception e){
            System.out.println("hata:"+e);
        }




    }

    public void favorGuncelle(){

        universitelerDAOinterface.favoriGuncelle(tikKontrol,universite.getSlug()).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {

            }
        });
    }


    public int favKontrol(){


            universitelerDAOinterface.fav_kontrol(android_id,universite.getSlug()).enqueue(new Callback<CRUDCevap>() {
                @Override
                public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                    Log.e("FavoriMEsaj",response.body().getSuccess().toString());
                    favKontrol=response.body().getSuccess();


                }

                @Override
                public void onFailure(Call<CRUDCevap> call, Throwable t) {

                    System.out.println("hata");
                }
            });



            return favKontrol;


    }



}
