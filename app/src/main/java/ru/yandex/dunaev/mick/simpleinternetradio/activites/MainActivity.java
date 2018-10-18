package ru.yandex.dunaev.mick.simpleinternetradio.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.dunaev.mick.simpleinternetradio.R;
import ru.yandex.dunaev.mick.simpleinternetradio.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    //binding views
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setViews();
    }

    private void setViews(){
        setSupportActionBar(mToolbar);
    }
}
