package ru.yandex.dunaev.mick.simpleinternetradio.models;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRadioStation {
    @POST("json/stations")
    Flowable<List<RadioStation>> getStationList();
}
