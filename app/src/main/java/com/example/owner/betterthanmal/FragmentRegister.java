package com.example.owner.betterthanmal;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.ap1.libap1util.CallbackDefaultVolley;

public class FragmentRegister extends Fragment implements View.OnClickListener, CallbackDefaultVolley, Constants{

    private final String TAG = "FragmentRegister";

    public FragmentRegister() {
        super();
        Log.i(TAG, "Created Register");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        ((ActivityStartup)getActivity()).getSupportActionBar().setTitle("YAHAALLOOOO REGISTER PLS");
        return rootView;
    }

    @Override
    public void onDelivered(String result) {

    }

    @Override
    public void onException(String e) {

    }

    @Override
    public void onClick(View v) {

    }
}
