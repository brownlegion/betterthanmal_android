package com.example.owner.betterthanmal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeiyuuTitle extends FragmentAbstract {


    public FragmentSeiyuuTitle() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seiyuu_title, container, false);
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
