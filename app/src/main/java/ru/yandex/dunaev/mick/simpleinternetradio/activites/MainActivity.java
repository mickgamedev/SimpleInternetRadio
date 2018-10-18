package ru.yandex.dunaev.mick.simpleinternetradio.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.yandex.dunaev.mick.simpleinternetradio.R;

public class MainActivity extends AppCompatActivity {

    //binding views
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setViews();


        ///test
 //       Observable<String> ob1 = Observable.fromArray("A", "B", "C", "D");
  //      Observable<String> ob2 = Observable.fromArray("E", "C", "B", "G", "J", "O");

//        Observable.combineLatest(ob2.toList().toObservable(), ob1, (list, value) -> list.contains(value) ? "" : value)
 //               .filter(value -> !TextUtils.isEmpty(value))
 //               .subscribeOn(Schedulers.computation())
 //               .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(value -> Log.d("foo", "Удалить из первого списка: " + value));

 //       Observable.combineLatest(ob1.toList().toObservable(), ob2, (list, value) -> list.contains(value) ? "" : value)
 //               .filter(value -> !TextUtils.isEmpty(value))
 //               .subscribeOn(Schedulers.computation())
 //               .observeOn(AndroidSchedulers.mainThread())
 //               .subscribe(value -> Log.d("foo", "Добавить в первый список: " + value));

 //       ob1.count().subscribe(v -> Log.v("TEST","Count " + v));

    }

    private void setViews(){
        setSupportActionBar(mToolbar);
    }
}
