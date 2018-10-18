package ru.yandex.dunaev.mick.simpleinternetradio.models;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RadioBrowserHelper {
    private static final String sBaseUrl = "http://www.radio-browser.info/webservice/";
    private static RadioBrowserHelper sHelper;

    private Retrofit mRetrofit;

    private RadioBrowserHelper(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static void init(){
        if(sHelper == null) sHelper = new RadioBrowserHelper();
    }
}
