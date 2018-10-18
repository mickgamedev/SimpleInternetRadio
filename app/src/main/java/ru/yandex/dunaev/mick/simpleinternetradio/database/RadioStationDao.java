package ru.yandex.dunaev.mick.simpleinternetradio.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Observable;
import ru.yandex.dunaev.mick.simpleinternetradio.models.RadioStation;

@Dao
public interface RadioStationDao {
    @Insert
    void insertStation(RadioStation station);

    @Query("SELECT * FROM RadioStation")
    List<RadioStation> getAllStation();

    @Query("SELECT COUNT(*) FROM RadioStation")
    long getStationCount();

    @Query("DELETE FROM RadioStation")
    void deleteAllStation();

    @Update
    void updateStation(RadioStation station);

    @Query("SELECT * FROM RadioStation WHERE stationuuid LIKE :uuid")
    List<RadioStation> getRadioStationFromUUID(String uuid);

    @Query("SELECT * FROM RadioStation WHERE _id LIKE :id")
    List<RadioStation> getRadioStationFromID(String id);

    @Delete
    void deleteStation(RadioStation station);

    @Query("UPDATE RadioStation SET _insert=:insert, _update=:update, _delete=:delete, _sync=:sync")
    void updateAllMetaData(boolean insert, boolean update, boolean delete, boolean sync);

    @Query("SELECT * FROM RadioStation WHERE _sync=:sync AND stationuuid=:uuid")
    List<RadioStation> getSyncRadioStation(boolean sync, String uuid);

    @Query("SELECT * FROM RadioStation WHERE _insert=1")
    List<RadioStation> getSyncInsertStation();

    @Query("SELECT * FROM RadioStation WHERE _delete=1")
    List<RadioStation> getSyncDeleteStation();
}
