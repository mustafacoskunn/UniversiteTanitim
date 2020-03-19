package com.tanitim.universitetanitim.retrofit;

public class ApiUtils {
    public static final String BASE_URL = "https://tohere.net/";

    public static UniversitelerDAOinterface getUniversilerDAOinterface() {
        return RetrofitClient.getClient(BASE_URL).create(UniversitelerDAOinterface.class);
    }
}
