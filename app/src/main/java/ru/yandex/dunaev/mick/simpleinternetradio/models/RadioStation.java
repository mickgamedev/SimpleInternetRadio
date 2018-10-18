package ru.yandex.dunaev.mick.simpleinternetradio.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index(value = "stationuuid", unique = true)})
public class RadioStation {
    @PrimaryKey private int _id;
    @Ignore private String id;
    private String changeuuid;
    private String stationuuid;
    private String name;
    private String url;
    //private String homepage;
    private String favicon;
    private String tags;
    private String country;
    private String language;
    //private String votes;
    //private String negativevotes;
    //private String lastchangetime;
    private String ip;
    private String codec;
    private String bitrate;
    //private String hls;
    //private String lastcheckok;
    //private String lastchecktime;
    //private String lastcheckoktime;
    //private String clicktimestamp;
    //private String clickcount;
    //private String clicktrend;

    //for database
    private boolean _insert;
    private boolean _update;
    private boolean _delete;
    private boolean _sync;

    public boolean is_insert() {
        return _insert;
    }

    public void set_insert(boolean _insert) {
        this._insert = _insert;
    }

    public boolean is_update() {
        return _update;
    }

    public void set_update(boolean _update) {
        this._update = _update;
    }

    public boolean is_delete() {
        return _delete;
    }

    public void set_delete(boolean _delete) {
        this._delete = _delete;
    }

    public boolean is_sync() {
        return _sync;
    }

    public void set_sync(boolean _sync) {
        this._sync = _sync;
    }

    public int get_id() {
        return Integer.parseInt(id);
    }

    public void set_id(int _id) {
        this.id = String.valueOf(_id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChangeuuid() {
        return changeuuid;
    }

    public void setChangeuuid(String changeuuid) {
        this.changeuuid = changeuuid;
    }

    public void setStationuuid(String stationuuid) {
        this.stationuuid = stationuuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getId() {
        return id;
    }

    public String getStationuuid() {
        return stationuuid;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getFavicon() {
        return favicon;
    }

    public String getTags() {
        return tags;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getIp() {
        return ip;
    }

    public String getCodec() {
        return codec;
    }

    public String getBitrate() {
        return bitrate;
    }
}
