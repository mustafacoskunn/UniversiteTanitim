package com.tanitim.universitetanitim.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UniversitelerDAOinterface {
    @GET("yekimsdfdf/yedek/tum_universiteler.php")
    Call<UniversitelerCevap> tumUniversiteler();

    @GET("yekimsdfdf/yedek/sehirler.php")
    Call<UniversitelerCevap> tumSehirler();

    @GET("yekimsdfdf/yedek/populersehir.php")
    Call<UniversitelerCevap> populersehir();

    @POST("yekimsdfdf/yedek/universite_ara.php")
    @FormUrlEncoded
    Call<UniversitelerCevap> universiteAra(@Field("isim") String isim);

    @POST("yekimsdfdf/yedek/il_ara.php")
    @FormUrlEncoded
    Call<UniversitelerCevap> ilAra(@Field("il") String il);

    @POST("yekimsdfdf/yedek/sehiregorefiltre.php")
    @FormUrlEncoded
    Call<UniversitelerCevap> sehirilAra(@Field("il") String il);

    @POST("yekimsdfdf/yedek/deneme2.php")
    @FormUrlEncoded
    Call<CRUDCevap> favoriEkle(@Field("cihazid") String cihazid
            ,@Field("favoriMi") int favoriMi
            ,@Field("slug") String slug);

    @POST("yekimsdfdf/yedek/fav_guncelle.php")
    @FormUrlEncoded
    Call<CRUDCevap> favoriGuncelle(@Field("favoriMi") int favoriMi
            ,@Field("slug") String slug);

    @POST("yekimsdfdf/yedek/fav_kontrol.php")
    @FormUrlEncoded
    Call<CRUDCevap> fav_kontrol(@Field("cihazid") String cihazid
            ,@Field("slug") String slug);


    @POST("yekimsdfdf/yedek/tum_favoriler.php")
    @FormUrlEncoded
    Call<UniversitelerCevap> tumFavoriler(@Field("cihazid") String cihazid);

    @GET("yekimsdfdf/yedek/populeruni.php")
    Call<UniversitelerCevap> pupulerUni();


}
