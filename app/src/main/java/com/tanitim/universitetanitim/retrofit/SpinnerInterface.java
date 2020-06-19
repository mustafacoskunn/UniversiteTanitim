package com.tanitim.universitetanitim.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpinnerInterface {

    String JSONURL = "https://www.tohere.net/";

    @GET("yekimsdfdf/yedek/tum_universiteler.php")
    Call<String> getJSONString();
}
