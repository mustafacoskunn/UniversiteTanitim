package com.tanitim.universitetanitim.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UniversitelerDAOinterface {
    @GET("yedek/tum_universiteler.php")
    Call<UniversitelerCevap> tumUniversiteler();

    @GET("yedek/sehirler.php")
    Call<UniversitelerCevap> tumSehirler();

    @POST("yedek/universite_ara.php")
    @FormUrlEncoded
    Call<UniversitelerCevap> universiteAra(@Field("isim") String isim);

    @POST("yedek/il_ara.php")
    @FormUrlEncoded
    Call<UniversitelerCevap> ilAra(@Field("il") String il);

    @POST("yedek/sehiregorefiltre.php")
    @FormUrlEncoded
    Call<UniversitelerCevap> sehirilAra(@Field("il") String il);

}
