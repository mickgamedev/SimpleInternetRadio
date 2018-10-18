package ru.yandex.dunaev.mick.simpleinternetradio.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.yandex.dunaev.mick.simpleinternetradio.models.RadioStation;

@Database(entities = {RadioStation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RadioStationDao getRadioStationDao();


}
