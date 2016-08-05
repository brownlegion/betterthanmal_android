package com.example.owner.betterthanmal.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class FragmentSeiyuuTitle extends FragmentAbstract implements Constants{

    private ListView listview;
    private AdapterWaifu adapter;
    private ArrayList<WaifuDatabaseObject> list;
    private final String TAG = "FragmentSeiyuuTitle";

    public FragmentSeiyuuTitle() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seiyuu_title, container, false);
        listview = (ListView)rootView.findViewById(R.id.listView_seiyuu_title);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the waifu name using the seiyuu and the title. If nothing is returned, say that. If more than 1 is returned, display all.
                WaifuDatabaseObject object = list.get(position);
                Log.i(TAG, "Name: " + object.getName() + " Title: " + object.getTitle());
            }
        });
        getEverything();
        return rootView;
    }

    private void getEverything() {

        list = new ArrayList<>();
        adapter = new AdapterWaifu(getActivity());
        Map<String, String> parameters = new HashMap<>();
        parameters.put("query", "select distinct tv.title_va_id, title_name, va_name from title_voice_actress tv, voice_actress v, title t where v.va_id =" +
                " tv.va_fk_id and tv.title_fk_id = t.title_id order by title_va_id");
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
                this.list.add(new WaifuDatabaseObject("seiyuu-title").setTitle(object.getString("title_name")).setId(object.getString("title_va_id"))
                        .setName(object.getString("va_name")));
            }
            reorder();

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void reorder() {
        Collections.sort(list, ((ActivityUser)getActivity()).comparator);
        adapter.replaceWith(list);
        listview.setAdapter(adapter);
    }

    @Override
    public AdapterWaifu getAdapter() {
        return adapter;
    }
}
