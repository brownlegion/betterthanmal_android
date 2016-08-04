package com.example.owner.betterthanmal.ui;

import android.app.Fragment;

import com.example.owner.betterthanmal.AdapterWaifu;

/**
 * Created by Krishna N. Deoram on 2016-07-28.
 * Gumi is love. Gumi is life.
 */
public abstract class FragmentAbstract extends Fragment {
    public abstract void reorder();
    public abstract AdapterWaifu getAdapter();
}
