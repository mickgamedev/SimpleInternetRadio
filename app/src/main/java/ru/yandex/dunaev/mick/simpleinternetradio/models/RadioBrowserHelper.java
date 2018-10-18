package ru.yandex.dunaev.mick.simpleinternetradio.models;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.yandex.dunaev.mick.simpleinternetradio.database.DatabaseHelper;

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

    private Flowable<RadioStation> getRadioStationsList(){
        return mRetrofit != null ?
                mRetrofit.create(IRadioStation.class)
                        .getStationList()
                        .doOnNext(v -> DatabaseHelper.updateMetaData(false,false,true,false)) //если новые данные пришли обновить состояние существующих
                        .flatMap(v -> Flowable.fromIterable(v))
                        .subscribeOn(Schedulers.io())
                         : null;

    }

    public static void init(){
        if(sHelper == null) sHelper = new RadioBrowserHelper();
    }
    public static Flowable<RadioStation> getRadioStations(){
        return sHelper != null ? sHelper.getRadioStationsList() : null;
    }
}
