package ru.yandex.dunaev.mick.simpleinternetradio.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Period;

public class DatabaseHelper {
    private static DatabaseHelper sHelper;

    private AppDatabase mDatabase;

    private String ID_LAST_DATE_SYNC = "LastDateSync";
    private static String TAG = "DatabaseHelper";

    private DatabaseHelper(Context context){
        //mDatabase = Room.databaseBuilder(context, AppDatabase.class, "sync-database").build();
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


        PreferencesHelper.putString(ID_LAST_DATE_SYNC, getCurrentDate());
    }

    public static void init(Context context){
        if(sHelper == null) sHelper = new DatabaseHelper(context);
    }

    public static void sync(){
        if(sHelper != null) sHelper.syncRadioStation();
    }
}
