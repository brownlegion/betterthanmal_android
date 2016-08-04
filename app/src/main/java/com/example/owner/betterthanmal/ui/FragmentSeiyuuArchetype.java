package com.example.owner.betterthanmal.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.owner.betterthanmal.AdapterWaifu;
import com.example.owner.betterthanmal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeiyuuArchetype extends FragmentAbstract {


    public FragmentSeiyuuArchetype() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seiyuu_archetype, container, false);
        return rootView;
    }

    @Override
    public void reorder() {

    }

    @Override
    public AdapterWaifu getAdapter() {
        return null;
    }
}
