package ru.yandex.dunaev.mick.simpleinternetradio;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import ru.yandex.dunaev.mick.simpleinternetradio.database.DatabaseHelper;
import ru.yandex.dunaev.mick.simpleinternetradio.database.PreferencesHelper;
import ru.yandex.dunaev.mick.simpleinternetradio.models.RadioBrowserHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesHelper.init(this);
        AndroidThreeTen.init(this);
        RadioBrowserHelper.init();

        DatabaseHelper.init(this);
        DatabaseHelper.sync();
    }
}
