package ru.yandex.dunaev.mick.simpleinternetradio.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Period;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.yandex.dunaev.mick.simpleinternetradio.models.RadioBrowserHelper;
import ru.yandex.dunaev.mick.simpleinternetradio.models.RadioStation;

public class DatabaseHelper {
    private static DatabaseHelper sHelper;

    private AppDatabase mDatabase;

    private String ID_LAST_DATE_SYNC = "LastDateSync";
    private static String TAG = "DatabaseHelper";

    private DatabaseHelper(Context context){
        mDatabase = Room.databaseBuilder(context, AppDatabase.class, "sync-database").build();
    };

    private String getCurrentDate(){
        return LocalDateTime.now().toString();
    }

    private boolean isNeedSyncRadioStation(){
        boolean isNeedSync = false;
        String lastDateSync = PreferencesHelper.getString(ID_LAST_DATE_SYNC);
        if(lastDateSync.isEmpty()) return true;
        String currentDate = getCurrentDate();

        LocalDateTime lastTime = LocalDateTime.parse(lastDateSync);
        LocalDateTime currTime = LocalDateTime.parse(currentDate);
        if(lastTime == null || currTime == null) return true;
        Period period = Period.between(lastTime.toLocalDate(), currTime.toLocalDate());

        int y = period.getYears();
        int m = period.getMonths();
        int d = period.getDays();

        if(y != 0 || m != 0 || d != 0) return true;

        return true;//!!! заменить на false после отладки синхронизации
    }

    private void syncRadioStation(){
        if(!isNeedSyncRadioStation()) return;
        Log.v(TAG,"Sync radio stations database");

        Flowable<RadioStation> stationObservable = RadioBrowserHelper.getRadioStations();
        stationObservable
                .subscribe(v -> processRadioStation(v),
                        err -> Log.v(TAG, "Error to subscrible " + err.getMessage()),
                        () -> compliteSync());


        PreferencesHelper.putString(ID_LAST_DATE_SYNC, getCurrentDate());
    }

    private void processRadioStation(RadioStation radioStation){
        List<RadioStation> list = mDatabase.getRadioStationDao().getSyncRadioStation(false,radioStation.getStationuuid());
        radioStation.set_sync(true);
        if(list == null || list.size() == 0) {
            radioStation.set_insert(true);
            mDatabase.getRadioStationDao().insertStation(radioStation);
        } else {
            radioStation.set_update(true);
            mDatabase.getRadioStationDao().updateStation(radioStation);
            if(list.size() > 1) Log.wtf(TAG,"list.size() > 1 ???");
        }
    }

    private void compliteSync(){
        Log.v(TAG,"Complite sync");

        Flowable.fromIterable(mDatabase.getRadioStationDao().getSyncDeleteStation())
                .subscribeOn(Schedulers.io())
                .subscribe(v -> mDatabase.getRadioStationDao().deleteStation(v),
                        err -> Log.v(TAG,"DeleteStation err " + err.getMessage()),
                        () -> Log.v(TAG,"Delete Stations complete"));

        Flowable.fromIterable(mDatabase.getRadioStationDao().getSyncInsertStation())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> Log.v(TAG, "Insert Station: " + v.getName()),
                        err -> Log.v(TAG,"InsertedStation err " + err.getMessage()),
                        () -> Log.v(TAG,"Inserted Stations complete"));

    }

    private void updateAllMetaData(boolean insert, boolean update, boolean delete, boolean sync) {
        mDatabase.getRadioStationDao().updateAllMetaData(insert,update,delete,sync);
    }

    private void deleteAllStation() {
        mDatabase.getRadioStationDao().deleteAllStation();
    }

    public static void init(Context context){
        if(sHelper == null) sHelper = new DatabaseHelper(context);
    }

    public static void sync(){
        if(sHelper != null) sHelper.syncRadioStation();
    }

    public static void deleteAll(){
        if(sHelper != null) sHelper.deleteAllStation();
    }

    public static void updateMetaData(boolean insert, boolean update, boolean delete, boolean sync){
        if(sHelper != null) sHelper.updateAllMetaData(insert, update, delete, sync);
    }


}
