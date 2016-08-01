package com.example.owner.betterthanmal;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.ap1.libap1util.ApiCaller;
import io.ap1.libap1util.CallbackDefaultVolley;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeiyuu extends FragmentAbstract implements Constants{

    private EditText searchWaifu;
    private String currentSearch = "";
    private AdapterWaifu waifuAdapter;
    private ListView listview;
    private final String TAG = "FragmentSeiyuu";
    private ArrayList<WaifuDatabaseObject> waifus;
    private ArrayList<WaifuDatabaseObject> waifusSearched = new ArrayList<>();
    private View rootView;

    public FragmentSeiyuu() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_seiyuu, container, false);
        searchWaifu = (EditText)rootView.findViewById(R.id.seiyuu_search);
        listview = (ListView)rootView.findViewById(R.id.listView_seiyuu);
        searchWaifu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged: " + s.toString());
                waifusSearched.clear();
                for (WaifuDatabaseObject object : waifus) {
                    if (object.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                        waifusSearched.add(object);
                    }
                }
                Log.i(TAG, "Size: " + waifusSearched.size());
                reorder();
                //waifuAdapter.replaceWith(waifusSearched);
                //listview.setAdapter(waifuAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
                currentSearch = s.toString();
            }
        });
        searchWaifu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch: " + ((EditText)v).getText().toString());
                searchWaifu.setText(currentSearch);
                return false;
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WaifuDatabaseObject object = ((AdapterWaifu)parent.getAdapter()).getItem(position);
                Log.i(TAG, object.getName());
                FragmentWebview webview = new FragmentWebview();
                Bundle bundle = new Bundle();
                bundle.putString("search", object.getName());
                webview.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_main, webview)
                        .addToBackStack(fragmentTags[5]).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                //Open the webview here.
            }
        });
        getEverything();
        return rootView;
    }

    private void getEverything() {

        waifus = new ArrayList<>();
        waifuAdapter = new AdapterWaifu(getActivity());
        Map<String, String> parameters = new HashMap<>();
        parameters.put("query", "select * from voice_actress order by va_id asc");
        parameters.put("hash", hash);
        ApiCaller.getInstance(getActivity()).setAPI(ip, queryPath, null, parameters, Request.Method.POST).exec(new CallbackDefaultVolley() {
            @Override
            public void onDelivered(String result) {
                Log.i(TAG, result);
                formatWaifus(result);
            }

            @Override
            public void onException(String e) {
                Log.e(TAG, e);
            }
        });
    }

    private void formatWaifus(String result) {

        try {
            JSONArray array = new JSONArray(result);
            JSONObject object;

            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                this.waifus.add(new WaifuDatabaseObject("seiyuu").setEmployer(object.getString("va_employer")).setId(object.getString("va_id"))
                        .setName(object.getString("va_name")).setAge(object.getString("va_age")).setDirty(object.getString("va_dirty")));
            }
            reorder();

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void reorder() {
        if (waifusSearched.size() > 0) {
            Collections.sort(waifusSearched, ((ActivityUser)getActivity()).comparator);
            waifuAdapter.replaceWith(waifusSearched);
            listview.setAdapter(waifuAdapter);
        } else {
            Collections.sort(waifus, ((ActivityUser)getActivity()).comparator);
            waifuAdapter.replaceWith(waifus);
            listview.setAdapter(waifuAdapter);
        }
    }

    @Override
    public AdapterWaifu getAdapter() {
        return waifuAdapter;
    }
}
