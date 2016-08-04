package com.example.owner.betterthanmal.ui;


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
import com.example.owner.betterthanmal.AdapterWaifu;
import com.example.owner.betterthanmal.Constants;
import com.example.owner.betterthanmal.R;
import com.example.owner.betterthanmal.WaifuDatabaseObject;

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
public class FragmentTitle extends FragmentAbstract implements Constants {

    private EditText searchWaifu;
    private String currentSearch = "";
    private AdapterWaifu waifuAdapter;
    private ListView listview;
    private final String TAG = "FragmentTitle";
    private ArrayList<WaifuDatabaseObject> waifus;
    private ArrayList<WaifuDatabaseObject> waifusSearched = new ArrayList<>();
    private View rootView;

    public FragmentTitle() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_title, container, false);
        searchWaifu = (EditText)rootView.findViewById(R.id.title_search);
        listview = (ListView)rootView.findViewById(R.id.listView_title);
        searchWaifu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChaned: " + s.toString());
                waifusSearched.clear();
                for (WaifuDatabaseObject object : waifus) {
                    if (object.getTitle().toLowerCase().contains(s.toString().toLowerCase())) {
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
                Log.i(TAG, object.getTitle());
                FragmentWebview webview = new FragmentWebview();
                Bundle bundle = new Bundle();
                bundle.putString("search", object.getTitle());
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
        parameters.put("query", "select title_id, title_name, title_released, title_animator, canon_type from title, canon where canon_fk_id = canon_id order by title_id");
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
                this.waifus.add(new WaifuDatabaseObject("title").setCanon(object.getString("canon_type")).setTitle(object.getString("title_name"))
                        .setId(object.getString("title_id")).setYearReleased(object.getString("title_released"))
                        .setAnimator(object.getString("title_animator")));
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
