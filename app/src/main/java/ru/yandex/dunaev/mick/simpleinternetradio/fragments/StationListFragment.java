package ru.yandex.dunaev.mick.simpleinternetradio.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.yandex.dunaev.mick.simpleinternetradio.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StationListFragment extends Fragment {


    public StationListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_station_list, container, false);
    }

}
